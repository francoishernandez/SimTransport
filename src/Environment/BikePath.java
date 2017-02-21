package Environment;

public class BikePath extends Path {
	
	private static int factor = 6;

	public BikePath(Point A, Point B) {
		super(A, B);
		// TODO Auto-generated constructor stub
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
