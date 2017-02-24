package Environment;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import Graphics.*;

public class test {
	
	private static Window f;
	private static Panel pan;
	
	public static int windowSize = 700; // taille de la fenêtre en pixels (représente 10km)
	public static int simSize = 10000; // taille en metres de la simulation

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		InterestPoint A = new InterestPoint("A",3000,2000,0);
		InterestPoint B = new InterestPoint("B",2000,4000,0);
		InterestPoint C = new InterestPoint("C",7000,5000,0);
		InterestPoint D = new InterestPoint("D",2500,5000,0);
		EntryPoint E1 = new EntryPoint("E1",8000,9000,0);
		EntryPoint E2 = new EntryPoint("E2",9000,1000,0);
		EntryPoint E3 = new EntryPoint("E3",1000,7000,0);
		ArrayList<EntryPoint> pointsEntree = new ArrayList<EntryPoint>();
		ArrayList<InterestPoint> pointsTravail = new ArrayList<InterestPoint>();
		pointsTravail.add(A);
		pointsTravail.add(B);
		pointsTravail.add(C);
		pointsTravail.add(D);
		pointsEntree.add(E1);
		pointsEntree.add(E2);
		pointsEntree.add(E3);

		ArrayList<Point> points = new ArrayList<Point>();
		points.addAll(pointsEntree);
		points.addAll(pointsTravail);

		FootPath E3B = new FootPath(E3,B);
		FootPath BE3 = new FootPath(B,E3);
		FootPath E3D = new FootPath(E3,D);
		FootPath DE3 = new FootPath(D,E3);
		FootPath BD = new FootPath(B,D);
		FootPath DB = new FootPath(D,B);
		FootPath E1C = new FootPath(E1,C);
		FootPath CE1 = new FootPath(C,E1);
		FootPath CD = new FootPath(C,D);
		FootPath DC = new FootPath(D,C);
		FootPath BA = new FootPath(B,A);
		FootPath AB = new FootPath(A,B);
		FootPath E2A = new FootPath(E2,A);
		FootPath AE2 = new FootPath(A,E2);
		FootPath E2C = new FootPath(E2,C);
		FootPath CE2 = new FootPath(C,E2);
		FootPath AC = new FootPath(A,C);
		FootPath CA = new FootPath(C,A);
		
		ArrayList<Path> paths = new ArrayList<Path>();
		paths.add(E3B);
		paths.add(BE3);
		paths.add(E3D);
		paths.add(DE3);
		paths.add(BD);
		paths.add(DB);
		paths.add(E1C);
		paths.add(CE1);
		paths.add(CD);
		paths.add(DC);
		paths.add(BA);
		paths.add(AB);
		paths.add(E2A);
		paths.add(AE2);
		paths.add(E2C);
		paths.add(CE2);
		paths.add(AC);
		paths.add(CA);
		
		Environment env = new Environment(points, paths);
		env.display();
		
		double[][] weight = env.initializeWeight(points,paths);
		System.out.println("i \t\t j \t\t weight");
		for(int i=0; i<points.size(); i++){
			for(int j=0; j<points.size(); j++){
				System.out.println(i + " \t\t " + j + " \t\t " + weight[i][j]);
			}
		}
		
		env.findShortestPath(A, E2);
		for(Path p : env.shortestPath(A, E2)){
			System.out.println(p.getA().toString()+","+p.getB().toString());
		}
		
		/*for(Path p : env.shortestPath(A, E2)){
			System.out.println(p.getA().toString()+","+p.getB().toString());
		};*/
		
		// AFFICHAGE
		
		/*
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					try {
						f = new Window();
					} catch (IOException e) {
						e.printStackTrace();
					}

					//pan = new Panel(env);

					f.add(pan);
				}
			});
		}
		
		

		catch(Exception e){
			System.err.println("Erreur a la creation de l'interface Swing.");
			System.err.println(e);
		}
		*/
		
		

	}

}
