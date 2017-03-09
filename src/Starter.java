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

	private static int simulationTime = 2000; //en s
	private static int stepLength = 15; //en s
	private static int startHour = 10; 
	
	// On fait tourner un certain nombre d'agents Person, mais ces agents peuvent représenter plusieurs personnes réelles
	// pour alléger la simulation. Ceci intervient dans l'encombrement des routes.
	private static int nbPersons = 2000;
	private static int realUsersPerPerson = 10;
	
	// Heures génération aléatoire des schedules :
	private static int centerBeginTime = 10; 
	private static double sigmaBeginTime = 4;
	private static int centerEndTime = 18; 
	private static double sigmaEndTime = 4;
	
	private static Window f;
	private static Panel pan;

	public static int windowSize = 700; // taille de la fenêtre en pixels (représente 10km)
	public static int simSize = 10000; // taille en metres de la simulation

	// Dans le setup du starter on crée et lance tous les agents désirés
	public void setup(){

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
		
		//EntryPoint RERno = new EntryPoint("RER Nord-Ouest",0,200,0);
		//EntryPoint RERso = new EntryPoint("RER Sud-Ouest",0,8640,0);
		EntryPoint D446 = new EntryPoint("D446 Nord",490,0,0);
		EntryPoint N118n = new EntryPoint("N118 Nord",5670,0,0);
		EntryPoint N118s = new EntryPoint("N118 Sud",3250,10000,0);
		//EntryPoint RERe = new EntryPoint("RER Est",10000,3540,0);
		EntryPoint D306 = new EntryPoint("D306 Ouest",0,6970,0);
		EntryPoint Igny = new EntryPoint("Igny",5310,4520,0);
		EntryPoint Massy = new EntryPoint("Massy",9370,5030,0);
		EntryPoint A10 = new EntryPoint("A10 Est",10000,7020,0);
		
		ArrayList<EntryPoint> pointsEntree = new ArrayList<EntryPoint>();
		//pointsEntree.add(RERno);
		//pointsEntree.add(RERso);
		pointsEntree.add(D446);
		pointsEntree.add(N118n);
		pointsEntree.add(N118s);
		//pointsEntree.add(RERe);
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
		Point p6 = new Point("6",4230,2810,0);
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

		ArrayList<Path> userPaths = new ArrayList<Path>();
		
		FootPath footPath1 = new FootPath(L,X);
		FootPath footPath2 = new FootPath(BsY,IUT);
		FootPath footPath1return = new FootPath(X,L);
		FootPath footPath2return = new FootPath(IUT,BsY);
		
		ArrayList<Path> footPaths = new ArrayList<Path>();
		
		footPaths.add(footPath1);
		footPaths.add(footPath2);
		footPaths.add(footPath1return);
		footPaths.add(footPath2return);
		
		
		userPaths.addAll(footPaths);
		
		RoadPath roadPath1 = new RoadPath(HEC,p3);
		RoadPath roadPath2 = new RoadPath(DGA,p4);
		RoadPath roadPath3 = new RoadPath(p7,B);
		RoadPath roadPath4 = new RoadPath(JeJ,p2);
		RoadPath roadPath5 = new RoadPath(p2,V);
		RoadPath roadPath6 = new RoadPath(p7,V);
		RoadPath roadPath7 = new RoadPath(Igny,p12);
		RoadPath roadPath8 = new RoadPath(Igny,p17);
		RoadPath roadPath9 = new RoadPath(Igny,p15);
		RoadPath roadPath10 = new RoadPath(p12,Igny);
		RoadPath roadPath11 = new RoadPath(p15,X);
		RoadPath roadPath12 = new RoadPath(p16,X);
		RoadPath roadPath13 = new RoadPath(X,EDF);
		RoadPath roadPath14 = new RoadPath(X,Nano);
		RoadPath roadPath15 = new RoadPath(Nano,EDF);
		RoadPath roadPath16 = new RoadPath(p18,Nano);
		RoadPath roadPath17 = new RoadPath(p18,LaMart);
		RoadPath roadPath18 = new RoadPath(p17,LaMart);
		RoadPath roadPath19 = new RoadPath(p18,IUT);
		RoadPath roadPath20 = new RoadPath(IUT,Supelec);
		RoadPath roadPath21 = new RoadPath(Supelec,D306);
		RoadPath roadPath22 = new RoadPath(p18,p23);
		RoadPath roadPath23 = new RoadPath(p18,LG);
		RoadPath roadPath24 = new RoadPath(p18,ParisSud);
		RoadPath roadPath25 = new RoadPath(ParisSud,OV);
		RoadPath roadPath26 = new RoadPath(ParisSud,p24);
		RoadPath roadPath27 = new RoadPath(p24,p20);
		RoadPath roadPath28 = new RoadPath(p15,p25);
		RoadPath roadPath29 = new RoadPath(p25,p26);
		RoadPath roadPath30 = new RoadPath(p25,ONERA);
		RoadPath roadPath31 = new RoadPath(ONERA ,PV);
		RoadPath roadPath32 = new RoadPath(p26,P);
		RoadPath roadPath33 = new RoadPath(p26,p21);
		RoadPath roadPath34 = new RoadPath(p21,MP);
		RoadPath roadPath35 = new RoadPath(p21,Safran);
		RoadPath roadPath36 = new RoadPath(Safran,MP);
		RoadPath roadPath37 = new RoadPath(Safran,Massy);
		RoadPath roadPath38 = new RoadPath(Massy,MV);
		RoadPath roadPath39 = new RoadPath(p22,Villebon);
		RoadPath roadPath40 = new RoadPath(p22,p20);
		
		RoadPath roadPath1return = new RoadPath(p3,HEC);
		RoadPath roadPath2return = new RoadPath(p4,DGA);
		RoadPath roadPath3return = new RoadPath(B,p7);
		RoadPath roadPath4return = new RoadPath(p2,JeJ);
		RoadPath roadPath5return = new RoadPath(V,p2);
		RoadPath roadPath6return = new RoadPath(V,p7);
		RoadPath roadPath7return = new RoadPath(p12,Igny);
		RoadPath roadPath8return = new RoadPath(p17,Igny);
		RoadPath roadPath9return = new RoadPath(p15,Igny);
		RoadPath roadPath10return = new RoadPath(Igny,p12);
		RoadPath roadPath11return = new RoadPath(X,p15);
		RoadPath roadPath12return = new RoadPath(X,p16);
		RoadPath roadPath13return = new RoadPath(EDF,X);
		RoadPath roadPath14return = new RoadPath(Nano,X);
		RoadPath roadPath15return = new RoadPath(EDF,Nano);
		RoadPath roadPath16return = new RoadPath(Nano,p18);
		RoadPath roadPath17return = new RoadPath(LaMart,p18);
		RoadPath roadPath18return = new RoadPath(LaMart,p17);
		RoadPath roadPath19return = new RoadPath(IUT,p18);
		RoadPath roadPath20return = new RoadPath(Supelec,IUT);
		RoadPath roadPath21return = new RoadPath(D306,Supelec);
		RoadPath roadPath22return = new RoadPath(p23,p18);
		RoadPath roadPath23return = new RoadPath(LG,p18);
		RoadPath roadPath24return = new RoadPath(ParisSud,p18);
		RoadPath roadPath25return = new RoadPath(OV,ParisSud);
		RoadPath roadPath26return = new RoadPath(p24,ParisSud);
		RoadPath roadPath27return = new RoadPath(p20,p24);
		RoadPath roadPath28return = new RoadPath(p25,p15);
		RoadPath roadPath29return = new RoadPath(p26,p25);
		RoadPath roadPath30return = new RoadPath(ONERA,p25);
		RoadPath roadPath31return = new RoadPath(PV,ONERA );
		RoadPath roadPath32return = new RoadPath(P,p26);
		RoadPath roadPath33return = new RoadPath(p21,p26);
		RoadPath roadPath34return = new RoadPath(MP,p21);
		RoadPath roadPath35return = new RoadPath(Safran,p21);
		RoadPath roadPath36return = new RoadPath(MP,Safran);
		RoadPath roadPath37return = new RoadPath(Massy,Safran);
		RoadPath roadPath38return = new RoadPath(MV,Massy);
		RoadPath roadPath39return = new RoadPath(Villebon,p22);
		RoadPath roadPath40return = new RoadPath(p20,p22);
		
		ArrayList<Path> roadPaths = new ArrayList<Path>();
		
		roadPaths.add(roadPath1);
		roadPaths.add(roadPath2);
		roadPaths.add(roadPath3);
		roadPaths.add(roadPath4);
		roadPaths.add(roadPath5);
		roadPaths.add(roadPath6);
		roadPaths.add(roadPath7);
		roadPaths.add(roadPath8);
		roadPaths.add(roadPath9);
		roadPaths.add(roadPath10);
		roadPaths.add(roadPath11);
		roadPaths.add(roadPath12);
		roadPaths.add(roadPath13);
		roadPaths.add(roadPath14);
		roadPaths.add(roadPath15);
		roadPaths.add(roadPath16);
		roadPaths.add(roadPath17);
		roadPaths.add(roadPath18);
		roadPaths.add(roadPath19);
		roadPaths.add(roadPath20);
		roadPaths.add(roadPath21);
		roadPaths.add(roadPath22);
		roadPaths.add(roadPath23);
		roadPaths.add(roadPath24);
		roadPaths.add(roadPath25);
		roadPaths.add(roadPath26);
		roadPaths.add(roadPath27);
		roadPaths.add(roadPath28);
		roadPaths.add(roadPath29);
		roadPaths.add(roadPath30);
		roadPaths.add(roadPath31);
		roadPaths.add(roadPath32);
		roadPaths.add(roadPath33);
		roadPaths.add(roadPath34);
		roadPaths.add(roadPath35);
		roadPaths.add(roadPath36);
		roadPaths.add(roadPath37);
		roadPaths.add(roadPath38);
		roadPaths.add(roadPath39);
		roadPaths.add(roadPath40);
		
		roadPaths.add(roadPath1return);
		roadPaths.add(roadPath2return);
		roadPaths.add(roadPath3return);
		roadPaths.add(roadPath4return);
		roadPaths.add(roadPath5return);
		roadPaths.add(roadPath6return);
		roadPaths.add(roadPath7return);
		roadPaths.add(roadPath8return);
		roadPaths.add(roadPath9return);
		roadPaths.add(roadPath10return);
		roadPaths.add(roadPath11return);
		roadPaths.add(roadPath12return);
		roadPaths.add(roadPath13return);
		roadPaths.add(roadPath14return);
		roadPaths.add(roadPath15return);
		roadPaths.add(roadPath16return);
		roadPaths.add(roadPath17return);
		roadPaths.add(roadPath18return);
		roadPaths.add(roadPath19return);
		roadPaths.add(roadPath20return);
		roadPaths.add(roadPath21return);
		roadPaths.add(roadPath22return);
		roadPaths.add(roadPath23return);
		roadPaths.add(roadPath24return);
		roadPaths.add(roadPath25return);
		roadPaths.add(roadPath26return);
		roadPaths.add(roadPath27return);
		roadPaths.add(roadPath28return);
		roadPaths.add(roadPath29return);
		roadPaths.add(roadPath30return);
		roadPaths.add(roadPath31return);
		roadPaths.add(roadPath32return);
		roadPaths.add(roadPath33return);
		roadPaths.add(roadPath34return);
		roadPaths.add(roadPath35return);
		roadPaths.add(roadPath36return);
		roadPaths.add(roadPath37return);
		roadPaths.add(roadPath38return);
		roadPaths.add(roadPath39return);
		roadPaths.add(roadPath40return);
		
		HighwayPath highwayPath1 = new HighwayPath(D446,p1);
		HighwayPath highwayPath2 = new HighwayPath(p1,p2);
		HighwayPath highwayPath3 = new HighwayPath(p2,p3);
		HighwayPath highwayPath4 = new HighwayPath(p3,p4);
		HighwayPath highwayPath5 = new HighwayPath(p4,p5);
		HighwayPath highwayPath6 = new HighwayPath(p5,INSTN);
		HighwayPath highwayPath7 = new HighwayPath(INSTN,D306);
		HighwayPath highwayPath8 = new HighwayPath(p5,p6);
		HighwayPath highwayPath9 = new HighwayPath(p6,p7);
		HighwayPath highwayPath10 = new HighwayPath(p7,p8);
		HighwayPath highwayPath11 = new HighwayPath(p8,p9);
		HighwayPath highwayPath12 = new HighwayPath(p9,p10);
		HighwayPath highwayPath13 = new HighwayPath(p10,p11);
		HighwayPath highwayPath14 = new HighwayPath(p11,N118n);
		HighwayPath highwayPath15 = new HighwayPath(p7,p12);
		HighwayPath highwayPath16 = new HighwayPath(p12,p13);
		HighwayPath highwayPath17 = new HighwayPath(p13,Vilgenis);
		HighwayPath highwayPath18 = new HighwayPath(Vilgenis,p14);
		HighwayPath highwayPath19 = new HighwayPath(p14,p15);
		HighwayPath highwayPath20 = new HighwayPath(p15,p16);
		HighwayPath highwayPath21 = new HighwayPath(p16,p17);
		HighwayPath highwayPath22 = new HighwayPath(p17,p5);
		HighwayPath highwayPath23 = new HighwayPath(p5,p18);
		HighwayPath highwayPath24 = new HighwayPath(p18,p19);
		HighwayPath highwayPath25 = new HighwayPath(p19,LG);
		HighwayPath highwayPath26 = new HighwayPath(LG,p20);
		HighwayPath highwayPath27 = new HighwayPath(p20,N118s);
		HighwayPath highwayPath28 = new HighwayPath(p14,p21);
		HighwayPath highwayPath29 = new HighwayPath(p21,p22);
		HighwayPath highwayPath30 = new HighwayPath(p21,A10);
		
		HighwayPath highwayPath1return = new HighwayPath(p1,D446);
		HighwayPath highwayPath2return = new HighwayPath(p2,p1);
		HighwayPath highwayPath3return = new HighwayPath(p3,p2);
		HighwayPath highwayPath4return = new HighwayPath(p4,p3);
		HighwayPath highwayPath5return = new HighwayPath(p5,p4);
		HighwayPath highwayPath6return = new HighwayPath(INSTN,p5);
		HighwayPath highwayPath7return = new HighwayPath(D306,INSTN);
		HighwayPath highwayPath8return = new HighwayPath(p6,p5);
		HighwayPath highwayPath9return = new HighwayPath(p7,p6);
		HighwayPath highwayPath10return = new HighwayPath(p8,p7);
		HighwayPath highwayPath11return = new HighwayPath(p9,p8);
		HighwayPath highwayPath12return = new HighwayPath(p10,p9);
		HighwayPath highwayPath13return = new HighwayPath(p11,p10);
		HighwayPath highwayPath14return = new HighwayPath(N118n,p11);
		HighwayPath highwayPath15return = new HighwayPath(p12,p7);
		HighwayPath highwayPath16return = new HighwayPath(p13,p12);
		HighwayPath highwayPath17return = new HighwayPath(Vilgenis,p13);
		HighwayPath highwayPath18return = new HighwayPath(p14,Vilgenis);
		HighwayPath highwayPath19return = new HighwayPath(p15,p14);
		HighwayPath highwayPath20return = new HighwayPath(p16,p15);
		HighwayPath highwayPath21return = new HighwayPath(p17,p16);
		HighwayPath highwayPath22return = new HighwayPath(p5,p17);
		HighwayPath highwayPath23return = new HighwayPath(p18,p5);
		HighwayPath highwayPath24return = new HighwayPath(p19,p18);
		HighwayPath highwayPath25return = new HighwayPath(LG,p19);
		HighwayPath highwayPath26return = new HighwayPath(p20,LG);
		HighwayPath highwayPath27return = new HighwayPath(N118s,p20);
		HighwayPath highwayPath28return = new HighwayPath(p21,p14);
		HighwayPath highwayPath29return = new HighwayPath(p22,p21);
		HighwayPath highwayPath30return = new HighwayPath(A10,p21);
		
		ArrayList<Path> highwayPaths = new ArrayList<Path>();
		
		highwayPaths.add(highwayPath1);
		highwayPaths.add(highwayPath2);
		highwayPaths.add(highwayPath3);
		highwayPaths.add(highwayPath4);
		highwayPaths.add(highwayPath5);
		highwayPaths.add(highwayPath6);
		highwayPaths.add(highwayPath7);
		highwayPaths.add(highwayPath8);
		highwayPaths.add(highwayPath9);
		highwayPaths.add(highwayPath10);
		highwayPaths.add(highwayPath11);
		highwayPaths.add(highwayPath12);
		highwayPaths.add(highwayPath13);
		highwayPaths.add(highwayPath14);
		highwayPaths.add(highwayPath15);
		highwayPaths.add(highwayPath16);
		highwayPaths.add(highwayPath17);
		highwayPaths.add(highwayPath18);
		highwayPaths.add(highwayPath19);
		highwayPaths.add(highwayPath20);
		highwayPaths.add(highwayPath21);
		highwayPaths.add(highwayPath22);
		highwayPaths.add(highwayPath23);
		highwayPaths.add(highwayPath24);
		highwayPaths.add(highwayPath25);
		highwayPaths.add(highwayPath26);
		highwayPaths.add(highwayPath27);
		highwayPaths.add(highwayPath28);
		highwayPaths.add(highwayPath29);
		highwayPaths.add(highwayPath30);
		
		highwayPaths.add(highwayPath1return);
		highwayPaths.add(highwayPath2return);
		highwayPaths.add(highwayPath3return);
		highwayPaths.add(highwayPath4return);
		highwayPaths.add(highwayPath5return);
		highwayPaths.add(highwayPath6return);
		highwayPaths.add(highwayPath7return);
		highwayPaths.add(highwayPath8return);
		highwayPaths.add(highwayPath9return);
		highwayPaths.add(highwayPath10return);
		highwayPaths.add(highwayPath11return);
		highwayPaths.add(highwayPath12return);
		highwayPaths.add(highwayPath13return);
		highwayPaths.add(highwayPath14return);
		highwayPaths.add(highwayPath15return);
		highwayPaths.add(highwayPath16return);
		highwayPaths.add(highwayPath17return);
		highwayPaths.add(highwayPath18return);
		highwayPaths.add(highwayPath19return);
		highwayPaths.add(highwayPath20return);
		highwayPaths.add(highwayPath21return);
		highwayPaths.add(highwayPath22return);
		highwayPaths.add(highwayPath23return);
		highwayPaths.add(highwayPath24return);
		highwayPaths.add(highwayPath25return);
		highwayPaths.add(highwayPath26return);
		highwayPaths.add(highwayPath27return);
		highwayPaths.add(highwayPath28return);
		highwayPaths.add(highwayPath29return);
		highwayPaths.add(highwayPath30return);
		
		ArrayList<Path> carPaths = new ArrayList<Path>();
		carPaths.addAll(highwayPaths);
		carPaths.addAll(roadPaths);

		Environment env = new Environment(points, carPaths, userPaths, stepLength, realUsersPerPerson);
		
		ArrayList<Person> persons = new ArrayList<Person>();
		
		for (int i = 0; i < nbPersons; i++){
			Person newPerson = Person.rand_AllerRetour(pointsEntree, pointsInteret, env, centerBeginTime, sigmaBeginTime, centerEndTime, sigmaEndTime);
			try {
				this.getContainerController().acceptNewAgent("person"+(i+1), newPerson).start();
				persons.add(newPerson);
			} catch (StaleProxyException e) {
				e.printStackTrace();
			}	
		}
		
		Clock c = new Clock(simulationTime, stepLength, startHour); 
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

					pan = new Panel(env, persons, c, windowSize, simSize);

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