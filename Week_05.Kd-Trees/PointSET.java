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
import java.util.TreeSet;

/**
 *
 * @author Eduardo
 */
public class PointSET {

    private TreeSet<Point2D> tree;

    public PointSET() {
        tree = new TreeSet<>();
    }

    public boolean isEmpty() {
        return tree.isEmpty();
    }

    public int size() {
        return tree.size();
    }

    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }

        tree.add(p);
    }

    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        return tree.contains(p);
    }

    public void draw() {
        StdDraw.setPenRadius(0.02);
        for (Point2D point : tree) {
            StdDraw.point(point.x(), point.y());
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException();
        }

        Queue<Point2D> queue = new Queue<>();

        for (Point2D point : tree) {
            if (rect.contains(point)) {
                queue.enqueue(point);
            }
        }

        return queue;
    }

    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }

        if (isEmpty()) {
            return null;
        }
        Point2D closestPoint = null;
        for (Point2D point : tree) {
            if (closestPoint == null || point.distanceSquaredTo(p) < closestPoint.distanceSquaredTo(p)) {
                closestPoint = point;
            }
        }

        return closestPoint;
    }

    public static void main(String[] args) {
        PointSET search = new PointSET();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.02);
        StdDraw.rectangle(0.2, 0.2, 0.1, 0.1);
        StdDraw.setPenColor(StdDraw.RED);
        search.insert(new Point2D(0.1, 0.1));
        search.insert(new Point2D(0.3, 0.3));
        search.insert(new Point2D(0.35, 0.13));
        search.draw();

        Iterable<Point2D> stack = search.range(new RectHV(0.1, 0.1, 0.3, 0.3));
        System.out.println(stack);
        System.out.println(search.nearest((new Point2D(0.3, 0.6))));
    }
}
