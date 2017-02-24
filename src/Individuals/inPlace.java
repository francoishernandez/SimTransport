package Individuals;

import Environment.*;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class inPlace extends Behaviour {

	public void action() {
		// on regarde les messages de l'horloge
		ACLMessage m = ((Person) myAgent).receive(new MessageTemplate(new FilterClockTick()));
		if (m == null) {
			block();
		} else {
			// Quand on reçoit le message d'horloge, on en extrait l'heure
			Time currentTime = new Time((byte)0,(byte)0);
			currentTime.stringToTime(m.getContent());
			// On récupère le prochain rdv
			Appointement nextAppointement = nextAppointement();
			// Et on regarde si c'est l'heure de partir
			if (nextAppointement().timeToGo(currentTime)) {
				// Si oui, on lance le mouvement
				setPersonState(PersonState.moving);
				Point destination = nextAppointement.getDestination();
				((Person)(this.myAgent)).addBehaviour(new Moving(destination));
				// et on supprime le rdv de l'agenda (rdv restants)
				cleanAppointement();
				// PRINT :
				System.out.println(intro()+" départ de "+
				((Person)(this.myAgent)).localisation.getName()+ " vers " +
				destination.getName());
			}	
		}
	}
	
	public boolean done() {
		return (getPersonState()!=PersonState.in_place);
	}
	
	public String intro() {
		return this.myAgent.getAID().getLocalName() + " :";
	}
	
	Appointement nextAppointement() {
		return ((Person) myAgent).getSchedule().get(0);
	}
	
	public void cleanAppointement() {
		((Person) myAgent).getSchedule().remove(0);
	}
	
	public PersonState getPersonState(){
		return ((Person)(this.myAgent)).getPersonState();
	}
	
	public void setPersonState(PersonState p){
		((Person)(this.myAgent)).setPersonState(p);
	}
	
}
