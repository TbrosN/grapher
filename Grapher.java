import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import java.util.ArrayList;

class Grapher extends JFrame implements ActionListener
{
    private GraphView view;
    private GraphModel model;
    
    private JButton graphButton;    
    private JLabel yEquals;
    private JTextField equation;
    private JButton clearButton;
    
    Grapher()
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

      // put buttons, view together
      Container c = getContentPane();
      c.add(controlPanel, BorderLayout.NORTH);
      c.add(view, BorderLayout.CENTER);

      // build the model
      model = new GraphModel(view);
    }

    public void run(JButton button)
    {
      if (button == graphButton)
        model.graph(equation.getText(), -view.getWidth()/2.0, view.getWidth()/2.0);
      else if (button == clearButton)
        model.clearPoints();
    }

    public void actionPerformed(ActionEvent e)
    {
        JButton b = (JButton)e.getSource();
        this.run(b);
    }
}
