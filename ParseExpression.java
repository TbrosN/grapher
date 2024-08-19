// reads infix expression (including numeric values)
// and converts to ArrayList of Strings

import java.util.List;
import java.util.ArrayList;

public class ParseExpression
{
	public static boolean isNumber(String s)
	{
		String digits = ".0123456789.";
		
		if (digits.indexOf( s.substring(0,1) ) > -1)
			return true;
		return false;	
	}
	
	public static boolean isOperator(String s)
	{
		String operator = "+-*/()";
		
		if (operator.indexOf( s.substring(0,1) ) > -1)
			return true;
		return false;	
	}
	
	public static List<String> parser(String exp)
	{
		List<String> result = new ArrayList<String>();
		
		String theNumber="";
		
		for (int i = 0; i < exp.length(); i++)
		{
			String token = exp.substring(i,i+1);
			if ( isOperator(token) )
				{	
					if ( !theNumber.equals("") ) 
						result.add(theNumber);
					result.add(token);
					theNumber = "";
				}
			else
				theNumber += token;	
		}
		if ( !theNumber.equals("") ) 
			result.add(theNumber);
		return result;
	}

}