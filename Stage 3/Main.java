import java.util.Scanner;

/**
 * Main
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // System.out.print("> ");
        long psuedoRandomNumber = System.nanoTime();

        StringBuilder code = new StringBuilder(Long.toString(psuedoRandomNumber));
        System.out.println("Original String: " + code);

        code.reverse();
        System.out.println("Reverse String: " + code);

        StringBuilder noDuplicate = new StringBuilder(removeDuplicate(code));

        System.out.println("No Duplicate: " + noDuplicate);
    }

    public static StringBuilder removeDuplicate(StringBuilder string) {
        StringBuilder code = new StringBuilder();

        for (int i = 0; i < string.length(); i++) {
            int j;
            for (j = 0; j < i; j++) {
                if (string.charAt(i) == string.charAt(j)) {
                    break;
                }
            }

            if (i == j) {
                code.append(string.charAt(i));
            }
        }

        code.deleteCharAt(0);

        return code;
    }
}