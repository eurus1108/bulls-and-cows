import java.util.Scanner;

/**
 * Main
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("> ");
        int stringLength = scanner.nextInt();

        if (stringLength > 10) {
            System.out.println(
                    "Error: can't generate a secret number with a length of 11 because there aren't enough unique digits.");
            return;
        }

        String initialCode = randomNumber();
        String secretCode = "";

        for (int i = 0; i < stringLength; i++) {
            secretCode += initialCode.charAt(i);
        }

        System.out.println("The random secret number is " + secretCode + ".");
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