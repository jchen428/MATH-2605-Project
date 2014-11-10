package gaussnewton;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public abstract class Initializer {
	
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
		
		System.out.print("Enter maximum number of iterations: ");
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
		
		System.out.println("Max iterations = " + N);
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
	
	//qr_fact_househ
	//qr_fact_givens
}
