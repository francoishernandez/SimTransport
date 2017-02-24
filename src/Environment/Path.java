package Environment;

public abstract class Path {

	private Point A;
	private Point B;


	public Path(Point A, Point B){
		this.A = A;
		this.B = B;
	}

	// récupère la distance "réelle" dans l'espace
	public double length(){
		return Math.sqrt(
				Math.pow(B.getX()-A.getX(),2)
				+Math.pow(B.getY()-A.getY(),2)
				+Math.pow(B.getZ()-A.getZ(),2)); 
	}

	// récupère la distance dans le plan (X,Y)
	public double planDist(){
		return Math.sqrt(
				Math.pow(B.getX()-A.getX(),2)
				+Math.pow(B.getY()-A.getY(),2)); 
	}

	// récupère le dénivelé
	public double heightDiff(){
		return B.getZ()-A.getZ(); 
	}
	
	public abstract double weight();

	// récupère la pente
	public double slope(){
		return heightDiff()/planDist(); 
	}
	
	public Point getA() {
		return A;
	}

	public void setA(Point a) {
		A = a;
	}

	public Point getB() {
		return B;
	}

	public void setB(Point b) {
		B = b;
	}

	public String toString(){
		return "["+A.getName()+","+B.getName()+"]";
	}
	
}
