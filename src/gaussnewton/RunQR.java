package gaussnewton;

import java.util.ArrayList;
import java.util.Scanner;

import basicfunctions.BasicFunctions;

/**
 * Tests the QR decomposition methods
 * 
 * @author Jesse
 */
public class RunQR {

	private Scanner keyboard = new Scanner(System.in);
	private int m, n;
	ArrayList<float[][]> result;
	private float[][] A, Q, R, QR;
	
	/**
	 * Main method
	 * 
	 * @param args Unused
	 */
	public static void main(String[] args) {
		RunQR execute = new RunQR();
	}
	
	/**
	 * Constructor. Does all the work.
	 */
	public RunQR() {
		//Get user input for matrix dimensions
		System.out.print("Enter the number of rows for the matrix A: ");
		m = keyboard.nextInt();
		System.out.print("Enter the number of columns for the matrix A: ");
		n = keyboard.nextInt();
		A = new float[m][n];
		
		//Get user input for matrix values
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print("Enter value floating-point value for "
						+ "position [" + i + "][" + j + "]: ");
				A[i][j] = keyboard.nextFloat();
			}
		}
		System.out.println("Matrix = ");
		BasicFunctions.print(A);
		
		//Get user input to use Householders or Givens
		System.out.println("Choose to use Householders reflections or Givens "
				+ "rotations:");
		System.out.println("1. Householders reflections");
		System.out.println("2. Givens rotations");
		int in = keyboard.nextInt();

		//Perform specified QR decomposition and print results
		if (in == 1) {
			result = Base.qr_fact_househ(A);
		} else if (in == 2) {
			result = Base.qr_fact_givens(A);
		} else {
			System.out.println("Invalid input");
			return;
		}
		
		Q = result.get(0);
		R = result.get(1);
		QR = result.get(2);
        System.out.println("Q = ");
        BasicFunctions.print(Q);
        System.out.println("R = ");
        BasicFunctions.print(R);
        System.out.println("QR = ");
        BasicFunctions.print(QR);
	}
	
	/**
	 * Unused
	 */
	protected float function(float b1, float b2, float b3, float x) {
		return 0;
	}

	/**
	 * Unused
	 */
	protected float drdB1(float b1, float b2, float b3, float x) {
		return 0;
	}

	/**
	 * Unused
	 */
	protected float drdB2(float b1, float b2, float b3, float x) {
		return 0;
	}

	/**
	 * Unused
	 */
	protected float drdB3(float b1, float b2, float b3, float x) {
		return 0;
	}

}
