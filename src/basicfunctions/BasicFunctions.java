package basicfunctions;

public class BasicFunctions {
    //TO DO:
    //import matrix/vector basic functions(java.math??)
    //1. inverse of a matrix - Hannah
    //2. QR factorization - Hannah
    //3. determinant - Forrest
    //4. trace - Forrest
    //5. eigenvalues and eigenvectors - Jesse
    //6. rotate, reflect, project vectors - Jesse
	
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
		float[][] v = {{7}, {3}, {8}, {4}};
		float s = 2;
		
		//System.out.println("a + b = ");
		//print(matrixAdd(a, b));
		//System.out.println("Norm(a) = " + norm(v));
		//print(scalarMult(a, s));
		//print(scalarMult(v, s));
		//System.out.println(norm(v));
		//print(unitize(v));
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
		
//    public static double[][] invert(double mat[][]) {
//	    if (mat != null) {
//    	    int l = mat.length;
//	        double ret[][] = new double[l][l];
//	        double b[][] = new double[l][l];
//	        int index[] = new int[l];
//	        for (int i=0; i<l; ++i) 
//	            b[i][i] = 1;
//	 
//	        upperTriangular(mat, index);
//	 
//	        for (int i = 0; i < l - 1; ++i)
//	            for (int j = i + 1; j < l; ++j)
//	                for (int k = 0; k < l; ++k)
//	                    b[index[j]][k]
//	                    	    -= mat[index[j]][i] * b[index[i]][k];
//	 
//	        for (int i=0; i<l; ++i) 
//	        {
//	            ret[l-1][i] = b[index[l-1]][i] / mat[index[l-1]][l-1];
//	            for (int j=l-2; j>=0; --j) 
//	            {
//	                ret[j][i] = b[index[j]][i];
//	                for (int k = j + 1; k < l; ++k) 
//	                {
//	                    ret[j][i] -= mat[index[j]][k]*ret[k][i];
//	                }
//	                ret[j][i] /= mat[index[j]][j];
//	            }
//	        }
//	        return ret;
//	    } else {
//	    	throw new IllegalArgumentException("Argument is null.");
//	    }
//    }
//
//    
//    /**
//	 * Puts a matrix in upper triangular
//	 * @param a Left matrix
//	 * @param b Right Matrix
//	 * @return The resulting matrix
//	 */
//    public static void upperTriangular(double a[][], int index[]) 
//    {
//        int length = index.length;
//        double c[] = new double[length];
//        for (int i = 0; i < length; ++i) { 
//            index[i] = i;
//        }
//        for (int i = 0; i < length; ++i) {
//            double c1 = 0;
//            for (int j = 0; j < length; ++j) {
//                double c0 = Math.abs(a[i][j]);
//                if (c0 > c1) c1 = c0;
//            }
//            c[i] = c1;
//        }
//        int k = 0;
//        for (int j = 0; j < length-1; ++j) 
//        {
//            double pi1 = 0;
//            for (int i = j; i < length; ++i) 
//            {
//                double pi0 = Math.abs(a[index[i]][j]);
//                pi0 /= c[index[i]];
//                if (pi0 > pi1) 
//                {
//                    pi1 = pi0;
//                    k = i;
//                }
//            }
//            int itmp = index[j];
//            index[j] = index[k];
//            index[k] = itmp;
//            for (int i = j + 1; i < length; ++i) 	
//            {
//                double pj = a[index[i]][j] / a[index[j]][j];
//                a[index[i]][j] = pj;
//                for (int l = j + 1; l < length; ++l)
//                    a[index[i]][l] -= pj * a[index[j]][l];
//            }
//        }
//    }

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
	 * @param a Left matrix
	 * @param b Right Matrix
	 * @return The resulting matrix
	 */
	public static float[][] matrixMult(float[][] a, float[][] b) {
		int m = a.length;
		int n = b[0].length;
		
		if (a[0].length != b.length) {
			return null;
		}
		
		float[][] result = new float[m][n];
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < b.length; k++) {
					result[i][j] += a[i][k] * b[k][j];
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
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[0].length; j++) {
				mat[i][j] *= s;
			}
		}
		
		return mat;
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
		
		int m = v.length;
		float norm = norm(v);
		
		for (int i = 0; i < m; i++) {
			v[i][0] /= norm;
		}
		
		return v;
	}
	
	/**
	 * A function to find the transpose of a matrix
	 * 
	 * @param mat
	 * @return trans the transpose of mat
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
	 * Gets the determinant
	 * 
	 * @param matrixA
	 * @return
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
	 * Does the trace
	 * 
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