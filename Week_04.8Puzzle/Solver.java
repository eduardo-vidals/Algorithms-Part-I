/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computerscience.algorithms.week4.puzzle;

import computerscience.algorithms.datastructures.stacks.Stack;
import edu.princeton.cs.algs4.MinPQ;
import java.util.Iterator;

/**
 *
 * @author EduardoPC
 */
public class Solver {

    private MinPQ pq;

    private Board initial;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        this.initial = initial;
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return moves() >= 0;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {

        Iterable<Board> boards = this.initial.neighbors();

        for (Board board : boards) {

            if (this.initial.manhattan() == 0) {
                return 0;
            }

            if (board.manhattan() == this.initial.manhattan() - 1) {
                return this.initial.manhattan();
            }
        }

        return -1;

    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {

        if (moves() >= 0) {

            Board firstNode = this.initial;
            Board endNode = this.endNode();

        }

        return null;

    }

    private Board endNode() {
        int n = initial.dimension();
        int[][] goalTiles = new int[n][n];

        int count = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                goalTiles[i][j] = count;
                count++;

                // last tile must be 0, so assign it a value of 0
                if (goalTiles[i][j] == n * n) {
                    goalTiles[i][j] = 0;
                }

            }
        }
        
        Board board = new Board(goalTiles);
        
        return board;
    }

    // test client (see below) 
    public static void main(String[] args) {
        int[][] tiles = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
        int[][] tiles2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        int[][] tiles3 = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
        int[][] tiles4 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        int[][] tiles5 = {{1, 0, 3}, {4, 2, 5}, {7, 8, 6}};

        Board board = new Board(tiles);
        Board board2 = new Board(tiles2);
        Board board3 = new Board(tiles5);

        Solver solver = new Solver(board);

        System.out.println("Solved in " + solver.moves() + " moves");

    }

}
