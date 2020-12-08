package computerscience.algorithms.week1.percolation;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author EduardoPC
 */
import computerscience.algorithms.week1.percolation.Percolation;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;

public class PercolationStats {

    private final double[] values;
    private final double mean;
    private final double stddev;
    private final double confidenceLo;
    private final double confidenceHi;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Values must be greater than 0.");
        }
        int gridSize = n * n;
        values = new double[trials];

        int i = 0;
        while (i < trials) {
            Percolation grid = new Percolation(n);
            while (true) {
                int row = StdRandom.uniform(1, n + 1);
                int column = StdRandom.uniform(1, n + 1);

                if (grid.percolates()) {
                    double siteRatio = (double) grid.numberOfOpenSites() / (double) gridSize;
                    values[i] = siteRatio;
                    break;
                }
                grid.open(row, column);
            }
            i++;

        }

        this.mean = StdStats.mean(values);
        this.stddev = StdStats.stddev(values);
        this.confidenceLo = this.mean - ((1.96 * this.stddev) / Math.sqrt(trials));
        this.confidenceHi = this.mean + ((1.96 * this.stddev) / Math.sqrt(trials));
    }

// sample mean of percolation threshold
    public double mean() {
        return this.mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return this.stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.confidenceLo;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.confidenceHi;
    }

    // test client (see below)
    public static void main(String[] args) {

        int grid = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(grid, trials);
        StdOut.println("mean                    = " + stats.mean());
        StdOut.println("stddev                  = " + stats.stddev());
        StdOut.println("95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");

    }
}
