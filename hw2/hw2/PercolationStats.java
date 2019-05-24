package hw2;
import edu.princeton.cs.introcs.StdRandom;

public class PercolationStats {
    private double[] threshold;
    private int t;
    /**perform T independent experiments on an N-by-N grid*/
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        t = T;
        threshold = new double[t];
        for (int i = 0; i < t; i++) {
            Percolation p = pf.make(N);
            while (!p.percolates()) {
                int row = StdRandom.uniform(0, N);
                int col = StdRandom.uniform(0, N);
                p.open(row, col);
            }
            threshold[i] = (double)p.numberOfOpenSites() / (N * N);
            System.out.println(threshold[i]);
        }
    }

    /**sample mean of percolation threshold*/
    public double mean() {
        double u = 0;
        for (int i = 0; i < t; i++) {
            u += threshold[i];
        }
        return u / t;
    }

    /**sample standard deviation of percolation threshold*/
    public double stddev() {
        double e = 0;
        double u = mean();
        for (int i = 0; i < t; i++) {
            e += Math.pow(threshold[i] - u, 2);
        }

        return Math.sqrt(e / (t - 1));
    }
    /**low endpoint of 95% confidence interval*/
    public double confidenceLow() {
        double u = mean();
        double e = stddev();
        return u - 1.96 * e / Math.sqrt(t);
    }
    /**high endpoint of 95% confidence interval*/
    public double confidenceHigh() {
        double u = mean();
        double e = stddev();
        return u + 1.96 * e / Math.sqrt(t);
    }


    public static void main(String[] args) {
        PercolationFactory x = new PercolationFactory();
        PercolationStats y = new PercolationStats(20, 10, x);
        System.out.println(y.mean());

    }

}
