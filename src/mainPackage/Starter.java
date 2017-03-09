package mainPackage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.SwingUtilities;

import Environment.*;
import Environment.Paths.Path;
import Graphics.Panel;
import Graphics.Window;
import Individuals.*;
import Util.Import;
import jade.wrapper.StaleProxyException;

public class Starter extends jade.core.Agent {

	public Starter() {
	}
	
	// Lancer dans Run Configurations, Main class : jade.Boot, Program arguments : -local-host 127.0.0.1 -agents "starter:mainPackage.Starter"
	
	/////////////////////////////////// PARAMETRES ///////////////////////////////////
	

	public static int simulationTime = 500; // en s pour une journée complète
	public static int stepLength = 15; // en s (maximum 15s recommandé, sinon arrondis violents)
	public static int startHour = 10; // heure de début de la simulation
	
	// On fait tourner un certain nombre d'agents Person, mais ces agents peuvent 
	// représenter plusieurs personnes réelles pour alléger la simulation. 
	// Ceci intervient dans l'encombrement des routes.
	public static int nbPersons = 100;
	public static int realUsersPerPerson = 20;
	
	// Heures génération aléatoire des schedules :
	public static int centerBeginTime = 10; 
	public static double sigmaBeginTime = 4;
	public static int centerEndTime = 18; 
	public static double sigmaEndTime = 4;

	// Affichage
	public static boolean showSimulation = true;
	public static int windowSize = 700; // taille de la fenêtre en pixels (représente 10km)
	public static int verbose = 1; 
	// 0 : rien
	// 1 : Lancement des personnes et départs/arrivées
	// 2 : 1 + détail des trajets

	
	/////////////////////////////////// FIN PARAMETRES ///////////////////////////////////

	
	private static Window f;
	private static Panel pan;
	public static int simSize = 10000; // taille en metres de la simulation

	// Dans le setup du starter on crée et lance tous les agents désirés
	public void setup() {
		
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

		Environment env = new Environment(points, carPaths, userPaths);

		ArrayList<Person> persons = new ArrayList<Person>();

		
		for (int i = 0; i < nbPersons; i++){
			Person newPerson = Person.rand_AllerRetour(pointsPreEntree, pointsInteret, env, TransportChoice.car);
			try {
				this.getContainerController().acceptNewAgent("person" + (i + 1), newPerson).start();
				persons.add(newPerson);
			} catch (StaleProxyException e) {
				e.printStackTrace();
			}
		}

		Clock c = new Clock(simulationTime, stepLength, startHour);
		try {
			this.getContainerController().acceptNewAgent("clock", c).start();
		} catch (StaleProxyException e) {
			e.printStackTrace();
		}

		// AFFICHAGE

		if (showSimulation) {
		
			try {
				SwingUtilities.invokeAndWait(new Runnable() {
					public void run() {
						try {
							f = new Window();
						} catch (IOException e) {
							e.printStackTrace();
						}
	
						pan = new Panel(env, persons, c, windowSize, simSize);
	
						f.add(pan);
					}
				});
			} catch (Exception e) {
				System.err.println("Erreur a la creation de l'interface Swing.");
				System.err.println(e);
			}
			
		}
		
		
		
		

	}
	

}