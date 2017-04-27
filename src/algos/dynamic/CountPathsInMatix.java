package algos.dynamic;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class CountPathsInMatix {

	public static void main(String[] args) {
		String fileName = "./src/tests/dynamic/countpathsinmatrix/in-0.txt";
		if (args != null && args.length > 0) {
			fileName = args[0];
		}
		try {
			System.out.println("Total paths in given matrix : " + totalpaths(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static int totalpaths(String fileName) throws IOException {
		int m = 0, n = 0;
		int [][] matrix;
		System.out.println("Current Directory : " + Paths.get("./").toAbsolutePath().toString());
		Stream<Path> paths = Files.list(Paths.get("./"));
		paths.forEach(el -> System.out.println(el));
		try (Scanner scn = new Scanner(new FileInputStream(fileName))) {
			m = scn.nextInt();
			n = scn.nextInt();
			int blocks = scn.nextInt();
			matrix = new int [m][n];
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					matrix[i][j] = 0;
				}
			}
			for (int i = 0; i < blocks; i++) {
				int row = scn.nextInt();
				int col = scn.nextInt();
				matrix[row][col] = -1;
			}
			matrix[m - 1][ n - 1] = 1;
			return calculateTotalPaths(matrix, m, n);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	private static int calculateTotalPaths(int[][] matrix, int m, int n) {
		for (int i = m - 1; i >= 0; i--) {
			for (int j = n - 1; j >= 0; j--) {
				if (!(i == m - 1 && j == n - 1) && matrix[i][j] != -1) {
					matrix[i][j] = paths(matrix, m, n, i + 1, j) + paths(matrix, m, n, i, j + 1);
				}
			}
		}
		System.out.println("Matrix Allocation :");
		for (int i = 0; i < m; i++) {
			System.out.println(Arrays.toString(matrix[i]));
		}
		return matrix[0][0];
	}

	private static int paths(int[][] matrix, int m, int n, int i, int j) {
		if (j < n && i < m && matrix[i][j] != -1) {
			return matrix[i][j];
		}
		return 0;
	}
}
