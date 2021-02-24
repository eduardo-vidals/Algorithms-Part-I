package computerscience.algorithms.week4.puzzle;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import edu.princeton.cs.algs4.Stack;
import java.util.Arrays;

/**
 *
 * @author EduardoPC
 */
public class Board {

    private final int[][] tiles;
    private final int n;
    private final int manhattanDistance;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        this.tiles = tiles;
        n = tiles.length;

        int total = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] != getIndex(i, j) && tiles[i][j] != 0) {

                    // row
                    int row;
                    if (tiles[i][j] % n == 0) {
                        row = (tiles[i][j] / n) - 1;
                    } else {
                        row = (tiles[i][j] / n);
                    }

                    // column
                    int column = tiles[i][j] - (row * n) - 1;

                    int manhattan = Math.abs((i - row)) + Math.abs((j - column));
                    total += manhattan;

                }
            }
        }
        
        this.manhattanDistance = total;

    }

    // string representation of this board
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(n).append("\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s.append(String.format("%2d ", tiles[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    // board dimension n
    public int dimension() {
        return n;
    }

    // number of tiles out of place
    public int hamming() {

        int hammingCounter = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] != getIndex(i, j)) {
                    hammingCounter++;

                    if (tiles[i][j] == 0) {
                        hammingCounter--;
                    }
                }
            }
        }

        return hammingCounter;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        return this.manhattanDistance;
    }

    // is this board the goal board?
    public boolean isGoal() {

        int[][] goalTiles = new int[n][n];

        int count = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                goalTiles[i][j] = count;
                count++;
            }
        }

        goalTiles[n - 1][n - 1] = 0;

        return Arrays.deepEquals(tiles, goalTiles);
    }

    // does this board equal y?
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        Board other = (Board) obj;
        return this.n == other.n && Arrays.deepEquals(tiles, other.tiles);
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        Stack<Board> boardStack = new Stack<>();

        int row = 0;
        int column = 0;

        // get the index for the row and column that the blank tile is located on
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (tiles[i][j] == 0) {
                    row = i;
                    column = j;
                }
            }
        }

        // If the row has a swappable element to the bottom, then it gets checked
        if (row < n - 1) {
            int[][] tilesCopy = Arrays.stream(tiles).map(int[]::clone).toArray(int[][]::new);

            swap(tilesCopy, row, column, row + 1, column);
            Board board = new Board(tilesCopy);
            boardStack.push(board);

        }

        // If the row has a swappable element to the top, then it gets checked
        if (row > 0) {
            int[][] tilesCopy = Arrays.stream(tiles).map(int[]::clone).toArray(int[][]::new);

            swap(tilesCopy, row, column, row - 1, column);
            Board board = new Board(tilesCopy);
            boardStack.push(board);

        }

        // If the column has a swappable element to the right, then it gets checked
        if (column < n - 1) {
            int[][] tilesCopy = Arrays.stream(tiles).map(int[]::clone).toArray(int[][]::new);

            swap(tilesCopy, row, column, row, column + 1);
            Board board = new Board(tilesCopy);
            boardStack.push(board);

        }

        // If the column has a swappable element to the left, then it gets checked
        if (column > 0) {
            int[][] tilesCopy = Arrays.stream(tiles).map(int[]::clone).toArray(int[][]::new);

            swap(tilesCopy, row, column, row, column - 1);
            Board board = new Board(tilesCopy);
            boardStack.push(board);
        }

        return boardStack;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int count = 0;

        int row = 0;
        int column = 0;
        int row2 = 0;
        int column2 = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                if (count > 1) {
                    break;
                }

                if (tiles[i][j] != 0 && count == 1) {
                    row2 = i;
                    column2 = j;
                    count++;
                }

                if (tiles[i][j] != 0 && count == 0) {
                    row = i;
                    column = j;
                    count++;
                }

            }
        }

        int[][] tilesCopy = Arrays.stream(tiles).map(int[]::clone).toArray(int[][]::new);
        swap(tilesCopy, row, column, row2, column2);

        Board board = new Board(tilesCopy);
        return board;

    }

    // returns the respective index of the tile
    private int getIndex(int row, int column) {
        return n * (row) + column + 1;
    }

    private void swap(int[][] arr, int a, int b, int c, int d) {
        int temp = arr[a][b];
        arr[a][b] = arr[c][d];
        arr[c][d] = temp;
    }

    // unit testing (not graded)
    public static void main(String[] args) {

        int[][] tiles = {{0, 1, 3}, {4, 2, 5}, {7, 8, 6}};
        int[][] tiles1 = {{1, 0}, {2, 3}};

        Board board = new Board(tiles1);

        System.out.println("Manhattan Distance: " + board.manhattan());

    }

}
