/*
 * This class represents a player's paddle.
 */
public class Paddle {

	// We need to set all paddles' size.
	private final int PADDLE_X_SIZE = 100;
	private final int PADDLE_Y_SIZE = 10;
	
	// We need to set how many pixels per click we 
	// want the paddle to move.
	private final int  PADDLE_VELOCITY = 25;
	
	// We need to set limits on the horizontal
	// position of the paddle.
	private final int MIN_X = 150;
	private final int MAX_X = 350;
	
	// We need to set the two possible vertical
	// positions of the paddle.
	private final int LOWER_Y = 90;
	private final int UPPER_Y = 500;
	
	// We store the paddle's location.
	private int xPosition;
	private int yPosition;
	
	/*
	 * This constructor simply takes an initial position and creates a new
	 * paddle at that location if the location is within the acceptable
	 * bounds.
	 */
	public Paddle(int xInitial, int yInitial) throws IllegalArgumentException{
		
		// Check to see if the x-axis position is within bounds.
		if (xInitial >= MIN_X && xInitial <= MAX_X) {
			xPosition = xInitial;
		} else {
			throw new IllegalArgumentException("Initial horizontal position out of bounds.");
		}
		
		// Check to see if the y-axis position is acceptable.
		if (yInitial == LOWER_Y || yInitial == UPPER_Y) {
			yPosition = yInitial;
		} else {
			throw new IllegalArgumentException("Initial vertical position out of bounds.");
		}
	}
		
	/*
	 * This method moves the paddle to the right 
	 */
	public void moveRight() {
		xPosition += PADDLE_VELOCITY;
		
		// We might need to snap to an acceptable position.
		if (xPosition > MAX_X) {
			xPosition = MAX_X;
		}	
	}
	public void moveLeft() {
		xPosition -= PADDLE_VELOCITY;
		
		// We might need to snap to an acceptable position.
		if (xPosition < MIN_X) {
			xPosition = MIN_X;
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
