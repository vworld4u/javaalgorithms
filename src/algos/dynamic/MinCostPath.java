package algos.dynamic;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class MinCostPath {

	public static void main(String[] args) {
		String fileName = "./src/tests/dynamic/mincostpath/in-0.txt";
		if (args != null && args.length > 0) {
			fileName = args[0];
		}
		int[][] minCosts = minCostPaths(fileName);
		System.out.println("Total paths in given matrix : ");
		for (int[] arr : minCosts) {
			System.out.println(Arrays.toString(arr));
		}
	}

	private static int[][] minCostPaths(String fileName) {
		int[][] minCosts;
		int m, n;
		int[][] matrix;
		char[][] from;
		try (Scanner scn = new Scanner(new FileInputStream(fileName))) {
			m = scn.nextInt();
			n = scn.nextInt();
			matrix = new int[m][n];
			minCosts = new int[m][n];
			from = new char[m][n];
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					matrix[i][j] = 0;
					minCosts[i][j] = 0;
					from[i][j] = 'l';
				}
			}
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					matrix[i][j] = scn.nextInt();
					minCosts[i][j] = matrix[i][j];
				}
			}
			for (int i = 1; i < m; i++) {
				minCosts[i][0] = minCosts[i - 1][0] + matrix[i][0];
				from[i][0] = 't';
			}
			for (int i = 1; i < n; i++) {
				minCosts[0][i] = minCosts[0][i - 1] + matrix[0][i];
				from[0][i] = 'l';
			}
			for (int i = 1; i < m; i++) {
				for (int j = 1; j < n; j++) {
					minCosts[i][j] = matrix[i][j] + Math.min(minCosts[i - 1][j], minCosts[i][j - 1]);
					from[i][j] = minCosts[i - 1][j] < minCosts[i][j - 1] ? 't' : 'l';
				}
			}
			System.out.println("Traversal Matrix : ");
			for (char [] tr: from) {
				System.out.println(Arrays.toString(tr));
			}
			return minCosts;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
