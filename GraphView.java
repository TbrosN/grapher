import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.geom.*;

/*
  this is the View component
*/

class GraphView extends JPanel
{
    private ArrayList<Locus> loci;

    public GraphView()
    {
        loci = new ArrayList<Locus>();
    }

    // entry point from model, requests grid be redisplayed
    public void updateView( ArrayList<Locus> theLoci )
    {
        loci = theLoci;
        repaint();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        int w = getWidth();
        int h = getHeight();
        g2.drawLine(w/2,0,w/2,h);
        g2.drawLine(0,h/2,w,h/2);
        for (Locus lo : loci)
        {
          ArrayList<Point2D.Double> points = lo.getPoints();
          for (int i = 0; i < points.size()-1; i++)
              g2.draw( new Line2D.Double(points.get(i).getX(), points.get(i).getY(),
                        points.get(i+1).getX(), points.get(i+1).getY()) );
        }
    }
}
