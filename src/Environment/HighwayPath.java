package Environment;

public class HighwayPath extends Path {
	
	// Représente un chemin emprunté par des vélos
	// La difficulté ne varie pas en fonction de la pente
	
	private static int speed = 90; // km/h en moyenne sur route non embouteillée 
	private static int criticalDensity = 200; // nombre maximal de voyageurs sur 1km pour rouler à vitesse normale

	public HighwayPath(Point A, Point B) {
		super(A, B);
	}

	// calcule un poids (en minutes) pour le chemin
	public double weight() {
		if(currentDensity()<=criticalDensity) { // cas non embouteillé
			return (length()/1000)/((double)speed/60); // t = d / v
		} else { // cas embouteillé
			// durée accrue en hypothèse non linéaire
			return ((length()/1000)/((double)speed/60))*Math.pow((currentDensity()/criticalDensity), 3); 
			// Ce qui donne par exemple 2 fois plus long pour une densité de 125%; 3.5 fois plus long à 150%; 8 fois plus long à 200%
		}
	}

}
