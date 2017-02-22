package Individuals;

import Environment.*;

public class Appointement {

	Time departureTime;
	Point destination;
	
	public Appointement(Time departure, Point dest){
		departureTime = departure;
		destination = dest;
	}
	
	public boolean timeToGo(Time currentTime){
		return departureTime.equals(currentTime);
	}
	
	public String toString(){
		return "( Départ à " + departureTime.toString() + " à destination de " + destination.getName() + " )";
	}

	public Time getDepartureTime() {
		return departureTime;
	}

	public Point getDestination() {
		return destination;
	}
	
}
