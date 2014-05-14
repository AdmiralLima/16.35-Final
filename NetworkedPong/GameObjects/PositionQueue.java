package GameObjects;
import java.util.concurrent.ArrayBlockingQueue;


public class PositionQueue extends ArrayBlockingQueue<Position> {
	
	
	static int CAPACITY = 5;
	
	
	/*
	 * ArrayBlockingQueue with capacity 5;
	 */
	public PositionQueue(){
		super(CAPACITY);
	}
	
	
	public boolean offer(Position p){
		boolean notfull = super.offer(p);
		if (notfull){
			this.clear();
			super.offer(p);
		}
		return true;
	}
}
