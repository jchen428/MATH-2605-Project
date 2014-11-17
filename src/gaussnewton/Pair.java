package gaussnewton;

/**
 * Represents ordered pairs and triples
 * 
 * @author Jesse
 */
public class Pair {
		
	private float x, y;
	
	/**
	 * Constructor for ordered pairs
	 * 
	 * @param x First value
	 * @param y Second value
	 */
	public Pair(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Gets x
	 * 
	 * @return x
	 */
	public float getX() {
		return x;
	}
	
	/**
	 * Gets y
	 * 
	 * @return y
	 */
	public float getY() {
		return y;
	}
	
	/**
	 * Returns a string representation of the NTuple
	 */
	public String toString() {
		return "(" + getX() + ", " + getY() + ")";
	}
	
}
