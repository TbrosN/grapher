import java.awt.*;
import java.util.*;
import java.awt.geom.*;

/**
 * The Locus class represents a mathematical locus of points, typically used for plotting graphs
 * of functions. It manages points calculated from a function string and allows dynamic updates.
 */
public class Locus
{
    private ArrayList<Point2D.Double> points; // List of points representing the locus
    private Point origin; // The origin point of the graph

    /**
     * Constructs a Locus object with a specified width and height.
     * The origin is initialized at the center of the given dimensions.
     *
     * @param w The width of the graphing area.
     * @param h The height of the graphing area.
     */
    public Locus(int w, int h)
    {
        points = new ArrayList<Point2D.Double>();
        origin = new Point(w/2, h/2);
    }

    /**
     * Returns the list of points representing the locus.
     *
     * @return An ArrayList of Point2D.Double objects.
     */
    public ArrayList<Point2D.Double> getPoints()
    {
        return points;
    }

    /**
     * Updates the locus points based on the given function string and range of x values.
     *
     * @param func The function string to evaluate.
     * @param start The starting x-coordinate.
     * @param end The ending x-coordinate.
     */
    public void updatePoints(String func, double start, double end)
    {
        if (func.length() < 1) return;
        for (double i = start; i <= end; i++)
        {
            points.add(new Point2D.Double(origin.x + i, origin.y - eval(func, i)));
        }
    }

    /**
     * Evaluates the given function string at a specific x value.
     *
     * @param func The function string to evaluate.
     * @param x The value of x to substitute into the function.
     * @return The result of the function evaluation as a double.
     */
    public double eval(String func, double x)
    {
        int i = 0;
        while (i < func.length())
        {
            String token = func.substring(i, i + 1);
            if (token.equals("x"))
            {
                String after = func.substring(i + 1);
                func = func.substring(0, i);
                func += "(" + x + ")";
                func += after;
                i += 1;
            }
            else if (isBeforeNegativeSign(func, i - 1))
            {
                String after = func.substring(i + 1);
                func = func.substring(0, i);
                func += "(0-1)*";
                func += after;
                i += 5;
            }
            else
                i += 1;
        }

        for (i = 0; i < func.length(); i++)
        {
            String token = func.substring(i, i + 1);
            if (i - 1 >= 0 && mightPrecedeOp(func, i - 1))
            {
                if (token.equals("(") ||
                    !isOperator(token) &&
                    func.substring(i - 1, i).equals(")") &&
                    !token.equals(")"))
                {
                    String after = func.substring(i);
                    func = func.substring(0, i);
                    func += "*";
                    func += after;
                }
            }
        }

        return EvalInfix2.result(
                Postfix2.convert(
                        ParseExpression.parser(func)
                )
        );
    }

    /**
     * Determines if the ith character in the function string is before a negative sign.
     *
     * @param f The function string.
     * @param i The index to check.
     * @return True if the character is before a negative sign, otherwise false.
     */
    public boolean isBeforeNegativeSign(String f, int i)
    {
        if (!f.substring(i + 1, i + 2).equals("-")) return false;
        if (i < 0) return true;
        return !mightPrecedeOp(f, i);
    }

    /**
     * Determines if the ith character in the function string could precede an operator.
     *
     * @param f The function string.
     * @param i The index to check.
     * @return True if the character could precede an operator, otherwise false.
     */
    public boolean mightPrecedeOp(String f, int i)
    {
        String beforeOp = ")1234567890";
        return beforeOp.indexOf(f.substring(i, i + 1)) > -1;
    }

    /**
     * Determines if the given string is an operator.
     *
     * @param s The string to check.
     * @return True if the string is an operator, otherwise false.
     */
    public boolean isOperator(String s)
    {
        String operators = "+-/*";
        return operators.indexOf(s) > -1;
    }
}
