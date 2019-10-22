package clustering;

import java.util.ArrayList;

/**
 *
 * @author Hera
 */
public class Cluster {
    
    ArrayList <Point> points = new ArrayList<Point>();
    Point meanPoint;
    boolean wasUpdated = true;
    
    Cluster(Point mean) {
        meanPoint = mean;
    }
    
    Cluster(ArrayList<Point> points) {
        this.points = new ArrayList (points);
        meanPoint = new Point (0.0, 0.0);
        for (Point p : points) {
            meanPoint.x += p.x;
            meanPoint.y += p.y;
        }
        meanPoint.x /= points.size();
        meanPoint.y /= points.size();
    }
    
    void recalculateMean() {
        Point previousMean = new Point(meanPoint);
        meanPoint.x = 0;
        meanPoint.y = 0;
        for (Point p : points) {
            meanPoint.x += p.x;
            meanPoint.y += p.y;
        }
        meanPoint.x /= (double)points.size();
        meanPoint.y /= (double)points.size();
        if (meanPoint.x == previousMean.x && meanPoint.y == previousMean.y) {
            this.wasUpdated = false;
        } else {
            this.wasUpdated = true;
        }
    }
    
    // will NOT copy the points. Only the mean
    Cluster(Cluster c) {
        this.points = new ArrayList<Point>();
        this.meanPoint = c.meanPoint;
    }
    
    double getTotalSumError() {
        double val = 0.0;
        for (Point p : points) {
            val += Math.pow(p.distance(meanPoint), 2);
        }
        return val;
    }
    
    void makeClusterEmpty() {
        this.points = new ArrayList<Point> ();
    }
    
}
