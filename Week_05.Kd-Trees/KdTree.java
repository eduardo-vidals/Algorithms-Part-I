/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computerscience.algorithms.week5.kdtrees;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import java.util.NoSuchElementException;
import java.util.TreeSet;

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

    public KdTree() {
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return n;
    }

    public void insert(Point2D p) {
        root = insert(root, p, new RectHV(0, 0, 1, 1), 0);
    }

    private int cmp(Point2D p1, Point2D p2, int l) {

        if (l % 2 == 0) {
            Double xValue = p1.x();
            int cmpX = xValue.compareTo(p2.x());

            if (cmpX == 0) {
                Double yValue = p1.y();
                return yValue.compareTo(p2.y());
            } else {
                return cmpX;
            }
        } else {
            Double yValue = p1.y();
            int cmpY = yValue.compareTo(p2.y());

            if (cmpY == 0) {
                Double xValue = p1.x();
                return xValue.compareTo(p2.x());
            } else {
                return cmpY;
            }
        }
    }

    private Node insert(Node x, Point2D p, RectHV rect, int l) {
        if (x == null) {
            n++;
            return new Node(p, new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), rect.ymax()));
        }

        int cmp = cmp(p, x.p, l);

        if (cmp < 0) {
            if (l % 2 == 0) {
                x.lb = insert(x.lb, p, new RectHV(rect.xmin(), rect.ymin(), x.p.x(), rect.ymax()), l + 1);
            } else {
                x.lb = insert(x.lb, p, new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), x.p.y()), l + 1);
            }
        } else if (cmp > 0) {
            if (l % 2 == 0) {
                x.rt = insert(x.rt, p, new RectHV(x.p.x(), rect.ymin(), rect.xmax(), rect.ymax()), l + 1);
            } else {
                x.rt = insert(x.rt, p, new RectHV(rect.xmin(), x.p.y(), rect.xmax(), rect.ymax()), l + 1);
            }
        }

        return x;
    }

    public boolean contains(Point2D p) {
        return contains(root, p) != null;
    }

    private Point2D contains(Node x, Point2D p) {
        if (x == null) {
            return null;
        }

        int cmp = p.compareTo(x.p);

        if (cmp < 0) {
            return contains(x.lb, p);
        } else if (cmp > 0) {
            return contains(x.rt, p);
        } else {
            return p;
        }
    }

    public void draw() {
        draw(root, 0);
    }

    private void draw(Node x, int l) {
        if (x == null) {
            return;
        }
        draw(x.lb, l + 1);
        StdDraw.setPenRadius(0.005);
        if (l % 2 == 0) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(x.p.x(), x.rect.ymin(), x.p.x(), x.rect.ymax());
        } else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(x.rect.xmin(), x.p.y(), x.rect.xmax(), x.p.y());
        }

        StdDraw.setPenRadius(0.015);
        StdDraw.setPenColor(StdDraw.BLACK);
        x.p.draw();

        draw(x.rt, l + 1);
    }

    public Iterable<Point2D> range(RectHV rect) {
        Queue<Point2D> queue = new Queue<>();

        return queue;
    }

    public Point2D nearest(Point2D p) {
        return null;
    }

    public static void main(String[] args) {
        KdTree search = new KdTree();
        search.insert(new Point2D(0.7, 0.2));
        search.insert(new Point2D(0.5, 0.4));
        // search.insert(new Point2D(0.2, 0.3));
        // search.insert(new Point2D(0.4, 0.7));
        // search.insert(new Point2D(0.9, 0.6));
        System.out.println(search.contains(new Point2D(0.7, 0.2)));
        // StdDraw.line(0.7, 0, 0.7, 1); x-axis StdDraw.line(x, 0, x, 1);
        // StdDraw.line(0, 0.2, 1, 0.2); y-axis StdDraw.line(0, y, 1, y);
        search.draw();
    }
}
