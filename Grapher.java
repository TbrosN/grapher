import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.ArrayList;

/**
 * The Grapher class is a JFrame-based application for graphing mathematical equations.
 * It provides a GUI interface for entering equations, graphing them, and clearing the graph.
 */
public class Grapher extends JFrame implements ActionListener
{
    private GraphView view; // The visual component for displaying the graph
    private GraphModel model; // The model responsible for managing the graph data

    private JButton graphButton; // Button to trigger graphing the equation
    private JLabel yEquals; // Label indicating the equation input field
    private JTextField equation; // Text field for entering the equation
    private JButton clearButton; // Button to clear the graph

    /**
     * Constructs the Grapher application window.
     * Initializes the GUI components and links them to the graphing model and view.
     */
    public Grapher()
    {
        super("Grapher");
        view = new GraphView();
        model = new GraphModel(view);

        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        yEquals = new JLabel("y = ");
        controlPanel.add(yEquals);
        equation = new JTextField(10);
        controlPanel.add(equation);
        graphButton = new JButton("Graph");
        graphButton.addActionListener(this);
        controlPanel.add(graphButton);
        clearButton = new JButton("Clear");
        clearButton.addActionListener(this);
        controlPanel.add(clearButton);

        // Add the control panel and graph view to the main container
        Container c = getContentPane();
        c.add(controlPanel, BorderLayout.NORTH);
        c.add(view, BorderLayout.CENTER);

        model = new GraphModel(view);
    }

    /**
     * Handles the button actions for graphing or clearing the graph.
     *
     * @param button The JButton that triggered the action.
     */
    public void run(JButton button)
    {
        if (button == graphButton)
        {
            model.graph(equation.getText(), -view.getWidth() / 2.0, view.getWidth() / 2.0);
        }
        else if (button == clearButton)
        {
            model.clearPoints();
        }
    }

    /**
     * Invoked when an action occurs, such as pressing a button.
     *
     * @param e The ActionEvent triggered by a button press.
     */
    public void actionPerformed(ActionEvent e)
    {
        JButton b = (JButton) e.getSource();
        this.run(b);
    }
}
