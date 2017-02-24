package Individuals;
import java.io.IOException;

import Environment.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class Moving extends jade.core.behaviours.Behaviour {
	
	private Point destination;
	private int currentPathProgress;
	private int currentPathWeight;
	
	public Moving(Point p){
		destination = p;
		setMovingState(MovingState.point);
	}
	
	public void action() {
		switch (getMovingState()) {
		
		case point :
			// On trouve le prochain chemin à emprunter (Djikstra impémenté dans Environnement)
			Path nextPath = findNextPath();
			Point nextPoint = nextPath.getB();
			// On traite pour l'instant le cas où la capacité des routes n'a pas de limite
			// On s'engage donc sur la route
			setCurrentPath(nextPath);
			currentPathProgress = 0;
			currentPathWeight = (int) nextPath.weight();
			setMovingState(MovingState.path);
			break;
			
		case path :
			ACLMessage m = ((Person) myAgent).receive(new MessageTemplate(new FilterClockTick()));
			if (m == null) {
				block();
			} else {
				currentPathProgress++;
				if (currentPathProgress == currentPathWeight){
					setCurrentPath(null);
					setLocalisation(nextPoint);
					if (nextPoint == destination){
						setMovingState(MovingState.none);
						if (noMoreAppointement()){
							setPersonState(PersonState.gone);
						} else {
							setPersonState(PersonState.in_place);
							((Person)(this.myAgent)).addBehaviour(new inPlace());
						}
					}
				}
			}
			break;
			
		}	
	}
	
	public boolean done() {
		return (getPersonState()==PersonState.in_place);
	}
	
	public MovingState getMovingState(){
		return ((Person)(this.myAgent)).getMovingState();
	}

	public void setMovingState(MovingState s){
		((Person)(this.myAgent)).setMovingState(s);
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
