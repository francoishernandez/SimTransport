package Individuals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import Environment.*;
import Environment.Paths.Path;
import jade.core.AID;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import mainPackage.Starter;

public class Person extends jade.core.Agent {
	

	PreEntryPoint inPoint;
	// On supposera les rdv triés temporellement (faudra juste faire attention à la création)
	ArrayList<Appointement> schedule;
	
	Point localisation;
	Path currentPath;
	PersonState personState = PersonState.in_place;
	MovingState movingState = MovingState.none;
	TransportChoice transportChoice;
	Environment env;
	// ligne de transport en commun actuelle, 0 quand pas dans un tranport en commun
	int currentLineID = 0;
	
	static int nbPeople = 0;
	
	public Person(PreEntryPoint in, ArrayList<Appointement> sched, Environment env, TransportChoice transportChoice) {
		nbPeople++;
		inPoint = in;
		localisation = in;
		schedule = sched;
		this.env = env;
		this.transportChoice = transportChoice;
	}
	
	static Random rand = new Random();
	static <T> T getRandomItem(ArrayList<T> list) {
	    return list.get(rand.nextInt(list.size()));
	}
	
	public static Person rand_AllerRetour(ArrayList<PreEntryPoint> possibleIn, ArrayList<InterestPoint> possibleWork, Environment env, TransportChoice transportChoice){
		PreEntryPoint in = getRandomItem(possibleIn);
		Point work = getRandomItem(possibleWork);
		Time beginTime = Time.randomBegin();
		Time endTime = Time.randomEnd(beginTime);
		Appointement beginApp = new Appointement(beginTime,work);
		Appointement endApp = new Appointement(endTime,in);
		ArrayList<Appointement> sched = new ArrayList<Appointement>();
		sched.add(beginApp);
		sched.add(endApp);
		return new Person(in, sched, env, transportChoice);
	}
	
	public void setup(){
		// On prévient du lancement de la personne
		if (Starter.verbose>=1){ System.out.println("Lancement de " + this.getAID().getLocalName() + " à " + inPoint.getName()); }
		if (Starter.verbose>=2){ System.out.println("Emploi du temps de " + this.getAID().getLocalName() + " : " + this.schedule.toString()); }
		
		// On lui ajoute les comportements initiaux
		this.addBehaviour(new InPlace());
		// On créée une DFAgentDescription pour la personne
		DFAgentDescription dfd = new DFAgentDescription();
		// Contenant son AID,
		dfd.setName(this.getAID());
		// Et son Service "Person"
		ServiceDescription service = new ServiceDescription();
		service.setName("Person");
		service.setType("Person");
		dfd.addServices(service);
		// On ajoute finalement la description à DFService qui joue le rôle d'annuaire
		try {
			DFService.register(this, dfd);
		} catch (FIPAException e) {
			e.printStackTrace();
		}
		
	}

	public Point getLocalisation() {
		return localisation;
	}

	public void setLocalisation(Point localisation) {
		this.localisation = localisation;
	}

	public MovingState getMovingState() {
		return movingState;
	}

	public void setMovingState(MovingState movingState) {
		this.movingState = movingState;
	}

	public Path getCurrentPath() {
		return currentPath;
	}

	public void setCurrentPath(Path currentPath) {
		this.currentPath = currentPath;
	}

	public PersonState getPersonState() {
		return personState;
	}

	public void setPersonState(PersonState state) {
		this.personState = state;
	}

	public ArrayList<Appointement> getSchedule() {
		return schedule;
	}

	public Environment getEnv() {
		return env;
	}

	public TransportChoice getTransportChoice() {
		return transportChoice;
	}

	public int getCurrentLineID() {
		return currentLineID;
	}

	public void setCurrentLineID(int currentLineID) {
		this.currentLineID = currentLineID;
	}
	
	
	
	
	
}
