import GameObjects.Ball;
import GameObjects.Paddle;
import GameObjects.Position;
import GameObjects.PositionQueue;

/*
 * This class provides a simple AI player for the game.
 */
public class BobTheAI implements Runnable{

	private Paddle paddle;
	private Ball ball;
	private PositionQueue q;
	public int sleepTime = 100;
	
	public BobTheAI(Paddle paddle,Ball ball, PositionQueue queue) {
		
		this.paddle = paddle;
		this.ball = ball;
		this.q = queue;
	}
	
	public void run() {
		while (true) {
		// Bob needs his rest.
		try {
			Thread.sleep(sleepTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Bob needs info on his paddle.
		int paddlePosition = paddle.getPosition()[0];
		int mySize = Paddle.getSize()[0];
		
		// Bob needs info on the ball's location.
		int[] ballPosition = ball.getPosition();
		
			if (ballPosition[0]> paddlePosition + (mySize * 0.75)) {
				paddle.moveRight();
			} 
			if (ballPosition[0]< paddlePosition + (mySize * 0.25)) {
				paddle.moveLeft();
			}
		
		q.offer(new Position(paddle.getPosition()));
		}
	}
}
