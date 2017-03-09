package Environment;

public class RoadPath extends Path {
	
	// Représente un chemin emprunté par des vélos
	// La difficulté ne varie pas en fonction de la pente
	
	private static int speed = 35; // km/h en moyenne sur route non embouteillée, avec des feux, des piétons...
	private static int criticalDensity = 100; // densité critique (en utilisateurs/km) à partir de laquelle la circulation se dégrade

	public RoadPath(Point A, Point B) {
		super(A, B);
	}

	// calcule un poids (en minutes) pour le chemin
	public double weight() {
		if(currentDensity()<=criticalDensity) { // cas non embouteillé
			return (length()/1000)/((double)speed/60); // t = d / v
		} else { // cas embouteillé
			// durée accrue en hypothèse non linéaire
			return ((length()/1000)/((double)speed/60))*Math.pow(((double)currentDensity()/criticalDensity), 3); 
			// Ce qui donne par exemple 2 fois plus long pour une densité de 125%; 3.5 fois plus long à 150%; 8 fois plus long à 200%
		}
	}

	public static int getCriticalDensity() {
		return criticalDensity;
	}
	
	

}
