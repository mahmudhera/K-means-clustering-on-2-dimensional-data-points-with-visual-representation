package clustering;

/**
 *
 * @author Hera
 */
public class Point {
    
    double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public double distance(Point p) {
        double delX = this.x - p.x;
        double delY = this.y - p.y;
        return Math.sqrt(delX*delX + delY*delY);
    }
    
    public Point (Point p) {
        this.x = p.x;
        this.y = p.y;
    }
    
}
