package Environment;

public class FootPath extends Path {
	
	// Représente un chemin emprunté par des piétons
	// Nous avons effectué le choix d'autoriser le parcours en montée, mais en ajoutant une difficulté en fonction de la pente
		
	
	private static int factor = 10; // Un chemin piéton aura un poids 10 fois supérieur à un chemin pour voiture

	public FootPath(Point A, Point B) {
		super(A, B);
	}
	
	// calcule un poids pour le chemin
	public double weight() {
		if(heightDiff()<=0) {
			return length()*factor;
		} else { // cas en montée
			return length()*factor*Math.pow(slope(), 2); // difficulté accrue en montée (hypothèse non linéaire)
		}
	}
	
}
