package gaussnewton;

import basicfunctions.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
		qr_fact_givens(mat);
		qr_fact_givens(mat2);
	}
		
		private Scanner keyboard = new Scanner(System.in);
		
		private ArrayList<NTuple> pairs = new ArrayList<NTuple>();
		private NTuple triple;
		private int N;
		
		private float[][] beta;
		private float[][] residuals;
		private float[][] jacobian;
	
		/**
		 * Initializes user input values
		 */
		public void initialize() {
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
			
			System.out.print("Enter initial guess for parameter a: ");
			float a = keyboard.nextFloat();
			System.out.print("Enter initial guess for parameter b: ");
			float b = keyboard.nextFloat();
			System.out.print("Enter initial guess for parameter c: ");
			float c = keyboard.nextFloat();
			triple = new NTuple(a, b, c);
			
			System.out.println("beta = " + triple);
			
			System.out.print("Enter number of iterations: ");
			N = keyboard.nextInt();
		}
		
		/**
		 * Constructs the beta, residuals, and jacobian matrices
		 */
		public void construct() {
			int n = pairs.size();
			beta = new float[][] {{triple.getX()}, {triple.getY()}, {triple.getZ()}};
			residuals = new float[n][1];
			jacobian = new float[n][3];
			
			for (int i = 0; i < n; i++) {
				residuals[i][0] = pairs.get(i).getY() - function(triple.getX(), triple.getY(), triple.getZ(), pairs.get(i).getX());
			}
			
			System.out.println("Number of iterations = " + N);
			System.out.println(Arrays.deepToString(residuals));
			
			for (int a = 0; a < n; a++) {
				jacobian[a][0] = -(float) (Math.pow(pairs.get(a).getX(), 2));
			}
			for (int a = 0; a < n; a++) {
				jacobian[a][1] = -pairs.get(a).getX();
			}
			for (int a = 0; a < n; a++) {
				jacobian[a][2] = -1;
			}
			
			System.out.println(Arrays.deepToString(jacobian));
		}
		
		/**
		 * Applies parameters to a particular function of variable x
		 * 
		 * @param a First parameter
		 * @param b Second parameter
		 * @param c Third parameter
		 * @param x Variable
		 * @return result
		 */
		protected abstract float function(float a, float b, float c, float x);
		
		/**
		 * Performs the QR-factorization of the jacobian matrix
		 * 
		 * @return An ArrayList of 2 floating point matrices, the first being Q and the second being R.
		 */
		public ArrayList<float[][]> qr_fact_househ() {
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
			float[][] g = new float[m][m];
			float[][] q = new float[m][m];
					
			//Make G an identity
			for (int i = 0; i < m; i++){
	            for (int j = 0; j < m; j++){
	                if (i == j){
	                    g[i][j] = 1;
	                    q[i][j] = 1;
	                }
	                else {
	                    g[i][j] = 0;
	                    q[i][j] = 0;
	                }
	            }
	        }
			
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
	        
	        //Get rid of the padding zeroes
	        if (m != n) {
	        	float[][] newMat = new float[n][n];
	        	for (int i = 0; i < n; i++) {
	        		for (int j = 0; j < n; j++) {
	        			newMat[i][j] = mat[i][j];
	        		}
	        	}
	        	mat = newMat;
	        	float[][] newQ = new float[m][n];
	        	for (int i = 0; i < m; i++) {
	        		for (int j = 0; j < n; j++) {
	        			newQ[i][j] = q[i][j];
	        		}
	        	}
	        	q = newQ;
	        }
	        
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