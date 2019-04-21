public interface Deque<T> {
    /**Adds an item of type T to the front of the deque.*/
    void addFirst(T item);
    /**Adds an item of type T to the back of the deque.*/
    void addLast(T item);
    /**Returns true if deque is empty, false otherwise.*/
    boolean isEmpty();
    /**Returns the number of items in the deque.*/
    int size();
    /**Prints the items in the deque from first to last, separated by a space.
     * Once finished, print out a new line.*/
    void printDeque();
    /**Removes and returns the first item. If no such item exists, returns null.*/
    T removeFirst();
    /**Removes and returns the last item. If no such item exists, returns null.*/
    T removeLast();
    /** Gets the item at the given index, where 0 is the front.
     * If no such item exists, returns null. Must not alter the deque!*/
    T get(int index);
}
