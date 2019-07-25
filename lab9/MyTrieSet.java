import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyTrieSet implements TrieSet61B {
    private static final int R = 128;   //ASCII
    private Node root;      // root of trie
    private class Node {
        private char character;
        private boolean isKey;
        private HashMap<Character, Node> map;
        private Node(char c, boolean b) {
            character = c;
            isKey = b;
            map = new HashMap<>(R);
        }
    }

    public MyTrieSet() {
        root = new Node(' ', true);
    }

    /** Clears all items out of Trie */
    @Override
    public void clear() {
        root.map = new HashMap<>(R);
    }

    /** Returns true if the Trie contains KEY, false otherwise */
    @Override
    public boolean contains(String key) {
        if (key == null || key.length() < 1) {
            return false;
        }
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                return false;
            }
            curr = curr.map.get(c);
        }
        return curr.isKey;
    }

    /** Inserts string KEY into Trie */
    @Override
    public void add(String key) {
        if (key == null || key.length() < 1) {
            return;
        }
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                curr.map.put(c, new Node(c, false));
            }
            curr = curr.map.get(c);
        }
        curr.isKey = true;
    }

    /** Returns a list of all words that start with PREFIX */
    @Override
    public List<String> keysWithPrefix(String prefix) {
        List<String> res = new ArrayList<>();
        if (prefix == null || prefix.length() < 1) {
            return res;
        }
        Node curr = root;
        for (int i = 0, n = prefix.length(); i < n; i++) {
            char c = prefix.charAt(i);
            if (!curr.map.containsKey(c)) {
                return res;
            }
            curr = curr.map.get(c);
        } //curr is now at the end of the prefix's next node
        if (curr.isKey) {
            res.add(prefix);
        }
        for (Node i : curr.map.values()) {
            prefixHelper(prefix + i.character, res, i);
        }
        return res;
    }

    private void prefixHelper(String s, List<String> l, Node n) {
        if (n.isKey) {
            l.add(s);
        }
        for (Node i : n.map.values()) {
            prefixHelper(s + i.character, l, i);
        }
    }

    /** Returns the longest prefix of KEY that exists in the Trie
     * Not required for Lab 9. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }
}
