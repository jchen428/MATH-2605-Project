import org.ojalgo.matrix.BasicMatrix;
import org.ojalgo.matrix.BasicMatrix.Factory;
import org.ojalgo.matrix.PrimitiveMatrix;
import java.lang.Math;


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
		Factory<PrimitiveMatrix> testFactory = PrimitiveMatrix.FACTORY;
		double[][] testData = new double[][] {{5.0, 2.0, 3.0, 6.0}, {4.0, 5.0, 6.0, 8.0}, {7.0, 8.0, 9.0, 3.0}, {7.0, 8.0, 9.0, 4.0}};
		BasicMatrix<?> testMatrix = testFactory.rows(testData);
		//System.out.println(testMatrix.toString());
		
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
	
	
//	//Finds the inverse of a square matrix
//	public static double[][] inverse(double[][] mat){
//		System.out.println("Original Matrix:");
//		for (int i = 0; i < mat.length; i++) {
//			for (int j = 0; j < mat[i].length; j++) {
//				System.out.print(mat[i][j] + " ");
//			}
//			System.out.println("");
//		}
//		if (mat.length == 1 && mat[0].length == 1) {
//			return mat;
//		} else if (mat.length == 2 && mat[0].length == 2) {
//			double a = mat[0][0];
//			double b = mat[0][1];
//			double c = mat[1][0];
//			double d = mat[1][1];
//			double det = (a*d) - (b*c);
//			mat[0][0] = d/det;
//			mat[0][1] = -b/det;
//			mat[1][0] = -c/det;
//			mat[1][1] = a/det;
//		} else {
//			double[][] id = new double[mat.length][mat[0].length];
//
//			for (int i = 0; i < id.length; i++) {
//				for (int j = 0; j < id[i].length; j++) {
//					if (i == j) {
//						id[i][j] = 1.0;
//					}
//				}
//			}
//			
//			double[][] full = new double[mat.length][mat[0].length * 2];
//			for (int i = 0; i < full.length; i++) {
//				for (int j = 0; j < full[i].length; j++) {
//					if (j < mat.length) {
//						full[i][j] = mat[i][j];
//					} else {
//						full[i][j] = id[i][j - mat[0].length];
//					}
//				}
//			}
//			
//			System.out.println("\nFull matrix before:");
//			for (int i = 0; i < full.length; i++) {
//				for (int j = 0; j < full[i].length; j++) {
//					System.out.print(full[i][j] + " ");
//				}
//				System.out.println("");
//			}
//			for (int h = 0; h < full.length; h++) {
//				double d = full[h][h];
//				for (int i = h; i < full[h].length; i++) {
//					full[h][i] = full[h][i] / d;
//				}
//				for (int i = h + 1; i < full.length; i++) {
//					double f = full[i][h];
//					for (int j = h; j < full[i].length; j++) {
//						full[i][j] = full[i][j] - full[h][j] * f;
//					}
//				}
//			}
//			
//			for (int c = full.length - 1; c > 0; c--) {
//				//c is the index of the current column
//				for (int r = c - 1; r >= 0; r--) {
//					System.out.println("H");
//					//r is the index of the position above the 1.0 in column c
//				    double mult = full[r][c];
//				    for (int p = c; p < full.length; p++) {
//					    //p is the column position of all of the ones in the same row as r
//				    	full[r][p] = full[r][p] - (mult * full[r+1][p]);
//				    }
//				}
//				
//			}
}
