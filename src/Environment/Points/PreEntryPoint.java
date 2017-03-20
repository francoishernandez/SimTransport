package Environment.Points;

import Environment.Points.Point;

// Classe représentant les points de pré-entrée.
// Ces points servent à distribuer les utilisateurs sur différents points d'entrée.
// Par exemple, une personne venant du sud ouest peut entrer sur le point RER sud-ouest
// ou sur la D306 suivant qu'il vient en voiture ou en transport en commun.

public class PreEntryPoint extends Point {
	
	public PreEntryPoint(String name) {
		super(name, 11000, 11000, 0);
		// Ces points sont des points abstraits, et sont donc placés à l'extérieur
		// du cadre affiché pour éviter tout conflit d'affichage
	}
	
}