package Individuals;
import java.io.IOException;

import Environment.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class Moving extends jade.core.behaviours.Behaviour {
	
	private Point destination;
	
	public Moving(Point p){
		destination = p;
	}
	
	public void action() {
		// On trouve le prochain chemin à emprunter (Djikstra impémenté dans Environnement)
		Path nextPath = findNextPath();
		Point nextPoint = nextPath.getB();
		// On traite pour l'instant le cas où la capacité des routes n'a pas de limite
		// On s'engage donc sur la route
		setCurrentPath(nextPath);
		ACLMessage m = ((Person) myAgent).receive(new MessageTemplate(new finTrajet()));
		if (m == null) {
			block();
		} else {
			setCurrentPath(null);
			setLocalisation(nextPoint);	
			if (nextPoint == destination){
				if (noMoreAppointement()){
					setPersonState(PersonState.gone);
				} else {
					setPersonState(PersonState.in_place);
					((Person)(this.myAgent)).addBehaviour(new inPlace());
				}
			}
		}
			
	}
	
	public boolean done() {
		return (getPersonState()==PersonState.in_place);
	}
	
	public PersonState getPersonState(){
		return ((Person)(this.myAgent)).getPersonState();
	}
	
	public Point getLocalisation(){
		return ((Person)(this.myAgent)).getLocalisation();
	}
	
	public void setLocalisation(Point p){
		((Person)(this.myAgent)).setLocalisation(p);
	}
	
	public void setCurrentPath(Path p){
		((Person)(this.myAgent)).setCurrentPath(p);
	}
	
	public void setPersonState(PersonState p){
		((Person)(this.myAgent)).setPersonState(p);
	}
	
	public boolean noMoreAppointement(){
		return ((Person)(this.myAgent)).getSchedule().isEmpty();
	}
	
}
