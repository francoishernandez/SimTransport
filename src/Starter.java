import java.io.IOException;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import Environment.*;
import Graphics.Panel;
import Graphics.Window;
import Individuals.*;
import jade.wrapper.StaleProxyException;

public class Starter extends jade.core.Agent {

	public Starter(){
	}

	private static int simulationTime = 100; //en s
	
	private static Window f;
	private static Panel pan;

	public static int windowSize = 700; // taille de la fenêtre en pixels (représente 10km)
	public static int simSize = 10000; // taille en metres de la simulation

	// Dans le setup du starter on crée et lance tous les agents désirés
	public void setup(){

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
		FootPath E3D = new FootPath(E3,D);
		FootPath BD = new FootPath(B,D);
		FootPath E1C = new FootPath(E1,C);
		FootPath CD = new FootPath(C,D);
		FootPath BA = new FootPath(B,A);
		FootPath E2A = new FootPath(E2,A);
		FootPath E2C = new FootPath(E2,C);
		FootPath AC = new FootPath(A,C);
		
		ArrayList<Path> paths = new ArrayList<Path>();
		paths.add(E3B);
		paths.add(E3D);
		paths.add(BD);
		paths.add(E1C);
		paths.add(CD);
		paths.add(BA);
		paths.add(E2A);
		paths.add(E2C);
		paths.add(AC);
		
		Environment env = new Environment(points, paths);
		
		ArrayList<Person> persons = new ArrayList<Person>();

		for (int i = 0; i < 10; i++){
			Person newPerson = Person.rand_AllerRetour(pointsEntree, pointsTravail, env);
			try {
				this.getContainerController().acceptNewAgent("person"+(i+1), newPerson).start();
				persons.add(newPerson);
			} catch (StaleProxyException e) {
				e.printStackTrace();
			}	
		}
		
		Clock c = new Clock(simulationTime); 
		try {
			this.getContainerController().acceptNewAgent("clock", c).start();
		} catch (StaleProxyException e) {
			e.printStackTrace();
		}	

		// AFFICHAGE

		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					try {
						f = new Window();
					} catch (IOException e) {
						e.printStackTrace();
					}

					pan = new Panel(env, persons, c);

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