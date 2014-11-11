package gaussnewton;

/**
 * Performs the modified Gauss-Newton method for a quadratic curve
 * 
 * @author Jesse
 */
public class gn_log extends Base {
	
	/**
	 * Main method
	 * 
	 * @param args Unused
	 */
	public static void main(String[] args) {
		gn_log execute = new gn_log();
	}
	
	/**
	 * Constructor
	 */
	public gn_log() {
		initialize();
		construct();
	}
	
	/**
	 * Applies parameters to an exponential function of variable x
	 * 
	 * @param a First parameter
	 * @param b Second parameter
	 * @param c Third parameter
	 * @param x Variable
	 * @return result
	 */
	protected float function(float a, float b, float c, float x) {
		float result = a * (float) (Math.log(x + b)) + c;
		
		return result;
	}
}
