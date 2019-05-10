package creatures;

import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

import static huglife.HugLifeUtils.randomEntry;

/**A fierce blue-colored predator that enjoys nothing more than snacking on hapless Plips. */
public class Clorus extends Creature {
    /**
     * red color.
     */
    private int r = 34;
    /**
     * green color.
     */
    private int g = 0;
    /**
     * blue color.
     */
    private int b = 231;
    /**
     * creates clorus with energy equal to E.
     */
    public Clorus (double e) {
        super("clorus");
        energy = e;
    }
    /**
     * creates a clorus with energy equal to 1.
     */
    public Clorus() {
        this(1);
    }
    /**
     * Return color
     */
    public Color color() {
        return color(r, g, b);
    }

    /**
     * Cloruses should lose 0.03 units of energy on a MOVE action.
     */
    public void move() {
        if (energy < 0.03) {
            energy = 0;
        } else {
            energy -= 0.03;
        }
    }

    /**
     * Called when this creature attacks C.
     */
    public void attack(Creature c) {
        energy += c.energy();

    }

    /**
     * when a Clorus replicates, it keeps 50% of its energy.
     * The other 50% goes to its offspring. No energy is lost in the replication process.
     */
    public Clorus replicate() {
        energy /= 2;
        return new Clorus(energy);
    }

    /**
     * Cloruses should lose 0.01 units of energy on a STAY action.
     */
    public void stay() {
        if (energy < 0.01) {
            energy = 0;
        } else {
            energy -= 0.01;
        }
    }

    /**
     * 1.If there are no empty squares, the Clorus will STAY
     * (even if there are Plips nearby they could attack since plip squares do not count as empty squares).
     * 2.Otherwise, if any Plips are seen, the Clorus will ATTACK one of them randomly.
     * 3.Otherwise, if the Clorus has energy >= one, it will REPLICATE to a random empty square.
     * 4.Otherwise, the Clorus will MOVE to a random empty square.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        /* Rule 1 */
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> nearbyPlips = new ArrayDeque<>();
        for (Direction d : neighbors.keySet()) {
            if (neighbors.get(d).name().equals("empty")) {
                emptyNeighbors.addFirst(d);
            } else if (neighbors.get(d).name().equals("plip")) {
                nearbyPlips.addFirst(d);
            }
        }
        if (emptyNeighbors.size() == 0) {
            return new Action(Action.ActionType.STAY);
        }
        /* Rule 2 */
        if (nearbyPlips.size() != 0) {
            return new Action(Action.ActionType.ATTACK, randomEntry(nearbyPlips));
        }
        /* Rule 3 */
        if (energy >= 1) {
            return new Action(Action.ActionType.REPLICATE, randomEntry(emptyNeighbors));
        }
        /* Rule 4 */
        return new Action(Action.ActionType.MOVE, randomEntry(emptyNeighbors));
    }






}
