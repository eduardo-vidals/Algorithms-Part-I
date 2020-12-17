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

    private final Board initial;
    private final int manhattanDistance;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        pq = new MinPQ<>();
        this.initial = initial;

        Iterable<Board> boards = this.initial.neighbors();

        int initialDistance = initial.manhattan();

        Board current = initial;
        while (initialDistance > 0) {
            for (Board board : current.neighbors()) {
                if (current.manhattan() == board.manhattan() - 1) {
                    pq.insert(board.manhattan());
                    current = board;
                }
            }
            initialDistance--;
        }

        if (initialDistance == 0) {
            manhattanDistance = initial.manhattan();
        } else {
            manhattanDistance = -1;
        }

    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return moves() >= 0;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {

        return this.manhattanDistance;

    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {

        int initialDistance = initial.manhattan();
        Board initialBoard = initial;
        while (initialDistance > 0) {

            Board current = null;
            for (Board board : initial.neighbors()) {

                if (initialDistance != board.manhattan() - 1) {
                    return null;
                } else if (initialDistance == board.manhattan() - 1) {
                    pq.insert(board);
                    current = board;
                }
                initialDistance--;

            }

            initialBoard = current;

        }

        Board currentNode = this.initial;
        Board endNode = this.endNode();

        while (!(pq.isEmpty())) {

            if (currentNode.equals(endNode)) {

            }
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
        int[][] tiles = {{0, 1, 3}, {4, 2, 5}, {7, 8, 6}};
        int[][] tiles2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        int[][] tiles5 = {{1, 0, 3}, {4, 2, 5}, {7, 8, 6}};

        Board board = new Board(tiles);

        Solver solver = new Solver(board);

        System.out.println("Solved in " + solver.moves() + " moves");
        System.out.println("");

    }

}
