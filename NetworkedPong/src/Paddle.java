/*
 * This class represents a player's paddle.
 */
public class Paddle {

	// We store the paddle's location.
	private int xPosition;
	private int yPosition;
	
	// We need to set all paddles' size.
	private final int PADDLE_X_SIZE = 100;
	private final int PADDLE_Y_SIZE = 10;
	
	// We need to set how fast paddles move.
	private final int  PADDLE_VELOCITY = 25;
	
	/*
	 * This constructor simply takes an initial position and creates a new
	 * paddle at that location if the location is within the acceptable
	 * bounds.
	 */
	public Paddle(int xInitial, int yInitial) throws IllegalArgumentException{
		
		// Check to see if the x-axis position is within bounds.
		if (xInitial >= 150 && xInitial <= 350) {
			
			xPosition = xInitial;
		} else {
			
			throw new IllegalArgumentException("Initial horizontal position out of bounds.");
		}
		
		// Check to see if the y-axis position is acceptable.
		if (yInitial == 90 || yInitial == 500) {
			
			yPosition = yInitial;
		} else {
			
			throw new IllegalArgumentException("Initial vertical position out of bounds.");
		}
	}
		
	public void moveRight() {
		
		xPosition += PADDLE_VELOCITY;
		
		if (xPosition > 350) {
			xPosition = 350;
		}	
	}
	public void moveLeft() {
		
		xPosition -= PADDLE_VELOCITY;
		
		if (xPosition < 150) {
			xPosition = 150;
		}
	}
	
	/*
	 * This method returns an array of integers containing the 
	 * x-axis and y-axis positions of the paddle.
	 */
	public int[] getPosition() {
		
		return new int[]{xPosition, yPosition};
	}
	
	/*
	 * This method returns an array of integers containing the 
	 * x-axis and y-axis dimensions of the paddle.
	 */
	public int[] getSize() {
		
		return new int[]{PADDLE_X_SIZE, PADDLE_Y_SIZE};
	}
}
