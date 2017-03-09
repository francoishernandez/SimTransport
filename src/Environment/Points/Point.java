package Environment.Points;


public class Point {

	private static int nbPoints = 0;
	
	private int x;
	private int y;
	private int z; //altitude
	private int id;
	private String name;
	
	public Point(String name, int x, int y, int z){
		this.name = name;
		this.x = x;
		this.y = y;
		this.z = z;
		this.id = nbPoints;
		nbPoints++;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}
	
	public String getXYZ(){
		return Integer.toString(x)+", "+Integer.toString(y)+", "+Integer.toString(z);
	}
	
	public int getID(){
		return id;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String toString(){
		return id+", "+getName();
	}
	
	
}
