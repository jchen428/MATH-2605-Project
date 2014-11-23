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
	private ArrayList<float[][]> householders;
	private ArrayList<float[][]> givens;
	
	/**
	 * Makes a random m x n matrix up to 100 x 100 with random values up to 100
	 * 
	 * @return The random matrix
	 */
	private static float[][] makeRandomMatrix() {
		int m = 3 + (int) (Math.random() * 97);
		int n = 3 + (int) (Math.random() * 97);
		
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
		householders = qr_fact_househ(matrix);
		givens = qr_fact_givens(matrix);
	}
	
	@Test
	public void testQRHouseholders() {
		float[][] QR = householders.get(2);
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				assertEquals(matrix[i][j], QR[i][j], 0.005);
			}
		}
	}
	
	@Test
	public void testQRGivens() {
		float[][] QR = givens.get(2);
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				assertEquals(matrix[i][j], QR[i][j], 0.0005);
			}
		}
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
