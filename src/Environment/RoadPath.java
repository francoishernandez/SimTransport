package Environment;

public class RoadPath extends Path {
	
	// Représente un chemin emprunté par des vélos
	// La difficulté ne varie pas en fonction de la pente
	
	private static int speed = 40; // km/h en moyenne sur route non embouteillée 

	public RoadPath(Point A, Point B) {
		super(A, B);
	}

	// calcule un poids pour le chemin
	public double weight() {
		return (length()/1000)/((double)speed/60); // t = d / v
	}

}
