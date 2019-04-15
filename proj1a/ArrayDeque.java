public class ArrayDeque<T> {
    private T[] array = (T[]) new Object[8];
    private int size = 0;
    private int nextFirst = 7;
    private int nextLast = 0;         //circular array

    /**Creates an empty array deque.*/
    public ArrayDeque() {
    }

    public ArrayDeque(T item) {
        array[0] = item;
        nextLast++;
        size++;
    }

    /**Creates a deep copy of other.*/
    public ArrayDeque(ArrayDeque other) {
//        for (int i = 0; i < other.size(); i++){     //slower
//            addLast((T) other.get(i));
//        }
        array = (T[]) new Object[other.array.length];
        size = other.size();
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
        System.arraycopy(other.array, 0, array, 0, array.length);
    }

    /**Re-sizes the array to the target capacity*/
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        System.arraycopy(array, (nextFirst + 1) % size, a, 0, size - 1 - nextFirst);
        System.arraycopy(array, 0, a, size - 1 - nextFirst, nextFirst + 1);
        array = a;
        nextFirst = capacity - 1 ;
        nextLast = size;
    }

    /**Downsize the array.*/
    private  void downsize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        if (nextFirst < nextLast) {
            System.arraycopy(array, nextFirst + 1, a, 0, size);
        } else {
            System.arraycopy(array, (nextFirst + 1) % array.length, a, 0, array.length - 1 - nextFirst);
            System.arraycopy(array, 0, a, array.length - 1 - nextFirst, nextLast);
        }
        array = a;
        nextFirst = capacity - 1 ;
        nextLast = size;
    }

    /**Adds an item of type T to the front of the deque.*/
    public void addFirst(T item) {
        if (size == array.length) {
            resize(size*2);
        }
        array[nextFirst] = item;
        nextFirst = (nextFirst - 1) % array.length;
        size++;
    }

    /**Adds an item of type T to the back of the deque.*/
    public void addLast(T item) {
        if (size == array.length) {
            resize(size*2);
        }
        array[nextLast] = item;
        nextLast = (nextLast + 1) % array.length;
        size++;
    }

    /**Returns true if deque is empty, false otherwise.*/
    public boolean isEmpty() {
        return size == 0;
    }

    /**Returns the number of array in the deque.*/
    public int size() {
        return size;
    }

    /**Prints the array in the deque from first to last, separated by a space. Once all the array have been printed, print out a new line.*/
    public void printDeque() {
        int i = (nextFirst + 1) % array.length;
        while (i != nextLast) {
            System.out.print(array[i] + " ");
            i = (i + 1) % array.length;
        }
        System.out.println("\n");
    }

    /**Removes and returns the item at the front of the deque. If no such item exists, returns null.*/
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        nextFirst = (nextFirst + 1) % array.length;
        T item = array[nextFirst];
        array[nextFirst] = null;
        size--;
        if ( 4 * size < array.length) {
            downsize(size * 4);
        }
        return item;
    }

    /**Removes and returns the item at the back of the deque. If no such item exists, returns null.*/
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        nextLast = (nextLast - 1) % array.length;
        T item = array[nextLast];
        array[nextLast] = null;
        size--;
        if ( 4 * size < array.length) {
            downsize(size * 4);
        }
        return item;
    }

    /**Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no such item exists, returns null. Must not alter the deque!*/
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        return array[(nextFirst + 1 + index) % array.length];
    }

//    /**test*/
//    public static void main(String[] args) {
//        ArrayDeque<String> a = new ArrayDeque<>("z");
//        a.addFirst("y");
//        a.addFirst("x");
//        a.addLast("1");
//        a.addLast("2");
//        a.addLast("3");
//        a.addLast("4");
//        a.addLast("5");
//        a.addLast("6");
//        a.addLast("7");
//        a.addFirst("w");
//        a.printDeque();
//        System.out.println(a.get(1));
//        ArrayDeque<String> b = new ArrayDeque<>(a);
//        b.printDeque();
//        b.removeFirst();
//        b.removeFirst();
//        b.removeFirst();
//        b.removeFirst();
//        b.removeFirst();
//        b.removeFirst();
//        b.removeFirst();
//        b.removeFirst();
//        b.removeFirst();
//        b.printDeque();
//    }
}
