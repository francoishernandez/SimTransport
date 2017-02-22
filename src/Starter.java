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
	
	private static Window f;
	private static Panel pan;
	
	public static int windowSize = 700; // taille de la fenêtre en pixels (représente 10km)
	public static int simSize = 10000; // taille en metres de la simulation
	
	// Dans le setup du starter on crée et lance tous les agents désirés
	public void setup(){
		
		InterestPoint A = new InterestPoint("A",0,0,0);
		InterestPoint B = new InterestPoint("B",0,20,0);
		InterestPoint C = new InterestPoint("C",30,0,0);
		InterestPoint D = new InterestPoint("D",60,50,0);
		EntryPoint E1 = new EntryPoint("E1",80,120,0);
		EntryPoint E2 = new EntryPoint("E2",50,30,0);
		EntryPoint E3 = new EntryPoint("E3",10,70,0);
		ArrayList<EntryPoint> pointsEntree = new ArrayList<EntryPoint>();
		ArrayList<InterestPoint> pointsTravail = new ArrayList<InterestPoint>();
		pointsTravail.add(A);
		pointsTravail.add(B);
		pointsTravail.add(C);
		pointsTravail.add(D);
		pointsEntree.add(E1);
		pointsEntree.add(E2);
		pointsEntree.add(E3);
		

		
		for (int i = 0; i < 10; i++){
			Person newPerson = Person.rand_AllerRetour(pointsEntree, pointsTravail);
			try {
				this.getContainerController().acceptNewAgent("person"+(i+1), newPerson).start();
			} catch (StaleProxyException e) {
				e.printStackTrace();
			}	
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

							pan = new Panel(env);

							f.add(pan);
						}
					});
				}
			
	}
	
}