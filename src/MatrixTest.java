public class MatrixTest{

  public static void main(String[] args) {
    double[][] arrayForMatrixA = {{3,2,1}, {0,2,-5}, {-2,1,4}};
    double[][] arrayForMatrixB = {{3,0,1}, {6,2,-1}, {5,4,-2}};
    double[][] arrayForMatrixC = {{-4,2,1,5}, {3,1,-1,9}, {1,-4,8,4}};
    
    Matrix a = new Matrix(arrayForMatrixA);
    Matrix b = new Matrix(arrayForMatrixB);
    Matrix c = new Matrix(arrayForMatrixC);
    
    Matrix d = new Matrix(3,2);
    
    System.out.println("matrix a:");
    System.out.println(a.toString());
    
    System.out.println("matrix b:");
    System.out.println(b.toString());
    
    System.out.println("num rows in a = " + a.numRows());
    System.out.println("num cols in b = " + b.numCols());
    
    System.out.println("element in c at (2,3) = " + c.getElement(2,3) + "\n");
    
    d.setElement(2, 0, 5);
    System.out.println("matrix d with 5 in position (2,0):");
    System.out.println(d.toString());
    
    System.out.println("matrix sum a + b:");
    System.out.println(Matrix.sum(a, b));
    
    System.out.println("matrix product a * b:");
    System.out.println(Matrix.product(a, b));
    
    System.out.println("submatrix of a with 1st row, 1st column deleted:");
    System.out.println(a.subMatrix(1,1));
    
    System.out.println("matrix c:");
    System.out.println(c.toString());
    System.out.println("submatrix of c with 0th row, 2nd column deleted:");
    System.out.println(c.subMatrix(0,2));
    
    System.out.println("determinant of matrix a:");
    System.out.println(a.determinant());
  }
}