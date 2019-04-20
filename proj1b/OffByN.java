public class OffByN implements CharacterComparator {
    public int n;
    public OffByN(int N) {
        n = N;
    }

    /**returns true for characters that are different by exactly N. */
    @Override
    public boolean equalChars(char x, char y) {
        int diff = x - y;
        return diff == n || diff == -n;
    }

}