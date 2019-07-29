package bearmaps;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayHeapMinPQTest {
    PrintHeapDemo print = new PrintHeapDemo();
    @Test
    public void basicTest() {
        ArrayHeapMinPQ<String> a = new ArrayHeapMinPQ<>();
        int heapSize = 10;
        for (int i = heapSize - 1; i >= 0; i--) {
            a.add("a" + i, i);
        }

        assertTrue(a.contains("a0"));
        assertEquals(a.size(), heapSize);
        assertEquals(a.getSmallest(), "a0");

//        int demoSize = a.size();
//        String[] demo = new String[demoSize + 1];
//        for (int i = 0; i < demoSize; i++) {
//            demo[i + 1] = a.heap.get(i).item;
//        }
//        print.printFancyHeapDrawing(demo);
    }

    @Test
    public void removeSmallestTest() {
        ArrayHeapMinPQ<String> b = new ArrayHeapMinPQ<>();
        int heapSize = 10;
        for (int i = 0; i < heapSize; i++) {
            b.add("b" + i, i);
        }
        assertEquals(b.removeSmallest(), "b0");
        assertEquals(b.size(), 9);
        assertFalse(b.contains("b0"));

//        int demoSize = b.size();
//        String[] demo = new String[demoSize + 1];
//        for (int i = 0; i < demoSize; i++) {
//            demo[i + 1] = b.heap.get(i).item;
//        }
//        print.printFancyHeapDrawing(demo);
    }

    @Test
    public void changePriorityTest() {
        ArrayHeapMinPQ<String> c = new ArrayHeapMinPQ<>();
        int heapSize = 10;
        for (int i = 0; i < heapSize; i++) {
            c.add("c" + i, i);
        }
        c.changePriority("c7", -5);

//        int demoSize = c.size();
//        String[] demo = new String[demoSize + 1];
//        for (int i = 0; i < demoSize; i++) {
//            demo[i + 1] = c.heap.get(i).item;
//        }
//        print.printFancyHeapDrawing(demo);
    }

}
