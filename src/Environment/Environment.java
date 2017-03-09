package Environment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import Environment.Paths.Path;

// classe contenant le graphe représentant les différents points et chemins

public class Environment {

	private ArrayList<Point> points;
	private ArrayList<Path> carPaths;
	private ArrayList<Path> publicTransportPaths;
	private ArrayList<Journey> journeys; // recense les plus court chemin entre deux points
	
	// retourne le chemin le plus court d'un point A à un point B
	/*public ArrayList<Point> shortestPath(Point A, Point B){
		ArrayList<Point> res = new ArrayList<Point>();
		res.add(A);
		return res;
	}*/

	public Environment(ArrayList<Point> points, ArrayList<Path> carPaths, ArrayList<Path> userPaths){
		this.points = points;
		this.carPaths = carPaths;
		this.publicTransportPaths = userPaths;
	}

	public void display(){
		System.out.println("Sommet \t\t Nom \t\t Coordonnées");
		for (Point p : points){
			System.out.println(p.getID()+" \t\t "+p.getName()+" \t\t "+Integer.toString(p.getX())+", "+Integer.toString(p.getY())+", "+Integer.toString(p.getZ()));
		}
	}

	public void addPoint(Point p){
		points.add(p);
	}

	public void addUserPath(Path p){
		publicTransportPaths.add(p);
	}
	
	public void addCarPath(Path p){
		carPaths.add(p);
	}

	public ArrayList<Point> getPoints(){
		return points;
	}

	public ArrayList<Path> getPaths(){
		ArrayList<Path> paths = new ArrayList<Path>();
		paths.addAll(publicTransportPaths);
		paths.addAll(carPaths);
		return paths;
		
	}
	
	public ArrayList<Path> getUserPaths(){
		return publicTransportPaths;
	}
	
	public ArrayList<Path> getCarPath(){
		return carPaths;
	}


	// détermination du prochain chemin a emprunter (à partir de Djikstra ci dessous)


	// implémentation de l'algorithme du plus court chemin de Djikstra

	
	// Rend la liste des points du chemin choisi
	public ArrayList<Point> shortestPathPointList( ArrayList<Path> paths, Point source, Point target){
		double[][] weight = initializeWeight(points, paths); // matrice des poids
		int n = points.size(); // nombre de points
		double[] D = new double[n]; // tableau des poids
		Point[] P = new Point[n]; // tableau des 'précédents'
		ArrayList<Point> C = new ArrayList<Point>(n); // liste des 'candidats'
		Point[] Carray = new Point[n];



		// initialisation des tableaux
		for (int i=0; i<n;i++) {
			Carray[points.get(i).getID()] = points.get(i);
			//System.out.println("C["+points.get(i).getID()+"] : "+Carray[points.get(i).getID()].getID());
			D[points.get(i).getID()] = weight[source.getID()][points.get(i).getID()];
			//System.out.println("D["+points.get(i).getID()+"] : "+weight[0][points.get(i).getID()]);
			if(D[points.get(i).getID()] != Double.MAX_VALUE){
				P[points.get(i).getID()] = source;
				//System.out.println("P["+points.get(i).getID()+"] : "+source.getID());
			}
		}
		for (int i = 0; i<n; i++){
			C.add(Carray[i]);
		}

		// parcours du graphe
		for (int i=0; i<n; i++) {
			// trouver le chemin de poids minimal parmi les candidats
			double w = Double.MAX_VALUE;
			Point p = source;
			for(Point j : C){
				if(D[j.getID()] < w){
					p = j;
					w = D[j.getID()];
				}
			}
			C.remove(p);

			// vérifier qu'aucun autre chemin depuis ce point n'est plus court jusqu'à la source
			for (int j=0; j<n; j++) {
				if(D[p.getID()] != Double.MAX_VALUE
						&& weight[p.getID()][j] != Double.MAX_VALUE
						&& D[p.getID()]+weight[p.getID()][j]<D[j]
						) {
					// il existe un tel chemin
					D[j] = D[p.getID()] + weight[p.getID()][j];
					P[j] = p;
				}
			}
		}

		// le chemin est trouvé, utilisons C pour récupérer le résultat


		C.clear();
		int loc = target.getID();
		C.add(target);


		// backtrack depuis la source en parcourant le tableau des précédents
		while(P[loc] != source) {
			if(P[loc] == null) {
				// aucun chemin depuis la source
				System.out.println("PAS DE CHEMIN ["+source.getName()+","+target.getName()+"]");
				return null;
			}
			C.add(0,P[loc]);
			loc = P[loc].getID();
		}
		C.add(0, source);
		System.out.println("PLUS COURT CHEMIN ["+source.getName()+","+target.getName()+"]");
		for (int i=0; i<C.size(); i++){
			System.out.println(C.get(i).getID()+","+C.get(i).getName());
		}
		return C;
	}

	
	public ArrayList<Path> shortestCarPath(Point source, Point target){
		return shortestPath(carPaths,source,target);
	}
	
	public ArrayList<Path> shortestPublicTransportPath(Point source, Point target){
		return shortestPath(publicTransportPaths,source,target);
	}

	// Rend la liste des chemins empruntés
	public ArrayList<Path> shortestPath(ArrayList<Path> paths, Point source, Point target){
		ArrayList<Point> shortestPath = shortestPathPointList(paths, source, target);
		if (shortestPath != null) {
			double[][] weight = initializeWeight(points, paths); // matrice des poids
			Path[][] pathsTab = initializePaths(points, paths, weight);
			ArrayList<Path> selectedPaths = new ArrayList<Path>();
			for (int i = 0; i<shortestPath.size()-1; i++){
				selectedPaths.add(pathsTab[shortestPath.get(i).getID()][shortestPath.get(i+1).getID()]);
			}
			return selectedPaths;
		}
		else return null;

	}


	// initialisation du tableau de poids
	public double[][] initializeWeight(ArrayList<Point> points, ArrayList<Path> paths){
		int n = points.size();
		double[][] weight = new double[n][n];
		for (int i=0; i<n; i++) {
			Arrays.fill(weight[i], Double.MAX_VALUE);
		}
		for (Path p : paths) {
			if(weight[p.getA().getID()][p.getB().getID()] >= p.weight()){
				weight[p.getA().getID()][p.getB().getID()] = p.weight(); // initialise la matrice de poids en considérant le chemin de poids minimal
			}
		}
		return weight;
	}

	// initialisation du tableau des chemins
	public Path[][] initializePaths(ArrayList<Point> points, ArrayList<Path> paths, double[][] weight){
		int n = points.size();
		Path[][] pathsTab = new Path[n][n];
		for (int i=0; i<n; i++){
			Arrays.fill(pathsTab[i], null);
		}
		for (Path p : paths) {
			if(weight[p.getA().getID()][p.getB().getID()] >= p.weight()){
				pathsTab[p.getA().getID()][p.getB().getID()] = p;

			}
		}
		return pathsTab;
	}
	
	

}