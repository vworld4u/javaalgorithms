package algos.dynamic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ZeroOneKnapsack {

	public static class Knapsack {
		int profit;
		List<Integer> members;

		public Knapsack(List<Integer> members, int profit) {
			this.members = members;
			this.profit = profit;
		}

		public Knapsack(Knapsack another) {
			this.members = new ArrayList<>();
			for (int m : another.members) {
				this.members.add(m);
			}
			this.profit = another.profit;
		}

		public int getProfit() {
			return profit;
		}

		public void setProfit(int profit) {
			this.profit = profit;
		}

		public List<Integer> getMembers() {
			return members;
		}

		public void setMembers(List<Integer> members) {
			this.members = members;
		}
	}

	public static void main(String[] args) {
		String fileName = "./src/tests/dynamic/zerooneknapsack/in-0.txt";
		if (args != null && args.length > 0) {
			fileName = args[0];
		}

		try (Scanner scn = new Scanner(new FileInputStream(fileName))) {
			int n = scn.nextInt();
			int weight = scn.nextInt();
			int[] weights = new int[n];
			int[] profits = new int[n];
			for (int i = 0; i < n; i++) {
				weights[i] = scn.nextInt();
				profits[i] = scn.nextInt();
			}
			Knapsack kn = new Knapsack(new ArrayList<>(), 0);
			Knapsack optimal = findOptimalKnapsack(weight, 0, kn, weights, profits, new Knapsack[weight + 1][n + 1]);
			System.out.println("Number Of Items : " + n);
			System.out.println("Weight : " + weight);
			System.out.println("Weights: " + Arrays.toString(weights));
			System.out.println("Profits: " + Arrays.toString(profits));
			System.out.println("Optimal Solution: ");
			System.out.println(Arrays.toString(optimal.members.toArray()));
			System.out.println("Total Profit Earned : " + optimal.profit);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Knapsack findOptimalKnapsack(int weight, int n, Knapsack knapsack, int[] weights, int[] profits,
			Knapsack[][] memorized) {
		if (weight == 0 || n == weights.length) {
			return knapsack;
		}

		if (weights[n] > weight) {
			if (memorized[weight][n] == null) {
				memorized[weight][n] = findOptimalKnapsack(weight, n + 1, knapsack, weights, profits, memorized);
			}
			return memorized[weight][n];
		}
		if (memorized[weight - weights[n]][n + 1] == null) {
			memorized[weight - weights[n]][n + 1] = findOptimalKnapsack(weight - weights[n], n + 1, knapsack, weights,
					profits, memorized);
		}
		Knapsack including = new Knapsack(memorized[weight - weights[n]][n + 1]);
		including.members.add(n);
		including.setProfit(including.getProfit() + profits[n]);
		System.out.println("Include(" + n + ") Profit = " + including.getProfit());
		if (memorized[weight][n + 1] == null) {
			memorized[weight][n + 1] = findOptimalKnapsack(weight, n + 1, knapsack, weights, profits, memorized);
		}
		Knapsack excluding = new Knapsack(memorized[weight][n + 1]);
		System.out.println("Exclude(" + n + ") Profit = " + excluding.getProfit());

		if (including.profit > excluding.profit) {
			return including;
		} else {
			return excluding;
		}
	}

}
