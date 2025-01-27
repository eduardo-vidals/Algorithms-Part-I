package computerscience.algorithms.week3.collinearpoints;

/** ****************************************************************************
 *  Compilation:  javac Point.java
 *  Execution:    java Point
 *  Dependencies: none
 *
 *  An immutable data type for points in the plane.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 ***************************************************************************** */

import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {
    
    // public static final Comparator<Point> BY_SLOPE = new PolarOrder();
    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw() {
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point. Formally,
     * if the two points are (x0, y0) and (x1, y1), then the slope is (y1 - y0)
     * / (x1 - x0). For completeness, the slope is defined to be +0.0 if the
     * line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical; and
     * Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        
        if (this.x == that.x && this.y == that.y) {
            return Double.NEGATIVE_INFINITY;
        }

        if (this.x == that.x) {
            return Double.POSITIVE_INFINITY;
        }
        
        if (this.y - that.y == 0) {
            return +0.0f;
        }
        return 1.0 * (this.y - that.y) / (this.x - that.x); //Multiplying by 1.0 to prevent integer division

    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument point
     * (x0 = x1 and y0 = y1); a negative integer if this point is less than the
     * argument point; and a positive integer if this point is greater than the
     * argument point
     */
    // sorts the points by least to greatest
    // NOTE: Can also be done from greatest to least and the only change that it will do is
    // if you have a line segment p -> q -> r -> s, then instead of it being p -> s it will
    // be s -> p
    @Override
    public int compareTo(Point that) {

        if (this.y == that.y) {
            return this.x - that.x;
        } else {
            return this.y - that.y;
        }
    }

    /**
     * Compares two points by the slope they make with this point. The slope is
     * defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        return new PolarOrder();
    }

    private class PolarOrder implements Comparator<Point> {

        @Override
        public int compare(Point a, Point b) {

            boolean slopeLess = slopeTo(a) < slopeTo(b);
            boolean slopeMore = slopeTo(a) > slopeTo(b);

            if (slopeLess) {
                return -1;
            } else if (slopeMore) {
                return 1;
            } else {
                return 0;
            }

        }

    }

    /**
     * Returns a string representation of this point. This method is provide for
     * debugging; your program should not rely on the format of the string
     * representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit tests the Point data type.
     * @param args
     */
    public static void main(String[] args) {

    }
}
