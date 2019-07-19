public class RabinKarpAlgorithm {


    /**
     * This algorithm returns the starting index of the matching substring.
     * This method will return -1 if no matching substring is found, or if the input is invalid.
     */
    public static int rabinKarp(String input, String pattern) {
        int iLeng = input.length();
        int pLeng = pattern.length();
        if (iLeng < pLeng) {
            return -1;
        }
        RollingString rollInput = new RollingString(input.substring(0, pLeng), pLeng);
        RollingString rollPattern = new RollingString(pattern, pLeng);
        for (int i = 0; i < iLeng - pLeng; i++) {
            if (rollInput.hashCode() == rollPattern.hashCode()) {
                return i;
            } else {
                rollInput.addChar(input.charAt(pLeng + i - 1));
            }
        }
        return -1;
    }
}
