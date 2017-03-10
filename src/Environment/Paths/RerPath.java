package Environment.Paths;

import Environment.Points.Point;

public class RerPath extends Path {

	// Représente une ligne de Rer
	
	private static int speed = 80; // km/h en moyenne sans compter les arrêts (ligne de bus => pas d'embouteillage)
	private static int stopTime = 15; // durée d'un arrêt en secondes
	private static int meanWaitingTime = 3; // durée moyenne d'attente en minutes
	// Pour un modèle plus réaliste on pourrait aussi calculer cette valeur 
	// en fonction de la ligne et de l'heure de la journée
	
	public RerPath(Point A, Point B, int ID) {
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
