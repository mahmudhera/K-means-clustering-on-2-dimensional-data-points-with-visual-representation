package clustering;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Hera
 */
public class FileParser {
    
    File file;
    
    FileParser (String fileName) {
        file = new File (fileName);
    }
    
    void test() throws FileNotFoundException {
        Scanner scanner = new Scanner (file);
        while (scanner.hasNextDouble()) {
            System.out.println(scanner.nextDouble());
        }
    }
    
    ArrayList<Point> getPoints() throws FileNotFoundException {
        ArrayList<Point> list = new ArrayList<Point>();
        Scanner scanner = new Scanner (file);
        while (scanner.hasNextDouble()) {
            list.add( new Point (scanner.nextDouble(), scanner.nextDouble()) );
        }
        return list;
    }
    
}
