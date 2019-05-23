package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.util.TreeSet;

public class Percolation {
    private int n, top, bottom;
    private WeightedQuickUnionUF grid, gridNoBottom;
    private TreeSet<Integer> openSite=new TreeSet<>();

    /**create N-by-N grid, with all sites initially blocked */
    public Percolation(int N) {
        if (N < 0) {
            throw new IllegalArgumentException();
        }
        n = N;
        top = n * n;     //Assume virtual topSite is n*n, bottomSite is n*n+1
        bottom = n * n + 1;
        grid = new WeightedQuickUnionUF(n * n + 2);
        gridNoBottom = new WeightedQuickUnionUF(n * n + 1);

    }

    /**open the site (row, col) if it is not open already*/
    public void open(int row, int col) {
        validate(row, col);
        if (isOpen(row, col)) {
            return;
        }
        int index = xyTo1D(row, col);
        openSite.add(index);
        if (row == 0) {
            grid.union(top, index);
            gridNoBottom.union(top, index);
        } else if (row == n - 1) {
            grid.union(bottom, index);
        }

        if (col > 0 && isOpen(row, col - 1)) {
            grid.union(xyTo1D(row, col - 1), index);
            gridNoBottom.union(xyTo1D(row, col - 1), index);
        }
        if (col < n - 1 && isOpen(row, col + 1)) {
            grid.union(xyTo1D(row, col + 1), index);
            gridNoBottom.union(xyTo1D(row, col + 1), index);
        }
        if (row > 0 && isOpen(row - 1, col)) {
            grid.union(xyTo1D(row - 1, col), index);
            gridNoBottom.union(xyTo1D(row - 1, col), index);
        }
        if (row < n - 1 && isOpen(row + 1, col)) {
            grid.union(xyTo1D(row + 1, col), index);
            gridNoBottom.union(xyTo1D(row + 1, col), index);
        }
    }

    /** is the site (row, col) open?*/
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return openSite.contains(xyTo1D(row, col));
    }

    /**is the site (row, col) full?*/
    public boolean isFull(int row, int col) {
        validate(row, col);
        return gridNoBottom.connected(xyTo1D(row, col), top);    //create gridNoBottom to prevent backwash
    }

    /**number of open sites, including both empty and full*/
    public int numberOfOpenSites() {
        return openSite.size();
    }

    /**does the system percolate?*/
    public boolean percolates() {
        return grid.connected(top, bottom);
    }

    /**convert a coordinate to a number
     * 0(0,0) 1(0,1) 2(0,2)
     * 3(1,0) 4(1,1) 5(1,2)
     * 6(2,0) 7(2,1) 8(2,2)
     */
    private int xyTo1D(int row, int col) {
        return row * n + col;
    }

    /**the row and column indices are integers between 0 and N − 1, where (0, 0) is the upper-left site:
     * Throw a java.lang.IndexOutOfBoundsException if any argument is outside its prescribed range.
     */
    private void validate(int row, int col) {
        if (row < 0 || row >= n || col < 0 || col >= n) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**use for unit testing (not required, but keep this here for the autograder) */
    public static void main(String[] args) {
//        WeightedQuickUnionUF x = new WeightedQuickUnionUF(3);
//        x.union(1,0);
//        System.out.println(x.find(0));
//        System.out.println(x.find(1));
//        System.out.println(x.count());
        Percolation x = new Percolation(3);
        x.open(2,2);
        x.open(1,2);
        System.out.println(x.percolates());
        x.open(0,0);
        x.open(1,0);
        x.open(2,0);
        System.out.println(x.percolates());
        System.out.println(x.isFull(2,0));
        System.out.println(x.isFull(2,2));
        System.out.println(x.grid.find(5));
        System.out.println(x.grid.find(10));
        System.out.println(x.grid.find(4));


    }
}
