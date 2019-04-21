import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome1() {
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("s"));
        assertTrue(palindrome.isPalindrome("abccba"));
        assertTrue(palindrome.isPalindrome("abcba"));
        assertTrue(palindrome.isPalindrome("aaaa"));
        assertFalse(palindrome.isPalindrome(null));
        assertFalse(palindrome.isPalindrome("cat"));
        assertFalse(palindrome.isPalindrome("abcdba"));
        assertFalse(palindrome.isPalindrome("sssssssd"));
    }

    @Test
    public void testIsPalindrome2() {
        CharacterComparator cc = new OffByOne();
        assertTrue(palindrome.isPalindrome("", cc));
        assertTrue(palindrome.isPalindrome("s", cc));
        assertTrue(palindrome.isPalindrome("flake", cc));
        assertFalse(palindrome.isPalindrome("abcba", cc));
        assertFalse(palindrome.isPalindrome("cccc", cc));
        assertFalse(palindrome.isPalindrome(null, cc));
    }
}
