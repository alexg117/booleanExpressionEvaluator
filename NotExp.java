package booleanExpressionEvaluator;

/**
 * This class holds a single Or expression.
*
* Specification fields: 
 *  @specfield left      : BooleanExp // sub-expression to be inverted
*
* Rep Invariant:
* left is non-null
* right should be null, but it doesn't really matter.
* 
* Abs. function:	
* this--> !left
*/

public class NotExp extends CompositeExp {
	public int priority() {return 5;}
	public String type() {return "NOT";}
	public NotExp(BooleanExp exp) {
		this.left=exp;this.right=null;
	}
	@Override
	public boolean evaluate(Context context) {
		return (!(left.evaluate(context)));
	}
	@Override
	public String printPreorder() { // prints this boolean expression in preorder
		return type()+" "+left.printPreorder();
	}
	@Override
	public String printInorder() { // prints this boolean expression in inorder
		return type()+" "+append(left);
	}
	@Override
	public void accept(Visitor V) {
		left.accept(V);
		V.visit(this);
	}
}
