package computerscience.algorithms.week5.kdtrees;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

/**
 *
 * @author Eduardo
 */
public class KdTree {

    private Node root;
    private int n;

    private static class Node {

        private Point2D p;
        private RectHV rect;
        private Node lb;
        private Node rt;

        public Node(Point2D p, RectHV rect) {
            this.p = p;
            this.rect = rect;
        }
    }

    // is the tree empty ?
    public boolean isEmpty() {
        return root == null;
    }

    // number of points in the tree
    public int size() {
        return n;
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("Calls insert() with a null point");
        }

        // if the tree has the point then return do not insert anything
        if (contains(p)) {
            return;
        }

        // initial rectangle
        RectHV rect = new RectHV(0, 0, 1, 1);

        // input node
        Node inputNode = new Node(p, rect);

        // our new root
        root = insert(root, inputNode, 0, 0, 1, 1, true);
    }

    private Node insert(Node x, Node t, double xmin, double ymin, double xmax, double ymax, boolean level) {
        // if the node is null, then create a new node
        // if the node is in our symbol table, then return the parent node
        if (x == null) {
            n++;
            t.rect = new RectHV(xmin, ymin, xmax, ymax);
            return t;
        } else if (x.p.equals(t.p)) {
            return x;
        }
        // level determines whether we are going left/right or bottom/top
        if (level) {
            // if input node x value is less than the parent node, then insert 
            // it to the left, else insert it to the right

            // if inserted to the left, the rectangle should have a xmax of
            // the parent node, if inserted to the right, the rectangle should
            // have a xmin of the parent node
            if (t.p.x() < x.p.x()) {
                x.lb = insert(x.lb, t, xmin, ymin, x.p.x(), ymax, !level);
            } else {
                x.rt = insert(x.rt, t, x.p.x(), ymin, xmax, ymax, !level);
            }
        } else {
            // if input node y value is less than the parent node, then insert 
            // it to the the bottom, else insert it to the top

            // if inserted to the bottom, the rectangle should have a ymax of
            // the parent node, if inserted to the right, the rectangle should
            // have a ymin of the parent node
            if (t.p.y() < x.p.y()) {
                x.lb = insert(x.lb, t, xmin, ymin, xmax, x.p.y(), !level);
            } else {
                x.rt = insert(x.rt, t, xmin, x.p.y(), xmax, ymax, !level);
            }
        }
        // return new root
        return x;
    }

    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("argument to contains() is null");
        }
        return contains(root, p, true);
    }

    private boolean contains(Node x, Point2D p, boolean level) {
        // if the node point is null, then return false
        // if the node point is equal to the target point, then return true
        if (x == null) {
            return false;
        } else if (x.p.equals(p)) {
            return true;
        }

        // use the same method for adding except this time we are searching
        // for the node that has the point
        if (level) {
            if (p.x() < x.p.x()) {
                return contains(x.lb, p, !level);
            } else {
                return contains(x.rt, p, !level);
            }
        } else {
            if (p.y() < x.p.y()) {
                return contains(x.lb, p, !level);
            } else {
                return contains(x.rt, p, !level);
            }
        }
    }

    // draw all points to standard draw 
    public void draw() {
        draw(root, true);
    }
    
    // recursively draw the left side and then the right side
    private void draw(Node x, boolean level) {
        if (x == null) {
            return;
        }
        draw(x.lb, !level);
        StdDraw.setPenRadius(0.005);
        if (level) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(x.p.x(), x.rect.ymin(), x.p.x(), x.rect.ymax());
        } else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(x.rect.xmin(), x.p.y(), x.rect.xmax(), x.p.y());
        }

        StdDraw.setPenRadius(0.015);
        StdDraw.setPenColor(StdDraw.BLACK);
        x.p.draw();

        draw(x.rt, !level);
    }

    // all points that are inside the rectangle (or on the boundary) 
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException();
        }
        StdDraw.setPenRadius(0.005);
        rect.draw();
        Queue<Point2D> queue = new Queue<>();
        if (root == null) {
            return queue;
        }
        range(root, rect, queue);
        return queue;
    }

    private void range(Node x, RectHV rect, Queue<Point2D> queue) {
        if (x == null) {
            return;
        }

        if (!rect.intersects(x.rect)) {
            return;
        }

        // if the rectangles intersect, then there might be a point within that
        // range, if the rectangle contains the node point, then add it to
        // the queue
        if (rect.contains(x.p)) {
            queue.enqueue(x.p);
        }
        range(x.lb, rect, queue);
        range(x.rt, rect, queue);

    }

    // a nearest neighbor in the set to point p; null if the set is empty 
    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }

        if (root == null) {
            return null;
        }

        StdDraw.setPenColor(StdDraw.ORANGE);
        StdDraw.setPenRadius(0.015);
        p.draw();
        Point2D min = root.p;
        return nearest(root, p, min);
    }

    private Point2D nearest(Node x, Point2D p, Point2D min) {
        // if the node is null, return the existing min value
        if (x == null) {
            return min;
        }

        // return the min if the distance is shorter than the distance of
        // the node rectangle
        if (p.distanceSquaredTo(min) < x.rect.distanceSquaredTo(p)) {
            return min;
        }

        // if the current node point is closer, then update the min value
        if (x.p.distanceSquaredTo(p) < min.distanceSquaredTo(p)) {
            min = x.p;
        }

        // if the left/bottom side is null, then check the right side
        if (x.lb == null) {
            return nearest(x.rt, p, min);
        } // if the right/top side is null, then check the left side
        else if (x.rt == null) {
            return nearest(x.lb, p, min);
        } // if the left/bottom subtree has a closer point than the right/top
        // subtree, then there is no need to into the right/top subtree.
        // as a result, if we find a new min value, then we assign it the new
        // min value and prune the right/top sub-tree
        else if (x.lb.rect.distanceSquaredTo(p) < x.rt.rect.distanceSquaredTo(p)) {
            min = nearest(x.lb, p, min);
            return nearest(x.rt, p, min);
        } // if the right/top subtree has a closer point than the left/bottom
        // subtree, then there is no need to into the left/bottom subtree.
        // as a result, if we find a new min value, then we assign it the new
        // min value and prune the left/bottom sub-tree
        else {
            min = nearest(x.rt, p, min);
            return nearest(x.lb, p, min);
        }
    }

    public static void main(String[] args) {
        KdTree search = new KdTree();
        search.insert(new Point2D(0.7, 0.2));
        search.insert(new Point2D(0.5, 0.4));
        search.insert(new Point2D(0.2, 0.3));
        search.insert(new Point2D(0.4, 0.7));
        search.insert(new Point2D(0.9, 0.6));
        System.out.println("Contains? " + search.contains(new Point2D(0.7, 0.2)));
        System.out.println("Range: " + search.range(new RectHV(0.15, 0.4, 0.65, 0.7)));
        System.out.println("Nearest: " + search.nearest(new Point2D(0.25, 0.3)));
        // StdDraw.line(0.7, 0, 0.7, 1); x-axis StdDraw.line(x, 0, x, 1);
        // StdDraw.line(0, 0.2, 1, 0.2); y-axis StdDraw.line(0, y, 1, y);
        search.draw();
    }
}
