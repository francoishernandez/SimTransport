package Graphics;

import java.awt.*;
import java.io.IOException;
import javax.swing.*;
import Environment.*;
import Starter;

public class Window extends JFrame {

	public Window() throws IOException {
		this.gui();
	}
	
	public void gui() throws IOException {
		this.setTitle("SimTransport");
		
		this.setVisible(true);
		
		this.setPreferredSize(new Dimension(test.windowSize,test.windowSize));
		
		this.setResizable(false);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.pack();
	}
}
