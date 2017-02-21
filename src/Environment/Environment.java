package Environment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

// classe contenant le graphe représentant les différents points et chemins

public class Environment {

	private ArrayList<Point> points;
	private ArrayList<Path> paths;
	private ArrayList<Journey> journeys; // recense les plus court chemin entre deux points

	// retourne le chemin le plus court d'un point A à un point B
	/*public ArrayList<Point> shortestPath(Point A, Point B){
		ArrayList<Point> res = new ArrayList<Point>();
		res.add(A);
		return res;
	}*/
	
	public Environment(ArrayList<Point> points, ArrayList<Path> paths){
		this.points = points;
		this.paths = paths;
	}
	
	public void display(){
		System.out.println("Sommet \t\t Coordonnées");
		for (Point p : points){
			System.out.println(p.getID()+" \t\t "+Integer.toString(p.getX())+", "+Integer.toString(p.getY())+", "+Integer.toString(p.getZ()));
		}
	}

	public void addPoint(Point p){
		points.add(p);
	}

	public void addPath(Path p){
		paths.add(p);
	}

	public ArrayList<Point> getPoints(){
		return points;
	}

	public ArrayList<Path> getPaths(){
		return paths;
	}


	/*

	// méthode utilitaire pour trouver le sommet avec minimum de distance dans l'ensemble de sommets
	// non inclus dans l'arbre du plus court chemin
	static final int V=9;
	int minDistance(int dist[], Boolean sptSet[]) {
		// initialiser la valeur min
		int min = Integer.MAX_VALUE, min_index = -1;
		for (int v = 0; v<V; v++)
			if (sptSet[v] == false && dist[v] <=min)
			{
				min = dist[v];
				min_index = v;
			}
		return min_index;
	}

	// méthode utilitaire pour afficher le tableau des distances
	void printSolution(int dist[], int n) {
		System.out.println("Sommet \t\t Distance à l'origine");
		for(int i = 0; i<V; i++) {
			System.out.println(i+" \t\t "+dist[i]);
		}

	}

	 */

	// implémentation de l'algorithme du plus court chemin de Djikstra

	ArrayList<Point> findShortestPath(ArrayList<Point> points, ArrayList<Path> paths, Point source, Point target){
		// mettre la source en première position
		Collections.swap(points, 0, points.indexOf(source));
		
		double[][] weight = initializeWeight(points, paths);
		double[] D = new double[points.size()];
		Point[] P = new Point[points.size()];
		ArrayList<Point> C = new ArrayList<Point>();

		int n = points.size();

		// initialisation de :
		// C : l'espace des points candidats
		// D : les poids des chemnins de Djikstra
		// P : le point précédent sur le plus court chemin

		for (int i=0; i<n; i++){
			C.add(points.get(i));
			System.out.println("C["+i+"] : "+points.get(i).getXYZ());
			D[i] = weight[0][i];
			System.out.println("D["+i+"] : "+weight[0][i]);
			if(D[i] != Double.MAX_VALUE){
				P[i] = points.get(0);
				System.out.println("P["+i+"] : "+points.get(0).getXYZ());
				
			}
		}
		
		// parcours du graphe
		System.out.println("PARCOURS DU GRAPHE");
		for(int i=0; i<n-1; i++){
			// trouver le chemin de poids le plus faible parmi les candidats
			double l = Double.MAX_VALUE;
			Point p = points.get(0);
			for (Point j : C){
				System.out.println(j.getXYZ());
				System.out.println(C.indexOf(j));
				System.out.println(D[C.indexOf(j)]);
				if(D[C.indexOf(j)]<l){
					p = j;
					l = D[C.indexOf(j)];
				}
			}
			
			ArrayList<Point> Copy = new ArrayList<Point>(C);
			Copy.addAll(0, C);
			
			C.remove(p);
			
			// vérifier qu'aucun des chemins de ce point n'a un plus court chemin depuis l'origine
			for (int j=0; j<n-1; j++){
				System.out.println("AAAAAAAAAAAAHHHHHH "+p.getXYZ());
				System.out.println("AAAAAAAAAAAAHHHHHH "+Copy.indexOf(p));
				if(D[Copy.indexOf(p)] != Double.MAX_VALUE 
						&& weight[Copy.indexOf(p)][j] != Double.MAX_VALUE
						&& D[Copy.indexOf(p)] + weight[Copy.indexOf(p)][j] < D[j]){
					// il existe un tel chemin
					D[j] = D[Copy.indexOf(p)] + weight[Copy.indexOf(p)][j];
					P[j] = p;
				}
			}
		}
		
		// nous avons le plus court chemin, utiliser C comme la liste résultat
		C.clear();
		int loc = points.indexOf(target);
		C.add(target);
		System.out.println("PLUS COURT CHEMIN");
		System.out.println(target.getXYZ());
		// backtrack de la cible vers l'origine
		while (P[loc] != points.get(0)){
			if(P[loc] == null){
				// aucun chemin de la source à la cible
				return null;
			}
			System.out.println(P[loc].getXYZ());
			C.add(0,P[loc]);
			loc = points.indexOf(P[loc]);
		}
		C.add(0,points.get(0));
		return C;
	}




	// initialisation du tableau de poids
	private double[][] initializeWeight(ArrayList<Point> points, ArrayList<Path> paths){
		int n = points.size();
		double[][] weight = new double[n][n];
		for (int i=0; i<n; i++) {
			Arrays.fill(weight[i], Double.MAX_VALUE);
		}
		for (Path p : paths) {
			weight[points.indexOf(p.getA())][points.indexOf(p.getB())] = p.weight();
		}
		return weight;
	}







}
