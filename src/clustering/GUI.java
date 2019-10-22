package clustering;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class GUI extends JFrame {

    private JFrame jf = this;
    private Grid grid;
    
    ArrayList<Cluster> clusters;
    
    public GUI(ArrayList<Cluster> clusters, String str) {
        
        this.clusters = clusters;
        
        setTitle(str);
        this.setLayout(new BorderLayout());
        
        this.setSize(700, 700);
        
        grid = new Grid(clusters, str);
        add(grid);
    
    }

    
    class Grid extends JPanel {

        private Color[] colors = {Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.GREEN, Color.MAGENTA, Color.ORANGE,
                Color.PINK, Color.RED, Color.YELLOW,
                Color.LIGHT_GRAY, Color.GRAY
        };
        
        private Color c = Color.BLACK;
        private JButton jButton1;
        private JTextArea jTextArea;
        
        private double minX, minY;
        private double scaleFactorX, scaleFactorY;
        
        ArrayList<Cluster> clusters;
        
        public Grid(ArrayList<Cluster> clusters, String str) {
            
            this.clusters = clusters;
            
            double maxX = Double.MIN_VALUE;
            double maxY = Double.MIN_VALUE;
            minX = Double.MAX_VALUE;
            minY = Double.MAX_VALUE;

            for (Cluster c : clusters) {
                for (Point p : c.points) {
                    if (p.x > maxX) maxX = p.x;
                    if (p.y > maxY) maxY = p.y;
                    if (p.x < minX) minX = p.x;
                    if (p.y < minY) minY = p.y;
                }
            }
            
            scaleFactorX = 500.0 / (maxX - minX);
            scaleFactorY = 500.0 / (maxY - minY);
            
            setLayout(new FlowLayout());
            jButton1 = new JButton("Close");
            add(jButton1);
            repaint();
            
            jTextArea = new JTextArea();
            add(jTextArea);
            repaint();
            
            jButton1.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jButton1ActionPerformed(evt);
                }
            });
        
        }

        private void jButton1ActionPerformed(ActionEvent evt) {
            jf.dispose();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (int i = 0; i < this.clusters.size(); i++) {
                g.setColor(colors[i%12]);
                Cluster c = this.clusters.get(i);
                for (Point p : c.points) {
                    g.fillRect( (int)((p.x-minX)*(scaleFactorX)) + 30, (int)((p.y-minY)*(scaleFactorY)) + 50, 5, 5);
                }
                //g.setColor(colors[(i+1)%12]);
                g.drawLine((int)((c.meanPoint.x-minX)*(scaleFactorX)) + 15, (int)((c.meanPoint.y-minY)*(scaleFactorY)) + 50,
                        (int)((c.meanPoint.x-minX)*(scaleFactorX)) + 45, (int)((c.meanPoint.y-minY)*(scaleFactorY)) + 50);
                g.drawLine((int)((c.meanPoint.x-minX)*(scaleFactorX)) + 30, (int)((c.meanPoint.y-minY)*(scaleFactorY)) + 35,
                        (int)((c.meanPoint.x-minX)*(scaleFactorX)) + 30, (int)((c.meanPoint.y-minY)*(scaleFactorY)) + 65);
            }
        }
    }
}