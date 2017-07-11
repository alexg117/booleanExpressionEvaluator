package booleanExpressionEvaluator;

/**
 * This class holds a single Or expression.
 *
 * Specification fields: All inherited from CompositeExp
 *
 * Rep Invariant:
 * left and right are non-null
 * 
 * Abs. function:	
 *this--> left||right
 */

public class OrExp extends CompositeExp {
	public final String TYPE;
	public final int PRIORITY;
	public int priority() {return 100;}
	public String type() {return "OR";}
	public OrExp(BooleanExp left,BooleanExp right) {
		this.left=left;this.right=right;
		PRIORITY=100;
		TYPE="OR";
	}
	//Returns left||right
	@Override
	public boolean evaluate(Context context) {
		return (left.evaluate(context)||right.evaluate(context));
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
