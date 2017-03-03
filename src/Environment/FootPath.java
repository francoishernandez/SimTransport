package Environment;

public class FootPath extends Path {
	
	// Représente un chemin emprunté par des piétons
	// Nous avons effectué le choix d'autoriser le parcours en montée, mais en ajoutant une difficulté en fonction de la pente
		
	
	private static int speed = 5; // km/h sur du plat
	
	public FootPath(Point A, Point B) {
		super(A, B);
	}
	
	// calcule un poids pour le chemin
	public double weight() {
		if(heightDiff()<=0) {
			return (length()/1000)/((double)speed/60); // t = d / v
		} else { // cas en montée
			return (length()/1000)/((double)speed/60)*Math.pow(slope(), 2); // difficulté accrue en montée (hypothèse non linéaire)
		}
	}
	
}
