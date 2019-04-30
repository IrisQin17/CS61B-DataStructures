import static org.junit.Assert.*;
import org.junit.Test;
public class TestArrayDequeGold {
    @Test
    public void randomTest() {
        StudentArrayDeque<Integer> student = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> correct = new ArrayDequeSolution<>();
        String msg = "";
        for (int i = 0; i < 100; i++) {
            int num = StdRandom.uniform(100);  //Returns a random integer uniformly in [0, n).
            if (num < 25) {
                correct.addFirst(num);
                student.addFirst(num);
                msg += "addFirst(" + num + ")\n";
                assertEquals(msg, correct.get(0), student.get(0));
            } else if (num < 50) {
                correct.addLast(num);
                student.addLast(num);
                msg += "addLast(" + num + ")\n";
                assertEquals(msg, correct.get(student.size() - 1), student.get(student.size() - 1));
            } else if (num < 75) {
                if (!correct.isEmpty()) {
                    Integer a = correct.removeFirst();
                    Integer b = student.removeFirst();
                    msg += "removeFirst()\n";
                    assertEquals(msg, a, b);
                }
            } else {
                if (!correct.isEmpty()) {
                    Integer c = correct.removeLast();
                    Integer d = student.removeLast();
                    msg += "removeLast()\n";
                    assertEquals(msg, c, d);
                }
            }
        }
    }
}
