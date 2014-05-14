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
		
		int ballspeed = 700;
		int bobspeed = 500;
					
			// First we create our game objects.
			Paddle paddle1 = new Paddle(250,500);
			Paddle paddle2 = new Paddle(250,90);
			Ball ball = new Ball(300,300,4);
			ball.BALL_VELOCITY = ballspeed;
		
			//Create queues.
			PositionQueue pad1 = new PositionQueue();
			PositionQueue pad2 = new PositionQueue();
			PositionQueue ballq = new PositionQueue();
		
			// Create a new AI.
			BobTheAI bob = new BobTheAI(paddle2, ball, pad2);			
			
			// Create a new view.
			GameView view = new GameView(pad1, pad2, ballq);
		
			// Create a new game.
			GameController game = new GameController(paddle1, paddle2, ball, view, pad1, ballq);
		
			// Make the game visible.
			game.pack();
			game.setVisible(true);
		
			Thread ai;
			Thread v;
			Thread con;

			while (true) {
				
				bob.sleepTime = bobspeed;
				ball.BALL_VELOCITY = ballspeed;
			
				ai = new Thread(bob);
				v = new Thread(view);
				con = new Thread(game);
			
				// We need to start our threads.
				ai.start();
				v.start();
				try {
					Thread.sleep(500);
				} catch (Exception e) {}
				con.start();
			
				while (view.gameStatus.get() == 0) {}
				try {
					Thread.sleep(2000);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (view.gameStatus.get() == -1) {break;}
				view.gameStatus.set(0);
				ballspeed = ballspeed + 50;
				if (bobspeed >= 50) {
					bobspeed = bobspeed - 25;
				}
				if (Paddle.PADDLE_X_SIZE > 10) {
					Paddle.PADDLE_X_SIZE -= 5;
				}
				view.level += 1;
				}
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
			if (ballLocation[0] > 155 + radius  && ballLocation[0] < 445 - radius) {
				if (ballLocation[0] < myLocation  - radius - 10 || ballLocation[0] > myLocation + mySize + radius + 10) {
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
			if (ballLocation[0] > 155 + radius && ballLocation[0] < 445 - radius) {
				if (ballLocation[0] < yourLocation  - radius - 10 || ballLocation[0] > yourLocation + yourSize + radius + 10) {
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
