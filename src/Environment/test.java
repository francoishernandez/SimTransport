package Environment;

import java.util.ArrayList;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Point A = new Point(0,0,0);
		Point B = new Point(0,20,0);
		Point C = new Point(30,0,0);
		Point D = new Point(60,50,0);
		Point E = new Point(80,120,0);
		Point F = new Point(50,30,0);
		Point G = new Point(10,70,0);
		ArrayList<Point> points = new ArrayList<Point>();
		points.add(A);
		points.add(B);
		points.add(C);
		points.add(D);
		points.add(E);
		points.add(F);
		points.add(G);
		
		
		FootPath ab = new FootPath(A,B);
		FootPath ac = new FootPath(A,C);
		FootPath cf = new FootPath(C,F);
		FootPath bf = new FootPath(B,F);
		FootPath fd = new FootPath(F,D);
		FootPath bg = new FootPath(B,G);
		FootPath fg = new FootPath(F,G);
		FootPath dg = new FootPath(D,G);
		FootPath de = new FootPath(D,E);
		FootPath ge = new FootPath(G,E);
		ArrayList<Path> paths = new ArrayList<Path>();
		paths.add(ab);
		paths.add(ac);
		paths.add(cf);
		paths.add(bf);
		paths.add(fd);
		paths.add(bg);
		paths.add(fg);
		paths.add(dg);
		paths.add(de);
		paths.add(ge);
		
		Environment e = new Environment(points, paths);
		e.display();
		
		double[][] weight = e.initializeWeight(points,paths);
		System.out.println("i \t\t j \t\t weight");
		for(int i=0; i<points.size(); i++){
			for(int j=0; j<points.size(); j++){
				System.out.println(i + " \t\t " + j + " \t\t " + weight[i][j]);
			}
		}
		
		e.findShortestPath(points, paths, A, E);

	}

}
