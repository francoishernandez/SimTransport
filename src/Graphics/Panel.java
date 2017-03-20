package Graphics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import Environment.*;
import Environment.Paths.*;
import Environment.Points.EntryPoint;
import Environment.Points.InterestPoint;
import Individuals.Person;
import Individuals.PersonState;

// La classe Panel contient les méthodes d'affichage des différents éléments de la simulation
// Elle sera ajoutée à une instance de la classe Window lors de la création de l'agent Starter

public class Panel extends JPanel {
	Graphics bufferGraphics;
	Image offscreen;
	Dimension dim;
	int windowSize;
	int simSize;

	Environment env; // environnement en cours
	ArrayList<Person> persons; // personnes évoluant dans l'environnement
	Clock clock;

	// le constructeur permet de récupérer l'environnement et les personnes à son appel lors de la création de l'agent Starter
	public Panel(Environment env, ArrayList<Person> persons, Clock c, int windowSize, int simSize){ 
		this.env = env;
		this.persons = persons;
		this.clock = c;
		this.windowSize = windowSize;
		this.simSize = simSize;
	}

	public Panel(Environment env, int windowSize, int simSize){ 
		this.env = env;
		this.windowSize = windowSize;
		this.simSize = simSize;
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
		double ratio = ((double) windowSize) / simSize;

		// AFFICHAGE DU BACKGROUND

		ImageIcon bkg = new ImageIcon("images/background.png"); // chargement de l'icone représentant une personne
		bufferGraphics.drawImage(bkg.getImage(), 
				0,
				0,
				windowSize,windowSize,
				null);

		// Ajout d'un alpha
		int alpha = 127; // 50% transparent
		Color myColor = new Color(255, 255, 255, alpha);
		bufferGraphics.setColor(myColor);
		bufferGraphics.fillRect(0, 0, size, size);


		// DESSIN DES CHEMINS
		
		Stroke defaultStroke = new BasicStroke(0);

		for (int i=0; i<env.getPaths().size(); i++){ // On parcourt la liste des chemins de l'environnement
			Path p = env.getPaths().get(i);
			
			// Tracé de l'utilisation des routes (voitures) en premier car le chemin est tracé par dessus
			if(p instanceof RoadPath){
				int density = p.currentDensity();
				((Graphics2D) bufferGraphics).setStroke(new BasicStroke(density/10));
				double rapport = ((double)density)/((double)((RoadPath)p).getCriticalDensity());
				if (rapport<1){
					bufferGraphics.setColor(Color.GREEN);
				} else if (rapport<1.5){
					bufferGraphics.setColor(Color.ORANGE);
				} else {
					bufferGraphics.setColor(Color.RED);
				}
				bufferGraphics.drawLine((int) (p.getA().getX()*ratio), 
						(int) (p.getA().getY()*ratio), 
						(int) (p.getB().getX()*ratio), 
						(int) (p.getB().getY()*ratio));
			}
			
			if(p instanceof HighwayPath){
				int density = p.currentDensity();
				((Graphics2D) bufferGraphics).setStroke(new BasicStroke(density/10));
				double rapport = ((double)density)/((double)((HighwayPath)p).getCriticalDensity());
				if (rapport<1){
					bufferGraphics.setColor(Color.GREEN);
				} else if (rapport<1.5){
					bufferGraphics.setColor(Color.ORANGE);
				} else {
					bufferGraphics.setColor(Color.RED);
				}
				bufferGraphics.drawLine((int) (p.getA().getX()*ratio), 
						(int) (p.getA().getY()*ratio), 
						(int) (p.getB().getX()*ratio), 
						(int) (p.getB().getY()*ratio));
			}
			
			// Tracé du chemin à proprement parler
			if (!(p instanceof EntryPath)) { // On ne trace pas les EntryPath (abstraits)
				// différentes couleurs suivant les types
				if(p instanceof RoadPath) { 
					bufferGraphics.setColor(Color.BLACK);
					((Graphics2D) bufferGraphics).setStroke(defaultStroke);
				} else if(p instanceof HighwayPath) { 
					bufferGraphics.setColor(Color.BLACK);
					((Graphics2D) bufferGraphics).setStroke(new BasicStroke(3));
				} else if(p instanceof RerPath) { 
					bufferGraphics.setColor(Color.BLUE);
					((Graphics2D) bufferGraphics).setStroke(new BasicStroke(4));
				} else if(p instanceof BusPath) { 
					bufferGraphics.setColor(new Color(0, 0, 1, 0.1f));
					((Graphics2D) bufferGraphics).setStroke(new BasicStroke(8));
				} else if(p instanceof FootPath) { 
					bufferGraphics.setColor(Color.GREEN);
					((Graphics2D) bufferGraphics).setStroke(defaultStroke);
				} else { bufferGraphics.setColor(Color.BLACK); ((Graphics2D) bufferGraphics).setStroke(defaultStroke);}
				// dessin du chemin de A vers B pour chaque chemin
				bufferGraphics.drawLine((int) (p.getA().getX()*ratio), 
						(int) (p.getA().getY()*ratio), 
						(int) (p.getB().getX()*ratio), 
						(int) (p.getB().getY()*ratio));
			}
		}

		// DESSIN DES POINTS

		for (int i=0; i<env.getPoints().size(); i++){ // On parcourt la liste des points de l'environnement
			//Point p = env.getPoints().get(i);
			if(env.getPoints().get(i) instanceof EntryPoint) { // il s'agit d'un point d'entrée
				bufferGraphics.setColor(Color.MAGENTA);
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
				bufferGraphics.setColor(Color.RED);
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

		if(persons !=null){
			bufferGraphics.setColor(Color.BLACK);
			ImageIcon img = new ImageIcon("images/person.png"); // chargement de l'icone représentant une personne
			for (int i=0; i<persons.size(); i++){ // parcours de la liste des personnes évoluant dans l'environnement
				if (persons.get(i).getPersonState()==PersonState.moving){
					bufferGraphics.drawImage(img.getImage(), 
							(int)(persons.get(i).getLocalisation().getX()*ratio - 10),
							(int)(persons.get(i).getLocalisation().getY()*ratio -10),
							20, 20,
							null);
				}
			}
		}

		// AFFICHAGE DE L'HORLOGE

		if(clock!=null){
			int clockWidth = 1600;
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
		}

		g.drawImage(offscreen, 0, 0, this);
		repaint();

	}
}
