import java.util.Scanner;

/**
 * Main
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        final String secretCode = "9305";
        int bulls = 0;
        int cows = 0;

        System.out.print("> ");
        String guessCode = scanner.nextLine();

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
            grade = bulls + " bulls(s) and " + cows + " cows(s)";
        } else if (bulls > 0) {
            grade = bulls + " bulls(s)";
        } else if (cows > 0) {
            grade = cows + " cow(s)";
        } else {
            grade = "None";
        }

        System.out.println("Grade: " + grade + ". The secret code is " + secretCode + ".");
    }
}
