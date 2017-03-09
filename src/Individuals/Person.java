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

public class Person extends jade.core.Agent {
	

	EntryPoint inPoint;
	// On supposera les rdv triés temporellement (faudra juste faire attention à la création)
	ArrayList<Appointement> schedule;
	
	Point localisation;
	Path currentPath;
	PersonState personState = PersonState.in_place;
	MovingState movingState = MovingState.none;
	TransportChoice transportChoice;
	Environment env;
	
	static int nbPeople = 0;
	
	public Person(EntryPoint in, ArrayList<Appointement> sched, Environment env, TransportChoice transportChoice) {
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
	
	public static Person rand_AllerRetour(ArrayList<EntryPoint> possibleIn, ArrayList<InterestPoint> possibleWork, Environment env, TransportChoice transportChoice){
		EntryPoint in = getRandomItem(possibleIn);
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
		System.out.println("Lancement de " + this.getAID().getLocalName() + " à " + inPoint.getName());
		System.out.println("Schedule :" + this.schedule.toString());
		
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
	
	
	
}
