package Environment;

public class CarPath extends Path {
	
	private static int factor = 1;
	
	public CarPath(Point A, Point B) {
		super(A, B);
		// TODO Auto-generated constructor stub
	}

	// calcule un poids pour le chemin
	public double weight() {
		return length()*factor;
	}

}
