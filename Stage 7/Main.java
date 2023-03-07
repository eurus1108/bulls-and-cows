import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * Main
 */
public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String secretCode = gameSettings();
        gameLogic(secretCode);
    }

    public static void gameLogic(String secretCode) {
        int turn = 1;

        while (true) {
            System.out.println("Turn " + turn + ": ");

            String guessCode = scanner.next();

            if (guessCode.length() != secretCode.length()) {
                continue;
            }

            VerifyMatch verify = new VerifyMatch();

            int cows = verify.checkForCows(secretCode, guessCode);
            int bulls = verify.checkForBulls(secretCode, guessCode);

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

            if (bulls == secretCode.length()) {
                break;
            }
        }
    }

    public static String gameSettings() {
        int lengthOfCode = 0;
        int possibleSymbol = 0;

        try {
            System.out.println("Input the length of the secret code:");
            lengthOfCode = scanner.nextInt();
            System.out.println("Input the number of possible symbols in the code:");
            possibleSymbol = scanner.nextInt();
        } catch (InputMismatchException e) {
            // TODO: handle exception
            System.out.println("Error: \"" + lengthOfCode + "\" isn't a valid number.");
            System.exit(0);
        }

        if (lengthOfCode > possibleSymbol) {
            System.out.println("Error: it's not possible to generate a code with a length of " + lengthOfCode + " with "
                    + possibleSymbol + " unique symbols.");
            System.exit(0);
        }

        if (possibleSymbol > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            System.exit(0);
        }

        String stars = "";
        for (int i = 0; i < lengthOfCode; i++) {
            stars += "*";
        }

        int minNumber = 0;
        int maxNumber = 0;

        if (possibleSymbol >= 10) {
            maxNumber = 9;
        } else {
            maxNumber = possibleSymbol;
        }

        char minLetter = 97;
        char maxLetter = (char) ((char) (possibleSymbol - 11) + 97);

        String range = "";

        if (possibleSymbol > 10) {
            range = " (" + minNumber + "-" + maxNumber + ", " + minLetter + "-" + maxLetter + ").";
        } else {
            range = " (" + minNumber + "-" + maxNumber + ").";
        }

        System.out.println("The secret code prepared: " + stars + range);
        System.out.println("Okay, let's start a game!");

        return secretCode(lengthOfCode, possibleSymbol);
    }

    public static final String secretCode(int length, int bound) {
        Random random = new Random();
        final String collection = "0123456789abcdefghijklmnopqrstuvwxyz";
        String possibleSymbol = "";
        String secretCode = "";

        for (int i = 0; i < bound; i++) {
            possibleSymbol += collection.charAt(i);
        }

        while (secretCode.length() != length) {
            final int index = random.nextInt(possibleSymbol.length());
            char ch = collection.charAt(index);

            if (filterDuplicate(ch, secretCode)) {
                continue;
            }

            secretCode += collection.charAt(index);
        }

        return secretCode;
    }

    public static boolean filterDuplicate(char character, String string) {
        for (int i = 0; i < string.length(); i++) {
            if (character == string.charAt(i)) {
                return true;
            }
        }

        return false;
    }
}