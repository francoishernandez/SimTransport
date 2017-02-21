import java.util.ArrayList;

import Environment.*;
import Individuals.*;

public class Starter extends jade.core.Agent {

	public Starter(){
	}
	
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
		

		Person test = Person.rand_AllerRetour(pointsEntree, pointsTravail);
		test.run();
		Person test2 = Person.rand_AllerRetour(pointsEntree, pointsTravail);
		test2.run();
		Person test3 = Person.rand_AllerRetour(pointsEntree, pointsTravail);
		test3.run();
		
	}
	
}