package booleanExpressionEvaluator;

import java.util.*;

/** An immutable class, stores variable to {true,false} mappings
 */
class Context {

	HashMap<String,Boolean> map = new HashMap<String,Boolean>();
	boolean lookup(String var) {
		return map.get(var).booleanValue();
	}
	public Context(HashMap<String,Boolean> m) {
		map = new HashMap<>(m);
	}
}

/**
 * Class ExpressionParser encapsulates a Boolean expression and a Context in 
 * which the expression is evaluated
 */
public class ExpressionParser {
	
	private BooleanExp expression;
	private Context context;
	
	/**
	 * This method is part of public interface. DO NOT REMOVE or change signature.
	 * 
	 * @param e is encapsulated Boolean expression
	 * @param hm is String->Boolean context map
	 */	
	public void init(BooleanExp e, HashMap<String,Boolean> hm) {
		expression = e;
		context = new Context(hm);
	}
	
		
	/** 
	  *  This method is part of public interface. DO NOT REMOVE or change signature.
	  * 
	  *  @param: str the string expression in preorder. E.g., AND OR x y z represents (x OR y) AND z
	  *  @return: returns the corresponding Boolean expression or null if str is invalid 
	  *  static "position" is used to avoid passing "position" as argument to the recursive calls
	  *  str must be a sequence of white-space separated strings, e.g., "OR x y", "AND OR x y OR z w"
	 */	
	public static int position;	
	public static BooleanExp parse(String str) {
		if (position >= str.length()) {
			return null;
		}
		String token;
		int i = str.indexOf(" ",position);

		// Read the next token from String str.
		if (i != -1)
			token = str.substring(position,i+1);
		else 
			token = str.substring(position);
		
		// Advance "position" beyond token
		position += token.length();

		// If token is AND, parse the left operand into "left", 
		// then parse the right operand into "right" and create 
		// an And Boolean Expression
		if (token.equals("AND ")) {
			BooleanExp left = parse(str);
			BooleanExp right = parse(str);
			if ((left == null) || (right == null)) { 
				return null;
			}
			return new AndExp(left,right);
		}
		else if (token.equals("OR ")) {
			BooleanExp left = parse(str);
			BooleanExp right = parse(str);
			if ((left == null) || (right == null)) {
				return null;
			}
			return new OrExp(left,right);			
		}
		else if (token.equals("NOT ")) {
			BooleanExp exp = parse(str);
			if ((exp == null)) {
				return null;
			}
			return new NotExp(exp);			
		}
		else if (token.equals("true") || token.equals("true ")) {
			return Constant.create(true);
		}
		else if (token.equals("false") || token.equals("false ")) {
			return Constant.create(false);
		}
		// Otherwise, the token is a variable (e.g., x, xyz). 
		// Get rid of the white space if necessary 
		else { 
		    if (token.charAt(token.length()-1)==' ') {
		    	token = token.substring(0,token.length()-1);
		    }
		    return VarExp.create(token);
		}
			
	}
	/**
	 * This method is part of public interface. Do not remove or change signature.
	 * 
	 * @return boolean value of the enclosed expression,
	 *         evaluated in Context context.
	 */	
	public boolean evaluate() {
		return evaluate(context, expression);		
	}

	/**
	 * This method is part of public interface. Do not remove or change signature.
	 * 
	 * @param preorder  If True, returns expression in Preorder, False returns Inorder 
	 * @return string corresponding to Preorder of expression if preorder is True
	 * 	   string corresponding to Inorder of expression otherwise
	 */	
	public String print(boolean preorder) {
		if (preorder) return expression.printPreorder();
		else return expression.printInorder();
	}
	
	/**
	 * 
	 * @param context
	 * @param exp
	 * @return value of BooleanExp exp in Context context
	 */
	private boolean evaluate(Context context, BooleanExp exp) {
		return exp.evaluate(context);	
	}

		
	public boolean visitorEvaluate() {
		EvaluateVisitor V=new EvaluateVisitor(context);
		expression.accept(V);
		return V.getResult();
	}
	
	public String visitorPrint() {
		PrintVisitor V=new PrintVisitor(context);
		expression.accept(V);
		return V.getResult();
	}
	
}
