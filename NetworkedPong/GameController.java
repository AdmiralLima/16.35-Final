import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import GameObjects.Ball;
import GameObjects.Paddle;

public class GameController extends JFrame implements Runnable{
	
	Paddle myPaddle;
	Paddle yourPaddle;
	Ball ball;
	GameView view;
	BobTheAI bob;

	public static void main(String[] args) {
		
		// Create a new game.
		GameController game = new GameController();
		
		// Make the game visible.
		game.pack();
		game.setVisible(true);
		game.run();
		
	}

	public void run() {
		long time = System.currentTimeMillis();
		boolean gameOn = true;
		while (gameOn) {
			try{
				Thread.sleep(5);
			}
			catch (Exception e) {}
			bob.thinkBob();
			long current = System.currentTimeMillis();
			ball.advance(current - time);
			boolean checkForWin = ball.collision();
			if (checkForWin) {
				if (checkDefeat()) {
					gameOn = false;
					view.gameStatus = -1;
				} else if (checkVictory()) {
					gameOn = false;
					view.gameStatus = 1;
				}
			}
			view.repaint();
			time = current;
		}
	}
	
	public GameController() {
		
		// Set the window title with the super constructor.
		super("Networked Pong");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		myPaddle = new Paddle(250,500);
		yourPaddle = new Paddle(250,90);
		ball = new Ball(300,300,2);
		bob = new BobTheAI(yourPaddle, ball);
		
		view = new GameView(myPaddle, yourPaddle, ball);
		
		addKeyListener(new arrowKeyListener());
		
		Container container = getContentPane();
		container.add(view, BorderLayout.CENTER);
	}
	
	public boolean checkDefeat() {
		
		int myLocation = myPaddle.getPosition()[0];
		int mySize = myPaddle.getSize()[0];
		
		int[] ballLocation = ball.getPosition();
		int radius = ball.getSize();
		if (ballLocation[1] > 300) {
			if (ballLocation[0] > 150 + radius && ballLocation[0] < 450 - radius) {
				if (ballLocation[0] < myLocation  - radius || ballLocation[0] > myLocation + mySize + radius) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean checkVictory() {
		
		int yourLocation = yourPaddle.getPosition()[0];
		int yourSize = yourPaddle.getSize()[0];
		
		int[] ballLocation = ball.getPosition();
		int radius = ball.getSize();
		if (ballLocation[1] < 300) {
			if (ballLocation[0] > 150 + radius && ballLocation[0] < 450 - radius) {
				if (ballLocation[0] < yourLocation  - radius || ballLocation[0] > yourLocation + yourSize + radius) {
					return true;
				}
			}
		}
		return false;
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
