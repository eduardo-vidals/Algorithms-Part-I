package computerscience.algorithms.week3.collinearpoints;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author EduardoPC
 */
public class FastCollinearPoints {

    private final LineSegment[] segments;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {

        ArrayList<LineSegment> segmentsList = new ArrayList<>();

        checkNull(points);
        Point[] pointsCopy = points.clone();
        Arrays.sort(pointsCopy);
        checkDuplicates(pointsCopy);

        for (int i = 0; i < pointsCopy.length; i++) {

            // Sort the points by the the slope it makes with point p     
            Point p = pointsCopy[i];
            Point[] pointsBySlope = pointsCopy.clone();
            Arrays.sort(pointsBySlope, p.slopeOrder());
            System.out.println(Arrays.toString(pointsBySlope));

            int x = 0;

            // First loop is used to go through the pointsBySlope array
            while (x < pointsBySlope.length) {

                // Second loop is used to determine if a point has three or more valid points of the same slope
                // ahead of it. The loop will stop if the point ahead does not have the same slope with point p 
                // The list will be instantiated after every new iteration.
                ArrayList<Point> validPoints = new ArrayList<>();
                double slope = p.slopeTo(pointsBySlope[x]);
                while (x < pointsBySlope.length && p.slopeTo(pointsBySlope[x]) == slope) {
                    validPoints.add(pointsBySlope[x++]);
                }

                // If the there are 3 or more validPoints, then compare p with the
                // first point from the list because a line segment will always
                // have the first point being less than the last point
                // This works because any point that is the lowest in the y-axis
                // will always be the lowest point. The highest point is always
                // the highest within the y-axis. If two points have the same y
                // value, then the lowest point in the x-axis will have the
                // lowest point, while the highest point being the highest
                // within the x-axis.
                // The program sorts the points by slope with respect to point p
                // and this allows us to not have duplicate segments because if
                // there was a line segment from p -> q -> r -> s, then the only
                // line segment that will be counted is the one with the lower
                // value at either end of the segment
                if (validPoints.size() >= 3 && p.compareTo(validPoints.get(0)) < 0) {
                    // The first point of the segment
                    Point first = p;
                    // The last point of the segment
                    Point last = validPoints.get(validPoints.size() - 1);
                    // Creation of the line segment object
                    segmentsList.add(new LineSegment(first, last));
                }

            }

        }

        segments = segmentsList.toArray(new LineSegment[0]);

    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.length;
    }

    // the line segments
    public LineSegment[] segments() {
        return segments.clone();
    }

    // check if array has null element
    private void checkNull(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }

        for (Point point : points) {
            if (point == null) {
                throw new IllegalArgumentException();
            }
        }
    }

    // check if array has a repeated point 
    private void checkDuplicates(Point[] points) {
        for (int i = 1; i < points.length; i++) {
            if (points[i].compareTo(points[i - 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }
    }

    // test client
    public static void main(String[] args) {

        Point point = new Point(1, 4);
        Point point1 = new Point(2, 3);
        Point point2 = new Point(3, 2);
        Point point3 = new Point(4, 1);
        Point point4 = new Point(5, 2);
        Point point5 = new Point(6, 3);
        Point point6 = new Point(7, 4);

        Point[] points = {point, point1, point2, point3, point4, point5, point6};

        FastCollinearPoints fcp = new FastCollinearPoints(points);

        System.out.println("Number of segments: " + fcp.numberOfSegments());

        System.out.println("Segments: " + Arrays.toString(fcp.segments()));

    }
}
