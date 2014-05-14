import GameObjects.Ball;
import GameObjects.Paddle;
import GameObjects.Position;
import GameObjects.PositionQueue;

/*
 * This class provides a simple AI player for the game.
 */
public class BobTheAI implements Runnable{

	Paddle paddle;
	Ball ball;
	PositionQueue q;
	
	public BobTheAI(Paddle paddle,Ball ball, PositionQueue queue) {
		
		this.paddle = paddle;
		this.ball = ball;
		this.q = queue;
	}
	
	public void run() {
		while (true) {
		// Bob needs his rest.
		try {
			Thread.sleep(200);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Bob needs info on his paddle.
		int paddlePosition = paddle.getPosition()[0];
		int mySize = Paddle.getSize()[0];
		
		// Bob needs info on the ball's location.
		int[] ballPosition = ball.getPosition();
		
		if (ballPosition[1] < 300) {
			if (ballPosition[0]> paddlePosition + mySize) {
				paddle.moveRight();
			} 
			if (ballPosition[0]< paddlePosition) {
				paddle.moveLeft();
			}
		}
		
		q.offer(new Position(paddle.getPosition()));
		}
	}
}
