package Individuals;

import Environment.*;

public class Appointement {

	Time departureTime;
	Point destination;
	
	public Appointement(Time departure, Point dest){
		departureTime = departure;
		destination = dest;
	}
	
	public String toString(){
		return "( Départ à " + departureTime.toString() + " à destination de " + destination.getName() + " )";
	}
	
}
