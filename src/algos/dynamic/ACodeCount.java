package algos.dynamic;

import java.util.Arrays;

public class ACodeCount {

	/**
	 * 
	 * 
	 * Problem statement taken from http://www.spoj.com/problems/ACODE/
	 * 
	 * Alice and Bob need to send secret messages to each other and are discussing ways to encode their messages:
	 * 	Alice: “Let’s just use a very simple code: We’ll assign ‘A’ the code word 1, ‘B’ will be 2, and so on down to ‘Z’ being assigned 26.”

Bob: “That’s a stupid code, Alice. Suppose I send you the word ‘BEAN’ encoded as 25114. You could decode that in many different ways!”
Alice: “Sure you could, but what words would you get? Other than ‘BEAN’, you’d get ‘BEAAD’, ‘YAAD’, ‘YAN’, ‘YKD’ and ‘BEKD’. I think you would be able to figure out the correct decoding. And why would you send me the word ‘BEAN’ anyway?”
Bob: “OK, maybe that’s a bad example, but I bet you that if you got a string of length 5000 there would be tons of different decodings and with that many you would find at least two different ones that would make sense.”
Alice: “How many different decodings?”
Bob: “Jillions!”
For some reason, Alice is still unconvinced by Bob’s argument, so she requires a program that will determine how many decodings there can be for a given string using her code.

Input

Input will consist of multiple input sets. Each set will consist of a single line of at most 5000 digits representing a valid encryption (for example, no line will begin with a 0). There will be no spaces between the digits. An input line of ‘0’ will terminate the input and should not be processed.

Output

For each input set, output the number of possible decodings for the input string. All answers will be within the range of a 64 bit signed integer.


	 * @param args
	 */
	public static void main(String[] args) {
		String input = "25114";
		if (args != null && args.length > 0) {
			for (String arg: args) {
				System.out.println("Total Possibilities for Code : " + arg + " is " + countPossibilities(arg));
			}
		} else {
			System.out.println("Total Possibilities for Code : " + input + " is " + countPossibilities(input));
		}
	}

	private static int countPossibilities(String input) {
		int [] counts = new int[input.length() + 1];
		for (int i = 0; i < counts.length; i++) {
			counts[i] = 0;
		}
		counts[counts.length - 2] = 1;
		counts[counts.length - 1] = 1;
		for (int i = input.length() - 2; i >= 0; i--) {
			counts[i] = Integer.parseInt(input.substring(i, i + 2)) <= 26 ? counts[i + 1] + counts[i + 2] : counts[i + 1];
		}
		System.out.println("Counts Array Built = " + Arrays.toString(counts));
		return counts[0];
	}

}