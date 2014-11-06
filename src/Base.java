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
		inverse(testData);
		
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
	
	
	//Finds the inverse of a square matrix
	public static double[][] inverse(double[][] mat){
		System.out.println("Original Matrix:");
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++) {
				System.out.print(mat[i][j] + " ");
			}
			System.out.println("");
		}
		if (mat.length == 1 && mat[0].length == 1) {
			return mat;
		} else if (mat.length == 2 && mat[0].length == 2) {
			double a = mat[0][0];
			double b = mat[0][1];
			double c = mat[1][0];
			double d = mat[1][1];
			double det = (a*d) - (b*c);
			mat[0][0] = d/det;
			mat[0][1] = -b/det;
			mat[1][0] = -c/det;
			mat[1][1] = a/det;
		} else {
			double[][] id = new double[mat.length][mat[0].length];

			for (int i = 0; i < id.length; i++) {
				for (int j = 0; j < id[i].length; j++) {
					if (i == j) {
						id[i][j] = 1.0;
					}
				}
			}
			
			double[][] full = new double[mat.length][mat[0].length * 2];
			for (int i = 0; i < full.length; i++) {
				for (int j = 0; j < full[i].length; j++) {
					if (j < mat.length) {
						full[i][j] = mat[i][j];
					} else {
						full[i][j] = id[i][j - mat[0].length];
					}
				}
			}
			
			System.out.println("\nFull matrix before:");
			for (int i = 0; i < full.length; i++) {
				for (int j = 0; j < full[i].length; j++) {
					System.out.print(full[i][j] + " ");
				}
				System.out.println("");
			}
			for (int h = 0; h < full.length; h++) {
				double d = full[h][h];
				for (int i = h; i < full[h].length; i++) {
					full[h][i] = full[h][i] / d;
				}
				for (int i = h + 1; i < full.length; i++) {
					double f = full[i][h];
					for (int j = h; j < full[i].length; j++) {
						full[i][j] = full[i][j] - full[h][j] * f;
					}
				}
			}

			
			System.out.println("\nFull matrix after:");
			for (int i = 0; i < full.length; i++) {
				for (int j = 0; j < full[i].length; j++) {
					System.out.print(full[i][j] + " ");
				}
				System.out.println("");
			}
		}
		return mat;
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
}
