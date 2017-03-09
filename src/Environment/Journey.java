package Environment;

import java.util.ArrayList;

import Environment.Paths.Path;

// Décrit le trajet d'un point A à un point B

public class Journey {

	private Point A;
	private Point B;
	private ArrayList<Path> paths;
	
	public Journey(Point A, Point B){
		this.A = A;
		this.B = B;
	}
	
	public void setA(Point p){
		this.A = p;
	}
	
	public void setB(Point p){
		this.B = p;
	}
	
	public Point getA(){
		return A;
	}
	
	public Point getB(){
		return B;
	}
	
	public void addPath(Path p){
		this.paths.add(p);
	}
	
	public ArrayList<Path> getPaths(){
		return paths;
	}
	
}
