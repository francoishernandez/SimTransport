package Environment;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import Graphics.*;

public class test {
	
	private static Window f;
	private static Panel pan;
	
	public static int windowSize = 500; // taille de la fenêtre en pixels (représente 10km)
	public static int simSize = 10000; // taille en metres de la simulation

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Point A = new EntryPoint("A",1000,1000,0);
		Point B = new EntryPoint("B",0,2000,0);
		Point C = new Point(3000,0,0);
		Point D = new InterestPoint("D",6000,5000,0);
		Point E = new EntryPoint("E",8000,9000,0);
		Point F = new Point(5000,3000,0);
		Point G = new InterestPoint("G",1000,7000,0);
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
		
		Environment env = new Environment(points, paths);
		env.display();
		
		double[][] weight = env.initializeWeight(points,paths);
		System.out.println("i \t\t j \t\t weight");
		for(int i=0; i<points.size(); i++){
			for(int j=0; j<points.size(); j++){
				System.out.println(i + " \t\t " + j + " \t\t " + weight[i][j]);
			}
		}
		
		env.findShortestPath(points, paths, A, E);
		
		// AFFICHAGE
		
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					try {
						f = new Window();
					} catch (IOException e) {
						e.printStackTrace();
					}

					pan = new Panel(env);

					f.add(pan);
				}
			});
		}
		
		

		catch(Exception e){
			System.err.println("Erreur a la creation de l'interface Swing.");
			System.err.println(e);
		}
		
		

	}

}
