package clustering;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;

/**
 *
 * @author Hera
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        
        FileParser fp = new FileParser ("bisecting.txt");
        ArrayList<Point> arr = fp.getPoints();
        
        /*Random random = new Random();
        
        ArrayList<Point> arr = new ArrayList<Point>();
        for (int i = 0; i < 30; i++) {
            arr.add( new Point (random.nextGaussian()*5+50, random.nextGaussian()*5+10) );
            arr.add( new Point (random.nextGaussian()*5+10, random.nextGaussian()*5+50) );
            arr.add( new Point (random.nextGaussian()*5+50, random.nextGaussian()*5+50) );
        }*/
        
        BisectingKMeans km = new BisectingKMeans (arr);
        ArrayList<Cluster> list = km.getClusters(12);
        
        GUI gui = new GUI(list, "Bisecting");
        gui.setVisible(true);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Point[] meanPoints = new Point [list.size()];
        for (int i = 0; i < meanPoints.length; i++) {
            meanPoints[i] = list.get(i).meanPoint;
        }
        KMeans kmn = new KMeans (meanPoints, arr);
        list = kmn.getClusters();
        
        gui = new GUI(list, "K Means");
        gui.setVisible(true);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
