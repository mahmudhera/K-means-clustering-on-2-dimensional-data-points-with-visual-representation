package clustering;

import java.util.ArrayList;

/**
 *
 * @author Hera
 */
public class BisectingKMeans {
    
    ArrayList<Point> allPoints;

    public BisectingKMeans(ArrayList<Point> allPoints) {
        this.allPoints = allPoints;
    }
    
    ArrayList<Cluster> getClusters(int k) {
        ArrayList<Cluster> list = new ArrayList<Cluster>();
        list.add ( new Cluster (allPoints) );
        for (int size = 2; size <= k; size++) {
            double maxError = Double.MIN_VALUE;
            Cluster worstCluster = null;
            for (Cluster c : list) {
                double val = c.getTotalSumError();
                if (val > maxError) {
                    maxError = val;
                    worstCluster = c;
                }
            }
            if (worstCluster != null) {
                list.remove(worstCluster);
                double minError = Double.MAX_VALUE;
                ArrayList<Cluster> bestSplittedClusters = null;
                for (int count = 0; count < 50; count++) {
                    KMeans km = new KMeans (worstCluster.points, 2);
                    ArrayList<Cluster> splittedClusters = km.getClusters();
                    double value = 0.0;
                    for (Cluster c : splittedClusters) {
                        value += c.getTotalSumError();
                    }
                    if (value < minError) {
                        value = minError;
                        bestSplittedClusters = splittedClusters;
                    }
                }
                list.addAll(bestSplittedClusters);
            }
        }
        return list;   
    }
}
