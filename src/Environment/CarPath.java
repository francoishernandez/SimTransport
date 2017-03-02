package Environment;

public class CarPath extends Path {
	
	// Représente un chemin emprunté par des vélos
	// La difficulté ne varie pas en fonction de la pente
	
	private static int speed = 50; // km/h sur route non embouteillée 

	public CarPath(Point A, Point B) {
		super(A, B);
	}

	// calcule un poids pour le chemin
	public double weight() {
		return (length()/1000)/(speed/60); // t = d / v
	}

}
