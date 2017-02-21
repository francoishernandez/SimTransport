package Environment;


public class Point {

	private static int nbPoints = 0;
	
	private int x;
	private int y;
	private int z; //altitude
	private int id;
	
	public Point(int x, int y, int z){
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
	
	
	
}
