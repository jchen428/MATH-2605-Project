package GaussNewtonMethod;

/**
 * Represents ordered pairs and triples
 * 
 * @author Jesse
 */
public class NTuple {
		
	private Float x, y, z;
	
	/**
	 * Constructor for ordered pairs
	 * 
	 * @param x First value
	 * @param y Second value
	 */
	public NTuple(float x, float y) {
		this.x = x;
		this.y = y;
		this.z = null;
	}
	
	/**
	 * Constructor for ordered triples
	 * 
	 * @param x First value
	 * @param y Second value
	 * @param z Third value
	 */
	public NTuple(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
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
	 * Gets z
	 * 
	 * @return z
	 */
	public float getZ() {
		return z;
	}
	
	/**
	 * Returns a string representation of the NTuple
	 */
	public String toString() {
		if (z == null) {
			return "(" + getX() + ", " + getY() + ")";
		} else {
			return "(" + getX() + ", " + getY() + ", " + getZ() + ")";
		}
		
	}
}
