package booleanExpressionEvaluator;

/**
 * This class holds a single And expression.
 *
 * Specification fields: All inherited from CompositeExp
 *
 * Rep Invariant:
 * left and right are non-null
 * 
 * Abs. function:	
 *this--> left&&right
 */
public class AndExp extends CompositeExp {
	public int priority() {return 10;}
	public String type() {return "AND";}
	public AndExp(BooleanExp left,BooleanExp right) {
		this.left=left;this.right=right;
	}
	//Returns left&&right
	@Override
	public boolean evaluate(Context context) {
		return (left.evaluate(context)&&right.evaluate(context));
	}
	//Accepts visitor. Visitor performs its function on this expression. Nothing in the expression itself
	//is changed.
	@Override
	public void accept(Visitor V) {
		left.accept(V);
		right.accept(V);
		V.visit(this);
	}
}
