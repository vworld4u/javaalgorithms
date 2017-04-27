package algos.dynamic;

public class Fibonacci {

	public static void main(String[] args) {
		int n = 10;
		if (args != null && args.length > 0) {
			n = Integer.parseInt(args[0]);
		}
		System.out.println("Fibonacci(" + n + ") = " + fib(n));
	}
	
	public static int fib(int n) {
		int [] mem = new int[n + 1];
		for (int i = 0; i < n + 1; i++) {
			mem[i] = 0;
		}
		return calculateFib(n, mem);
	}

	private static int calculateFib(int n, int[] mem) {
		if (n < 2) {
			return 1;
		} else if (mem[n] > 0) {
			return mem[n];
		} else {
			mem[n] = calculateFib(n - 1, mem) + calculateFib(n - 2, mem);
			return mem[n];
		}
	}

}
