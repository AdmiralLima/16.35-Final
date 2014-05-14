
public class Position {
	
	private final int[] pos;
	
	public Position(int[] pos) {
		this.pos = pos;
	}
	
	public synchronized int[] getPos(){
		return this.pos;
	}
}
