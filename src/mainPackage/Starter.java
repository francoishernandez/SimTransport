package mainPackage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.SwingUtilities;

import Environment.*;
import Environment.Paths.Path;
import Environment.Points.InterestPoint;
import Environment.Points.Point;
import Environment.Points.PreEntryPoint;
import Graphics.Panel;
import Graphics.Window;
import Individuals.*;
import Util.Import;
import jade.core.AID;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentContainer;
import jade.wrapper.ContainerController;
import jade.wrapper.ControllerException;
import jade.wrapper.StaleProxyException;

public class Starter extends jade.core.Agent {

	public Starter() {}
	
	// Lancer dans Run Configurations, Main class : jade.Boot, Program arguments :
	// -local-host 127.0.0.1 -agents "starter:mainPackage.Starter"
	
	/////////////////////////////////// PARAMETRES ///////////////////////////////////
	
	public static int simulationTime = 500; // en s pour une journée complète
	public static int stepLength = 15; // en s (maximum 15s recommandé, sinon arrondis hasardeux)
	public static int startHour = 10; // heure de début de la simulation (2 recommandée pour une 
	// simulation sur plusieurs jours, sinon risque d'oublier des départs et donc de fausser les scores)
	
	// On fait tourner un certain nombre d'agents Person, mais ces agents peuvent 
	// représenter plusieurs personnes réelles pour alléger la simulation. 
	// Ceci intervient dans l'encombrement des routes.
	public static int nbPersons = 100;
	public static int realUsersPerPerson = 50;
	
	// Heures génération aléatoire des schedules :
	public static int centerBeginTime = 10; 
	public static double sigmaBeginTime = 0;
	public static int centerEndTime = 18; 
	public static double sigmaEndTime = 4;

	// Affichage
	public static boolean showSimulation = true;
	public static boolean showTraffic = false;
	public static int windowSize = 700; // taille de la fenêtre en pixels (représente 10km)
	public static int verbose = 1; 
	// -1 : rien
	// 0 : Bilan des journées
	// 1 : 0 + Lancement des personnes et départs/arrivées + imports
	// 2 : 1 + détail des trajets
	
	/////////////////////////////////// MODELE DE DECISION ///////////////////////////////////

	// Simulation sur plusieurs jours
	public static int totalDays = 2;
	
	private static double carFactorInit = 0.5;
	
	// carFactor est compris entre 0 et 1 et décrit la probabilité de choisir la voiture
	// en fonction des résultats de la journée précédente
	public static double carFactor(){
		if (currentDay==0) {
			return carFactorInit;
		} else {
			// Simple ratio du temps moyen (plus c'est rapide, plus c'est choisi)
			// Mais le modèle peut bien sûr être perfectionné
			return (double)(timeUsedPublicTransport/Math.max(nbPeoplePublicTransport, 1))/
					(double)((timeUsedCars/Math.max(nbPeopleCar, 1))+(timeUsedPublicTransport/Math.max(nbPeoplePublicTransport, 1)));
		}
	}
	
	// Ces valeurs correpondent aux résultats de la journée précédente
	public static int timeUsedCars = 0; //en s
	public static int timeUsedPublicTransport = 0; //en s
	private static int nbPeopleCar = 0;
	private static int nbPeoplePublicTransport = 0;
	
	//////////////////////////////////////////////////////////////////////////////////////////
	
	
	public static Window f;
	public static Panel pan;
	public static int simSize = 10000; // taille en metres de la simulation
	public static Environment env;
	
	private static int currentDay = 0;
	
	
	private ArrayList<Person> personsSauv;
	
	// On utilise un AID public pour l'envoi des messages de nouvelle journée
	public static AID aidStarter; 

	// Dans le setup du starter on crée et lance tous les agents désirés
	public void setup() {
		
		aidStarter = this.getAID();
		
		// IMPORT DES POINTS DEPUIS LES FICHIERS CSV
		
		Import im = null;
		try {
			im = new Import();
		} catch (IOException e) {
			System.out.println("FAIL");
		}
		
		ArrayList<Point> points = im.getAllPoints();
		ArrayList<Path> carPaths = im.getCarPaths();
		ArrayList<Path> userPaths = im.getUserPaths();
		
		ArrayList<PreEntryPoint> pointsPreEntree = im.getPointsPreEntree();
		ArrayList<InterestPoint> pointsInteret = im.getPointsInteret();
		
		// CREATION DE L'ENVIRONNEMENT A PARTIR DES OBJETS

		env = new Environment(points, carPaths, userPaths);

		// On utilisera les mêmes personnes sur les différentes journées
		personsSauv = new ArrayList<Person>();

		// Création des personnes (le choix de leur mode de transport et leur lancement sera
		// effectué au début de chaque jour )
		for (int i = 0; i < nbPersons; i++){
			Person newPerson = Person.rand_AllerRetour(pointsPreEntree, pointsInteret, env);
			personsSauv.add(newPerson);
		}
		
		// Lancer le comportement des journées
		this.addBehaviour(new Days());
		
		// Lancer le premier jour
		ACLMessage m = new ACLMessage(2); // 2 pour un nouveau jour
		m.addReceiver(this.getAID());
		this.send(m);	

	}
	
	
	
	
	
	public boolean noMoreDays(){
		return currentDay == totalDays+1;
	}
	
	public void incCurrentDay(){
		currentDay++;
	}

	public ArrayList<Person> getPersonsSauv() {
		return personsSauv;
	}
	
	public void incNbPeopleCar() {
		nbPeopleCar++;
	}

	public void incNbPeoplePublicTransport() {
		nbPeoplePublicTransport++;
	}
	
	public void reset() {
		nbPeopleCar = 0;
		nbPeoplePublicTransport = 0;
		timeUsedCars = 0;
		timeUsedPublicTransport = 0;
		int offset = (currentDay-1)*nbPersons;
		for (int i = 0; i < personsSauv.size(); i++) {
			try {
				this.getContainerController().getAgent("person" + (i + 1 + offset)).kill();
			} catch (StaleProxyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ControllerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			this.getContainerController().getAgent("clock"+currentDay).kill();
		} catch (StaleProxyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ControllerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public int getCurrentDay() {
		return currentDay;
	}
	
	public void printScores() {
		System.out.println("Nombre d'utilisateurs ayant choisi la voiture : " + nbPeopleCar);
		System.out.println("Nombre d'utilisateurs ayant choisi les transports en commun : " + nbPeoplePublicTransport);
		System.out.println("Temps total passé en voiture (en s) : " + timeUsedCars);
		System.out.println("Temps total passé en transports en commun (en s) : " + timeUsedPublicTransport);
		System.out.println("Temps moyen passé en voiture : " + timeUsedCars/Math.max(nbPeopleCar, 1));
		System.out.println("Temps moyen passé en transports en commun : " + timeUsedPublicTransport/Math.max(nbPeoplePublicTransport, 1));
	}
	
	
}