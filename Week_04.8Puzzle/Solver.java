package computerscience.algorithms.week4.puzzle;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

/**
 *
 * @author EduardoPC
 */
public class Solver {

    private Stack<Board> boardSolution;
    private boolean isSolvable;

    private class Node implements Comparable<Node> {

        private Board board;
        private Node prev;
        private int moves;
        private final int manhattan;

        public Node(Board board, Node prev) {
            this.board = board;
            this.prev = prev;
            this.manhattan = board.manhattan();

            if (prev != null) {
                this.moves = prev.moves + 1;
            } else {
                this.moves = 0;
            }
        }

        @Override
        public int compareTo(Node that) {

            int priorityDiff = (this.manhattan + this.moves) - (that.manhattan + that.moves);

            if (priorityDiff == 0) {
                return this.manhattan - that.manhattan;
            } else {
                return priorityDiff;
            }

        }
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException();
        }

        this.isSolvable = false;
        this.boardSolution = new Stack<>();

        MinPQ<Node> pq = new MinPQ<>();
        
        // node starts with prev as null
        pq.insert(new Node(initial, null));
        pq.insert(new Node(initial.twin(), null));
        
        // the while loops ends when the min node in the PQ is the goal
        while (!(pq.min().board.isGoal())) {
            // we delete the min node each iteration
            Node minNode = pq.delMin();
            
            // we iterate through the board neighbors
            for (Board board : minNode.board.neighbors()) {
                
                // if we begin, then the prev node should be null
                // as a result, add the node to the PQ
                // if we continue, then the prev node should not be null, and
                // the current board should NOT be equal to the prev node board
                // as a result, add the node if it follows this criteria
               
                if (minNode.prev == null || minNode.prev != null && !minNode.prev.board.equals(board)) {
                    pq.insert(new Node(board, minNode));
                }
            }
        }
        
        Node current = pq.min();
        while (current.prev != null) {
            boardSolution.push(current.board);
            current = current.prev;
        }

        boardSolution.push(current.board);

        if (current.board.equals(initial)) {
            isSolvable = true;
        }

    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return isSolvable;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (!(isSolvable())) {
            return -1;
        }
        return this.boardSolution.size() - 1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {

        if (!(isSolvable())) {
            return null;
        }

        return this.boardSolution;

    }

    // test client (see below) 
    public static void main(String[] args) {
        // create initial board from file

        int[][] tiles = {{3, 2, 4, 8}, {1, 6, 0, 12}, {5, 10, 7, 11}, {9, 13, 14, 15}};
        int[][] tiles2 = {{0, 1, 3}, {4, 2, 5}, {7, 8, 6}};

        Board board = new Board(tiles);
        Board board2 = new Board(tiles2);

        Solver solver = new Solver(board2);

        System.out.println("Solvable? " + solver.isSolvable());
        

    }

}
