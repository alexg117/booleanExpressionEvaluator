package booleanExpressionEvaluator;

/**
 * This class holds a single Composite expression.
*
* Specification fields: 
 *  @specfield left      : BooleanExp // left sub-expression
 *  @specfield right     : BooleanExp // right sub-expression
*
* Rep Invariant:
* left and right are usually non-null, but this may be different for some composite expressions
* 
*/
public abstract class CompositeExp extends BooleanExp {
	protected BooleanExp left;
	protected BooleanExp right;
	public int priority() {return 0;}
	public String type() {return "INVALID";}
	public abstract boolean evaluate(Context context);
	//Helper method to decide if a sub-expression needs parentheses
	protected String append(BooleanExp exp) {
		if (exp.priority()>this.priority()) return "("+exp.printInorder()+")";
		return exp.printInorder();
	}
	 // prints this boolean expression in preorder
	public String printPreorder() {
		return this.type()+" "+left.printPreorder()+" "+right.printPreorder();
	}
	// prints this boolean expression in inorder
	public String printInorder() { 
		return append(left)+" "+this.type()+" "+append(right);
	}
}
