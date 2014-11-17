package powermethod;

public class PowerMethodConvergance {
    
    public float powerMethod (float[][] matrix, float[] vector, float tolerance, int MaxTimes) {
	float error = 999999; //make this infinity
	float[] nextIterationVector = vector;
	float eigenvalue = 0;
	float[] eigenvector = new float[matrix.length];
	float previousEigenvalue = 0;
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
	//NOT SURE WHAT TO RETURN
	return (float) 0.0;
    }
    
    public void MakeManyMatricies() {
	int counter = 0;
	while (counter < 1000) {
	    
	}
    }
}
