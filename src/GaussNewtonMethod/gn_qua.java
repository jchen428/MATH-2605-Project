package GaussNewtonMethod;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Performs the modified Gauss-Newton method for a quadratic curve
 * 
 * @author Jesse
 */
public class gn_qua  {

	private static Scanner keyboard = new Scanner(System.in);
	
	private static ArrayList<NTuple> pairs = new ArrayList<NTuple>();
	private static NTuple triple;
	private static int N;
	
	private float[][] beta;
	private float[][] r;
	private float[][] j;
	
	/**
	 * Main method
	 * 
	 * @param args Unused
	 */
	public static void main(String[] args) {
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
		
		System.out.print("Enter maximum number of iterations: ");
		int iterations = keyboard.nextInt();
		
		gn_qua test = new gn_qua(pairs, triple, iterations);
	}
	
	/**
	 * Constructor
	 * 
	 * @param pairs An ArrayList of n ordered floating point pairs
	 * @param triple An ordered triple for the initial parameters
	 * @param iterations Maximum number of iterations to be performed
	 */
	public gn_qua(ArrayList<NTuple> pairs, NTuple triple, int iterations) {
		N = iterations;
		int n = pairs.size();
		beta = new float[][] {{triple.getX()}, {triple.getY()}, {triple.getZ()}};
		r = new float[n][1];
		j = new float[n][3];
		
		for (int i = 0; i < n; i++) {
			r[i][0] = pairs.get(i).getY() - function(triple.getX(), triple.getY(), triple.getZ(), pairs.get(i).getX());
		}
		
		System.out.println("Max iterations = " + N);
		System.out.println(Arrays.deepToString(r));
		
		for (int a = 0; a < n; a++) {
			for (int b = 0; b < 3; b++) {
				j[a][b] = 0;
			}
		}
	}
	
	private float function(float a, float b, float c, float x) {
		float result = a * x * x + b * x + c;
		
		return result;
	}
}
