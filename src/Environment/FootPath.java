package Environment;

public class FootPath extends Path {
	
	private static int factor = 10;

	public FootPath(Point A, Point B) {
		super(A, B);
		// TODO Auto-generated constructor stub
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
