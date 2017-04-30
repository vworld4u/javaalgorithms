package algos.dynamic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class MaxSizeSubMatrix {

	public static void main(String[] args) {
		String fileName = "./src/tests/dynamic/maxsizesubmatrix/in-0.txt";
		if (args != null && args.length > 0) {
			fileName = args[0];
		}

		try (Scanner scn = new Scanner(new FileInputStream(fileName))) {
			int m = scn.nextInt();
			int n = scn.nextInt();
			int[][] matrix = new int[m][n];
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					matrix[i][j] = scn.nextInt();
				}
			}
			int [][] submatrix  = findSubMatrix(m, n, matrix);
			System.out.println("Dimension of Matrix : " + m + " X " + n);
			System.out.println("Matrix : ");
			for (int i = 0; i < m; i++) {
				System.out.println(Arrays.toString(matrix[i]));
			}
			System.out.println("Optimal Solution: ");
			for (int i = 0; i < m; i++) {
				System.out.println(Arrays.toString(submatrix[i]));
			}
			int max = 0, posI = 0, posJ = 0;
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					if (submatrix[i][j] > max) {
						max = submatrix[i][j];
						posI = i;
						posJ = j;
					}
				}
			}
			System.out.println("Max Sub Array of Ones is of size " + max + " and Starts at (" + (posI - max + 1) + ", " + (posJ - max + 1) + ")");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static int[][] findSubMatrix(int m, int n, int[][] matrix) {
		int sub[][] = new int[m][n];
		for (int i = 1; i < m; i++) {
			sub[i][0] = matrix[i][0];
			sub[0][i] = matrix[0][i];
		}
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				sub[i][j] = matrix[i][j] == 0 ? 0 : Math.min(Math.min(sub[i - 1][j], sub[i - 1][j - 1]), sub[i][j - 1]) + 1;
			}
		}
		return sub;
	}

}
