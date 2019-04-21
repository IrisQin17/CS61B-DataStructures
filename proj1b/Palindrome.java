/**A class for palindrome operations. */
public class Palindrome {
    /**Convert a string to a deque where the characters appear in the same order. */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            deque.addLast(c);
        }
        return deque;
    }

    public boolean isPalindrome(String word) {
        if (word == null) {
            return false;
        }
        Deque<Character> deque = wordToDeque(word);
        return isPalindromeHelper1(deque);
    }

    /**Little helper for isPalindrome(String word). */
    private boolean isPalindromeHelper1(Deque<Character> deque) {
        if (deque.size() < 2) {
            return true;
        }
        return deque.removeFirst() == deque.removeLast()
                && isPalindromeHelper1(deque);
    }




    /**return true if the word is a palindrome according to the CharacterComparator. */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word == null) {
            return false;
        }
        Deque<Character> deque = wordToDeque(word);
        return isPalindromeHelper2(deque, cc);
    }

    /**Little helper for isPalindrome(String word, CharacterComparator cc). */
    private boolean isPalindromeHelper2(Deque<Character> deque, CharacterComparator cc) {
        if (deque.size() < 2) {
            return true;
        }
        return cc.equalChars(deque.removeFirst(), deque.removeLast())
                && isPalindromeHelper2(deque, cc);
    }
}
