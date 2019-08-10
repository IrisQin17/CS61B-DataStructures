package bearmaps;

import java.util.ArrayList;
import java.util.List;

public class KDTree implements PointSet {
    private final List<Point> points;
    private Node root;
    /**You can assume points has at least size 1. */
    public KDTree(List<Point> points) {
        this.points = points;
        for (Point i : points) {
            root = insert(root, i, false);
        }
    }

    private class Node {
        private Point point;
        private Node leftChild, rightChild;
        private boolean isVertical;
        private Node(Point point, boolean isVertical) {
            this.point = point;
            this.isVertical = isVertical;
            leftChild = null;
            rightChild = null;
        }
        private Point getPoint() {
            return point;
        }
        private Node getLeftChild() {
            return leftChild;
        }
        private Node getRightChild() {
            return rightChild;
        }
        private boolean getIsVertial() {
            return isVertical;
        }
    }

    private Node insert(Node curr, Point point, boolean isVertical) {
        if (curr == null) {
            return new Node(point, isVertical);
        }
        if (isVertical) {
            if (point.getY() < curr.getPoint().getY()) {
                curr.leftChild = insert(curr.getLeftChild(), point, !isVertical);
            } else {
                curr.rightChild = insert(curr.getRightChild(), point, !isVertical);
            }
        } else {
            if (point.getX() < curr.getPoint().getX()) {
                curr.leftChild = insert(curr.getLeftChild(), point, !isVertical);
            } else {
                curr.rightChild = insert(curr.getRightChild(), point, !isVertical);
            }
        }
        return curr;
    }



    /** Returns the closest point to the inputted coordinates.
     * This should take O(logN) time on average, where N is the number of points. */
    @Override
    public Point nearest(double x, double y) {
        Point goal = new Point(x, y);
        return nearestHelper(root, goal, root).getPoint();
    }

    private Node nearestHelper(Node n, Point goal, Node best) {
        if (n == null) {
            return best;
        }
        if (goal.distance(goal, n.getPoint()) < goal.distance(goal, best.getPoint())) {
            best = n;
        }
        Node goodSide = null;
        Node badSide = null;
        if (n.getIsVertial()) {
            if (goal.getY() < n.getPoint().getY()) {
                goodSide = n.getLeftChild();
                badSide = n.getRightChild();
            } else {
                goodSide = n.getRightChild();
                badSide = n.getLeftChild();
            }
        } else {
            if (goal.getX() < n.getPoint().getX()) {
                goodSide = n.getLeftChild();
                badSide = n.getRightChild();
            } else {
                goodSide = n.getRightChild();
                badSide = n.getLeftChild();
            }
        }
        best = nearestHelper(goodSide, goal, best);
        double bestToGoal = goal.distance(goal, best.getPoint());
        if ((n.getIsVertial()
                && goal.distance(goal, new Point(goal.getX(), n.getPoint().getY())) < bestToGoal)
                || (!n.getIsVertial()
                && goal.distance(goal, new Point(n.getPoint().getX(), goal.getY())) < bestToGoal)) {
            best = nearestHelper(badSide, goal, best);
        }
        return best;
    }


    public static void main(String[] args) {
//        Point a = new Point(2, 3);
//        Point b = new Point(4, 2);
//        Point c = new Point(4, 5);
//        Point d = new Point(3, 3);
//        Point e = new Point(1, 5);
//        Point f = new Point(4, 4);
//
//        List<Point> list = new ArrayList<>();
//        list.add(a);
//        list.add(b);
//        list.add(c);
//        list.add(d);
//        list.add(e);
//        list.add(f);
//
//        KDTree kdTree = new KDTree(list);
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);
        List<Point> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);

        KDTree nn = new KDTree(list);
        Point ret = nn.nearest(3.0, 4.0); // returns p2
        ret.getX(); // evaluates to 3.3
        ret.getY(); // evaluates to 4.4
    }
}
