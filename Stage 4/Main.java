import java.util.Scanner;

/**
 * Main
 */
public class Main {
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		int bulls = 0;
		int cows = 0;
		int turn = 1;

		final int stringLength = takeStringLength();
		final String secretCode = secretCode(stringLength);
		System.out.println("Okay, let's start a game!");

		while (true) {
			System.out.println("Turn " + turn + ":");
			System.out.print("> ");
			String guessCode = scanner.nextLine();

			if (guessCode.length() != stringLength) {
				continue;
			}

			for (int i = 0; i < secretCode.length(); i++) {
				if (guessCode.charAt(i) == secretCode.charAt(i)) {
					bulls++;
				}

				for (int j = 0; j < secretCode.length(); j++) {
					if (guessCode.charAt(j) == secretCode.charAt(i) && !(guessCode.charAt(i) == secretCode.charAt(i))) {
						cows++;
					}
				}
			}

			String grade = "";

			if (bulls > 0 && cows > 0) {
				grade = bulls + " bulls and " + cows + " cows";
			} else if (bulls > 0) {
				grade = bulls + " bulls";
			} else if (cows > 0) {
				grade = cows + " cow";
			} else {
				grade = "None";
			}

			System.out.println("Grade: " + grade);
			turn++;

			if (bulls == stringLength) {
				break;
			}

			// This will reset the count for bulls and cows
			bulls = 0;
			cows = 0;
		}

		System.out.println("Congratulations! You guessed the secret code.");

	}

	public static String grader(int bulls, int cows) {
		String grade = "";

		if (bulls > 0 && cows > 0) {
			grade = bulls + " bulls and " + cows + " cows";
		} else if (bulls > 0) {
			grade = bulls + " bulls";
		} else if (cows > 0) {
			grade = cows + " cow";
		} else {
			grade = "None";
		}

		return grade;
	}

	public static int takeStringLength() {
		int stringLength;

		while (true) {
			System.out.println("Please, enter the secret code's length:");
			System.out.print("> ");
			stringLength = Integer.parseInt(scanner.nextLine());

			if (stringLength > 10) {
				System.out.println(
						"Error: can't generate a secret number with a length of 11 because there aren't enough unique digits.");
				continue;
			} else {
				break;
			}
		}

		return stringLength;
	}

	public static String secretCode(int stringLength) {
		String initialCode = randomNumber();
		String secretCode = "";

		for (int i = 0; i < stringLength; i++) {
			secretCode += initialCode.charAt(i);
		}

		return secretCode;
	}

	public static String randomNumber() {
		long psuedoRandomNumber = System.nanoTime();

		// I use StringBuilder because I want to use the reverse() function built in.
		StringBuilder reverseNumber = new StringBuilder(Long.toString(psuedoRandomNumber)).reverse();

		String filteredNumber = filterDuplicate(reverseNumber);
		String code = codeGenerator(filteredNumber);

		return code;
	}

	public static String filterDuplicate(StringBuilder string) {
		StringBuilder filteredNumber = new StringBuilder();

		for (int i = 0; i < string.length(); i++) {
			int j;
			for (j = 0; j < i; j++) {
				if (string.charAt(i) == string.charAt(j)) {
					break;
				}
			}

			if (i == j) {
				filteredNumber.append(string.charAt(i));
			}
		}

		filteredNumber.deleteCharAt(0);

		return filteredNumber.toString();
	}

	public static String codeGenerator(String code) {
		String[] listOfNumbers = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };

		for (int i = 0; i < listOfNumbers.length; i++) {
			if (!code.contains(listOfNumbers[i])) {
				code += listOfNumbers[i];
			}
		}

		return code;
	}
}