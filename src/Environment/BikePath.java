package Environment;

public class BikePath extends Path {
	
	// Représente un chemin emprunté par des vélos
	// Nous avons effectué le choix d'autoriser le parcours en montée, mais en ajoutant une difficulté en fonction de la pente
	
	private static int factor = 6; // Un chemin cycliste aura un poids 6 fois supérieur à un chemin pour voiture

	public BikePath(Point A, Point B) {
		super(A, B);
	}

	// calcule un poids pour le chemin
		public double weight() {
			if(heightDiff()<=0) {
				return length()*factor;
			} else { // cas en montée
				return length()*factor*Math.pow(slope(), 3); // difficulté accrue en montée (hypothèse non linéaire)
			}
		}
	
}
