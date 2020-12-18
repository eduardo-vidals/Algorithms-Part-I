package computerscience.algorithms.week4.puzzle;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import computerscience.algorithms.week4.puzzle.Board;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

/**
 *
 * @author EduardoPC
 */
public class Solver {

    private MinPQ<Node> pq;
    private MinPQ<Node> pqTwin;
    private Board initial;

    private class Node implements Comparable<Node> {

        private Board board;
        private Node prev;
        private int moves;
        private int priority;

        public Node(Board board, int moves, Node prev) {
            this.board = board;
            this.moves = moves;
            this.prev = prev;
            this.priority = moves + board.manhattan();
        }

        @Override
        public int compareTo(Node that) {
            return this.priority - that.priority;
        }

    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException();
        }

        this.initial = initial;
        this.pq = new MinPQ<>();
        this.pqTwin = new MinPQ<>();

        Node minNode;
        Node minNodeTwin;

        pq.insert(new Node(initial, 0, null));
        pqTwin.insert(new Node(initial.twin(), 0, null));

        while (!(pq.min().board.isGoal()) && !(pqTwin.min().board.isGoal())) {

            minNode = pq.min();
            pq.delMin();

            minNodeTwin = pqTwin.min();
            pqTwin.delMin();

            for (Board board : minNode.board.neighbors()) {
                if (minNode.moves == 0) {
                    pq.insert(new Node(board, minNode.moves + 1, minNode));
                } else if (!(board.equals(minNode.prev.board))) {
                    pq.insert(new Node(board, minNode.moves + 1, minNode));
                }
            }

            for (Board board : minNodeTwin.board.neighbors()) {
                if (minNodeTwin.moves == 0) {
                    pqTwin.insert(new Node(board, minNodeTwin.moves + 1, minNodeTwin));
                } else if (!(board.equals(minNodeTwin.prev.board))) {
                    pqTwin.insert(new Node(board, minNodeTwin.moves + 1, minNodeTwin));
                }
            }

        }

    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        if (pq.min().board.isGoal()) {
            return true;
        } else if (pqTwin.min().board.isGoal()) {
            return false;
        }

        return false;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (!(isSolvable())) {
            return -1;
        }
        return pq.min().moves;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {

        if (!(isSolvable())) {
            return null;
        }

        Stack<Board> boardSolution = new Stack<>();
        Node current = pq.min();

        while (current.prev != null) {
            boardSolution.push(current.board);
            current = current.prev;
        }

        boardSolution.push(initial);
        return boardSolution;

    }

    // test client (see below) 
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                tiles[i][j] = in.readInt();
            }
        }
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable()) {
            StdOut.println("No solution possible");
        } else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution()) {
                StdOut.println(board);
            }
        }

    }

}
