import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

public class GameView extends JPanel{

	Paddle myPaddle;
	Paddle yourPaddle;
	Ball gameBall;
	
	public GameView(Paddle myPaddle, Paddle yourPaddle, Ball gameBall) {
		
		this.myPaddle = myPaddle;
		this.yourPaddle = yourPaddle;
		this.gameBall = gameBall;
		
		setPreferredSize(new Dimension(600,600));
	}
	
	public void paintComponent(Graphics g) {
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
		int[] myLocation = myPaddle.getPosition();
		int[] mySize = myPaddle.getSize();
		g2.draw(new Rectangle2D.Double(myLocation[0],myLocation[1],mySize[0],mySize[1]));
	}
}