package Environment;

public abstract class Path {

	private Point A;
	private Point B;
	// Le nombre d'utilisateurs actuellement sur le chemin
	private int currentUsers;
	


	public Path(Point A, Point B){
		this.A = A;
		this.B = B;
		currentUsers = 0;
	}

	// récupère la distance "réelle" dans l'espace en mètres
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
	
	// récupère la densité actuelle en utilisateurs par km
	public int currentDensity(){
		return (int) (currentUsers * 1000 / length());
	}
	
	// Modificateurs du compteur d'utilisateurs :
	public void usersIn(int in){
		currentUsers+=in;
	}
	public void usersOut(int out){
		currentUsers-=out;
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
