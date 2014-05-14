import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import GameObjects.Ball;
import GameObjects.Paddle;
import GameObjects.Position;
import GameObjects.PositionQueue;

public class GameController extends JFrame implements Runnable{
	
	Paddle myPaddle;
	Paddle yourPaddle;
	Ball ball;
	GameView view;
	PositionQueue padq;
	PositionQueue ballq;

	public static void main(String[] args) {
		
		// First we create our game objects.
		Paddle paddle1 = new Paddle(250,500);
		Paddle paddle2 = new Paddle(250,90);
		Ball ball = new Ball(300,300,4);
		
		//Create queues.
		PositionQueue pad1 = new PositionQueue();
		PositionQueue pad2 = new PositionQueue();
		PositionQueue ballq = new PositionQueue();
		
		// Create a new AI.
		BobTheAI bob = new BobTheAI(paddle2, ball, pad2);
		Thread ai = new Thread(bob);
		
		// Create a new view.
		GameView view = new GameView(pad1, pad2, ballq);
		Thread v = new Thread(view);
		
		// Create a new game.
		GameController game = new GameController(paddle1, paddle2, ball, view, pad1, ballq);
		Thread con = new Thread(game);
		
		// Make the game visible.
		game.pack();
		game.setVisible(true);
		
		// We need to start our threads.
		con.start();
		ai.start();
		try {
		Thread.sleep(50);
		} catch (Exception e) {}
		v.start();
	}

	public void run() {
		long time = System.currentTimeMillis();
		
		while(true) {
		
			try{
				Thread.sleep(20);
			} catch (Exception e) {}
		
			long current = System.currentTimeMillis();
				ball.advance(current - time);
				if (ball.collision()) {
					if (checkVictory()) {
						view.updateGameStatus(1);
						break;
					}
					if (checkDefeat()) {
						view.updateGameStatus(-1);
						break;
					}
				}
				padq.offer(new Position(myPaddle.getPosition()));
				ballq.offer(new Position(ball.getPosition()));
				time = current;
		}
		
	}
	
	public GameController(Paddle pad1, Paddle pad2, Ball ball, GameView gView, PositionQueue pq, PositionQueue bq) {
		
		// Set the window title with the super constructor.
		super("Epic Pong.");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		addKeyListener(new arrowKeyListener());
		
		myPaddle = pad1;
		yourPaddle = pad2;
		this.ball = ball;
		view = gView;
		padq = pq;
		ballq = bq;
		
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
