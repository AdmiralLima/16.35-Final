import java.awt.*;
import java.awt.geom.*;

import javax.swing.*;

import GameObjects.*;
/*
 * This class provides 
 */
public class GameView extends JPanel implements Runnable{

	private PositionQueue myPaddle;
	private int[] myLocation = new int[2];
	private PositionQueue yourPaddle;
	private int[] yourLocation = new int[2];
	private PositionQueue gameBall;
	private int[] ballLocation = new int[2];
	
	private int[] paddleSize = Paddle.getSize();
	private int radius = Ball.getSize();
	
	public int gameStatus = 0;
	
	public GameView(PositionQueue myPaddle, PositionQueue yourPaddle, PositionQueue gameBall) {
		
		this.myPaddle = myPaddle;
		this.yourPaddle = yourPaddle;
		this.gameBall = gameBall;
		
		setPreferredSize(new Dimension(600,600));
	}
	
	
	public void run(){
		while(gameStatus == 0){
			myLocation = getPosition(myPaddle,myLocation);
			yourLocation = getPosition(yourPaddle,yourLocation);
			ballLocation = getPosition(gameBall, ballLocation);
			this.repaint();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void paintComponent(Graphics g) {
		
		if (gameStatus == 0) {
			
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

			// We need to draw both paddles.
			g2.setPaint( Color.green);
			//draw player paddle
			g2.draw(new Rectangle2D.Double(myLocation[0],myLocation[1],paddleSize[0],paddleSize[1]));
			//draw AI paddle
			g2.draw(new Rectangle2D.Double(yourLocation[0],yourLocation[1],paddleSize[0],paddleSize[1]));
		
			// We need to draw the ball.
			g2.setPaint(Color.cyan);
			g2.fill(new Ellipse2D.Double(ballLocation[0] - radius, ballLocation[1] - radius,2 * radius,2 * radius));
		} else if (gameStatus == -1) {
			
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
			g2.drawString("Epic Defeat", 150, 300);
		} else if (gameStatus == 1) {
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
			g2.drawString("Too Easy", 185, 300);
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
		
}
