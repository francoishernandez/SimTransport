package Environment.Paths;

import Environment.Points.Point;

public class EntryPath extends Path {

	// Les EntryPath permettent de distribuer les personnes des points
	// de pré-entrée vers les points d'entrée.
	
	public EntryPath(Point A, Point B) {
		super(A, B);
	}
	
	public double weight() {
		// Le poids est nul car la distribution est instantannée
		return 0;
	}
	
}
