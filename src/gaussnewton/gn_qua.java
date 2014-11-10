package gaussnewton;

/**
 * Performs the modified Gauss-Newton method for a quadratic curve
 * 
 * @author Jesse
 */
public class gn_qua extends Base {
	
	/**
	 * Main method
	 * 
	 * @param args Unused
	 */
	public static void main(String[] args) {
		gn_qua execute = new gn_qua();
	}
	
	/**
	 * Constructor
	 */
	public gn_qua() {
		initialize();
		construct();
	}
	
	/**
	 * Applies parameters to quadratic function of variable x
	 * 
	 * @param a First parameter
	 * @param b Second parameter
	 * @param c Third parameter
	 * @param x Variable
	 * @return result
	 */
	protected float function(float a, float b, float c, float x) {
		float result = a * (float) (Math.pow(x, 2)) + b * x + c;
		
		return result;
	}
}
