package basicfunctions;

public class BasicFunctions {
	
	public static void main(String[] args) {
		float[][] a = new float[][] {
				{2, 6, 34, 7, 7},
				{5, 7, 2, 56, 4},
				{4, 45, 67, 3, 75},
		};
		float[][] b = new float[][] {
				{27, 8, 54, 2, 6},
				{84, 3, 8, 90, 12},
				{4, 89, 12, 68, 3}
		};
		float[][] c = new float[][] {
				{2, 6, 34, 8},
				{5, 7, 2, 6},
				{4, 45, 67, 2},
				{4, 45, 67, 2}
		};
		float[][] d = new float[][] {
				{2, 4},
				{3, 5}
		};
		float[][] v = {{7}, {3}, {8}, {4}};
		float s = 2;
		
		//System.out.println("a + b = ");
		//print(matrixAdd(a, b));
		//System.out.println("Norm(a) = " + norm(v));
		//print(scalarMult(a, s));
		//print(scalarMult(v, s));
		//System.out.println(norm(v));
		//print(unitize(v));
		//print(trim(a, 1, 2, 2, 4));
		//print(pad(c, 7));
	}
	
	public static void print(float[][] a) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				System.out.printf("%8.4f" + "\t", a[i][j]);
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}
		
	/**
	 * Adds to matrices
	 * 
	 * @param a First matrix
	 * @param b Second matrix
	 * @return The resulting matrix
	 */
	public static float[][] matrixAdd(float[][] a, float[][] b) {
		int m = a.length;
		int n = a[0].length;
		
		if (m != b.length && n != b[0].length) {
			return null;
		}
		
		float[][] result = new float[a.length][a[0].length];
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				result[i][j] = a[i][j] + b[i][j];
			}
		}
		
		return result;
	}
	
	/**
	 * Multiplies to matrices in order given
	 * 
	 * @param left Left matrix
	 * @param right Right Matrix
	 * @return The resulting matrix
	 */
	public static float[][] matrixMult(float[][] left, float[][] right) {
		int m = left.length;
		int n = right[0].length;
		
		if (left[0].length != right.length) {
			return null;
		}
		
		float[][] result = new float[m][n];
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < right.length; k++) {
					result[i][j] += left[i][k] * right[k][j];
				}
			}
		}
		
		return result;
	}
	
	/**
	 * Multiplies a matrix by a scalar
	 * 
	 * @param mat Matrix
	 * @param s Scalar
	 * @return The resulting matrix
	 */
	public static float[][] scalarMult(float[][] mat, float s) {
		float[][] result = mat;
		
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[0].length; j++) {
				result[i][j] *= s;
			}
		}
		
		return result;
	}
	
	/**
	 * Calculates the norm of a vector v
	 * 
	 * @param v Vector
	 * @return The norm of v
	 */
	public static Float norm(float[][] v) {
		if (v[0].length != 1) {
			return null;
		}
		
		int m = v.length;
		float sumSquares = 0;
		float norm;
		
		for (int i = 0; i < m; i++) {
			sumSquares += Math.pow(v[i][0], 2);
		}
		
		norm = (float) Math.sqrt(sumSquares);
		
		return norm;
	}
	
	/**
	 * Unitizes a vector v
	 * 
	 * @param v Vector
	 * @return Unitized v
	 */
	public static float[][] unitize(float[][] v) {
		if (v[0].length != 1) {
			return null;
		}
		
		float[][] u = v;
		
		int m = u.length;
		float norm = norm(u);
		
		for (int i = 0; i < m; i++) {
			u[i][0] /= norm;
		}
		
		return u;
	}
	
	/**
	 * Trims a matrix to r1:r2 x c1:c2
	 * 
	 * @param mat Matrix
	 * @param r1 Rows lower bound
	 * @param r2 Rows upper bound
	 * @param c1 Columns lower bound
	 * @param c2 Columns upper bound
	 * @return The trimmed matrix
	 */
	public static float[][] trim(float[][] mat, int r1, int r2, int c1, int c2) {
		if (r1 < 0 || r2 > mat.length - 1 || c1 < 0 || c2 > mat[0].length - 1) {
			return null;
		}
		
		float[][] trimmed = new float[r2 - r1 + 1][c2 - c1 + 1];
		
		for (int i = r1; i < r2 + 1; i++) {
			for (int j = c1; j < c2 + 1; j++) {
				trimmed[i - r1][j - c1] = mat[i][j];
			}
		}
		
		return trimmed;
	}
	
	/**
	 * Pads the upper-left corner of a square matrix with part of an identity matrix until k x k
	 * 
	 * @param mat Matrix
	 * @param r Desired rows
	 * @param c Desired columns
	 * @return The padded matrix
	 */
	public static float[][] pad(float[][] mat, int k) {
		if (mat.length != mat[0].length || mat.length > k) {
			return null;
		}
		
		int m = mat.length;
		float[][] padded = new float[k][k];
		
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < k; j++) {
				if ((i > k - m - 1) && (j > k - m - 1)) {
					padded[i][j] = mat[m - (k - i - 1) - 1][m - (k - j - 1) - 1];
				} else if (i == j) {
					padded[i][j] = 1;
				} else {
					padded[i][j] = 0;
				}
			}
		}
		
		return padded;
	}
	
	/**
	 * Finds the transpose of a matrix
	 * 
	 * @param mat Matrix
	 * @return Transpose of mat
	 */
	public static float[][] transpose(float[][] mat) {
		float[][] trans = new float[mat[0].length][mat.length];
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				trans[j][i] = mat[i][j];
			}
		}
		return trans;
	}
	
	/**
	 * Calculates the determinant of a matrix
	 * 
	 * @param matrixA
	 * @return The determinant
	 */
	public static int determinant(float[][] matrixA) {
	    int det = 0;
	    int sign = 1;
	    int i = matrixA.length;
	    int j = matrixA[0].length;
	    
	    for (int l = 0; l < i; l++) {
			float[][] innerMatrix = new float[i-1][j-1];
			
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
	 * Calculates the trace of a matrix
	 * 
	 * @param matrix
	 * @return The trace
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
	
	
	public static float[][] inverseOf2x2(float[][] mat) {
		float a = mat[0][0];
		float b = mat[0][1];
		float c = mat[1][0];
		float d = mat[1][1];
		
		float det = (a*d) - (b*c);
		
		float[][] ret = new float[2][2];
		
		ret[0][0] = d/det;
		ret[0][1] = -b/det;
		ret[1][0] = -c/det;
		ret[1][1] = a/det;
		
		return ret;
	}
	
	/**
	 * Makes an n x n identity matrix
	 * 
	 * @param n Number of rows/columns
	 * @return The identity matrix
	 */
	public static float[][] makeIdentity(int n) {
		float[][] identity = new float[n][n];
		
		for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (i == j){
                    identity[i][j] = 1;
                }
            }
        }
		
		return identity;
	}
}