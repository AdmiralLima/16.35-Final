import GameObjects.Ball;
import GameObjects.Paddle;


public class BobTheAI {

	Paddle paddle;
	Ball ball;
	
	public BobTheAI(Paddle paddle,Ball ball) {
		
		this.paddle = paddle;
		this.ball = ball;
	}
	
	public void thinkBob() {
		int ballPosition = ball.getPosition()[0];
		int ballSize = ball.getSize();
		int paddlePosition = paddle.getPosition()[0];
		int mySize = paddle.getPosition()[0];
		
		if (ballPosition > paddlePosition + mySize) {
			paddle.moveRight();
		}
		
		if(ballPosition < paddlePosition) {
			paddle.moveLeft();
		}
	}
}
