/*
 * This class represents a game ball.
 */
public class Ball {

	// We store the ball's location.
	private int xPosition;
	private int yPosition;
	
	// We store the ball's velocity.
	private int xVelocity;
	private int yVelocity;
	
	// We store the ball's size.
	private int radius;
	
	/*
	 * This constructor simply takes an initial position and creates a 
	 * new paddle at that location if the given position is within the
	 * acceptable range.
	 */
	public Ball(int xInitial, int yInitial) throws IllegalArgumentException {
		
		// We initialize our internal variables.
		xVelocity = 0;
		yVelocity = 0;
		radius = 25;
		
		// Check to see if the x-axis position is within bounds.
		if (xInitial >= 100 + radius && xInitial <= 500 - radius) {
			
			xPosition = xInitial;
		} else {
			
			throw new IllegalArgumentException("Initial horizontal position out of bounds.");
		}
		
		// Check to see if the y-axis position is acceptable.
		if (yInitial >= 100 + radius && yInitial <= 500 - radius ) {
			
			yPosition = yInitial;
		} else {
			
			throw new IllegalArgumentException("Initial vertical position out of bounds.");
		}
	}
	
	/*
	 * This method advances the ball's internal simulation by the given amount of time.
	 */
	public void advance(int milliseconds) {
		
		// Check to make sure we are given a positive amount of time.
		if (milliseconds <= 0) {
			
			throw new IllegalArgumentException("Time interval cannot be less than 1.");
		} else {
			
			// Calculate new position.
			int xValue = (int) (xPosition + ((int) ((double) xVelocity) * ((double) milliseconds) / (1000.0)));
			int yValue = (int) (yPosition + ((int) ((double) yVelocity) * ((double) milliseconds) / (1000.0)));
			
			// Snap new x-axis position to acceptable position if necessary.
			if (xValue < 100 + radius) {
				
				xPosition = 100 + radius;
			} else if (xValue > 500 - radius) {
				
				xPosition = 500 - radius;
			} else {
				
				xPosition = xValue;
			}
			
			// Snap new y-axis position to acceptable position if necessary.
			if (yValue < 100 + radius) {
				
				yPosition = 100 + radius;
			} else if (yValue > 500 - radius) {
				
				yPosition = 500 - radius;
			} else {
				
				yPosition = yValue;
			}
		}
	}
	
	/*
	 * This method set the ball's velocity.
	 */
	public void updateVelocity(int newXVelocity, int newYVelocity) {
		
		// Check to see if the given velocity is acceptable.
		if (newXVelocity < -75 || newXVelocity > 75) {
			
			throw new IllegalArgumentException("New horizontal velocity value outside acceptable range.");
		} else {
			
			xVelocity = newXVelocity;
		}
		
		// Check to see if the given velocity is acceptable.
		if (newYVelocity < -75 || newYVelocity > 75) {
			
			throw new IllegalArgumentException("New vertical velocity value outside acceptable range.");
		} else {
			
			yVelocity = newYVelocity;
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
	public int getSize() {
		
		return radius;
	}
}
