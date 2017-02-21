package Individuals;
import java.util.ArrayList;

import Environment.*;
import jade.core.AID;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class Person extends jade.core.Agent {

	EntryPoint inPoint;
	ArrayList<Appointement> Schedule;
	AID ID;
	
	Point localisation;
	Path currentPath;
	PersonState state = PersonState.in_place;
	
	static int nbPeople = 0;
	
	public Person() {
		nbPeople++;
		ID = new AID("Person#"+nbPeople, false);
		localisation = inPoint;
	}
	
	public void setup(){
		// On prévient du lancement de la personne
		System.out.println("Lancement de " + ID.getLocalName());
		
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
