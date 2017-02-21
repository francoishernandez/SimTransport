package Environment;

import Environment.Point;

// Classe représentant les points d'entrée dans l'environnement (le plateau)

public class EntryPoint extends Point {

	private String name;
	
	public EntryPoint(String name, int x, int y, int z) {
		super(x, y, z);
		this.name = name;
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

}
