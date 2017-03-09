package Individuals;

import Environment.*;
import Environment.Points.Point;

public class Appointement {

	// Un rendez vous est constitué d'une heure de départ et d'une destination
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
