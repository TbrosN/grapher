import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

/**
 * The GraphModel class serves as the model component in an MVC (Model-View-Controller) architecture.
 * It manages the loci (sets of points) used to represent graphed equations and communicates updates to the view.
 */
public class GraphModel {

    /**
     * List of Locus objects representing graphed equations.
     */
    private ArrayList<Locus> loci;

    /**
     * Reference to the associated GraphView, responsible for displaying the graph.
     */
    private GraphView myView;

    /**
     * Constructs a GraphModel object and associates it with a GraphView.
     *
     * @param view The GraphView to update when the model changes.
     */
    public GraphModel(GraphView view)
    {
        loci = new ArrayList<Locus>();
        myView = view;
    }

    /**
     * Clears all graphed points by resetting the loci list.
     * Updates the associated GraphView to reflect the cleared state.
     */
    public void clearPoints()
    {
        loci = new ArrayList<Locus>();
        myView.updateView(loci);
    }

    /**
     * Adds a new graph of the specified function over the given range.
     * Updates the associated GraphView to display the new graph.
     *
     * @param func The mathematical function to graph, provided as a string.
     * @param start The starting x-coordinate for the graph.
     * @param end The ending x-coordinate for the graph.
     */
    public void graph(String func, double start, double end)
    {
        Locus lo = new Locus(myView.getWidth(), myView.getHeight());
        lo.updatePoints(func, start, end);
        loci.add(lo);
        myView.updateView(loci);
    }
}
