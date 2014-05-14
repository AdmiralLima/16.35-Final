package GameObjects;

/*
 * This class represents a player's paddle.
 */
public class Paddle {

	// We need to set all paddles' size.
	private static final int PADDLE_X_SIZE = 100;
	private static final int PADDLE_Y_SIZE = 10;
	
	// We need to set how many pixels per click we 
	// want the paddle to move.
	private final int  PADDLE_VELOCITY = 50;
	
	// We need to set limits on the horizontal
	// position of the paddle.
	private final int PADDLE_MIN_X = 150;
	private final int PADDLE_MAX_X = 350;
	
	// We need to set the two possible vertical
	// positions of the paddle.
	private final int PADDLE_LOWER_Y = 90;
	private final int PADDLE_UPPER_Y = 500;
	
	// We store the paddle's location.
	/* Shared Resource */
	private int xPosition;
	private int yPosition;
	
	/*
	 * This constructor simply takes an initial position and creates a new
	 * paddle at that location if the location is within the acceptable
	 * bounds.
	 */
	public Paddle(int xInitial, int yInitial) throws IllegalArgumentException{
		
		// Check to see if the x-axis position is within bounds.
		if (xInitial >= PADDLE_MIN_X && xInitial <= PADDLE_MAX_X) {
			xPosition = xInitial;
		} else {
			throw new IllegalArgumentException("Initial horizontal position out of bounds.");
		}
		
		// Check to see if the y-axis position is acceptable.
		if (yInitial == PADDLE_LOWER_Y || yInitial == PADDLE_UPPER_Y) {
			yPosition = yInitial;
		} else {
			throw new IllegalArgumentException("Initial vertical position out of bounds.");
		}
	}
		
	/*
	 * This method moves the paddle to the right at the
	 * set number of pixels per click.
	 */
	public void moveRight() {
		
		/* Critical Region */
		synchronized (this) {
			xPosition += PADDLE_VELOCITY;
		
			// We might need to snap to an acceptable position.
			if (xPosition > PADDLE_MAX_X) {
				xPosition = PADDLE_MAX_X;
			}	
		}
	}
	
	/*
	 * This method moves the paddle to the left at the
	 * set number of pixels per click.
	 */
	public void moveLeft() {
		
		/* Critical Region */
		synchronized (this) {
			xPosition -= PADDLE_VELOCITY;
		
			// We might need to snap to an acceptable position.
			if (xPosition < PADDLE_MIN_X) {
				xPosition = PADDLE_MIN_X;
			}
		}
	}
	
	/*
	 * This method sets the position of the paddle if the given horizontal
	 * and vertical positions are in the acceptable range.
	 */
	public synchronized void setPosition(int newX, int newY) throws IllegalArgumentException {
		
		// Check to see if the x-axis position is within bounds.
		if (newX >= PADDLE_MIN_X && newX <= PADDLE_MAX_X) {
			
			/* Critical Region */
			synchronized (this) {
				xPosition = newX;
			}
		} else {
			throw new IllegalArgumentException("Initial horizontal position out of bounds.");
		}
		
		// Check to see if the y-axis position is acceptable.
		if (newY == PADDLE_LOWER_Y || newY == PADDLE_UPPER_Y) {
			
			/* Critical Region */
			synchronized (this) {
				yPosition = newY;
			}
		} else {
			throw new IllegalArgumentException("Initial vertical position out of bounds.");
		}
	}
	
	/*
	 * This method returns an array of integers containing the 
	 * x-axis and y-axis positions of the paddle.
	 */
	public int[] getPosition() {
		
		/* Critical Region */
		synchronized (this) {
			return new int[]{xPosition, yPosition};
		}
	}
	
	/*
	 * This method returns an array of integers containing the 
	 * x-axis and y-axis dimensions of the paddle.
	 */
	public static int[] getSize() {
		return new int[]{PADDLE_X_SIZE, PADDLE_Y_SIZE};
	}
}
