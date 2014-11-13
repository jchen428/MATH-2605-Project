package gaussnewton;

/**
 * Performs the modified Gauss-Newton method for a quadratic curve
 * 
 * @author Jesse
 */
public class gn_rat extends Base {
	
	/**
	 * Main method
	 * 
	 * @param args Unused
	 */
	public static void main(String[] args) {
		gn_rat execute = new gn_rat();
	}
	
	/**
	 * Constructor
	 */
	public gn_rat() {
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
		float result = ((a * x) / (x + b)) + c;
		
		return result;
	}

	/**
	 * Calculates the partial derivative of r with respect to Beta1
	 * 
	 * @param a Beta1
	 * @param b Beta2
	 * @param c Beta3
	 * @param x Variable
	 * @return result
	 */
	protected float drdB1(float a, float b, float c, float x) {
		float result = -x / (x + b);
		
		return result;
	}

	/**
	 * Calculates the partial derivative of r with respect to Beta2
	 * 
	 * @param a Beta1
	 * @param b Beta2
	 * @param c Beta3
	 * @param x Variable
	 * @return result
	 */
	protected float drdB2(float a, float b, float c, float x) {
		float result = (a * x) / (float) (Math.pow(x + b, 2));
		
		return result;
	}

	/**
	 * Calculates the partial derivative of r with respect to Beta3
	 * 
	 * @param a Beta1
	 * @param b Beta2
	 * @param c Beta3
	 * @param x Variable
	 * @return result
	 */
	protected float drdB3(float a, float b, float c, float x) {
		float result = -1;
		
		return result;
	}
}
