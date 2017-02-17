package Environment;

public class Path {
	
	private Point A;
	private Point B;
	
	private PathType type;
	
	
	public Path(PathType type, Point A, Point B){
		this.A = A;
		this.B = B;
		this.type = type;
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
