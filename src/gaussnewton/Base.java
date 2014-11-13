package gaussnewton;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import basicfunctions.BasicFunctions;

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
		//qr_fact_givens(mat);
		//qr_fact_givens(mat2);
		qr_fact_househ(mat);
	}
		
		private Scanner keyboard = new Scanner(System.in);
		
		private ArrayList<NTuple> pairs = new ArrayList<NTuple>();
		//private NTuple triple;
		private int N;
		
		private float[][] beta;
		private float[][] residuals;
		private float[][] jacobian;
	
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
					NTuple pair = new NTuple(Float.valueOf(data[0]), Float.valueOf(data[1]));
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
			//triple = new NTuple(a, b, c);
			
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
			//beta = new float[][] {{triple.getX()}, {triple.getY()}, {triple.getZ()}};
			residuals = new float[n][1];
			jacobian = new float[n][3];
			float b1 = beta[0][0];
			float b2 = beta[1][0];
			float b3 = beta[2][0];
			
			//Construct residual matrix
			for (int i = 0; i < n; i++) {
				residuals[i][0] = pairs.get(i).getY() - function(b1, b2, b3, pairs.get(i).getX());
			}
			
			System.out.println("Number of iterations = " + N);
			
			System.out.println("Residuals = ");
			BasicFunctions.print(residuals);
			
			//Construct Jacobian matrix
			for (int a = 0; a < n; a++) {
				jacobian[a][0] = drdB1(b1, b2, b3, pairs.get(a).getX());
			}
			for (int a = 0; a < n; a++) {
				jacobian[a][1] = drdB2(b1, b2, b3, pairs.get(a).getX());
			}
			for (int a = 0; a < n; a++) {
				jacobian[a][2] = drdB3(b1, b2, b3, pairs.get(a).getX());
			}
			
			System.out.println("Jacobian = ");
			BasicFunctions.print(jacobian);
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
		 * Performs the QR-factorization of the jacobian matrix using Householder reflections
		 * 
		 * @return An ArrayList of 2 floating point matrices, the first being Q and the second being R.
		 */
		public static ArrayList<float[][]> qr_fact_househ(float[][] mat) {
			int m = mat.length;
			float[][] x = new float[m][1];
			
			for (int i = 0; i < mat.length; i++) {
				x[i][0] = mat[i][0];
			}
			
			BasicFunctions.print(x);
			System.out.println(BasicFunctions.norm(x));
			
			float[][] v = x;
			
			BasicFunctions.print(v);
			System.out.println(v[0][0] + BasicFunctions.norm(x));
			
			v[0][0] += BasicFunctions.norm(x);
			float[][] u = BasicFunctions.unitize(v);
			
			BasicFunctions.print(v);
			BasicFunctions.print(u);
			
			for (int i = 0; i < m; i++) {
				x[i][0] = mat[i][0];
			}
			
			return null;
		}
		
		/**
		 * Performs the QR-factorization of the jacobian matrix using Givens rotations
		 * 
		 * @return An ArrayList of 2 floating point matrices, the first being Q and the second being R.
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
	 
	        //Iteration for givens rotations
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
	        
	        //Print out Q, R, and QR to check that it equals the input
	        System.out.println("Q:");
	        BasicFunctions.print(q);
	        System.out.println("R:");
	        BasicFunctions.print(mat);
	        System.out.println("QR:");
	        BasicFunctions.print(BasicFunctions.matrixMult(q,mat));
	        
	        //Add Q and R to the ArrayList to return
	        ret.add(q);
	        ret.add(mat);
			return ret;
		}
}