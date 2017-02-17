package Environment;

import java.util.ArrayList;

// classe contenant le graphe représentant les différents points et chemins

public class Environment {
	
	private ArrayList<Point> points;
	private ArrayList<Path> paths;
	
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
