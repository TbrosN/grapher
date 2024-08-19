import java.util.*;
public class Postfix2 {
    public static int getPrecedence(String operator)
    {
        if (operator.equals("+") || operator.equals("-"))   return 1;
        if (operator.equals("*") || operator.equals("/"))   return 2;
        return -1;
    }
    public static List<String> convert(List<String> infix)
    {
        List<String> postfix = new ArrayList<String>();
        Stack<String> ops = new Stack<String>();
        for (int i = 0; i < infix.size(); i++)
        {
            String theChar = infix.get(i);
            //Opening parentheses are always pushed onto the stack
            if (theChar.equals("("))
                ops.push(theChar);
            // If the character is an operator 
            else if (theChar.equals("+") ||
                     theChar.equals("-") ||
                     theChar.equals("*") ||
                     theChar.equals("/"))
                     {
                    // You need to pop and append ALL operators that 
                    // are above the most recent
                    // opening parentheses AND that have precedence higher than
                    // or equal to the
                    // operator that you are processing
                    // Then you will push the operator onto the stack
                        while(!ops.isEmpty() &&
                              !ops.peek().equals("(") &&
                              getPrecedence(ops.peek())>=getPrecedence(theChar))
                              postfix.add(ops.pop());
                        ops.push(theChar);
                     }
            /*
            Closing parentheses cause all operators above the most recent opening 
         parentheses must be popped and appended to the postfix string.  
         Pop and discard the opening parentheses.
            */
            else if (theChar.equals(")"))
            {
                while (!ops.peek().equals("("))
                    postfix.add(ops.pop());
                ops.pop();
            }
            // If the character is an operand, append it to the postfix string
            else
                postfix.add(theChar);
        }
        //When you are done looping through the infix string, the stack
        // might still have some operators.  These need to be
        // popped and appended to the postfix string
        while (!ops.isEmpty())
            postfix.add(ops.pop());
        return postfix;
    }
}
