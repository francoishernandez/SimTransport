package Environment.Paths;

import Environment.Points.Point;

public class FootPath extends Path {
	
	// Représente un chemin emprunté par des piétons
	// Nous avons effectué le choix d'autoriser le parcours en montée, mais en ajoutant une difficulté en fonction de la pente
		
	
	private static int speed = 5; // km/h sur du plat
	
	public FootPath(Point A, Point B) {
		super(A, B);
	}
	
	// calcule un poids (en minutes) pour le chemin
	public double weight() {
		if(heightDiff()<=0) {
			return (length()/1000)/((double)speed/60); // t = d / v
		} else { // cas en montée
			// difficulté accrue en hypothèse non linéaire
			return ((length()/1000)/((double)speed/60))*(1+Math.pow(slope()*4, 2)); 
			// Ce qui donne par exemple 2 fois plus dur à 25% et 5 fois plus dur à 50%
		}
	}
	
}
