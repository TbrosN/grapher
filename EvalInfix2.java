/**
 * This class provides a method to evaluate mathematical expressions represented in postfix notation.
 */
import java.util.*;
public class EvalInfix2
{
    /**
     * Evaluates a mathematical expression given in postfix notation.
     *
     * @param postfix A list of tokens representing a postfix expression. Each element is either a numeric operand
     *                (e.g., "2.0") or an operator (e.g., "+", "-", "*", "/").
     * @return The evaluated result as a double.
     */
    public static double result(List<String> postfix)
    {
        String operators = "+-*/";
        Scanner scan = new Scanner(System.in);
        Stack<Double> nums = new Stack<Double>();
        for (int i = 0; i < postfix.size(); i++)
        {
            String theChar = postfix.get(i);
            if (operators.indexOf(theChar) == -1)
                nums.push(Double.valueOf(theChar));
            else
            {
               double b = nums.pop();
               double a = nums.pop();
               if (theChar.equals("+"))
                  nums.push(a+b);
               else if (theChar.equals("-"))
                  nums.push(a-b);
               else if (theChar.equals("*"))
                   nums.push(a*b);
               else if (theChar.equals("/")) {
                    if (b == 0) {
                        System.out.println("Division by zero occurred!");
                        return 0;
                    }
                    nums.push(a/b);
               }
            }

        }
        return nums.pop();
    }
}
