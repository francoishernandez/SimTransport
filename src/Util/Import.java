package Util;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Environment.*;
import Environment.Paths.*;

// Cette classe comporte une méthode d'import par type d'objet.
// Ces imports sont effectués depuis les fichiers .csv du dossier 'objects'.
// Tous les imports et créations d'objets sont effectués à l'instanciation d'un objet de cette classe
// et ils sont récupérables à l'aide des différents getters.

public class Import {
	
	ArrayList<Point> points = new ArrayList<Point>();
	ArrayList<Path> userPaths = new ArrayList<Path>();
	ArrayList<Path> carPaths = new ArrayList<Path>();
	ArrayList<EntryPoint> pointsEntree = new ArrayList<EntryPoint>();
	ArrayList<InterestPoint> pointsInteret = new ArrayList<InterestPoint>();

	// Hashmap des points créés à l'importation, pour faciliter la création des chemins (appel des objets correspondants)
	private Map<String, Point> pointsMap = new HashMap<String, Point>();
	
	public Import() throws IOException {
		pointsEntree = getEntryPoints();
		points.addAll(pointsEntree);
		pointsInteret = getInterestPoints();
		points.addAll(pointsInteret);
		points.addAll(getPoints());
		points.addAll(getPointsRER());
		userPaths.addAll(getFootPaths());
		//userPaths.addAll(getBikePaths());
		carPaths.addAll(getRoadPaths());
		carPaths.addAll(getHighwayPaths());
	};
	
	
	// Importe les points d'intérêt
	public ArrayList<InterestPoint> getInterestPoints() throws IOException {
		ArrayList<InterestPoint> points = new ArrayList<InterestPoint>();
		System.out.println("IN GETINTERESTPOINTS");
		FileReader filer = new FileReader("objects/interestPoints.csv");
		// NOMBRE DE LIGNES
		int lines = 0;
		BufferedReader reader = new BufferedReader(filer);
		while(reader.readLine()!=null) lines++;
		reader.close();
		// LECTURE DU FICHIER
		FileReader fr = new FileReader("objects/interestPoints.csv");
		BufferedReader br = new BufferedReader(fr);
		br.readLine(); // éliminer la ligne d'entête
		while (lines>1) {
			String[] line = br.readLine().split(",");
			String pointID = line[0];
			String pointName = line[1];
			int x = Integer.parseInt(line[2]);
			int y = Integer.parseInt(line[3]);
			int z = Integer.parseInt(line[4]);
			InterestPoint point = new InterestPoint(pointName, x, y, z);
			points.add(point);
			pointsMap.put(pointID, point);
			lines--;
		}
		
		br.close();

		return points;
	}
	
	
	// Importe les points d'entrée
	public ArrayList<EntryPoint> getEntryPoints() throws IOException {
		ArrayList<EntryPoint> points = new ArrayList<EntryPoint>();
		System.out.println("IN GETENTRYPOINTS");
		FileReader filer = new FileReader("objects/entryPoints.csv");
		// NOMBRE DE LIGNES
		int lines = 0;
		BufferedReader reader = new BufferedReader(filer);
		while(reader.readLine()!=null) lines++;
		reader.close();
		// LECTURE DU FICHIER
		FileReader fr = new FileReader("objects/entryPoints.csv");
		BufferedReader br = new BufferedReader(fr);
		br.readLine(); // éliminer la ligne d'entête
		while (lines>1) {
			String[] line = br.readLine().split(",");
			String pointID = line[0];
			String pointName = line[1];
			int x = Integer.parseInt(line[2]);
			int y = Integer.parseInt(line[3]);
			int z = Integer.parseInt(line[4]);
			EntryPoint point = new EntryPoint(pointName, x, y, z);
			points.add(point);
			pointsMap.put(pointID, point);
			lines--;
		}
		
		br.close();

		return points;
	}
	
	// Importe les points 'de passage'
	public ArrayList<Point> getPoints() throws IOException {
		ArrayList<Point> points = new ArrayList<Point>();
		System.out.println("IN GETPOINTS");
		FileReader filer = new FileReader("objects/points.csv");
		// NOMBRE DE LIGNES
		int lines = 0;
		BufferedReader reader = new BufferedReader(filer);
		while(reader.readLine()!=null) lines++;
		reader.close();
		// LECTURE DU FICHIER
		FileReader fr = new FileReader("objects/points.csv");
		BufferedReader br = new BufferedReader(fr);
		br.readLine(); // éliminer la ligne d'entête
		while (lines>1) {
			String[] line = br.readLine().split(",");
			String pointID = line[0];
			String pointName = line[1];
			int x = Integer.parseInt(line[2]);
			int y = Integer.parseInt(line[3]);
			int z = Integer.parseInt(line[4]);
			Point point = new Point(pointName, x, y, z);
			points.add(point);
			pointsMap.put(pointID, point);
			lines--;
		}
		
		br.close();

		return points;
	}
	
	// Importe les points de RER
	public ArrayList<Point> getPointsRER() throws IOException {
		ArrayList<Point> points = new ArrayList<Point>();
		System.out.println("IN GETPOINTSRER");
		FileReader filer = new FileReader("objects/pointsRER.csv");
		// NOMBRE DE LIGNES
		int lines = 0;
		BufferedReader reader = new BufferedReader(filer);
		while(reader.readLine()!=null) lines++;
		reader.close();
		// LECTURE DU FICHIER
		FileReader fr = new FileReader("objects/pointsRER.csv");
		BufferedReader br = new BufferedReader(fr);
		br.readLine(); // éliminer la ligne d'entête
		while (lines>1) {
			String[] line = br.readLine().split(",");
			String pointID = line[0];
			String pointName = line[1];
			int x = Integer.parseInt(line[2]);
			int y = Integer.parseInt(line[3]);
			int z = Integer.parseInt(line[4]);
			Point point = new Point(pointName, x, y, z);
			points.add(point);
			pointsMap.put(pointID, point);
			lines--;
		}
		
		br.close();

		return points;
	}
	
	// Importe les chemins autoroute
	public ArrayList<HighwayPath> getHighwayPaths() throws IOException {
		ArrayList<HighwayPath> paths = new ArrayList<HighwayPath>();
		System.out.println("IN GETHIGHWAYPATH");
		FileReader filer = new FileReader("objects/highwayPaths.csv");
		// NOMBRE DE LIGNES
		int lines = 0;
		BufferedReader reader = new BufferedReader(filer);
		while(reader.readLine()!=null) lines++;
		reader.close();
		// LECTURE DU FICHIER
		FileReader fr = new FileReader("objects/highwayPaths.csv");
		BufferedReader br = new BufferedReader(fr);
		br.readLine(); // éliminer la ligne d'entête
		while (lines>1) {
			String[] line = br.readLine().split(",");
			String pointAID = line[0];
			String pointBID = line[1];
			HighwayPath path = new HighwayPath(pointsMap.get(pointAID), pointsMap.get(pointBID));
			HighwayPath pathReturn = new HighwayPath(pointsMap.get(pointBID), pointsMap.get(pointAID));
			paths.add(path);
			paths.add(pathReturn);
			lines--;
		}
		
		br.close();

		return paths;
	}
	
	// Importe les chemins route
	public ArrayList<RoadPath> getRoadPaths() throws IOException {
		ArrayList<RoadPath> paths = new ArrayList<RoadPath>();
		System.out.println("IN GETROADPATH");
		FileReader filer = new FileReader("objects/roadPaths.csv");
		// NOMBRE DE LIGNES
		int lines = 0;
		BufferedReader reader = new BufferedReader(filer);
		while(reader.readLine()!=null) lines++;
		reader.close();
		// LECTURE DU FICHIER
		FileReader fr = new FileReader("objects/roadPaths.csv");
		BufferedReader br = new BufferedReader(fr);
		br.readLine(); // éliminer la ligne d'entête
		while (lines>1) {
			String[] line = br.readLine().split(",");
			String pointAID = line[0];
			String pointBID = line[1];
			RoadPath path = new RoadPath(pointsMap.get(pointAID), pointsMap.get(pointBID));
			RoadPath pathReturn = new RoadPath(pointsMap.get(pointBID), pointsMap.get(pointAID));
			paths.add(path);
			paths.add(pathReturn);
			lines--;
		}
		
		br.close();

		return paths;
	}
	
	// Importe les chemins piétons
	public ArrayList<Path> getFootPaths() throws IOException {
		ArrayList<Path> paths = new ArrayList<Path>();
		System.out.println("IN GETFOOTPATH");
		FileReader filer = new FileReader("objects/footPaths.csv");
		// NOMBRE DE LIGNES
		int lines = 0;
		BufferedReader reader = new BufferedReader(filer);
		while(reader.readLine()!=null) lines++;
		reader.close();
		// LECTURE DU FICHIER
		FileReader fr = new FileReader("objects/footPaths.csv");
		BufferedReader br = new BufferedReader(fr);
		br.readLine(); // éliminer la ligne d'entête
		while (lines>1) {
			String[] line = br.readLine().split(",");
			String pointAID = line[0];
			String pointBID = line[1];
			FootPath path = new FootPath(pointsMap.get(pointAID), pointsMap.get(pointBID));
			//System.out.println(path.toString());
			FootPath pathReturn = new FootPath(pointsMap.get(pointBID), pointsMap.get(pointAID));
			paths.add(path);
			paths.add(pathReturn);
			lines--;
		}
		
		br.close();
		
		/*for (Path p: paths){
			System.out.println(p.toString());
		}*/

		return paths;
	}
	
	/*
	// Importe les chemins cyclistes
		public ArrayList<Path> getBikePaths() throws IOException {
			ArrayList<Path> paths = new ArrayList<Path>();
			System.out.println("IN GETBIKEPATH");
			FileReader filer = new FileReader("objects/bikePaths.csv");
			// NOMBRE DE LIGNES
			int lines = 0;
			BufferedReader reader = new BufferedReader(filer);
			while(reader.readLine()!=null) lines++;
			reader.close();
			// LECTURE DU FICHIER
			FileReader fr = new FileReader("objects/bikePaths.csv");
			BufferedReader br = new BufferedReader(fr);
			br.readLine(); // éliminer la ligne d'entête
			while (lines>1) {
				String[] line = br.readLine().split(",");
				String pointAID = line[0];
				String pointBID = line[1];
				BikePath path = new BikePath(pointsMap.get(pointAID), pointsMap.get(pointBID));
				//System.out.println(path.toString());
				BikePath pathReturn = new BikePath(pointsMap.get(pointBID), pointsMap.get(pointAID));
				paths.add(path);
				paths.add(pathReturn);
				lines--;
			}
			
			br.close();
			
			for (Path p: paths){
				System.out.println(p.toString());
			}

			return paths;
		}
		*/


	// GETTERS DES LISTES D'OBJETS
	
	public ArrayList<EntryPoint> getPointsEntree(){
		return pointsEntree;
	}
	
	public ArrayList<InterestPoint> getPointsInteret(){
		return pointsInteret;
	}
	
	public ArrayList<Point> getAllPoints(){
		return points;
	}
	
	public ArrayList<Path> getUserPaths(){
		return userPaths;
	}
	
	public ArrayList<Path> getCarPaths(){
		return carPaths;
	}
	

}
