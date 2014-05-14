package GameObjects;

/*
 * This class represents a game ball.
 */
public class Ball{

	// We need to set the size of a game ball.
	private static final int BALL_RADIUS = 10;
	
	// We need to set the velocity of a game ball.
	public int BALL_VELOCITY = 600;
	
	// We need to set bounds on the orientation of a game ball.
	private final double BALL_MIN_THETA = 0;
	private final double BALL_MAX_THETA = 2 * Math.PI;
	
	// We need to set limits on the horizontal position of the ball.
	private final int BALL_MIN_X = 100;
	private final int BALL_MAX_X = 500;
	
	// We need to set limits on the vertical position of the ball.
	private final int BALL_MIN_Y = 100;
	private final int BALL_MAX_Y = 500;
	
	// We store the ball's location.
	/* Shared Resource */
	private int xPosition;
	private int yPosition;
	
	// We store the ball's direction.
	/* Shared Resource */
	private double theta;
	
	/*
	 * This constructor simply takes an initial position and creates a 
	 * new paddle at that location if the given position is within the
	 * acceptable range.
	 */
	public Ball(int xInitial, int yInitial, double thetaInitial) throws IllegalArgumentException {
		
		// Check to see if the x-axis position is within bounds.
		if (xInitial >= BALL_MIN_X + BALL_RADIUS && xInitial <= BALL_MAX_X - BALL_RADIUS) {
			xPosition = xInitial;
		} else {
			throw new IllegalArgumentException("Initial horizontal position out of bounds.");
		}
		
		// Check to see if the y-axis position is acceptable.
		if (yInitial >= BALL_MIN_Y + BALL_RADIUS && yInitial <= BALL_MAX_Y - BALL_RADIUS) {
			yPosition = yInitial;
		} else {
			throw new IllegalArgumentException("Initial vertical position out of bounds.");
		}
		
		// Check to see if the given orientation is acceptable.
		if (thetaInitial >= BALL_MIN_THETA && thetaInitial <= BALL_MAX_THETA) {
			if (thetaInitial == BALL_MAX_THETA) {
				theta = BALL_MIN_THETA;
			} else {
				theta = thetaInitial;
			}
		} else {
			throw new IllegalArgumentException("Initial orientation out of range.");
		}
	}
	
	/*
	 * This method advances the ball's internal simulation by the given amount of time.
	 * It also checks to see if the ball has collided with a wall and will update the ball's
	 * direction.
	 */
	public void advance(long milliseconds) {
		
		// Check to make sure we are given a positive amount of time.
		if (milliseconds < 0) {
			throw new IllegalArgumentException("Time interval cannot be less than 1.");
		} else if (milliseconds == 0) {
			return;
		} else {
			
			/* Critical Region */
			synchronized (this) {
				// Calculate new position.
				double newX = xPosition + (milliseconds * 0.001 * BALL_VELOCITY * Math.cos(theta));
				double newY = yPosition + (milliseconds * 0.001 * BALL_VELOCITY * Math.sin(theta));
			
				xPosition = (int) newX;
				yPosition = (int) newY;
			}
		}
	}
	
	public boolean collision() {
		
		// We hit the right wall.
		if (xPosition  + BALL_RADIUS > BALL_MAX_X) {
			if (theta < Math.PI / 2 || theta > 3 * Math.PI / 2) {
				
				/* Critical Region */
				synchronized (this) {
					theta = wrapTheta(Math.PI - theta);
					return true;
				}
			}
		}
		
		// We hit the left wall.
		if (xPosition  - BALL_RADIUS < BALL_MIN_X) {
			if (theta > Math.PI / 2 & theta < 3 * Math.PI / 2) {
				
				/* Critical Region */
				synchronized (this) {
					theta = wrapTheta(Math.PI - theta);
					return true;
				}
			}
		} 
		
		// We hit the top wall.
		if (yPosition  - BALL_RADIUS < BALL_MIN_Y ) {
			if (theta > Math.PI) {
				
				/* Critical Region */
				synchronized (this) {
					theta = wrapTheta((2 * Math.PI) - theta);
					return true;
				}
			}
		}
		
		// We hit the bottom wall.
		if (yPosition  + BALL_RADIUS > BALL_MAX_Y) {
			if (theta < Math.PI) {
				
				/* Critical Region */
				synchronized (this) {
					theta = wrapTheta((2 * Math.PI) - theta);
					return true;
				}
			}
		}
		
		// We did not hit anything.
		return false;
	}
	
	/*
	 * This method returns an array of integers containing the 
	 * x-axis and y-axis positions of the ball.
	 */
	public int[] getPosition() {
		
		/* Critical Region */
		synchronized (this) {
			return new int[]{xPosition, yPosition};
		}
	}
	
	/*
	 * This method returns the current orientation of the ball.
	 */
	public double getOrientation() {
		
		/* Critical Region */
		synchronized (this) {
			return theta;
		}
	}
	
	public void setPosition(int x, int y, double theta) {
		xPosition = x;
		yPosition = y;
		this.theta = wrapTheta(theta);
	}
	
	/*
	 * This method returns an array of integers containing the 
	 * x-axis and y-axis dimensions of the ball.
	 */
	public static int getSize() {
		return BALL_RADIUS;
	}
	
	/*
	 * This method sets theta to an equivalent value within the range
	 * zero to two times pi. 
	 */
	public static double wrapTheta(double theta) {
		while (theta < 0) {
			theta = theta + (2 * Math.PI);
		}
		while (theta > 2 * Math.PI) {
			theta = theta - (2 * Math.PI);
		}
		
		return theta;
	}
}
