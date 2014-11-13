package gaussnewton;

import java.util.ArrayList;

import basicfunctions.BasicFunctions;

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
		gn_exp execute = new gn_exp();
		guassNewton();
	}
	
	/**
	 * Constructor
	 */
	public gn_exp() {
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
		float result = a * (float) (Math.exp(b * x)) + c;
		
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
		float result = -(float) (Math.exp(b * x));
		
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
		float result = -x * (float) (Math.exp(b * x));
		
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
	
	protected static float[][] guassNewton() {
		float[][] J = Base.getJacobian();
        float[][] r = Base.getResiduals();
        
        for (int i = 1; i <= 5; i++) {
        	ArrayList<float[][]> qr = qr_fact_givens(J);
        	float[][] Q = qr.get(0);
        	float[][] R = qr.get(1);
        	
        	System.out.println("Q:");
        	BasicFunctions.print(Q);
        	System.out.println("R:");
        	BasicFunctions.print(R);
        }
		
		return null;
	}
}
