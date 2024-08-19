import java.util.*;
public class EvalInfix2
{
    /*
    For example, if the user enters (a+b)/(c-b) your code should:
    convert it to postfix ab+cb-/  (I suggest you print this for debugging purposes)
    perform ONE pass through the postfix expression:
    if the character is an operand, you will check to see if you have already encountered this operand.
    if you have not, you will prompt the user to enter a value for the operand.
    you will instantiate an object of your variable class, and store the character with its value
    you will push the value of the variable onto the stack
    if the character is an operator, you will pop, pop, and apply the operator. Push the result onto the stack.
    when you reach the end of the postfix string the value of the expression will be the only item on your stack.
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
               else if (theChar.equals("/"))
                   nums.push(a/b);
            }

        }
        return nums.pop();
    }
}
