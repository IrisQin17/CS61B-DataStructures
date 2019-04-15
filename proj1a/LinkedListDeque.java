public class LinkedListDeque<T> {
    private Node sentinel = new Node(null, null, null);                   //circular sentinel
    private int size = 0;
    private class Node {
        private Node prev, next;
        private T item;
        private Node(Node p, T i, Node n) {
            prev = p;
            item = i;
            next = n;
        }
    }
    public LinkedListDeque() {
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    /**Creates a deep copy of other*/
    public LinkedListDeque(LinkedListDeque other) {
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        Node temp = other.sentinel;
        for (int i = 0; i < other.size(); i++) {
            addLast(temp.next.item);
            temp = temp.next;
        }
    }

    /**Adds an item of type T to the front of the deque.*/
    public void addFirst(T item) {
        sentinel.next = new Node(sentinel, item, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size++;
    }

    /**Adds an item of type T to the back of the deque.*/
    public void addLast(T item) {
        sentinel.prev = new Node(sentinel.prev, item, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size++;
    }

    /**Returns true if deque is empty, false otherwise.*/
    public boolean isEmpty() {
        return size == 0;
    }

    /**Returns the number of items in the deque.*/
    public int size() {
        return size;
    }

    /**Prints the items in the deque from first to last, separated by a space.
     * Once finished, print out a new line.*/
    public void printDeque() {
        Node temp = sentinel;
        while (temp.next != sentinel) {
            System.out.print(temp.next.item + " ");
            temp = temp.next;
        }
        System.out.println("\n");
    }

    /**Removes and returns the first item. If no such item exists, returns null.*/
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T item = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return item;
    }

    /**Removes and returns the last item. If no such item exists, returns null.*/
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T item = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return item;
    }

    /** Gets the item at the given index, where 0 is the front.
     * If no such item exists, returns null. Must not alter the deque!*/
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        Node temp = sentinel;
        for (int i = 0; i <= index; i++) {
            temp = temp.next;
        }
        return temp.item;
    }

    /**Same as get, but uses recursion.*/
    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        if (index == 0) {
            return sentinel.next.item;
        }
        LinkedListDeque<T> temp = new LinkedListDeque<>(this);
        temp.removeFirst();
        return temp.getRecursive(index - 1);
    }

//    public static void main(String[] args) {
//        LinkedListDeque<String> x = new LinkedListDeque<>("hello");
//        x.addFirst("world");
//        x.addFirst("amazing");
//        x.addLast("!");
//        x.printDeque();
////        LinkedListDeque<String> y = new LinkedListDeque<>();
////        y.addFirst("aaa");
////        y.addFirst("bbb");
////        y.printDeque();
//        LinkedListDeque<String> z = new LinkedListDeque<>(x);
//        z.printDeque();
//        System.out.println(z.getRecursive(3));
//    }

}
