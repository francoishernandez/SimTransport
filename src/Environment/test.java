package Environment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import Util.Import;

import javax.swing.SwingUtilities;

import Environment.Paths.Path;
import Graphics.*;
import Individuals.Person;


public class test {

	private static Window f;
	private static Panel pan;
	private static int stepLength = 15; //en s
	private static int realUsersPerPerson = 10;

	public static int windowSize = 700; // taille de la fenêtre en pixels (représente 10km)
	public static int simSize = 10000; // taille en metres de la simulation

	public static void main(String[] args) {
		
		Import im = null;
		try {
			im = new Import();
		} catch (IOException e) {
			System.out.println("FAIL");
		}
		
		/*
		ArrayList<InterestPoint> pointsInteret = null;
		ArrayList<EntryPoint> pointsEntree = null;
		ArrayList<Point> points = null;
		ArrayList<Point> pointsRER = null;
		ArrayList<Path> footPaths = new ArrayList<Path>();
		ArrayList<HighwayPath> highwayPaths;
		ArrayList<RoadPath> roadPaths;
		
		ArrayList<Path> userPaths = new ArrayList<Path>();
		ArrayList<Path> carPaths = new ArrayList<Path>();
		
		
		try {
			pointsInteret = im.getInterestPoints();
			pointsEntree = im.getEntryPoints();
			pointsRER = im.getPointsRER();
			points = im.getPoints();
			footPaths = im.getFootPaths();
			highwayPaths = im.getHighwayPaths();
			roadPaths = im.getRoadPaths();
			userPaths.addAll(footPaths);
			carPaths.addAll(highwayPaths);
			carPaths.addAll(roadPaths);
			
		} catch (IOException e1) {
			System.err.println("Couldn't load objects");
		}
		
		/*
		Map<String,Point> pointsMap = im.getPointsMap();
		System.out.println("COUCOU");
		for (String name: pointsMap.keySet()){
			System.out.println(name+" : "+pointsMap.get(name).toString());
		}
		
		
		

		points.addAll(pointsEntree);
		points.addAll(pointsInteret);
		points.addAll(pointsRER);
	
		*/

		ArrayList<Point> points = im.getAllPoints();
		
		ArrayList<Path> carPaths = im.getCarPaths();
		ArrayList<Path> userPaths = im.getUserPaths();

		Environment env = new Environment(points, carPaths, userPaths, stepLength, realUsersPerPerson);
		//env.display();
		
		/*
		for(Path p : pathsTest){
			p.toString();
		}*/

		/*
		double[][] weight = env.initializeWeight(points,carPaths);
		System.out.println("i \t\t j \t\t weight");
		for(int i=0; i<points.size(); i++){
			for(int j=0; j<points.size(); j++){
				System.out.println(i + " \t\t " + j + " \t\t " + weight[i][j]);
			}
		}
		*/
		
		
		//env.shortestCarPath(Igny, INSTN);
		
		/*
		env.findShortestUserPath(E2,D);
		ArrayList<Path> sp = env.shortestUserPath(E2, D);


		for(int i=0; i<points.size(); i++){
			for(int j=0; j<points.size(); j++){
				if( i != j){
					env.shortestUserPath(points.get(i), points.get(j));
				}
			}
		}
		*/


		//Path nextPath = sp.get(0);
		//System.out.println(nextPath.toString());

		//env.shortestPath(E2, E3);
		/*for(Path p : env.shortestPath(E2, E3)){
			System.out.println(p.getA().toString()+","+p.getB().toString());
		}*/

		/*for(Path p : env.shortestPath(A, E2)){
			System.out.println(p.getA().toString()+","+p.getB().toString());
		};*/

		// AFFICHAGE

		
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					try {
						f = new Window();
					} catch (IOException e) {
						e.printStackTrace();
					}

					pan = new Panel(env, windowSize, simSize);

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
