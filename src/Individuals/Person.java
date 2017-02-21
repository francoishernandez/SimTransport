package Individuals;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import Environment.*;
import jade.core.AID;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class Person extends jade.core.Agent {
	

	EntryPoint inPoint;
	ArrayList<Appointement> schedule;
	AID ID;
	
	Point localisation;
	Path currentPath;
	PersonState state = PersonState.in_place;
	
	static int nbPeople = 0;
	
	public Person(EntryPoint in, ArrayList<Appointement> sched) {
		nbPeople++;
		ID = new AID("Person#"+nbPeople, false);
		inPoint = in;
		localisation = in;
		schedule = sched;
	}
	
	static Random rand = new Random();
	static <T> T getRandomItem(ArrayList<T> list) {
	    return list.get(rand.nextInt(list.size()));
	}
	
	public static Person rand_AllerRetour(ArrayList<EntryPoint> possibleIn, ArrayList<InterestPoint> possibleWork){
		EntryPoint in = getRandomItem(possibleIn);
		Point work = getRandomItem(possibleWork);
		Time beginTime = Time.randomBegin();
		Time endTime = Time.randomEnd(beginTime);
		Appointement beginApp = new Appointement(beginTime,work);
		Appointement endApp = new Appointement(endTime,in);
		ArrayList<Appointement> sched = new ArrayList<Appointement>();
		sched.add(beginApp);
		sched.add(endApp);
		return new Person(in, sched);
	}
	
	public void setup(){
		// On prévient du lancement de la personne
		System.out.println("Lancement de " + ID.getLocalName() + " à " + inPoint.getName());
		System.out.println("Schedule :" + this.schedule.toString());
		/*
		// On lui ajoute les comportements initiaux
		this.addBehaviour(new Trajet());
		// On créée une DFAgentDescription pour le bus
		DFAgentDescription dfd = new DFAgentDescription();
		// Contenant son AID,
		dfd.setName(aidID);
		// Et son Service "Bus"
		ServiceDescription bus = new ServiceDescription();
		bus.setName("Bus#"+ID);
		bus.setType("Bus");
		dfd.addServices(bus);
		// On ajoute finalement la description à DFService qui joue le rôle d'annuaire
		try {
			DFService.register(this, dfd);
		} catch (FIPAException e) {
			e.printStackTrace();
		}
		*/
	}
	
}
