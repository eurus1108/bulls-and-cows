/**
 * VerifyMatch
 */
class VerifyMatch {
    public int checkForCows(String secretCode, String guessCode) {
        int cows = 0;

        for (int i = 0; i < secretCode.length(); i++) {
            if (secretCode.charAt(i) != guessCode.charAt(i) && secretCode.indexOf(guessCode.charAt(i)) != -1) {
                cows++;
            }
        }

        return cows;
    }

    public int checkForBulls(String secretCode, String guessCode) {
        int bulls = 0;

        for (int i = 0; i < secretCode.length(); i++) {
            if (secretCode.charAt(i) == guessCode.charAt(i)) {
                bulls++;
            }
        }

        return bulls;
    }
}