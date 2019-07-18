import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MyHashMap<K, V> implements Map61B<K, V> {
    private int size = 0;
    private int initialSize = 16;
    private double loadFactor = 0.75;
    private Set<K> keys;
    private V[] values;
    public MyHashMap() {
        this.keys = new HashSet<>(initialSize);
        this.values = (V[]) new Object[initialSize];
    }
    public MyHashMap(int initialSize) {
        this.initialSize = initialSize;
        this.keys = new HashSet<>(initialSize);
        this.values = (V[]) new Object[initialSize];
    }
    public MyHashMap(int initialSize, double loadFactor) {
        this.initialSize = initialSize;
        this.loadFactor = loadFactor;
        this.keys = new HashSet<>(initialSize);
        this.values = (V[]) new Object[initialSize];
    }

    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {
        keys.clear();
        values = null;
        size = 0;
    }

    /** Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        return keys.contains(key);
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if (containsKey(key)) {
            return values[Math.floorMod(key.hashCode(), initialSize)];
        }
        return null;
    }

    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    @Override
    public void put(K key, V value) {
        if (!containsKey(key)) {
            keys.add(key);
            size++;
        }
        values[Math.floorMod(key.hashCode(), initialSize)] = value;
        if (size / initialSize > loadFactor) {
            resize();
        }
    }

    /** Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        return keys;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public V remove(K key) {
        if (containsKey(key)) {
            V value = values[Math.floorMod(key.hashCode(), initialSize)];
            values[Math.floorMod(key.hashCode(), initialSize)] = null;
            keys.remove(key);
            size--;
            return value;
        }
        return null;
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    @Override
    public V remove(K key, V value) {
        if (containsKey(key) && values[Math.floorMod(key.hashCode(), initialSize)] == value) {
            values[Math.floorMod(key.hashCode(), initialSize)] = null;
            keys.remove(key);
            size--;
            return value;
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return keys.iterator();
    }

    private void resize() {
        HashSet<K> tempKeys = new HashSet<>(2 * initialSize);
        V[] tempValues = (V[]) new Object[2 * initialSize];
        for (K key : keys) {
            tempKeys.add(key);
            tempValues[Math.floorMod(key.hashCode(), initialSize * 2)] =
                    values[Math.floorMod(key.hashCode(), initialSize)];
        }
        keys = tempKeys;
        values = tempValues;
        initialSize *= 2;
    }
}