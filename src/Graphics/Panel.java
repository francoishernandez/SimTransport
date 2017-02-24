package Graphics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import Environment.*;
import Environment.Point;
import Environment.test;
import Individuals.Person;

// La classe Panel contient les méthodes d'affichage des différents éléments de la simulation
// Elle sera ajoutée à une instance de la classe Window lors de la création de l'agent Starter

public class Panel extends JPanel {
	Graphics bufferGraphics;
	Image offscreen;
	Dimension dim;

	Environment env; // environnement en cours
	ArrayList<Person> persons; // personnes évoluant dans l'environnement
	Clock clock;
	
	// le constructeur permet de récupérer l'environnement et les personnes à son appel lors de la création de l'agent Starter
	public Panel(Environment env, ArrayList<Person> persons, Clock c){ 
		this.env = env;
		this.persons = persons;
		this.clock = c;
	}

	public void init(){
		dim = getSize();
		offscreen = createImage(dim.width, dim.height);
		bufferGraphics = offscreen.getGraphics();
	}

	@Override
	public void update(Graphics g){
		paintComponent(g);
	}

	@Override
	public void paintComponent(Graphics g){
		init();

		int size = dim.width;

		// Ajout d'un fond blanc
		bufferGraphics.setColor(Color.WHITE);
		bufferGraphics.fillRect(0, 0, size, size);
		
		int rpx = 5;
		double ratio = ((double)test.windowSize) / test.simSize;

		// DESSIN DES CHEMINS

		for (int i=0; i<env.getPaths().size(); i++){ // On parcourt la liste des chemins de l'environnement
			Path p = env.getPaths().get(i);
			bufferGraphics.setColor(Color.BLACK);
			// dessin du chemin de A vers B pour chaque chemin
			bufferGraphics.drawLine((int) (p.getA().getX()*ratio), 
					(int) (p.getA().getY()*ratio), 
					(int) (p.getB().getX()*ratio), 
					(int) (p.getB().getY()*ratio));
		}

		// DESSIN DES POINTS

		for (int i=0; i<env.getPoints().size(); i++){ // On parcourt la liste des points de l'environnement
			//Point p = env.getPoints().get(i);
			if(env.getPoints().get(i) instanceof EntryPoint) { // il s'agit d'un point d'entrée
				bufferGraphics.setColor(Color.RED);
				rpx = 5;
				// dessin du point, centré sur la position
				bufferGraphics.fillOval((int)(env.getPoints().get(i).getX()*ratio) - rpx,
						(int)(env.getPoints().get(i).getY()*ratio) - rpx,
						2*rpx, 2*rpx);
				// affichage du nom du point
				bufferGraphics.drawString(env.getPoints().get(i).getName(), 
						(int)(env.getPoints().get(i).getX()*ratio) + 3*rpx, 
						(int)(env.getPoints().get(i).getY()*ratio) + 3*rpx);
			} else if (env.getPoints().get(i) instanceof InterestPoint) { // il s'agit d'un point d'intérêt
				bufferGraphics.setColor(Color.BLUE);
				rpx = 4;
				// dessin du point, centré sur la position
				bufferGraphics.fillOval((int)(env.getPoints().get(i).getX()*ratio) - rpx,
						(int)(env.getPoints().get(i).getY()*ratio) - rpx,
						2*rpx, 2*rpx);
				// affichage du nom du point
				bufferGraphics.drawString(env.getPoints().get(i).getName(), 
						(int)(env.getPoints().get(i).getX()*ratio) + 3*rpx, 
						(int)(env.getPoints().get(i).getY()*ratio) + 3*rpx);
			} else { // il s'agit d'un point 'classique' sans signification particulière (intersection de routes, contournement, etc.)
				bufferGraphics.setColor(Color.BLACK);
				rpx = 3;
				// dessin du point, centré sur la position
				bufferGraphics.fillOval((int)(env.getPoints().get(i).getX()*ratio) - rpx,
						(int)(env.getPoints().get(i).getY()*ratio) - rpx,
						2*rpx, 2*rpx);
				//bufferGraphics.drawString(env.getPoints().get(i).getName(), (int)(env.getPoints().get(i).getX()*ratio) + 3*rpx, (int)(env.getPoints().get(i).getY()*ratio) + 3*rpx);
			}			
		}
		
		// DESSIN DES PERSONNES
		
		bufferGraphics.setColor(Color.BLACK);
		ImageIcon img = new ImageIcon("images/person.png"); // chargement de l'icone représentant une personne
		for (int i=0; i<persons.size(); i++){ // parcours de la liste des personnes évoluant dans l'environnement
			bufferGraphics.drawImage(img.getImage(), 
			(int)(persons.get(i).getLocalisation().getX()*ratio - 10),
			(int)(persons.get(i).getLocalisation().getY()*ratio -10),
			20, 20,
			null);
		}
		
		// AFFICHAGE DE L'HORLOGE
		
		int clockWidth = 1200;
		int clockHeight = 500;
		Font myFont = new Font ("Courier New", 1, 20);
		Font defaultFont = bufferGraphics.getFont();
		bufferGraphics.drawRect(0,0, (int)(clockWidth*ratio), (int)(clockHeight*ratio));
		bufferGraphics.setFont(myFont);
		FontMetrics metrics = bufferGraphics.getFontMetrics(myFont);
		String currentTime = clock.getCurrentTime().toString();
		bufferGraphics.drawString(currentTime,
				(int)(clockWidth*ratio/2 - metrics.stringWidth(currentTime)/2),
				(int)(clockHeight*ratio/2) - metrics.getHeight()/2 + metrics.getAscent());
		bufferGraphics.setFont(defaultFont);
		
		g.drawImage(offscreen, 0, 0, this);
		repaint();

	}
}
