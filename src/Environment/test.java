package Environment;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import Graphics.*;
import Individuals.Person;

public class test {

	private static Window f;
	private static Panel pan;

	public static int windowSize = 700; // taille de la fenêtre en pixels (représente 10km)
	public static int simSize = 10000; // taille en metres de la simulation

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		InterestPoint HEC = new InterestPoint("HEC",1750,1950,0);
		InterestPoint DGA = new InterestPoint("DGA Essais Propulseurs",2120,3010,0);
		InterestPoint INSTN = new InterestPoint("INSTN",790,5720,0);
		InterestPoint Supelec = new InterestPoint("Supélec",1230,7310,0);
		InterestPoint IUT = new InterestPoint("IUT Orsay",1860,7100,0);
		InterestPoint LaMart = new InterestPoint("La Martinière",2790,6170,0);
		InterestPoint ParisSud = new InterestPoint("Université Paris-Sud",2300,8590,0);
		InterestPoint X = new InterestPoint("Polytechnique",4530,6840,0);
		InterestPoint Nano = new InterestPoint("Nano-Innov",3650,6970,0);
		InterestPoint EDF = new InterestPoint("EDF",3740,6600,0);
		InterestPoint Vilgenis = new InterestPoint("Vilgenis",7180,4910,0);
		InterestPoint Safran = new InterestPoint("Safran",8930,5700,0);
		InterestPoint Villebon = new InterestPoint("Villebon2",7950,7910,0);
		InterestPoint ONERA = new InterestPoint("ONERA",6400,6800,0);
		ArrayList<InterestPoint> pointsInteret = new ArrayList<InterestPoint>();
		pointsInteret.add(HEC);
		pointsInteret.add(DGA);
		pointsInteret.add(INSTN);
		pointsInteret.add(Supelec);
		pointsInteret.add(IUT);
		pointsInteret.add(LaMart);
		pointsInteret.add(ParisSud);
		pointsInteret.add(X);
		pointsInteret.add(Nano);
		pointsInteret.add(EDF);
		pointsInteret.add(Vilgenis);
		pointsInteret.add(Safran);
		pointsInteret.add(Villebon);
		pointsInteret.add(ONERA);
		
		EntryPoint RERno = new EntryPoint("RER Nord-Ouest",0,200,0);
		EntryPoint RERso = new EntryPoint("RER Sud-Ouest",0,8640,0);
		EntryPoint D446 = new EntryPoint("D446 Nord",490,0,0);
		EntryPoint N118n = new EntryPoint("N118 Nord",5670,0,0);
		EntryPoint N118s = new EntryPoint("N118 Sud",3250,10000,0);
		EntryPoint RERe = new EntryPoint("RER Est",10000,3540,0);
		EntryPoint D306 = new EntryPoint("D306 Ouest",0,6970,0);
		EntryPoint Igny = new EntryPoint("Igny",5310,4520,0);
		EntryPoint Massy = new EntryPoint("Massy",9370,5030,0);
		EntryPoint A10 = new EntryPoint("A10 Est",10000,7020,0);
		ArrayList<EntryPoint> pointsEntree = new ArrayList<EntryPoint>();
		pointsEntree.add(RERno);
		pointsEntree.add(RERso);
		pointsEntree.add(D446);
		pointsEntree.add(N118n);
		pointsEntree.add(N118s);
		pointsEntree.add(RERe);
		pointsEntree.add(D306);
		pointsEntree.add(Igny);
		pointsEntree.add(Massy);
		pointsEntree.add(A10);
		
		Point MV = new Point("RER Massy-Verrières",9380,4500,0);
		Point MP = new Point("RER Massy-Palaiseau",8390,5570,0);
		Point P = new Point("RER Palaiseau",7410,6380,0);
		Point PV = new Point("RER Palaiseau-Villebon",6740,7530,0);
		Point L = new Point("RER Lozère",4880,7680,0);
		Point LG = new Point("RER Le Guichet",3430,7790,0);
		Point OV = new Point("RER Orsay Ville",2270,8630,0);
		Point BsY = new Point("RER Bures-sur-Yvette",550,8920,0);
		Point JeJ = new Point("RER Jouy-en-Josas",1650,1260,0);
		Point V = new Point("RER Vauboyen",3440,1780,0);
		Point B = new Point("RER Bièvres",5170,2660,0);
		Point I = new Point("RER Igny",6280,3910,0);
		


		ArrayList<Point> points = new ArrayList<Point>();
		points.addAll(pointsEntree);
		points.addAll(pointsInteret);
		points.add(MV);
		points.add(MP);
		points.add(P);
		points.add(PV);
		points.add(L);
		points.add(LG);
		points.add(OV);
		points.add(BsY);
		points.add(JeJ);
		points.add(V);
		points.add(B);
		points.add(I);


		FootPath path1 = new FootPath(HEC,DGA);
		CarPath path2 = new CarPath(Supelec,IUT);

		ArrayList<Path> userPaths = new ArrayList<Path>();
		userPaths.add(path1);

		ArrayList<Path> carPaths = new ArrayList<Path>();
		carPaths.add(path2);

		Environment env = new Environment(points, carPaths, userPaths);
		env.display();

		/*double[][] weight = env.initializeWeight(points,paths);
		System.out.println("i \t\t j \t\t weight");
		for(int i=0; i<points.size(); i++){
			for(int j=0; j<points.size(); j++){
				System.out.println(i + " \t\t " + j + " \t\t " + weight[i][j]);
			}
		}

		env.findShortestUserPath(A, E2);
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
