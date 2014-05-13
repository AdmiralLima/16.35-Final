import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class GameController extends JFrame{
	
	Paddle myPaddle;
	GameView view;

	public static void main(String[] args) {
		
		// Create a new game.
		GameController game = new GameController();
		
		
		
		// Make the game visible.
		game.pack();
		game.setVisible(true);
	}

	public GameController() {
		
		// Set the window title with the super constructor.
		super("Networked Pong");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		myPaddle = new Paddle(250,500);
		
		view = new GameView(myPaddle, null, null);
		
		addKeyListener(new arrowKeyListener());
		
		Container container = getContentPane();
		container.add(view, BorderLayout.CENTER);
	}
	
	public class arrowKeyListener implements KeyListener {
		
		public void keyTyped(KeyEvent k) {}
		
		public void keyPressed(KeyEvent k) {
			if (k.getKeyCode() == KeyEvent.VK_RIGHT) {
				
				myPaddle.moveRight();
				view.repaint();
				
			} else if (k.getKeyCode() == KeyEvent.VK_LEFT) {
				
				myPaddle.moveLeft();
				view.repaint();
			}
		}

		public void keyReleased(KeyEvent k) {}
	}
}
