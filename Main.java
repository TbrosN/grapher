import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

class Main {
  public static void main(String[] args)
  {
      Grapher conway = new Grapher();
      conway.addWindowListener(new WindowAdapter()
      {
         public void windowClosing(WindowEvent e)
         {
            System.exit(0);
         }
      });
      conway.setSize(750, 500);
      conway.setVisible(true);
  }
}