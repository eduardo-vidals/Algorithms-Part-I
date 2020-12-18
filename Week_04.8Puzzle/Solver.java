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

    private MinPQ<Node> pq;
    private MinPQ<Node> pqTwin;
    private final Board initial;
    private final Board goal;

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
            throw new NullPointerException();
        }

        this.initial = initial;
        this.pq = new MinPQ<>();
        this.goal = goalBoard();

        Node minNode;
        Node minNodeTwin;
        
        pq.insert(new Node(initial, 0, null));
        pqTwin.insert(new Node(initial.twin(), 0, null));

        while (!(pq.min().board.equals(goal)) && !(pqTwin.min().board.equals(goal))) {

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
            
            for (Board board : minNodeTwin.board.neighbors()){
                if (minNodeTwin.moves == 0){
                    pqTwin.insert(new Node(board, minNodeTwin.moves + 1, minNodeTwin));
                } else if (!(board.equals(minNodeTwin.prev.board))){
                    pqTwin.insert(new Node(board, minNodeTwin.moves + 1, minNodeTwin));
                }
            }
            
        }

    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        if (pq.min().board.equals(goal)){
            return true;
        } else if (pqTwin.min().board.equals(goal)){
            return false;
        }
        
        return false;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        if (!(isSolvable())){
            return -1;
        } else {
            return pq.min().moves;
        }
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {

        if (!(isSolvable())){
            return null;
        }
        
        Stack<Board> boardSolution = new Stack<>();
        Node current = pq.min();
        
        while (current.prev != null){
            boardSolution.push(current.board);
            current = current.prev;
        }
        
        boardSolution.push(initial);
        return boardSolution;
        

    }

    private Board goalBoard() {
        int n = initial.dimension();
        int[][] goalTiles = new int[n][n];

        int count = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                goalTiles[i][j] = count;
                count++;
            }
        }

        goalTiles[n - 1][n - 1] = 0;

        Board goalBoard = new Board(goalTiles);

        return goalBoard;
    }

    // test client (see below) 
    public static void main(String[] args) {
        int[][] tiles = {{0, 1, 3}, {4, 2, 5}, {7, 8, 6}};
        int[][] tiles2 = {{1, 2, 3}, {4, 5, 6}, {8, 7, 0}};

        Board board = new Board(tiles);

        Solver solver = new Solver(board);

        System.out.println("Solved in " + solver.moves() + " moves");
        System.out.println("Board solution \n" + solver.solution());

    }

}
