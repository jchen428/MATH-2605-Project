package gaussnewton;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import basicfunctions.BasicFunctions;

/**
 * JUnit test cases for Gauss-Newton stuff
 * 
 * @author Jesse
 */
public class Tests extends Base {

	private float[][] matrix;
	private ArrayList<float[][]> result;
	
	/**
	 * Makes a random m x n matrix up to 100 x 100 with random values up to 100
	 * 
	 * @return The random matrix
	 */
	private static float[][] makeRandomMatrix() {
		int m = (int) (Math.random() * 100);
		int n = (int) (Math.random() * 100);
		float[][] mat = new float[m][n];
		
		for (int i = 0; i < m; i ++) {
			for (int j = 0; j < n; j++) {
				mat[i][j] = (float) (Math.random() * 100);
			}
		}
		
		return mat;
	}
	
	@Before
	public void setup() {
		matrix = makeRandomMatrix();
		result = qr_fact_househ(matrix);
		
		System.out.println(matrix[0].length + " = " + result.get(2)[0].length);
	}
	
	@Test
	public void testQR() {
		float[][] QR = result.get(2);
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				assertEquals(matrix[i][j], QR[i][j], 0.05);
			}
		}
		
		//assertEquals(matrix, QR, .005);
	}
	
	/**
	 * Unused
	 */
	protected float function(float b1, float b2, float b3, float x) {
		float result = b1 * (float) (Math.pow(x, 2)) + b2 * x + b3;
		
		return result;
	}

	/**
	 * Unused
	 */
	protected float drdB1(float b1, float b2, float b3, float x) {
		float result = -(float) (Math.pow(x, 2));
		
		return result;
	}

	/**
	 * Unused
	 */
	protected float drdB2(float b1, float b2, float b3, float x) {
		float result = -x;
		
		return result;
	}
	
	/**
	 * Unused
	 */
	protected float drdB3(float b1, float b2, float b3, float x) {
		float result = -1;
		
		return result;
	}

}
