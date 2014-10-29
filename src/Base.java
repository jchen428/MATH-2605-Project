import org.ojalgo.matrix.BasicMatrix;
import org.ojalgo.matrix.BasicMatrix.Factory;
import org.ojalgo.matrix.PrimitiveMatrix;


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
		double[][] testData = new double[][] {{1.0, 2.0, 3.0}, {4.0, 5.0, 6.0}, {7.0, 8.0, 9.0}};
		BasicMatrix<?> testMatrix = testFactory.rows(testData);
		System.out.println(testMatrix.toString());
	
}
