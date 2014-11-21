package gaussnewton;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import basicfunctions.BasicFunctions;

/**
 * Base class for the Gauss-Newton Method. Performs all required operations
 * except those for the specified functions.
 * 
 * @author Jesse
 */
public abstract class Base {
	
	public static void main(String[] args) {
		float[][] mat = new float[][] {
				{2, 6, 34},
				{5, 7, 2},
				{4, 45, 67},
		};
		float[][] mat2 = new float[][] {
				{2, 6, 34},
				{5, 7, 2},
				{4, 45, 67},
				{2, 6, 34},
				{5, 7, 2},
		};
		float[][] mat3 = new float[][] {
				{0,5,36,3,5,7},
				{23,0,5,3,2,5},
				{4,2,0,6,35,3},
				{7,75,3,0,3,7},
				{34,3,6,6,0,6},
				{6,46,8,9,5,0},
				{4,2,2,4,2,72}
		};
		//qr_fact_givens(mat);
		//qr_fact_givens(mat2);
		//qr_fact_givens(mat3);
		//qr_fact_househ(mat);
		//qr_fact_househ(mat2);
		//qr_fact_househ(mat3);
	}
		
	private static Scanner keyboard = new Scanner(System.in);
	
	private static ArrayList<Pair> pairs = new ArrayList<Pair>();
	private static int N;
	private static float[][] beta;
	private static float[][] residuals;
	private static float[][] jacobian;

	/**
	 * Initializes user input values
	 */
	public void initialize() {
		//Read data from file
		System.out.print("Enter file path to data: ");
		String filePath = keyboard.nextLine();
		//C:\Users\Jesse\Downloads\TestData.txt
		try {
			File file = new File(filePath);
			FileReader reader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(reader);
			String inputLine;
			
			while ((inputLine = bufferedReader.readLine()) != null) {
				String[] data = inputLine.split(",");
				Pair pair = new Pair(Float.valueOf(data[0]),
						Float.valueOf(data[1]));
				pairs.add(pair);
			}
			
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < pairs.size(); i++) {
			System.out.println(pairs.get(i));
		}
		
		//Define and construct initial Beta matrix
		System.out.print("Enter initial guess for parameter a: ");
		float a = keyboard.nextFloat();
		System.out.print("Enter initial guess for parameter b: ");
		float b = keyboard.nextFloat();
		System.out.print("Enter initial guess for parameter c: ");
		float c = keyboard.nextFloat();
		beta = new float[][] {{a}, {b}, {c}};
		
		System.out.println("Beta = ");
		BasicFunctions.print(beta);
		
		//Set number of iterations
		System.out.print("Enter number of iterations: ");
		N = keyboard.nextInt();
	}
	
	/**
	 * Constructs the beta, residuals, and jacobian matrices
	 */
	public void construct() {
		int n = pairs.size();
		residuals = new float[n][1];
		jacobian = new float[n][3];
		float b1 = beta[0][0];
		float b2 = beta[1][0];
		float b3 = beta[2][0];
		
		//Construct residual matrix
		for (int i = 0; i < n; i++) {
			residuals[i][0] = pairs.get(i).getY() - function(b1, b2, b3,
					pairs.get(i).getX());
		}
		
		System.out.println("Number of iterations = " + N);
		
		System.out.println("Residuals = ");
		BasicFunctions.print(residuals);
		
		//Construct Jacobian matrix
		for (int i = 0; i < n; i++) {
			jacobian[i][0] = drdB1(b1, b2, b3, pairs.get(i).getX());
		}
		for (int i = 0; i < n; i++) {
			jacobian[i][1] = drdB2(b1, b2, b3, pairs.get(i).getX());
		}
		for (int i = 0; i < n; i++) {
			jacobian[i][2] = drdB3(b1, b2, b3, pairs.get(i).getX());
		}
		
		System.out.println("Jacobian = ");
		BasicFunctions.print(jacobian);
	}
	
	/**
	 * Asks user whether to use Householders reflections or Givens rotations
	 */
	public void chooseMethod() {
		System.out.println("Choose to use Householders reflections or Givens "
				+ "rotations:");
		System.out.println("1. Householders reflections");
		System.out.println("2. Givens rotations");

		BasicFunctions.print(gaussNewton(keyboard.nextInt()));
	}
	
	/**
	 * Applies parameters to a particular function of variable x
	 * 
	 * @param a Beta1
	 * @param b Beta2
	 * @param c Beta3
	 * @param x Variable
	 * @return result
	 */
	protected abstract float function(float b1, float b2, float b3, float x);
	
	/**
	 * Calculates the partial derivative of r with respect to Beta1
	 * 
	 * @param a Beta1
	 * @param b Beta2
	 * @param c Beta3
	 * @param x Variable
	 * @return result
	 */
	protected abstract float drdB1(float b1, float b2, float b3, float x);
	
	/**
	 * Calculates the partial derivative of r with respect to Beta2
	 * 
	 * @param a Beta1
	 * @param b Beta2
	 * @param c Beta3
	 * @param x Variable
	 * @return result
	 */
	protected abstract float drdB2(float b1, float b2, float b3, float x);
	
	/**
	 * Calculates the partial derivative of r with respect to Beta3
	 * 
	 * @param a Beta1
	 * @param b Beta2
	 * @param c Beta3
	 * @param x Variable
	 * @return result
	 */
	protected abstract float drdB3(float b1, float b2, float b3, float x);
	
	/**
	 * Performs the QR-factorization of the jacobian matrix using Householder
	 * reflections
	 * 
	 * @return An ArrayList of 2 floating point matrices, the first being Q, the
	 * second being R, and the third being QR.
	 */
	public static ArrayList<float[][]> qr_fact_househ(float[][] mat) {
		ArrayList<float[][]> H = new ArrayList<float[][]>();
		float[][] x, v, u, I, b, h;
		float[][] Q = BasicFunctions.makeIdentity(mat.length);
		float[][] R = mat;
		
		//Iterate for Householder reflections
		for (int iteration = 0; iteration < mat[0].length; iteration++) {
			//Make current column
			x = BasicFunctions.trim(R, iteration, R.length - 1, iteration,
					iteration);
			
			//v = x + ||x||e
			v = x;
			v[0][0] += BasicFunctions.norm(x);
			
			//Unitize v
			u = BasicFunctions.unitize(v);
			
			//Make I (identity matrix)
			I = BasicFunctions.makeIdentity(u.length);
			
			//b = -2 * u x u^T
			b = BasicFunctions.scalarMult(BasicFunctions.matrixMult(u,
					BasicFunctions.transpose(u)), -2);
			
			//h = id + b (Householder reflection matrix)
			h = BasicFunctions.matrixAdd(I, b);
			h = BasicFunctions.pad(h, mat.length);
			H.add(h);
			
			R = BasicFunctions.matrixMult(h, R);
		}
		
		//Calculate Q from all Householder reflection matrices
		for (int i = 0; i < H.size(); i++) {
			Q = BasicFunctions.matrixMult(Q, H.get(i));
		}
		
		float[][] QR = BasicFunctions.matrixMult(Q, R);
		
		//Print results
		System.out.println("A = ");
		BasicFunctions.print(mat);
		System.out.println("Q = ");
		BasicFunctions.print(Q);
		System.out.println("R = ");
		BasicFunctions.print(R);
		System.out.println("QR = ");
		BasicFunctions.print(QR);
		
		//Format for return
		ArrayList<float[][]> result = new ArrayList<float[][]>();
		result.add(Q);
		result.add(R);
		result.add(QR);
		
		return result;
	}
	
	/**
	 * Performs the QR-factorization of the jacobian matrix using Givens
	 * rotations
	 * 
	 * @return An ArrayList of 2 floating point matrices, the first being Q, the
	 * second being R, and the third being QR.
	 */
	public static ArrayList<float[][]> qr_fact_givens(float[][] mat) {
		int m = mat.length;
		int n = mat[0].length;
		ArrayList<float[][]> ret = new ArrayList<>();
		float[][] g = BasicFunctions.makeIdentity(m);
		float[][] q = BasicFunctions.makeIdentity(m);
		
        float x = mat[0][n-2];
        float y = mat[0][n-1];
        float cosX;
        float sinX;
 
        //Iterate for Givens rotations
        for (int i = 0; i < n; i++) {
            for (int j = (m - 1); j > i; j--) {
                x = mat[j-1][i];
                y = mat[j][i];   
                cosX = (float) (x / (Math.sqrt( x * x + y * y)));
                sinX = (float) (-y / (Math.sqrt(x * x + y * y)));
                g[j][j] = cosX;
                g[j][j-1] = sinX;
                g[j-1][j] = -sinX;
                g[j-1][j-1] = cosX;
                mat = BasicFunctions.matrixMult(g, mat);
                q = BasicFunctions.matrixMult(g, q); 
                for (int k = 0; k < m; k++){
                    for (int l = 0; l < m; l++){
                        if (k == l)
                            g[k][l] = 1;
                        else
                            g[k][l] = 0;
                    }
                }  
            }
        }
         
        q = BasicFunctions.transpose(q);
  		
  		float[][] QR = BasicFunctions.matrixMult(q, mat);
        
        //Print out Q, R, and QR to check that it equals the input
        System.out.println("Q:");
        BasicFunctions.print(q);
        System.out.println("R:");
        BasicFunctions.print(mat);
        System.out.println("QR:");
        BasicFunctions.print(BasicFunctions.matrixMult(q, mat));
        
        //Add Q and R to the ArrayList to return
        ret.add(q);
        ret.add(mat);
        ret.add(QR);
        
		return ret;
	}
	
	public float[][] gaussNewton(int in) {
		ArrayList<float[][]> QR;
		int n = pairs.size();
		float[][] Q, R, x;
		
		//Determine whether to use Householders reflections or Givens rotations
		if (in == 1) {
			QR = qr_fact_househ(jacobian);
		} else if (in == 2) {
			QR = qr_fact_givens(jacobian);
		} else {
			System.out.println("Invalid input");
			return null;
		}
		
		//Set Q and R
		Q = QR.get(0);
		R = QR.get(1);
		
		//Make Q n x 3 and R 3 x 3
		float[][] QFinal = new float[Q.length][3];
		float[][] RFinal = new float[3][3];
		
		for (int i = 0; i < Q.length; i++) {
			for (int j = 0; j < 3; j++) {
				QFinal[i][j] = Q[i][j];
			}
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				RFinal[i][j] = R[i][j];
			}
		}
		
		//Iterate N times
		for (int it = 0; it < N; it++) {
			//b = Q^T * r
			float[][] b = BasicFunctions.matrixMult(
					BasicFunctions.transpose(QFinal), residuals);
			//Solve R * x = Q^T * r by back substitution
			x = BasicFunctions.backSub(RFinal, b);
			//beta = beta - x
			beta = BasicFunctions.matrixAdd(beta, BasicFunctions.scalarMult(x,
					-1));
			
			//Get beta values
			float b1 = beta[0][0];
			float b2 = beta[1][0];
			float b3 = beta[2][0];
		
			//Recalculate residuals vector
			for (int i = 0; i < n; i++) {
				residuals[i][0] = pairs.get(i).getY() - function(b1, b2, b3,
						pairs.get(i).getX());
			}
			
			//Recalculate Jacobian matrix
			for (int i = 0; i < n; i++) {
				jacobian[i][0] = drdB1(b1, b2, b3, pairs.get(i).getX());
			}
			for (int i = 0; i < n; i++) {
				jacobian[i][1] = drdB2(b1, b2, b3, pairs.get(i).getX());
			}
			for (int i = 0; i < n; i++) {
				jacobian[i][2] = drdB3(b1, b2, b3, pairs.get(i).getX());
			}
		}
		
		return beta;
	}
	
}
