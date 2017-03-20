package Graphics;

import java.awt.*;
import java.io.IOException;
import javax.swing.*;
import Environment.*;
import mainPackage.test;

// La classe Window correspond à la fenêtre où s'afficheront les représentation graphiques de la simulation, dessinées dans un Panel
// Elle est instanciée au lancement de l'agent Starter

public class Window extends JFrame {

	public Window() throws IOException {
		this.gui();
	}
	
	public void gui() throws IOException {
		this.setTitle("SimTransport");
		
		this.setVisible(true);
		
		this.setPreferredSize(new Dimension(test.windowSize,test.windowSize+22));
		
		this.setResizable(false);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.pack();
	}
}
