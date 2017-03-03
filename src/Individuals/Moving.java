package Individuals;
import java.io.IOException;

import Environment.*;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class Moving extends jade.core.behaviours.Behaviour {
	
	private Point destination;
	private int currentPathProgress;
	private int currentPathWeight;
	private Point nextPoint;
	
	public Moving(Point p){
		destination = p;
	}
	
	public void action() {
		switch (getMovingState()) {
		
		case point : // Dans le cas ou la personne est à une intersection
			// On trouve le prochain segment à emprunter (Djikstra impémenté dans Environnement)
			Path nextPath = ((Person) myAgent).env.shortestUserPath(getLocalisation(),destination).get(0);
			// On récupère le poids du segment, qui correspond au nombre de minutes 
			// nécessaires pour le parcourir
			currentPathWeight = (int) nextPath.weight();
			// PRINT :
			System.out.println(intro()+" choix de "+ nextPath.toString() + 
			", devrait prendre " + currentPathWeight + " minutes.");
			nextPoint = nextPath.getB();
			// On traite pour l'instant le cas où la capacité des routes n'a pas de limite
			// On s'engage donc sur le segment
			setCurrentPath(nextPath);
			setMovingState(MovingState.path);
			// On initialise le nombre de minutes passées sur le segment à 0
			currentPathProgress = 0;
			break;
			
		case path : // Dans le cas où l'on se trouve sur un segment
			// On attend les ticks de minutes
			ACLMessage m = ((Person) myAgent).receive(new MessageTemplate(new FilterClockTick()));
			if (m == null) {
				block();
			} else {
				// à chaque minute passée, on incrémente donc le compteur
				currentPathProgress++;
				// Quand suffisament de minutes se sont écoulées :
				if (currentPathProgress == currentPathWeight){
					// On termine le parcours du segment et on se place à l'intersection
					setCurrentPath(null);
					setMovingState(MovingState.point);
					setLocalisation(nextPoint);
					// PRINT :
					System.out.println(intro()+" arrivée à "+ nextPoint.getName());
					// Si on est arrivé à destination :
					if (nextPoint == destination){
						// on termine le trajet
						setMovingState(MovingState.none);
						if (noMoreAppointement()){
							// Si la personne n'a plus de rdv, elle est sortie du plateau
							setPersonState(PersonState.gone);
						} else {
							// Sinon, on relance son comportement d'attente du prochain rdv
							setPersonState(PersonState.in_place);
							((Person)(this.myAgent)).addBehaviour(new inPlace());
						}
						// PRINT :
						System.out.println(intro()+" fin du trajet.");
					}
				}
			}
			break;
			
		}	
	}
	
	public boolean done() {
		return (getPersonState()==PersonState.in_place);
	}
	
	public String intro() {
		return this.myAgent.getAID().getLocalName() + " :";
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
