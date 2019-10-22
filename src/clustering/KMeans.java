package clustering;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Hera
 */
public class KMeans {
    
    int k;
    Point[] initialPoints;
    ArrayList<Point> allPoints;
    
    KMeans(Point[] initialPoints, ArrayList<Point> allPoints) {
        k = initialPoints.length;
        this.initialPoints = initialPoints;
        this.allPoints = allPoints;
    }
    
    KMeans (ArrayList<Point> allPoints, int k) {
        this.k = k;
        this.allPoints = allPoints;
        double minx, miny, maxx, maxy;
        minx = maxx = allPoints.get(0).x;
        miny = maxy = allPoints.get(0).y;
        for (int i = 1; i < allPoints.size(); i++) {
            if (allPoints.get(i).x < minx) minx = allPoints.get(i).x;
            if (allPoints.get(i).y < miny) miny = allPoints.get(i).y;
            if (allPoints.get(i).x > maxx) maxx = allPoints.get(i).x;
            if (allPoints.get(i).y > maxy) maxy = allPoints.get(i).y;
        }
        Random random = new Random ();
        initialPoints = new Point[k];
        for (int i = 0; i < k; i++) {
            initialPoints[i] = new Point ( minx + random.nextDouble()*(maxx-minx), miny + random.nextDouble()*(maxy-miny) );
        }
    }
    
    ArrayList<Cluster> getClusters () {
        
        ArrayList<Cluster> clusters = new ArrayList<Cluster> ();
        for (int i = 0; i < k; i++) {
            clusters.add( new Cluster (initialPoints[i]) );
        }
        for (Point p : allPoints) {
            double minDistance = Double.MAX_VALUE;
            Cluster candidate = null;
            for (Cluster c : clusters) {
                double distance = c.meanPoint.distance(p);
                if (distance < minDistance) {
                    minDistance = distance;
                    candidate = c;
                }
            }
            if (candidate != null) {
                candidate.points.add(p);
            }
        }
        for (int stepCount = 0; stepCount < 10; stepCount++) {
            for (Cluster c : clusters) {
                c.recalculateMean();
                c.makeClusterEmpty();
            }
            
            for (Point p : allPoints) {
                double minDistance = Double.MAX_VALUE;
                Cluster candidate = null;
                for (Cluster c : clusters) {
                    double distance = c.meanPoint.distance(p);
                    if (distance < minDistance) {
                        minDistance = distance;
                        candidate = c;
                    }
                }
                if (candidate != null) {
                    candidate.points.add(p);
                }
            }
            
            for (Cluster c : clusters) {
                c.recalculateMean();
            }
            
            boolean noneUpdated = true;
            for (Cluster c : clusters) {
                if (c.wasUpdated) {
                    noneUpdated = false;
                    break;
                }
            }
            if (noneUpdated) {
                break;
            }
        }
        return clusters;
    }
    
}
