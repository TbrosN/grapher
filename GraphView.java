import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.geom.*;

/**
 * The GraphView class serves as the view component in an MVC (Model-View-Controller) architecture.
 * It is responsible for visually rendering the graph, including axes and graphed functions.
 */
class GraphView extends JPanel
{
    /**
     * List of Locus objects representing the graphed equations.
     */
    private ArrayList<Locus> loci;

    /**
     * Constructs a GraphView object with an empty list of loci.
     */
    public GraphView()
    {
        loci = new ArrayList<Locus>();
    }

    /**
     * Updates the view with a new list of loci and triggers a repaint.
     *
     * @param theLoci The new list of Locus objects to render.
     */
    public void updateView(ArrayList<Locus> theLoci)
    {
        loci = theLoci;
        repaint();
    }

    /**
     * Paints the graph, including axes and the loci of points.
     *
     * @param g The Graphics object used for drawing.
     */
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);

        int w = getWidth();
        int h = getHeight();

        // Draw the x and y axes
        g2.drawLine(w / 2, 0, w / 2, h);
        g2.drawLine(0, h / 2, w, h / 2);

        // Draw the loci of points
        for (Locus lo : loci)
        {
            ArrayList<Point2D.Double> points = lo.getPoints();
            for (int i = 0; i < points.size() - 1; i++)
            {
                g2.draw(new Line2D.Double(
                        points.get(i).getX(), points.get(i).getY(),
                        points.get(i + 1).getX(), points.get(i + 1).getY()));
            }
        }
    }
}
