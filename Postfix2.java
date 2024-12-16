import java.util.*;

/**
 * The Postfix2 class provides methods for converting infix expressions to postfix notation.
 * It includes functionality for determining operator precedence and ensuring proper order
 * of operations using a stack.
 */
public class Postfix2 {

    /**
     * Determines the precedence of a given operator.
     *
     * @param operator The operator as a string.
     * @return The precedence of the operator (1 for + and -, 2 for * and /, -1 for invalid operators).
     */
    public static int getPrecedence(String operator)
    {
        if (operator.equals("+") || operator.equals("-")) return 1;
        if (operator.equals("*") || operator.equals("/")) return 2;
        return -1;
    }

    /**
     * Converts an infix expression into postfix notation.
     *
     * @param infix A list of strings representing the infix expression.
     * @return A list of strings representing the postfix expression.
     */
    public static List<String> convert(List<String> infix)
    {
        List<String> postfix = new ArrayList<String>();
        Stack<String> ops = new Stack<String>();

        for (int i = 0; i < infix.size(); i++)
        {
            String theChar = infix.get(i);

            // Opening parentheses are always pushed onto the stack
            if (theChar.equals("("))
                ops.push(theChar);

            // If the character is an operator
            else if (theChar.equals("+") ||
                     theChar.equals("-") ||
                     theChar.equals("*") ||
                     theChar.equals("/"))
            {
                // Pop and append all operators that have precedence >= the current operator
                while (!ops.isEmpty() &&
                       !ops.peek().equals("(") &&
                       getPrecedence(ops.peek()) >= getPrecedence(theChar))
                {
                    postfix.add(ops.pop());
                }
                ops.push(theChar);
            }

            // Closing parentheses trigger popping until the corresponding opening parenthesis
            else if (theChar.equals(")"))
            {
                while (!ops.peek().equals("("))
                    postfix.add(ops.pop());
                ops.pop(); // Discard the opening parenthesis
            }

            // If the character is an operand, append it to the postfix list
            else
            {
                postfix.add(theChar);
            }
        }

        // Pop and append remaining operators in the stack
        while (!ops.isEmpty())
            postfix.add(ops.pop());

        return postfix;
    }
}
