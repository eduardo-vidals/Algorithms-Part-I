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
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] grid;
    private final WeightedQuickUnionUF QU;
    private final WeightedQuickUnionUF bwQU;
    private final int top;
    private final int bottom;
    private final int length;
    private int numberOfSites = 0;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Value must be greater than 0");
        }
        grid = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = false;
            }
        }
        
        // A for-loop will NOT be used for QU to find FULLNESS as it will severely ruin the
        // time complexity of the program
        
        // QU checks for PERCOLATION
        // This checks for whether the top connects to the BOTTOM
        // QU CANNOT check for FULLNESS because all open sites in the bottom row
        // will automatically be full if the system were to percolate
        QU = new WeightedQuickUnionUF(n * n + 2);

        // bwQU checks for FULLNESS
        // This checks for whether the the top connets to the elements inside of the grid
        // bwQU CANNOT check for PERCOLATION because it has no way to check
        // whether the last row is connected to the BOTTOM
        bwQU = new WeightedQuickUnionUF(n * n + 1);
        top = 0;
        bottom = n * n + 1;
        length = n;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {

        if (row <= 0 || col <= 0 || row > length || col > length) {
            throw new IllegalArgumentException("Value was either less than 0 or greater than the row/column length");
        }

        if (grid[row - 1][col - 1] == false) {

            grid[row - 1][col - 1] = true;

            // All elements from the top row get connected to the top
            if (row == 1) {
                bwQU.union(getIndex(row, col), top);
                QU.union(getIndex(row, col), top);
            }

            // BOTTOM
            // All rows get checked, if there is a valid element to the bottom, then it will get connected
            // isOpen() method will check whether the element to the bottom is true or false
            if (row < length && isOpen(row + 1, col)) {
                bwQU.union(getIndex(row, col), getIndex(row + 1, col));
                QU.union(getIndex(row, col), getIndex(row + 1, col));
            }

            // LEFT
            // All rows get checked, if there is a valid element to the left, then it will get connected
            // isOpen() method will check whether the element to the left is true or false
            if (col > 1 && isOpen(row, col - 1)) {
                bwQU.union(getIndex(row, col), getIndex(row, col - 1));
                QU.union(getIndex(row, col), getIndex(row, col - 1));
            }

            // RIGHT
            // All rows get checked, if there is a valid element to the right, then it will get connected
            // isOpen() method will check whether the element ot the right is true or false
            if (col < length && isOpen(row, col + 1)) {
                bwQU.union(getIndex(row, col), getIndex(row, col + 1));
                QU.union(getIndex(row, col), getIndex(row, col + 1));
            }

            // TOP
            // All rows get checked, if there is a valid element to the top, then it will get connected
            // isOpen() method will check whether the element to the top is true or false
            if (row > 1 && isOpen(row - 1, col)) {
                bwQU.union(getIndex(row, col), getIndex(row - 1, col));
                QU.union(getIndex(row, col), getIndex(row - 1, col));
            }

            // All elements at the bottom row get connect to the bottom
            // only applies to QU because it checks for PERCOLATION
            if (row == length) {
                QU.union(getIndex(row, col), bottom);
            }
            numberOfSites++;
        }

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row <= 0 || col <= 0 || row > length || col > length) {
            throw new IllegalArgumentException("Value was either less than 0 or greater than the row/column length");
        }
        // Returns whether the element is open or not
        return grid[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row <= 0 || col <= 0 || row > length || col > length) {
            throw new IllegalArgumentException("Value was either less than 0 or greater than the row/column length");
        }

        return bwQU.find(getIndex(row, col)) == bwQU.find(top);

    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numberOfSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return QU.find(top) == QU.find(bottom);
    }

    private int getIndex(int row, int column) {
        return length * (row - 1) + column;
    }

}
