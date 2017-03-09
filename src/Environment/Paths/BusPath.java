package Environment.Paths;

import Environment.Point;

public class BusPath extends Path {

	// Représente une ligne de bus
	
	private static int speed = 40; // km/h en moyenne sans compter les arrêts (ligne de bus => pas d'embouteillage)
	private static int stopTime = 20; // durée d'un arrêt en secondes
	private static int meanWaitingTime = 5; // durée moyenne d'attente en minutes
	// Pour un modèle plus réaliste on pourrait aussi calculer cette valeur 
	// en fonction de la ligne et de l'heure de la journée
	
	public BusPath(Point A, Point B, int ID) {
		super(A, B);
		this.lineID = ID;
	}
	
	// calcule un poids (en minutes) pour le chemin
	public double weight() {
			return (length()/1000)/((double)speed/60) + (double)stopTime/60; // t = (d / v) + durée d'arrêt
	}

	public int getMeanWaitingTime() {
		return meanWaitingTime;
	}
	
	
}
