package Environment;

public class CarPath extends Path {
	
	// Représente un chemin emprunté par des vélos
	// La difficulté ne varie pas en fonction de la pente
	
	private static int factor = 1; // Les voitures sont la référence pour le poids, représentant la vitesse de parcours
	
	public CarPath(Point A, Point B) {
		super(A, B);
	}

	// calcule un poids pour le chemin
	public double weight() {
		return length()*factor;
	}

}
