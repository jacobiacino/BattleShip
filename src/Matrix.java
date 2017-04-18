
public class Matrix {
	
	private double[][] matrix;
	
	public Matrix(double[][] matrix) {
		this.matrix = matrix;
	}
	
	public Matrix(int rows, int cols) {
		matrix = new double[rows][cols];
	}

	public int numRows() {
		return matrix.length;
	}
	
	public int numCols() {
		return matrix[0].length;
	}
	
	public double getElement(int row, int col) {
		return matrix[row][col];
	}
	
	public void setElement(int row, int col, double val) {
		matrix[row][col] = val;
	}
	
	public static Matrix sum(Matrix a, Matrix b) {
		Matrix sum = new Matrix(a.numRows(), a.numCols());
		for (int i = 0; i < a.numRows(); i++) {
			for (int j = 0; j < a.numCols(); j++) {
				sum.setElement(i, j, a.getElement(i, j) + b.getElement(i, j));
			}
		}
		return sum;
	}
	
	public static Matrix product(Matrix a, Matrix b) {
		Matrix product = new Matrix(a.numRows(), b.numCols());
		for (int i = 0; i < product.numRows(); i++) {
			for (int j = 0; j < product.numCols(); j++) {
				for (int k = 0; k < a.numCols(); k++) {
					product.setElement(i, j, product.getElement(i, j)
							+ (a.getElement(i, k) * b.getElement(k, j)));
				}
			}
		}
		return product;
	}
	
	public Matrix subMatrix(int deletedRow, int deletedCol) {
		if (deletedRow > matrix.length - 1
				|| deletedCol > matrix[0].length - 1
				|| matrix.length == 1
				|| matrix[0].length == 1) {
			return null;
		}
		
		Matrix subMatrix = new Matrix(matrix.length - 1, matrix[0].length - 1);
		int subRow = 0;
		int subCol = 0;
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (i != deletedRow && j != deletedCol) {
					subRow = i;
					subCol = j;
					if (i > deletedRow) {
						subRow--;
					}
					if (j > deletedCol) {
						subCol--;
					}
					subMatrix.setElement(subRow, subCol, matrix[i][j]);
				}
			}
		}
		return subMatrix;
	}
	
	public double determinant() {
		if (matrix.length != matrix[0].length) {
			return 0;
		}
		double determinant = 0;
		int coefficient = 1;
		if (matrix.length == 2) {
			determinant = (matrix[0][0] * matrix[1][1]) - (matrix[1][0] * matrix[0][1]);
		} else {
			for (int j = 0; j < matrix[0].length; j++) {
				determinant += coefficient*(matrix[0][j] * subMatrix(0, j).determinant());
				coefficient *= -1;
			}
		}
		return determinant;
	}
	
	public String toString() {
		String matrixString = "";
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (j == 0) {
					matrixString += matrix[i][j];
				} else {
					matrixString += ("\t" + matrix[i][j]);
				}
			}
			matrixString += "\n";
		}
		return matrixString;
	}
}
