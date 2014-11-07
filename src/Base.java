import org.ojalgo.matrix.BasicMatrix;
import org.ojalgo.matrix.BasicMatrix.Factory;
import org.ojalgo.matrix.PrimitiveMatrix;
import java.util.Arrays;


public class Base {
    //TO DO:
    //import matrix/vector basic functions(java.math??)
    //1. inverse of a matrix - Hannah
    //2. QR factorization - Hannah
    //3. determinant - Forrest
    //4. trace - Forrest
    //5. eigenvalues and eigenvectors - Jesse
    //6. rotate, reflect, project vectors - Jesse
	

	
	public static void main(String[] args) {
		double[][] testData = new double[][] {{5.0, 2.0, 3.0, 6.0}, {4.0, 5.0, 6.0, 8.0}, {7.0, 8.0, 9.0, 3.0}, {7.0, 8.0, 9.0, 4.0}};
		
		System.out.println("Original Matrix:");
		for (int i = 0; i < testData.length; i++) {
			for (int j = 0; j < testData[i].length; j++) {
				System.out.print(testData[i][j] + " ");
			}
			System.out.println("");
		}
		
		double[][] testDataInvert = invert(testData);
		
		System.out.println("Inverted Matrix:");
		for (int i = 0; i < testDataInvert.length; i++) {
			for (int j = 0; j < testDataInvert[i].length; j++) {
				System.out.print(testDataInvert[i][j] + " ");
			}
			System.out.println("");
		}
		
		
		
		float[][] a = new float[][] {
				{2, 6, 34, 7, 7},
				{5, 7, 2, 56, 4},
				{4, 45, 67, 3, 75},
		};
		float[][] b = new float[][] {
				{27, 8},
				{84, 3},
				{4, 89},
				{12, 68},
				{86, 3}
		};
		System.out.println("\n" + Arrays.deepToString(matrixMult(a, b)));
	}
		
    public static double[][] invert(double mat[][]) 
    {
        int l = mat.length;
        double x[][] = new double[l][l];
        double b[][] = new double[l][l];
        int index[] = new int[l];
        for (int i=0; i<l; ++i) 
            b[i][i] = 1;
 
        gaussian(mat, index);
 
        for (int i=0; i<l-1; ++i)
            for (int j=i+1; j<l; ++j)
                for (int k=0; k<l; ++k)
                    b[index[j]][k]
                    	    -= mat[index[j]][i]*b[index[i]][k];
 
        for (int i=0; i<l; ++i) 
        {
            x[l-1][i] = b[index[l-1]][i]/mat[index[l-1]][l-1];
            for (int j=l-2; j>=0; --j) 
            {
                x[j][i] = b[index[j]][i];
                for (int k=j+1; k<l; ++k) 
                {
                    x[j][i] -= mat[index[j]][k]*x[k][i];
                }
                x[j][i] /= mat[index[j]][j];
            }
        }
        return x;
    }

    public static void gaussian(double a[][], int index[]) 
    {
        int n = index.length;
        double c[] = new double[n];
        for (int i=0; i<n; ++i) 
            index[i] = i;
        for (int i=0; i<n; ++i) 
        {
            double c1 = 0;
            for (int j=0; j<n; ++j) 
            {
                double c0 = Math.abs(a[i][j]);
                if (c0 > c1) c1 = c0;
            }
            c[i] = c1;
        }
        int k = 0;
        for (int j=0; j<n-1; ++j) 
        {
            double pi1 = 0;
            for (int i=j; i<n; ++i) 
            {
                double pi0 = Math.abs(a[index[i]][j]);
                pi0 /= c[index[i]];
                if (pi0 > pi1) 
                {
                    pi1 = pi0;
                    k = i;
                }
            }
            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i=j+1; i<n; ++i) 	
            {
                double pj = a[index[i]][j]/a[index[j]][j];
                a[index[i]][j] = pj;
                for (int l=j+1; l<n; ++l)
                    a[index[i]][l] -= pj*a[index[j]][l];
            }
        }
    }

    /**
	 * Multiplies to matrices in order given
	 * @param a Left matrix
	 * @param b Right Matrix
	 * @return The resulting matrix
	 */
	public static float[][] matrixMult(float[][] a, float[][] b) {
		if (a[0].length != b.length) {
			return null;
		}
		
		float[][] result = new float[a.length][b[0].length];
		
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < b[0].length; j++) {
				for (int k = 0; k < b.length; k++) {
					result[i][j] += a[i][k] * b[k][j];
				}
			}
		}
		
		return result;
	}
	
	/**
	 * gets the determinant.
	 * @param matrixA
	 * @return
	 */
	public static int determinant(float[][] matrixA) {
	    int det = 0;
	    int sign = 1;
	    int i = matrixA.length;
	    int j = matrixA[0].length;
	    for (int l = 0; l < i; l++) {
		float[][]innerMatrix = new float[i-1][j-1];
		for (int m = 1; m < i; m++) {
		    for(int n = 0; n < i; n++) {
			if(n < i) {
			    innerMatrix[m-1][n] = matrixA[m][n];
			}   else {
			    innerMatrix[m-1][n-1] = matrixA[m][n];
			}
		    }
		}
		if (l % 2 == 1) {
		    sign = -1;
		}
		det = (int) (sign * matrixA[0][l]*(determinant(innerMatrix)));
	    }
	    return det;
	}
	
	/**
	 * does the trace
	 * @param matrix
	 * @return
	 */
	public static float trace(float[][] matrix) {
	    if (matrix.length != matrix[0].length) {
		throw new IllegalArgumentException();
	    }
	    float sum = 0;
	    for (int i = 0; i < matrix.length; i++) {
		sum = sum + matrix[i][i];
	    }
	    return sum;
	}

}