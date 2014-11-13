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
	 * Applies parameters to a quadratic function of variable x
	 * 
	 * @param a First parameter
	 * @param b Second parameter
	 * @param c Third parameter
	 * @param x Variable
	 * @return result
	 */
	protected float function(float b1, float b2, float b3, float x) {
		float result = b1 * (float) (Math.pow(x, 2)) + b2 * x + b3;
		
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
	protected float drdB1(float b1, float b2, float b3, float x) {
		float result = -(float) (Math.pow(x, 2));
		
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
	protected float drdB2(float b1, float b2, float b3, float x) {
		float result = -x;
		
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
	protected float drdB3(float b1, float b2, float b3, float x) {
		float result = -1;
		
		return result;
	}
	
	public float[][] guassNewton() {
		return null;
	}
	
}
