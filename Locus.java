import java.awt.*;
import java.util.*;
import java.awt.geom.*;

public class Locus
{
  private ArrayList<Point2D.Double> points;
  private Point origin;

  public Locus(int w, int h)
  {
    points = new ArrayList<Point2D.Double>();
    origin = new Point(w/2,h/2);
  }

  public ArrayList<Point2D.Double> getPoints()
  {
    return points;
  }

  public void updatePoints(String func, double start, double end)
  {
    if (func.length()<1) return;
    for (double i = start; i <= end; i++)
        points.add( new Point2D.Double(origin.x+i, origin.y-eval(func, i)) );
  }

  public double eval(String func, double x)
  {
    // First pass through func:
    // Insert variables and account for implied multiplication by the variable
    // Account for negative signs
    for (int i = 0; i < func.length(); i++)
    {
      String token = func.substring(i,i+1);
      if (token.equals("x"))
      {
        String after = func.substring(i+1);
        func = func.substring(0,i);
        func += "(" + x + ")";
        func += after;
      }
      if (isBeforeNeg(func,i-1))
      {
        String after = func.substring(i+1);
        func = func.substring(0,i);
        func += "(0-1)";
        func += after;
      }
   }
   // Second pass through func:
   // Find all instances of implied multiplication
   // and insert * signs where necessary
   for (int i = 0; i < func.length(); i++)
   {
      String token = func.substring(i,i+1);
      // If there is implied multiplication
      if (i-1 >= 0 && mightPrecedeOp(func,i-1))
         if (token.equals("(") ||
             !isOperator(token) &&
             func.substring(i-1,i).equals(")") &&
             !token.equals(")"))
         {
            String after = func.substring(i);
            func = func.substring(0,i);
            func += "*";
            func += after;
         }
    }
    //System.out.println("Infix: " + func);
    return EvalInfix2.result(
               Postfix2.convert(
                  ParseExpression.parser(func)
               )
           );
  }

  // Returns true if the ith character of f is before a negative sign
  public boolean isBeforeNeg(String f, int i)
  {
     //If the next character is not a minus
     if (!f.substring(i+1,i+2).equals("-")) return false;
     //If the negative sign is the 0th character of f
     if (i < 0) return true;
     return !mightPrecedeOp(f,i);
  }
  
  //Returns true if the ith character of f COULD precede an operator
  //Precondition: i is a legal index of f
  public boolean mightPrecedeOp(String f, int i)
  {
      //All possible characters before an operator
      String beforeOp = ")1234567890";
      return beforeOp.indexOf(f.substring(i,i+1)) > -1;
  }
  
  public boolean isOperator(String s)
  {
      String operators = "+-/*";
      return operators.indexOf(s) > -1;
  }
}