import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Solver for the Flight problem (#9) from CS 61B Spring 2018 Midterm 2.
 * Assumes valid input, i.e. all Flight start times are >= end times.
 * If a flight starts at the same time as a flight's end time, they are
 * considered to be in the air at the same time.
 */
public class FlightSolver {
    private int maxPassengers = 0;
    public FlightSolver(ArrayList<Flight> flights) {

        PriorityQueue<Flight> startMinPQ = new PriorityQueue<>(new Comparator<Flight>() {
            @Override                                           //old expression
            public int compare(Flight o1, Flight o2) {
                return Integer.compare(o1.startTime(), o2.startTime());
            }
        });

        PriorityQueue<Flight> endMinPQ = new PriorityQueue<>((o1, o2) ->
                Integer.compare(o1.endTime(), o2.endTime()));   //lambda expression, cleaner
        startMinPQ.addAll(flights);
        endMinPQ.addAll(flights);
        int temp = 0;
        while (startMinPQ.size() != 0) {
            if (startMinPQ.peek().startTime() <= endMinPQ.peek().endTime()) {
                temp += startMinPQ.poll().passengers();
                if (maxPassengers < temp) {
                    maxPassengers = temp;
                }
            } else {
                temp -= endMinPQ.poll().passengers();
            }
        }
    }

    public int solve() {
        return maxPassengers;
    }
}
