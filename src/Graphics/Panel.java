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

public class Panel extends JPanel {
	Graphics bufferGraphics;
	Image offscreen;
	Dimension dim;

	Environment env;
	ArrayList<Person> persons;

	public Panel(Environment env, ArrayList<Person> persons){
		this.env = env;
		this.persons = persons;
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

		bufferGraphics.setColor(Color.WHITE);
		bufferGraphics.fillRect(0, 0, size, size+22);
		
		int rpx = 5;
		double ratio = ((double)test.windowSize) / test.simSize;

		// DESSIN DES CHEMINS

		for (int i=0; i<env.getPaths().size(); i++){
			Path p = env.getPaths().get(i);
			bufferGraphics.setColor(Color.BLACK);
			bufferGraphics.drawLine((int) (p.getA().getX()*ratio), (int) (p.getA().getY()*ratio), (int) (p.getB().getX()*ratio), (int) (p.getB().getY()*ratio));
		}

		// DESSIN DES POINTS

		for (int i=0; i<env.getPoints().size(); i++){
			//Point p = env.getPoints().get(i);
			if(env.getPoints().get(i) instanceof EntryPoint) {
				bufferGraphics.setColor(Color.RED);
				rpx = 5;
				bufferGraphics.fillOval((int)(env.getPoints().get(i).getX()*ratio) - rpx,(int)(env.getPoints().get(i).getY()*ratio) - rpx,2*rpx, 2*rpx);
				bufferGraphics.drawString(env.getPoints().get(i).getName(), (int)(env.getPoints().get(i).getX()*ratio) + 3*rpx, (int)(env.getPoints().get(i).getY()*ratio) + 3*rpx);
			} else if (env.getPoints().get(i) instanceof InterestPoint) {
				bufferGraphics.setColor(Color.BLUE);
				rpx = 4;
				bufferGraphics.fillOval((int)(env.getPoints().get(i).getX()*ratio) - rpx,(int)(env.getPoints().get(i).getY()*ratio) - rpx,2*rpx, 2*rpx);
				bufferGraphics.drawString(env.getPoints().get(i).getName(), (int)(env.getPoints().get(i).getX()*ratio) + 3*rpx, (int)(env.getPoints().get(i).getY()*ratio) + 3*rpx);
			} else {
				bufferGraphics.setColor(Color.BLACK);
				rpx = 3;
				bufferGraphics.fillOval((int)(env.getPoints().get(i).getX()*ratio) - rpx,(int)(env.getPoints().get(i).getY()*ratio) - rpx,2*rpx, 2*rpx);
				bufferGraphics.drawString(env.getPoints().get(i).getName(), (int)(env.getPoints().get(i).getX()*ratio) + 3*rpx, (int)(env.getPoints().get(i).getY()*ratio) + 3*rpx);
			}			
		}
		
		// DESSIN DES PERSONNES
		
		bufferGraphics.setColor(Color.BLACK);
		ImageIcon img = new ImageIcon("images/person.png");
		for (int i=0; i<persons.size(); i++){
			bufferGraphics.drawImage(img.getImage(), 
			(int)(persons.get(i).getLocalisation().getX()*ratio - 10),
			(int)(persons.get(i).getLocalisation().getY()*ratio -10),
			20, 20,
			null);
		}


		g.drawImage(offscreen, 0, 0, this);
		repaint();

	}
}
