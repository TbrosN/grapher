import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class GraphModel {

    /*
     *  This is the Model component.
    */

    private ArrayList<Locus> loci;
    private GraphView myView;

    public GraphModel(GraphView view)
    {
      loci = new ArrayList<Locus>();
      myView = view;
    }

    public void clearPoints()
    {
      loci = new ArrayList<Locus>();
      myView.updateView(loci);
    }

    public void graph(String func, double start, double end)
    {
      Locus lo = new Locus(myView.getWidth(),myView.getHeight());
      lo.updatePoints(func,start,end);
      loci.add(lo);
      myView.updateView(loci);
    }
}