/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computerscience.algorithms.datastructures.trees;

import edu.princeton.cs.algs4.Queue;
import java.util.NoSuchElementException;

/**
 *
 * @author Eduardo
 * @param <Key>
 * @param <Value>
 */
public class RedBlackBSTPractice<Key extends Comparable<Key>, Value> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    private class Node {

        private Key key;
        private Value val;
        private Node left, right;
        private boolean color;
        private int size;

        public Node(Key key, Value val, boolean color, int size) {
            this.key = key;
            this.val = val;
            this.color = color;
            this.size = size;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        }
        return x.size;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to get() is null");
        }
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (x == null) {
            return null;
        }

        int cmp = key.compareTo(x.key);

        if (cmp < 0) {
            return get(x.left, key);
        } else if (cmp > 0) {
            return get(x.right, key);
        } else {
            return x.val;
        }
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public void put(Key key, Value val) {
        if (key == null) {
            throw new IllegalArgumentException("first argument to put() is null");
        }
        if (val == null) {
            // delete(key);
            return;
        }

        root = put(root, key, val);
        root.color = BLACK;
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) {
            return new Node(key, val, RED, 1);
        }

        int cmp = key.compareTo(x.key);

        if (cmp < 0) {
            x.left = put(x.left, key, val);
        } else if (cmp > 0) {
            x.right = put(x.right, key, val);
        } else {
            x.val = val;
        }

        // fix right leaning links
        if (isRed(x.right) && !isRed(x.left)) {
            x = rotateLeft(x);
        }
        if (isRed(x.left) && isRed(x.left.left)) {
            x = rotateRight(x);
        }
        if (isRed(x.left) && isRed(x.right)) {
            flipColors(x);
        }
        x.size = size(x.left) + size(x.right) + 1;

        return x;
    }

    public void deleteMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("BST underflow");
        }

        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }

        root = deleteMin(root);
        if (!isEmpty()) {
            root.color = BLACK;
        }
    }

    private Node deleteMin(Node x) {
        if (x.left == null) {
            return null;
        }
        if (!isRed(x.left) && !isRed(x.left.left)) {
            x = moveRedLeft(x);
        }
        x.left = deleteMin(x.left);
        return balance(x);
    }

    public void deleteMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("BST underflow");
        }
        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }
        root = deleteMax(root);
        if (!isEmpty()) {
            root.color = BLACK;
        }
    }

    private Node deleteMax(Node x) {
        if (isRed(x.left)) {
            x = rotateRight(x);
        }
        if (x.right == null) {
            return null;
        }
        if (!isRed(x.right) && !isRed(x.right.left)) {
            x = moveRedRight(x);
        }
        x.right = deleteMax(x.right);
        return balance(x);
    }

    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to delete() is null");
        }
        if (!contains(key)) {
            return;
        }
        if (!isRed(root.left) && !isRed(root.right)) {
            root.color = RED;
        }
        root = delete(root, key);
        if (!isEmpty()) {
            root.color = BLACK;
        }
    }

    private Node delete(Node x, Key key) {
        int cmp = key.compareTo(x.key);

        if (cmp < 0) {
            if (!isRed(x.left) && !isRed(x.left.left)) {
                x = moveRedLeft(x);
            }
            x.left = delete(x.left, key);
        } else {
            if (isRed(x.left)) {
                x = rotateRight(x);
            }
            if (cmp == 0 && x.right == null) {
                return null;
            }
            if (!isRed(x.right) && !isRed(x.right.left)) {
                x = moveRedRight(x);
            }
            if (cmp == 0) {
                Node t = min(x.right);
                x.key = t.key;
                x.val = t.val;
                x.right = deleteMin(x.right);
            } else {
                x.right = delete(x.right, key);
            }
        }
        return balance(x);
    }

    public Key min() {
        if (isEmpty()) {
            throw new NoSuchElementException("calls min() with empty symbol table");
        }
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) {
            return x;
        } else {
            return min(x.left);
        }
    }

    public Key max() {
        if (isEmpty()) {
            throw new NoSuchElementException("calls min() with empty symbol table");
        }
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) {
            return x;
        } else {
            return max(x.right);
        }
    }

    /*
     * helper functions 
     */
    private boolean isRed(Node x) {
        if (x == null) {
            return false;
        }
        return x.color == RED;
    }

    private Node rotateRight(Node x) {
        Node t = x.left;
        x.left = t.right;
        t.right = x;
        t.color = t.right.color;
        t.right.color = RED;
        t.size = x.size;
        x.size = size(x.left) + size(x.right) + 1;
        return t;
    }

    private Node rotateLeft(Node x) {
        Node t = x.right;
        x.right = t.left;
        t.left = x;
        t.color = t.left.color;
        t.left.color = RED;
        t.size = x.size;
        x.size = size(x.left) + size(x.right) + 1;
        return t;
    }

    private void flipColors(Node x) {
        x.color = !x.color;
        x.left.color = !x.left.color;
        x.right.color = !x.right.color;
    }

    private Node moveRedLeft(Node x) {
        flipColors(x);
        if (isRed(x.right.left)) {
            x.right = rotateRight(x.right);
            x = rotateLeft(x);
            flipColors(x);
        }
        return x;
    }

    private Node moveRedRight(Node x) {
        flipColors(x);
        if (isRed(x.left.left)) {
            {
                x = rotateRight(x);
                flipColors(x);
            }
        }
        return x;
    }

    private Node balance(Node x) {
        if (isRed(x.right) && !isRed(x.left)) {
            x = rotateLeft(x);
        }
        if (isRed(x.left) && isRed(x.left.left)) {
            x = rotateRight(x);
        }
        if (isRed(x.left) && isRed(x.right)) {
            flipColors(x);
        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Key floor(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to floor() is null");
        }
        if (isEmpty()) {
            throw new NoSuchElementException("calls floor() with empty symbol table");
        }
        Node x = floor(root, key);
        if (x == null) {
            throw new NoSuchElementException("argument to floor() is too small");
        } else {
            return x.key;
        }
    }

    private Node floor(Node x, Key key) {
        if (x == null) {
            return null;
        }

        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x;
        } else if (cmp < 0) {
            return floor(x.left, key);
        }

        Node t = floor(x.right, key);
        if (t != null) {
            return t;
        } else {
            return x;
        }
    }

    public Key ceiling(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to floor() is null");
        }
        if (isEmpty()) {
            throw new NoSuchElementException("calls floor() with empty symbol table");
        }
        Node x = ceiling(root, key);
        if (x == null) {
            throw new NoSuchElementException("argument to floor() is too small");
        } else {
            return x.key;
        }
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) {
            return null;
        }

        int cmp = key.compareTo(x.key);

        if (cmp == 0) {
            return x;
        }
        if (cmp < 0) {
            Node t = ceiling(x.left, key);
            if (t != null) {
                return t;
            } else {
                return x;
            }
        }

        return ceiling(x.right, key);
    }

    public Key select(int rank) {
        if (rank < 0 || rank >= size()) {
            throw new IllegalArgumentException("argument to select() is invalid: " + rank);
        }
        return select(root, rank);
    }

    private Key select(Node x, int rank) {
        if (x == null) {
            return null;
        }
        int leftSize = size(x.left);
        if (leftSize > rank) {
            return select(x.left, rank);
        } else if (leftSize < rank) {
            return select(x.right, rank - leftSize - 1);
        } else {
            return x.key;
        }
    }

    public int rank(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to rank() is null");
        }
        return rank(root, key);
    }

    private int rank(Node x, Key key) {
        if (x == null) {
            return 0;
        }

        int cmp = key.compareTo(x.key);

        if (cmp < 0) {
            return rank(x.left, key);
        } else if (cmp > 0) {
            return size(x.left) + rank(x.right, key) + 1;
        } else {
            return size(x.left);
        }
    }

    public Iterable<Key> keys() {
        if (isEmpty()) {
            return new Queue<Key>();
        }
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) {
            throw new IllegalArgumentException("first argument to keys() is null");
        }
        if (hi == null) {
            throw new IllegalArgumentException("second argument to keys() is null");
        }
        Queue<Key> queue = new Queue<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) {
            return;
        }

        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);

        if (cmplo < 0) {
            keys(x.left, queue, lo, hi);
        }

        if (cmplo <= 0 && cmphi >= 0) {
            queue.enqueue(x.key);
        }

        if (cmphi > 0) {
            keys(x.right, queue, lo, hi);
        }
    }

    public int size(Key lo, Key hi) {
        if (lo == null) {
            throw new IllegalArgumentException("first argument to size() is null");
        }
        if (hi == null) {
            throw new IllegalArgumentException("second argument to size() is null");
        }

        if (lo.compareTo(hi) > 0) {
            return 0;
        }

        if (contains(hi)) {
            return rank(hi) - rank(lo) + 1;
        } else {
            return rank(hi) - rank(lo) + 1;
        }
    }

    public int height() {
        return height(root);
    }

    private int height(Node x) {
        if (x == null) {
            return -1;
        }
        return 1 + Math.max(height(x.left), height(x.right));
    }

    /**
     * *************************************************************************
     * Check integrity of red-black tree data structure.
     * *************************************************************************
     */
    private boolean check() {
        if (!isBST()) {
            System.out.println("Not in symmetric order");
        }
        if (!isSizeConsistent()) {
            System.out.println("Subtree counts not consistent");
        }
        if (!isRankConsistent()) {
            System.out.println("Ranks not consistent");
        }
        if (!is23()) {
            System.out.println("Not a 2-3 tree");
        }
        if (!isBalanced()) {
            System.out.println("Not balanced");
        }
        return isBST() && isSizeConsistent() && isRankConsistent() && is23() && isBalanced();
    }

    // does this binary tree satisfy symmetric order?
    // Note: this test also ensures that data structure is a binary tree since order is strict
    private boolean isBST() {
        return isBST(root, null, null);
    }

    // is the tree rooted at x a BST with all keys strictly between min and max
    // (if min or max is null, treat as empty constraint)
    // Credit: Bob Dondero's elegant solution
    private boolean isBST(Node x, Key min, Key max) {
        if (x == null) {
            return true;
        }
        if (min != null && x.key.compareTo(min) <= 0) {
            return false;
        }
        if (max != null && x.key.compareTo(max) >= 0) {
            return false;
        }
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
    }

    // are the size fields correct?
    private boolean isSizeConsistent() {
        return isSizeConsistent(root);
    }

    private boolean isSizeConsistent(Node x) {
        if (x == null) {
            return true;
        }
        if (x.size != size(x.left) + size(x.right) + 1) {
            return false;
        }
        return isSizeConsistent(x.left) && isSizeConsistent(x.right);
    }

    // check that ranks are consistent
    private boolean isRankConsistent() {
        for (int i = 0; i < size(); i++) {
            if (i != rank(select(i))) {
                return false;
            }
        }
        for (Key key : keys()) {
            if (key.compareTo(select(rank(key))) != 0) {
                return false;
            }
        }
        return true;
    }

    // Does the tree have no red right links, and at most one (left)
    // red links in a row on any path?
    private boolean is23() {
        return is23(root);
    }

    private boolean is23(Node x) {
        if (x == null) {
            return true;
        }
        if (isRed(x.right)) {
            return false;
        }
        if (x != root && isRed(x) && isRed(x.left)) {
            return false;
        }
        return is23(x.left) && is23(x.right);
    }

    // do all paths from root to leaf have same number of black edges?
    private boolean isBalanced() {
        int black = 0;     // number of black links on path from root to min
        Node x = root;
        while (x != null) {
            if (!isRed(x)) {
                black++;
            }
            x = x.left;
        }
        return isBalanced(root, black);
    }

    // does every path from the root to a leaf have the given number of black links?
    private boolean isBalanced(Node x, int black) {
        if (x == null) {
            return black == 0;
        }
        if (!isRed(x)) {
            black--;
        }
        return isBalanced(x.left, black) && isBalanced(x.right, black);
    }

    public static void main(String[] args) {

        RedBlackBSTPractice<String, Integer> bst = new RedBlackBSTPractice<>();
        System.out.println(bst.isEmpty()); // true
        bst.put("S", 1);
        bst.put("E", 2);
        bst.put("X", 3);
        bst.put("A", 4);
        bst.put("R", 5);
        bst.put("C", 6);
        bst.put("H", 7);
        bst.put("M", 8);
        System.out.println(bst.height()); // 3
        bst.delete("M");
        System.out.println(bst.floor("G")); // E
        System.out.println(bst.ceiling("G")); // H
        System.out.println(bst.height()); // 3
        System.out.println(bst.keys()); // A C E H R S X
        System.out.println(bst.select(3)); // H
        System.out.println(bst.size()); // 7
        System.out.println(bst.rank("F")); // 3
        System.out.println(bst.contains("E")); // true
        System.out.println(bst.contains("F")); // false
        System.out.println(bst.get("X")); // 3
        System.out.println("Min: " + bst.min() + " Max: " + bst.max()); // A | X
        bst.deleteMin();
        System.out.println(bst.keys()); // C E H R S X
        bst.deleteMax();
        System.out.println(bst.keys()); // C E H R S
        System.out.println("Min: " + bst.min() + " Max: " + bst.max()); // C | S

    }

}
