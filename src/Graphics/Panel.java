package Graphics;

import java.awt.*;
import java.io.IOException;
import javax.swing.*;
import Environment.*;
import Environment.Point;
import Environment.test;

public class Panel extends JPanel {
	Graphics bufferGraphics;
	Image offscreen;
	Dimension dim;

	Environment env;

	public Panel(Environment env){
		this.env = env;
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

		bufferGraphics.setColor(Color.BLACK);
		for (int i=0; i<env.getPoints().size(); i++){
			//Point p = env.getPoints().get(i);
			if(env.getPoints().get(i) instanceof EntryPoint) {
				bufferGraphics.setColor(Color.RED);
				rpx = 5;
				bufferGraphics.fillOval((int)(env.getPoints().get(i).getX()*ratio) - rpx,(int)(env.getPoints().get(i).getY()*ratio) - rpx,2*rpx, 2*rpx);

			} else if (env.getPoints().get(i) instanceof InterestPoint) {
				bufferGraphics.setColor(Color.BLUE);
				rpx = 4;
				bufferGraphics.fillOval((int)(env.getPoints().get(i).getX()*ratio) - rpx,(int)(env.getPoints().get(i).getY()*ratio) - rpx,2*rpx, 2*rpx);

			} else {
				bufferGraphics.setColor(Color.BLACK);
				rpx = 3;
				bufferGraphics.fillOval((int)(env.getPoints().get(i).getX()*ratio) - rpx,(int)(env.getPoints().get(i).getY()*ratio) - rpx,2*rpx, 2*rpx);
			}			
		}



		g.drawImage(offscreen, 0, 0, this);
		repaint();

	}
}
