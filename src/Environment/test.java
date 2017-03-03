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
		
		Point p1 = new Point("1",130,600,0);
		Point p2 = new Point("2",2190,1610,0);
		Point p3 = new Point("3",2140,1920,0);
		Point p4 = new Point("4",1480,2790,0);
		Point p5 = new Point("5",1310,4990,0);
		Point p6 = new Point("6",4250,3830,0);
		Point p7 = new Point("7",5320,2960,0);
		Point p8 = new Point("8",5610,2780,0);
		Point p9 = new Point("9",5790,1870,0);
		Point p10 = new Point("10",6150,1330,0);
		Point p11 = new Point("11",6160,550,0);
		Point p12 = new Point("12",5890,4080,0);
		Point p13 = new Point("13",6540,4790,0);
		Point p14 = new Point("14",7230,5600,0);
		Point p15 = new Point("15",5770,6230,0);
		Point p16 = new Point("16",4280,6130,0);
		Point p17 = new Point("17",2440,5180,0);
		Point p18 = new Point("18",1990,6620,0);
		Point p19 = new Point("19",2990,7140,0);
		Point p20 = new Point("20",3460,9070,0);
		Point p21 = new Point("21",8050,6240,0);
		Point p22 = new Point("22",7480,8290,0);
		Point p23 = new Point("23",2820,7790,0);
		Point p24 = new Point("24",2630,9250,0);
		Point p25 = new Point("25",6680,6620,0);
		Point p26 = new Point("26",7560,6080,0);

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

		points.add(p1);
		points.add(p2);
		points.add(p3);
		points.add(p4);
		points.add(p5);
		points.add(p6);
		points.add(p7);
		points.add(p8);
		points.add(p9);
		points.add(p10);
		points.add(p11);
		points.add(p12);
		points.add(p13);
		points.add(p14);
		points.add(p15);
		points.add(p16);
		points.add(p17);
		points.add(p18);
		points.add(p19);
		points.add(p20);
		points.add(p21);
		points.add(p22);
		points.add(p23);
		points.add(p24);
		points.add(p25);
		points.add(p26);

		FootPath path1 = new FootPath(HEC,DGA);

		ArrayList<Path> userPaths = new ArrayList<Path>();
		userPaths.add(path1);

		
		
		CarPath carPath1 = new CarPath(HEC,p3);
		CarPath carPath2 = new CarPath(DGA,p4);
		CarPath carPath3 = new CarPath(p7,B);
		CarPath carPath4 = new CarPath(JeJ,p2);
		CarPath carPath5 = new CarPath(p2,V);
		CarPath carPath6 = new CarPath(p7,V);
		CarPath carPath7 = new CarPath(Igny,p12);
		CarPath carPath8 = new CarPath(Igny,p17);
		CarPath carPath9 = new CarPath(Igny,p15);
		CarPath carPath10 = new CarPath(p12,Igny);
		CarPath carPath11 = new CarPath(p15,X);
		CarPath carPath12 = new CarPath(p16,X);
		CarPath carPath13 = new CarPath(X,EDF);
		CarPath carPath14 = new CarPath(X,Nano);
		CarPath carPath15 = new CarPath(Nano,EDF);
		CarPath carPath16 = new CarPath(p18,Nano);
		CarPath carPath17 = new CarPath(p18,LaMart);
		CarPath carPath18 = new CarPath(p17,LaMart);
		CarPath carPath19 = new CarPath(p18,IUT);
		CarPath carPath20 = new CarPath(IUT,Supelec);
		CarPath carPath21 = new CarPath(Supelec,D306);
		CarPath carPath22 = new CarPath(p18,p23);
		CarPath carPath23 = new CarPath(p18,LG);
		CarPath carPath24 = new CarPath(p18,ParisSud);
		CarPath carPath25 = new CarPath(ParisSud,OV);
		CarPath carPath26 = new CarPath(ParisSud,p24);
		CarPath carPath27 = new CarPath(p24,p20);
		CarPath carPath28 = new CarPath(p15,p25);
		CarPath carPath29 = new CarPath(p25,p26);
		CarPath carPath30 = new CarPath(p25,ONERA);
		CarPath carPath31 = new CarPath(ONERA ,PV);
		CarPath carPath32 = new CarPath(p26,P);
		CarPath carPath33 = new CarPath(p26,p21);
		CarPath carPath34 = new CarPath(p21,MP);
		CarPath carPath35 = new CarPath(p21,Safran);
		CarPath carPath36 = new CarPath(Safran,MP);
		CarPath carPath37 = new CarPath(Safran,Massy);
		CarPath carPath38 = new CarPath(Massy,MV);
		CarPath carPath39 = new CarPath(p22,Villebon);
		CarPath carPath40 = new CarPath(p22,p20);
		
		ArrayList<Path> carPaths = new ArrayList<Path>();
		carPaths.add(carPath1);
		carPaths.add(carPath2);
		carPaths.add(carPath3);
		carPaths.add(carPath4);
		carPaths.add(carPath5);
		carPaths.add(carPath6);
		carPaths.add(carPath7);
		carPaths.add(carPath8);
		carPaths.add(carPath9);
		carPaths.add(carPath10);
		carPaths.add(carPath11);
		carPaths.add(carPath12);
		carPaths.add(carPath13);
		carPaths.add(carPath14);
		carPaths.add(carPath15);
		carPaths.add(carPath16);
		carPaths.add(carPath17);
		carPaths.add(carPath18);
		carPaths.add(carPath19);
		carPaths.add(carPath20);
		carPaths.add(carPath21);
		carPaths.add(carPath22);
		carPaths.add(carPath23);
		carPaths.add(carPath24);
		carPaths.add(carPath25);
		carPaths.add(carPath26);
		carPaths.add(carPath27);
		carPaths.add(carPath28);
		carPaths.add(carPath29);
		carPaths.add(carPath30);
		carPaths.add(carPath31);
		carPaths.add(carPath32);
		carPaths.add(carPath33);
		carPaths.add(carPath34);
		carPaths.add(carPath35);
		carPaths.add(carPath36);
		carPaths.add(carPath37);
		carPaths.add(carPath38);
		carPaths.add(carPath39);
		carPaths.add(carPath40);

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
