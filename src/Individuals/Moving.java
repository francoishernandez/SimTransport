package Individuals;
import java.io.IOException;

import Environment.*;
import Environment.Paths.Path;
import Environment.Points.Point;
import Environment.Points.PreEntryPoint;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import mainPackage.Starter;

public class Moving extends jade.core.behaviours.Behaviour {
	
	private Point destination;
	private int currentPathProgress;
	private int timeNeeded;
	private Point nextPoint;
	
	public Moving(Point p){
		destination = p;
	}
	
	public void action() {
		switch (getMovingState()) {
		
		case point : // Dans le cas ou la personne est à une intersection
			// On trouve le prochain segment à emprunter (Djikstra impémenté dans Environnement) suivant le choix initial de transport
			Path nextPath;
			switch(getTransportChoice()) {
			case car :
				nextPath = ((Person) myAgent).env.shortestCarPath(getLocalisation(),destination).get(0);
				break;
			case publicTransport :
				nextPath = ((Person) myAgent).env.shortestPublicTransportPath(getLocalisation(),destination).get(0);
				break;
			default : // cette ligne n'est jamais atteinte, mais sinon eclipse râle
				nextPath = ((Person) myAgent).env.shortestCarPath(getLocalisation(),destination).get(0);
			}
			// On récupère le poids du segment, qui correspond au nombre de secondes 
			// nécessaires pour le parcourir
			int currentPathWeight =  Math.max((int)(60*nextPath.weight()), 1);
			// PRINT :
			if (Starter.verbose>=2){ if (getLocalisation() instanceof PreEntryPoint) {
				System.out.println(intro()+" Départ de la pré-entrée " + getLocalisation().getName() + 
				", entrée immédiate à " + nextPath.getB().getName() + ".");
				} else {
				System.out.println(intro()+" Choix de "+ nextPath.toString() + ", devrait prendre " + 
				currentPathWeight/60 + " minutes et " + currentPathWeight%60  +" secondes."); }
			}
			// Pour le transport en commun, on ajoute le temps d'attente moyen en cas de nouvelle ligne
			if (nextPath.getLineID() != getCurrentLineID()){
				currentPathWeight += nextPath.getMeanWaitingTime()*60;
				// et on met à jour la nouvelle ligne
				setCurrentLineID(nextPath.getLineID());
				// PRINT :
				if (Starter.verbose>=2){
					if (getCurrentLineID()!=0){ System.out.println(intro()+" Prend la ligne " + getCurrentLineID() + 
							" et attend ainsi " + nextPath.getMeanWaitingTime() + " minutes.");	}
				}
			}
			// Transformation pour arrondir à la step de temps supérieure
			timeNeeded = (int) ( Starter.stepLength * Math.ceil((double)currentPathWeight/(double)Starter.stepLength) );
			// On met à jour le compteur de temps passé général correspondant
			switch(getTransportChoice()) {
			case car :
				Starter.timeUsedCars+=timeNeeded;
				break;
			case publicTransport :
				Starter.timeUsedPublicTransport+=timeNeeded;
				break;
			}
			nextPoint = nextPath.getB();
			// On s'engage sur le segment
			setCurrentPath(nextPath);
			setMovingState(MovingState.path);
			// On met à jour le compteur d'utilisateurs du segment
			userIn(nextPath);
			// On initialise le nombre de secondes passées sur le segment à 0
			currentPathProgress = 0;
			break;
			
		case path : // Dans le cas où l'on se trouve sur un segment
			// On attend les ticks de minutes
			ACLMessage m = myAgent.receive(new MessageTemplate(new FilterClockTick()));
			if (m == null) {
				block();
			} else {
				// à chaque stepLength passée, on incrémente donc le compteur
				currentPathProgress+=Starter.stepLength;
				// Quand suffisament de minutes se sont écoulées :
				if (currentPathProgress == timeNeeded){
					// On sort du chemin, donc on met à jour le compteur d'utilisateurs du segment
					userOut(getCurrentPath());
					// On termine le parcours du segment et on se place à l'intersection
					setCurrentPath(null);
					setMovingState(MovingState.point);
					setLocalisation(nextPoint);
					// PRINT :
					if (Starter.verbose>=2){ System.out.println(intro()+" Arrivée à "+ nextPoint.getName()); }
					// Si on est arrivé à destination :
					if (nextPoint == destination){
						// on termine le trajet
						setMovingState(MovingState.none);
						// on fait sortir la personne de tout transport en commun dans laquelle elle serait
						setCurrentLineID(0);
						if (noMoreAppointement()){
							// Si la personne n'a plus de rdv, elle est sortie du plateau
							setPersonState(PersonState.gone);
						} else {
							// Sinon, on relance son comportement d'attente du prochain rdv
							setPersonState(PersonState.in_place);
							((Person)(this.myAgent)).addBehaviour(new InPlace());
						}
						// PRINT :
						if (Starter.verbose>=1){ System.out.println(intro()+" Arrivée à " + destination.getName() +", fin du trajet."); }
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
	
	public Path getCurrentPath(){
		return ((Person)(this.myAgent)).getCurrentPath();
	}
	
	public int getCurrentLineID(){
		return ((Person)(this.myAgent)).getCurrentLineID();
	}
	
	public TransportChoice getTransportChoice(){
		return ((Person)(this.myAgent)).getTransportChoice();
	}
	
	public void setCurrentPath(Path p){
		((Person)(this.myAgent)).setCurrentPath(p);
	}
	
	public void setPersonState(PersonState p){
		((Person)(this.myAgent)).setPersonState(p);
	}
	
	public void setCurrentLineID(int ID){
		((Person)(this.myAgent)).setCurrentLineID(ID);
	}
	
	public boolean noMoreAppointement(){
		return ((Person)(this.myAgent)).getSchedule().isEmpty();
	}
	
	public void userIn(Path p){
		p.usersIn(Starter.realUsersPerPerson);
	}
	public void userOut(Path p){
		p.usersOut(Starter.realUsersPerPerson);
	}
	
	
}
