package algos.dynamic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoinChange {

	public static void main(String[] args) {
		int amount =  124;
		int [] coins = new int [] { 1, 2, 5, 10, 20 };
		if (args != null && args.length > 2) {
			amount = Integer.parseInt(args[0]);
			coins = new int [args.length - 1];
			for (int i = 0; i < args.length - 1; i++) {
				coins[i] = Integer.parseInt(args[i + 1]);
			}	
		}
		System.out.println("Coin Denominations: " + Arrays.toString(coins));
		System.out.println("Amount to disburse: " + amount);
		System.out.println("Coins and Denominations:");
		List<Integer> denominations = coinChange(coins, amount);
		for (int i = 0; i < denominations.size(); i+= 2) {
			System.out.println("\t " + denominations.get(i) + " X " + denominations.get(i + 1));
		}
	}

	public static List<Integer> executeProgram(String [] args) throws Exception {
		int amount =  124;
		int [] coins = new int [] { 1, 2, 5, 10, 20 };
		if (args != null && args.length > 2) {
			amount = Integer.parseInt(args[0]);
			coins = new int [args.length - 1];
			for (int i = 0; i < args.length - 1; i++) {
				coins[i] = Integer.parseInt(args[i + 1]);
			}
			List<Integer> denominations = coinChange(coins, amount);
			return denominations;
		} else {
			throw new Exception("No necessaryInputs found");
		}
	}

	private static List<Integer> coinChange(int[] coins, int amount) {
		int [] c = new int [amount + 1];
		int [] prevs = new int [amount + 1];
		for (int i = 0; i < amount + 1; i++) {
			c[i] = 0;
			prevs[i] = 0;
		}

		for (int a = 1; a <= amount; a++) {
			int prev = 0;
			int min =amount + 1;
			for (int j = 0; j < coins.length && coins[j] <= a; j++) {
				if (c[a - coins[j]] + 1  < min) {
					min = c[a - coins[j]] + 1;
					prev = coins[j];
				}
			}
			c[a] = min;
			prevs[a] = prev;
		}
		System.out.println("Intermediaries : " + Arrays.toString(c));
		System.out.println("Prev Coins : " + Arrays.toString(prevs));
		Map<Integer, Integer> map = new HashMap<>();
		while (amount > 0) {
			map.put(prevs[amount], map.getOrDefault(prevs[amount], 0) + 1);
			amount = amount - prevs[amount];
		}
		List<Integer> result = new ArrayList<>(map.size() * 2);
		for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
			result.add(entry.getKey());
			result.add(entry.getValue());
		}
		return result;
	}

}
