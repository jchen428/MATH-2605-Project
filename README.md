MATH-2605-Project
=================

I. The Gauss-Newton Method

QR-Factorization
1. I'll write this once I figure out how she wants us to take in user input for matrices.

Gauss-Newton
1. Open and run gn_qua.java, gn_exp.java, gn_log.java, or gn_rat.java for quadratic,
    exponential, logarithmic, and rational curves respectively.
2. Enter the absolute file path to data file.
3. One at a time, enter the initial guesses for parameters a, b, and c.
4. Enter the number of iterations to run the Gauss-Newton algorithm.
5. Enter '1' or '2' to use either Householders reflections or Givens rotations respectively.
6. The resulting Beta values will be the final set of values printed.

II. Convergence of the Power Method
In Eclipse, open our project and navigate to and open the PowerMethodMain file. Run the file as a main. The program uses our coded power method and method to make at least 1000 matrices to grab points for the graphs that will be plotted when the program is run. 
The first graph that will pop up is the traces vs. determinants graph, colored by the number of iterations used. The second graph is the same, but colored according to the inverse iterations. The graphs are colored in intervals of 10.
To print out the eignevalue and eigenvector being checked, call PowerMethodConvergance.printValues(float[][] matrix, float[] vector, float tolerance, int MaxTimes), with the parameters being the matrix you want the values for, 
your starting vector, tolerance, and max iteration value. 