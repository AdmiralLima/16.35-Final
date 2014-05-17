/**
 * 
 */
package EpicPong.testPackage;

import static org.junit.Assert.*;

import java.awt.Component;
import java.awt.Container;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import EpicPong.GameController;
import EpicPong.GameView;
import EpicPong.GameObjects.Ball;
import EpicPong.GameObjects.Paddle;
import EpicPong.GameObjects.PositionQueue;

/**
 * @author gkravit
 *
 */
public class TestGameController {
	Paddle paddle1;
	Paddle paddle2;
	Ball ball;
	GameView view;
	PositionQueue padq1;
	PositionQueue padq2;
	PositionQueue ballq1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// First we create our game objects.
		paddle1 = new Paddle(250,500);
		paddle2 = new Paddle(250,90);
		ball = new Ball(300,300,4);

		//Create queues.
		padq1 = new PositionQueue();
		padq2 = new PositionQueue();
		ballq1 = new PositionQueue();
		
		view = new GameView(padq1,padq2,ballq1);
	}

	

	/**
	 * Test method for {@link GameController#GameController(EpicPong.GameObjects.Paddle, EpicPong.GameObjects.Paddle, EpicPong.GameObjects.Ball, GameView, EpicPong.GameObjects.PositionQueue, EpicPong.GameObjects.PositionQueue)}.
	 */
	@Test
	public void testGameController() {
		
		GameController gc = new GameController(paddle1,paddle2,ball,view,padq1,ballq1);
		assertEquals("Epic Pong.",gc.getTitle());
		Component[] components = gc.getContentPane().getComponents();
		boolean correct = false;
		for(Component c: components){
			if(c.equals(view)){
				correct = true;
			}
		}
		if(!correct)
			fail("Failure Trace:");
	}

	/**
	 * Test method for {@link GameController#checkDefeat()}.
	 */
	@Test
	public void testCheckDefeat() {
		GameController gc = new GameController(paddle1,paddle2,ball,view,padq1,ballq1);
		int radius = Ball.getSize();
		//ball is not near paddle zone
		boolean b = gc.checkDefeat();
		if(b)
			fail("Failure Trace: ");
		
		//ball y=300
		ball.setPosition(250, 300, 0);
		b= gc.checkDefeat();
		if(b)
			fail("Failure Trace: ");
		
		//ball y>300 x< 155+radius
		ball.setPosition(154+radius,350,0);
		b = gc.checkDefeat();
		if(b)
			fail("Failure Trace: ");
		
		//ball y>300, x>445-radius
		ball.setPosition(446-radius,500,0);
		b = gc.checkDefeat();
		if(b)
			fail("Failure Trace: ");
		
		//ball y>300 x= 155+radius
		ball.setPosition(155+radius,500,0);
		b = gc.checkDefeat();
		if(b)
			fail("Failure Trace: ");

		//ball y>300, x=445-radius
		ball.setPosition(445-radius,500,0);
		b = gc.checkDefeat();
		if(b)
			fail("Failure Trace: ");
		
		//ball y>300 x> 155+radius x < 445-radius
		//pad position > ball position + paddlesize + radius + 10
		//should be true
		ball.setPosition(300,500,0);
		paddle1.setPosition(150,500);
		b = gc.checkDefeat();
		if(!b)
			fail("Failure Trace: ");
		
		//ball y>300 x> 155+radius x < 445-radius
		//pad position < ball position + radius + 10
		//should be true
		ball.setPosition(170,500,0);
		paddle1.setPosition(350,500);
		b = gc.checkDefeat();
		if(!b)
			fail("Failure Trace: ");
		
		//ball y>300 x> 155+radius x < 445-radius
		// ball position + radius + 10 < pad position <ball position + paddlesize + radius + 10
		//should be false
		ball.setPosition(250,500,0);
		paddle1.setPosition(250,500);
		b = gc.checkDefeat();
		if(b)
			fail("Failure Trace: ");
		
	}

	/**
	 * Test method for {@link GameController#checkVictory()}.
	 */
	@Test
	public void testCheckVictory() {
		GameController gc = new GameController(paddle1,paddle2,ball,view,padq1,ballq1);
		int radius = Ball.getSize();
		//ball is not near paddle zone
		boolean b = gc.checkDefeat();
		if(b)
			fail("Failure Trace: ");
		
		//ball y=300
		ball.setPosition(250, 300, 0);
		b= gc.checkVictory();
		if(b)
			fail("Failure Trace: ");
		
		//ball y<300 x< 155+radius
		ball.setPosition(154+radius,90,0);
		b = gc.checkVictory();
		if(b)
			fail("Failure Trace: ");
		
		//ball y<300, x>445-radius
		ball.setPosition(446-radius,90,0);
		b = gc.checkVictory();
		if(b)
			fail("Failure Trace: ");
		
		//ball y<300 x= 155+radius
		ball.setPosition(155+radius,90,0);
		b = gc.checkVictory();
		if(b)
			fail("Failure Trace: ");

		//ball y<300, x=445-radius
		ball.setPosition(445-radius,90,0);
		b = gc.checkVictory();
		if(b)
			fail("Failure Trace: ");
		
		//ball y>300 x> 155+radius x < 445-radius
		//pad position > ball position + paddlesize + radius + 10
		//should be true
		ball.setPosition(325,90,0);
		paddle2.setPosition(150,90);
		b = gc.checkVictory();
		if(!b)
			fail("Failure Trace: ");
		
		//ball y>300 x> 155+radius x < 445-radius
		//pad position < ball position + radius + 10
		//should be true
		ball.setPosition(175,90,0);
		paddle2.setPosition(350,90);
		b = gc.checkVictory();
		if(!b)
			fail("Failure Trace: ");
		
		//ball y>300 x> 155+radius x < 445-radius
		// ball position + radius + 10 < pad position <ball position + paddlesize + radius + 10
		ball.setPosition(250,90,0);
		paddle2.setPosition(250,90);
		b = gc.checkVictory();
		if(b)
			fail("Failure Trace: ");
		
	}

}
