package Environment;

public class Path {

	private Point A;
	private Point B;

	private PathType type;

	private int foot = 10;
	private int bike = 6;
	private int car = 1;


	public Path(PathType type, Point A, Point B){
		this.A = A;
		this.B = B;
		this.type = type;
	}

	public double length(){
		return Math.sqrt(
				Math.pow(B.getX()-A.getX(),2)
				+Math.pow(B.getY()-A.getY(),2)
				+Math.pow(B.getZ()-A.getZ(),2)); // récupère la distance "réelle" dans l'espace
	}

	public double planDist(){
		return Math.sqrt(
				Math.pow(B.getX()-A.getX(),2)
				+Math.pow(B.getY()-A.getY(),2)); // récupère la distance dans le plan (X,Y)
	}

	public double heightDiff(){
		return B.getZ()-A.getZ(); // récupère le dénivelé
	}

	public double slope(){
		return heightDiff()/planDist(); // récupère la pente
	}

	public double weight(){
		double w = 0;
		if(type != PathType.car){
			if(heightDiff()<=0){
				if(type == PathType.foot){
					w = length()*foot;
				}
				else if (type == PathType.bike){
					w = length()*bike;
				}
			}
			else{
				if(type == PathType.foot){
					w = length()*foot*Math.pow(slope(),2); // difficulté accrue en montée
				}
				else if (type == PathType.bike){
					w = length()*bike*Math.pow(slope(),3); // difficulté accrue en montée
				}
			}
		} else {
			w = length()*car;
		}
		return w;
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

	public PathType getType() {
		return type;
	}

	public void setType(PathType type) {
		this.type = type;
	}
}
