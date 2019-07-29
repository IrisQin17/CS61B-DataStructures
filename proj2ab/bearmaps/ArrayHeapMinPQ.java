package bearmaps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private ArrayList<PriorityNode> heap;
    private HashMap<T, Integer> map;

    private class PriorityNode {
        private T item;
        private double priority;
        private PriorityNode(T i, double p) {
            item = i;
            priority = p;
        }
    }
    public ArrayHeapMinPQ() {
        heap = new ArrayList<>();
        map = new HashMap<>();
    }

    /* Adds an item with the given priority value. Throws an
     * IllegalArgumentException if item is already present.
     * You may assume that item is never null. */
    @Override
    public void add(T item, double priority) {
        if (contains(item)) {
            throw new IllegalArgumentException();
        }
        heap.add(new PriorityNode(item, priority));
        map.put(item, size() - 1);
        swinUp(size() - 1);
    }

    /* Returns true if the PQ contains the given item. */
    @Override
    public boolean contains(T item) {
        return map.containsKey(item);
    }

    /* Returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    @Override
    public T getSmallest() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException();
        }
        return heap.get(0).item;
    }

    /* Removes and returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    @Override
    public T removeSmallest() {
        T smallest = getSmallest();
        swap(0, size() - 1);
        map.remove(smallest);
        heap.remove(size() - 1);
        sinkDown(0);
        return smallest;
    }

    /* Returns the number of items in the PQ. */
    @Override
    public int size() {
        return heap.size();
    }

    /* Changes the priority of the given item. Throws NoSuchElementException if the item
     * doesn't exist. */
    @Override
    public void changePriority(T item, double priority) {
        if (!contains(item)) {
            throw new NoSuchElementException();
        }
        int index = map.get(item);
        double oldPriority = heap.get(index).priority;
        heap.get(index).priority = priority;
        if (oldPriority < priority) {
            sinkDown(index);
        } else {
            swinUp(index);
        }
    }

    private void swap(int i, int j) {
        PriorityNode temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
        map.put(heap.get(i).item, i);   //replace the old values
        map.put(heap.get(j).item, j);
    }

    private void swinUp(int i) {
        if (i <= 0) {
            return;
        }
        int parent = (i - 1) / 2;
        if (smaller(i, parent)) {
            swap(i, parent);
            swinUp(parent);
        }
    }

    private void sinkDown(int i) {
        int leftChild = 2 * i + 1;
        int rightChild = (i + 1) * 2;
        int smallest = i;
        if (leftChild < size() && smaller(leftChild, smallest)) {
            smallest = leftChild;
        }
        if (rightChild < size() && smaller(rightChild, smallest)) {
            smallest = rightChild;
        }
        if (smallest != i) {
            swap(smallest, i);
            sinkDown(smallest);
        }
    }

    private boolean smaller(int i, int j) {
        return heap.get(i).priority < heap.get(j).priority;
    }
}
