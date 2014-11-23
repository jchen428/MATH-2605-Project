package gaussnewton;

/**
 * Performs the modified Gauss-Newton method for a quadratic curve
 * 
 * @author Jesse
 */
public class gn_exp extends Base {
	
	/**
	 * Main method
	 * 
	 * @param args Unused
	 */
	public static void main(String[] args) {
		//C:\Users\Jesse\Downloads\TestData2.txt
		gn_exp execute = new gn_exp();
	}
	
	/**
	 * Constructor
	 */
	public gn_exp() {
		initialize();
		construct();
		chooseMethod();
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
	protected float function(float b1, float b2, float b3, float x) {
		float result = b1 * (float) (Math.exp(b2 * x)) + b3;
		
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
		float result = -(float) (Math.exp(b2 * x));
		
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
		float result = -b1 * x * (float) (Math.exp(b2 * x));
		
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
	
}
