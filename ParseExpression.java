import java.util.List;
import java.util.ArrayList;

/**
 * The ParseExpression class provides utility methods for parsing mathematical expressions.
 * It can convert an infix expression string into a list of tokens (numbers and operators).
 */
public class ParseExpression
{
    /**
     * Determines if a given string is a numeric value.
     *
     * @param s The string to check.
     * @return True if the string represents a number, otherwise false.
     */
    public static boolean isNumber(String s)
    {
        String digits = ".0123456789.";

        return digits.indexOf(s.substring(0, 1)) > -1;
    }

    /**
     * Determines if a given string is a mathematical operator.
     *
     * @param s The string to check.
     * @return True if the string is an operator, otherwise false.
     */
    public static boolean isOperator(String s)
    {
        String operator = "+-*/()";

        return operator.indexOf(s.substring(0, 1)) > -1;
    }

    /**
     * Parses an infix expression string into a list of tokens.
     *
     * @param exp The infix expression as a string.
     * @return A list of strings where each element is either a number or an operator.
     */
    public static List<String> parser(String exp)
    {
        List<String> result = new ArrayList<String>();
        String theNumber = "";

        for (int i = 0; i < exp.length(); i++)
        {
            String token = exp.substring(i, i + 1);
            if (isOperator(token))
            {
                if (!theNumber.equals("")) 
                    result.add(theNumber);
                result.add(token);
                theNumber = "";
            }
            else if (isNumber(token)) 
            {
                theNumber += token;
            }
        }

        if (!theNumber.equals("")) 
            result.add(theNumber);

        return result;
    }
}
