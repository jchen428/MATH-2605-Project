package powermethod;

import java.util.ArrayList;
import java.util.Random;

import basicfunctions.BasicFunctions;

public class PowerMethodConvergance {
    
    public static ArrayList powerMethod (float[][] matrix, float[] vector, float tolerance, int MaxTimes) {
	ArrayList returnMe = new ArrayList();
	float error = Float.POSITIVE_INFINITY; //make this infinity
	float[] nextIterationVector = vector;
	float eigenvalue = 0;
	float[] eigenvector = new float[matrix.length];
	float previousEigenvalue = 999;
	int counter = 0;
	while (error > tolerance && MaxTimes > counter) {
	    previousEigenvalue = eigenvalue;
	    float [] resultVector = new float[matrix.length];
	    for (int i = 0; i < matrix.length; i++) {
		resultVector[i] = 0;
		for (int j = 0; j < matrix.length; j++) {
		    resultVector[i] += matrix[i][j] * nextIterationVector[j];
		}
	    }
	    eigenvalue = resultVector[resultVector.length - 1];
	    for (int i = 0; i < resultVector.length; i++) {
		nextIterationVector[i] = resultVector[i];
		resultVector[i] = resultVector[i] / eigenvalue;
	    }
	    eigenvector = resultVector;
	    error = Math.abs(eigenvalue - previousEigenvalue);
	    counter++;
	}
	if (error > tolerance) {
	    return null; //return "null"
	}
	returnMe.add(eigenvector);
	returnMe.add(eigenvalue);
	returnMe.add(counter);
	return returnMe; //return eigenvalue and eigenvector
    }
    
    public static ArrayList makeManyMatricies() {
	Random r = new Random();
	int counter = 0;
	float [][] matrix = new float[2][2];
	for (int i = 0; i < 2; i++) {
	    for (int j = 0; j < 2; j++) {
		float next = (r.nextFloat() * 4) - 2;
		matrix[i][j] = next;
	    }	
	}
	float det = BasicFunctions.determinant(matrix);
	if (det == 0) {
	    return null;
	}
    float[][] inverseMatrix = BasicFunctions.inverseOf2x2(matrix);
	
	float trace = BasicFunctions.trace(matrix);
	float[] startingVector = new float[2];
	for (int i = 0; i < startingVector.length; i++) {
	    startingVector[i] = 1;
	}
	ArrayList data = powerMethod(matrix, startingVector, (float) 0.00005, 100);
	if (data == null) {
	    return null;
	}
	int numberOfIterations = (int) data.get(2);
	ArrayList data2 = powerMethod(BasicFunctions.inverseOf2x2(matrix), startingVector, (float) 0.00005, 100);
	if (data2 == null) {
	    return null;
	}
	int inverseNumberOfIterations = (int) data2.get(2);
	ArrayList returnMe = new ArrayList();
	returnMe.add(trace);
	returnMe.add(det);
	returnMe.add(numberOfIterations);
	returnMe.add(inverseNumberOfIterations);
	return returnMe;
    }
}

