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
public class BruteCollinearPoints {

    private final LineSegment[] segments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {

        ArrayList<LineSegment> segmentsList = new ArrayList<>();
        
        checkNull(points);
        Point[] pointsCopy = points.clone();
        Arrays.sort(pointsCopy);
        checkDuplicates(pointsCopy);

        // On the 1st loop you will need at least 4 elements, and on the last 3 iterations you will not
        // be able to have 4 elements
        // On the 2nd loop you will need at least 3 elements, and on the last 2 iterations you will not 
        // be able to have 3 elements
        // On the 3rd loop you will need at least 2 elements, and on the last iteration you will not
        // be able to have 2 elements
        // On the 4th loop you will need at least 1 element, so you do NOT need to subtract
        for (int i = 0; i < points.length - 3; i++) {
            for (int j = i + 1; j < points.length - 2; j++) {
                for (int k = j + 1; k < points.length - 1; k++) {
                    for (int l = k + 1; l < points.length; l++) {
                        
                        // Slope from first to second
                        double FS = pointsCopy[i].slopeTo(pointsCopy[j]);
                        // Slope from first to third
                        double FT = pointsCopy[i].slopeTo(pointsCopy[k]);
                        // Slope from first to fourth
                        double FF = pointsCopy[i].slopeTo(pointsCopy[l]);
                        
                        // If the elements from these four points are equal to one another
                        // then make a line segment from the first to the fourth point
                        if (FS == FT && FS == FF) {
                            LineSegment tempLS = new LineSegment(pointsCopy[i], pointsCopy[l]);
                            
                            // If the line segment is already on the list, do not add it again
                            if (!segmentsList.contains(tempLS)) {
                                segmentsList.add(tempLS);
                            }
                        }

                    }
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
        Point point = new Point(1, 1);
        Point point1 = new Point(2, 2);
        Point point2 = new Point(2, 3);
        Point point3 = new Point(3, 3);
        Point point4 = new Point(4, 4);
        Point point5 = new Point(5, 2);

        Point[] points = {point, point1, point2, point3, point4, point5};

        BruteCollinearPoints bcp = new BruteCollinearPoints(points);

        System.out.println(bcp.numberOfSegments());
        System.out.println(Arrays.toString(bcp.segments()));

    }

}
