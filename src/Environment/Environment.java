package Environment;

import java.util.ArrayList;

// classe contenant le graphe représentant les différents points et chemins

public class Environment {
	
	private ArrayList<Point> points;
	private ArrayList<Path> paths;
	private ArrayList<Journey> journeys; // recense les plus court chemin entre deux points
	
	// retourne le chemin le plus court d'un point A à un point B
	public ArrayList<Point> shortestPath(Point A, Point B){
		ArrayList<Point> res = new ArrayList<Point>();
		res.add(A);
		return res;
	}
	
	public void addPoint(Point p){
		points.add(p);
	}
	
	public void addPath(Path p){
		paths.add(p);
	}
	
	public ArrayList<Point> getPoints(){
		return points;
	}
	
	public ArrayList<Path> getPaths(){
		return paths;
	}
}
