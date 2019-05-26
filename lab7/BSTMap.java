import java.util.Set;
import java.util.Iterator;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private Node root;             // root of BST

    private class Node {
        private K key;           // sorted by key
        private V val;         // associated data
        private Node left, right;  // left and right subtrees
        private int size;          // number of nodes in subtree

        public Node(K key, V val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    public BSTMap() {
    }

    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
    }

    /** Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        return get(key) != null;
    }

    /** Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        return get(root, key);
    }
    private V get(Node x, K key) {
        if (x == null) {
            return null;
        } else if (key.compareTo(x.key) < 0) {
            return get(x.left, key);
        } else if (key.compareTo(x.key) > 0) {
            return get(x.right, key);
        } else {
            return x.val;
        }
    }

    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size(root);
    }
    private int size(Node x) {
        if (x == null) {
            return 0;
        } else {
            return x.size;
        }
    }

    /** Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        root = put(root, key, value);
    }
    private Node put(Node x, K key, V value) {
        if (x == null) {
            x = new Node(key, value, 1);
        }else if (key.compareTo(x.key) < 0) {
            x.left = put(x.left, key, value);
        }else if (key.compareTo(x.key) > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.val = value;
        }
        x.size = 1 + size(x.left) + size(x.right);
        return x;
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    /** Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    /** Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /** Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    /** prints out your BSTMap in order of increasing Key */
    public void printInOrder() {
        printInOrder(root);
    }
    private void printInOrder(Node x) {
        if (x == null) {
            return;
        }
        if (x.left == null) {
            System.out.println("Key:" + x.key + ", Value:" + x.val);
        } else {
            printInOrder(x.left);
            System.out.println("Key:" + x.key + ", Value:" + x.val);
        }
        if (x.right == null) {
            return;
        } else {
            printInOrder(x.right);
            return;
        }
    }

//    public static void main(String[] args) {
//        BSTMap b = new BSTMap<Integer, Integer>();
//        b.put(5,5);
//        b.put(8,8);
//        b.put(2,2);
//        b.put(0,0);
//        b.put(11,11);
//        b.put(9,9);
//        b.put(4,4);
//        b.printInOrder();
//    }
}
