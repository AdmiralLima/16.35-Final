import java.awt.*;
import java.awt.geom.*;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.*;

import GameObjects.Ball;
import GameObjects.Paddle;
import GameObjects.Position;
import GameObjects.PositionQueue;

/*
 * This class provides a GUI for the project.
 */
public class GameView extends JPanel implements Runnable{

	private PositionQueue myPaddle;
	private int[] myLocation = new int[2];
	private PositionQueue yourPaddle;
	private int[] yourLocation = new int[2];
	
	//private Vector<PositionQueue> balls;
	private PositionQueue gameBall;
	private int[] ballLocation = new int[2];
	
	private int[] paddleSize = Paddle.getSize();
	private int radius = Ball.getSize();
	
	public int level = 1;
	
	private int[] lastPaddle1;
	private int[] lastPaddle2;
	private int[] lastball;
	
	/* Shared Resource */
	public AtomicInteger gameStatus = new AtomicInteger(0);
	
	public GameView(PositionQueue myPaddle, PositionQueue yourPaddle, PositionQueue gameBall) {
		
		this.myPaddle = myPaddle;
		this.yourPaddle = yourPaddle;
		this.gameBall = gameBall;
		
		setPreferredSize(new Dimension(600,600));
	}
	
	
	public void run(){
		while(true){
			myLocation = getPosition(myPaddle,myLocation);
			yourLocation = getPosition(yourPaddle,yourLocation);
			ballLocation = getPosition(gameBall, ballLocation);
			this.repaint();			
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (gameStatus.get() == 0) {
				lastPaddle1 = myLocation;
				lastPaddle2 = yourLocation;
				lastball = ballLocation;
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		
		if (gameStatus.get() == 0) {
			
			super.paintComponent( g );
			Graphics2D g2= (Graphics2D) g;
			setBackground(Color.black);
			g2.setStroke(new BasicStroke(2));

			// We need to draw our walls.
			g2.setPaint( Color.blue);
			g2.fill(new Rectangle2D.Double(90,90,10,420));
			g2.fill(new Rectangle2D.Double(500,90,10,420));
			g2.fill(new Rectangle2D.Double(100,90,50,10));
			g2.fill(new Rectangle2D.Double(100,500,50,10));
			g2.fill(new Rectangle2D.Double(450,90,50,10));
			g2.fill(new Rectangle2D.Double(450,500,50,10));

			g2.setPaint( Color.green);
			g2.setFont(new Font("TimesRoman", Font.BOLD, 50)); 
			g2.drawString("Level " + level, 210, 300);
			
			// We need to draw both paddles.
			g2.setPaint( Color.green);
			//draw player paddle
			g2.draw(new Rectangle2D.Double(myLocation[0],myLocation[1],paddleSize[0],paddleSize[1]));
			//draw AI paddle
			g2.draw(new Rectangle2D.Double(yourLocation[0],yourLocation[1],paddleSize[0],paddleSize[1]));
		
			// We need to draw the ball.
			g2.setPaint(Color.cyan);
			g2.fill(new Ellipse2D.Double(ballLocation[0] - radius, ballLocation[1] - radius,2 * radius,2 * radius));
		} else if (gameStatus.get() == -1) {
			
			super.paintComponent( g );
			Graphics2D g2= (Graphics2D) g;
			setBackground(Color.red);
			g2.setStroke(new BasicStroke(2));

			// We need to draw our walls.
			g2.setPaint( Color.blue);
			g2.fill(new Rectangle2D.Double(90,90,10,420));
			g2.fill(new Rectangle2D.Double(500,90,10,420));
			g2.fill(new Rectangle2D.Double(100,90,50,10));
			g2.fill(new Rectangle2D.Double(100,500,50,10));
			g2.fill(new Rectangle2D.Double(450,90,50,10));
			g2.fill(new Rectangle2D.Double(450,500,50,10));

			// We need to post the defeat message.
			g2.setPaint( Color.black);
			g2.setFont(new Font("TimesRoman", Font.BOLD, 50)); 
			g2.drawString("Epic Defeat", 150, 200);
			g2.setFont(new Font("TimesRoman", Font.BOLD, 25)); 
			g2.drawString("Find a different job.", 170, 300);
			
			// We need to draw both paddles.
			g2.setPaint( Color.black);
			//draw player paddle
			g2.draw(new Rectangle2D.Double(lastPaddle1[0],lastPaddle1[1],paddleSize[0],paddleSize[1]));
			//draw AI paddle
			g2.draw(new Rectangle2D.Double(lastPaddle2[0],lastPaddle2[1],paddleSize[0],paddleSize[1]));
					
			// We need to draw the ball.
			g2.fill(new Ellipse2D.Double(lastball[0] - radius, lastball[1] - radius,2 * radius,2 * radius));
			
		} else if (gameStatus.get() == 1) {
			super.paintComponent( g );
			Graphics2D g2= (Graphics2D) g;
			setBackground(Color.green);
			g2.setStroke(new BasicStroke(2));

			// We need to draw our walls.
			g2.setPaint( Color.blue);
			g2.fill(new Rectangle2D.Double(90,90,10,420));
			g2.fill(new Rectangle2D.Double(500,90,10,420));
			g2.fill(new Rectangle2D.Double(100,90,50,10));
			g2.fill(new Rectangle2D.Double(100,500,50,10));
			g2.fill(new Rectangle2D.Double(450,90,50,10));
			g2.fill(new Rectangle2D.Double(450,500,50,10));

			// We need to post the defeat message.
			g2.setPaint( Color.black);
			g2.setFont(new Font("TimesRoman", Font.BOLD, 50)); 
			g2.drawString("Too Easy", 185, 200);
			g2.setFont(new Font("TimesRoman", Font.BOLD, 25)); 
			g2.drawString("I feel my power growing!", 140, 300);
			
			// We need to draw both paddles.
			g2.setPaint( Color.black);
			//draw player paddle
			g2.draw(new Rectangle2D.Double(lastPaddle1[0],lastPaddle1[1],paddleSize[0],paddleSize[1]));
			//draw AI paddle
			g2.draw(new Rectangle2D.Double(lastPaddle2[0],lastPaddle2[1],paddleSize[0],paddleSize[1]));
					
			// We need to draw the ball.
			g2.fill(new Ellipse2D.Double(lastball[0] - radius, lastball[1] - radius,2 * radius,2 * radius));

		}
	}
	
	public static int[] getPosition(PositionQueue queue, int[] prevPosition){
		Position p = queue.poll();
		if (p == null)
			return prevPosition;
		else{
			return p.getPosition();
		}
	}
	public void updateGameStatus(int status) {
		
		gameStatus.set(status);
	}
}
