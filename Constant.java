package booleanExpressionEvaluator;

/**
 * This class holds a single Constant (fixed true or false) expression.
*
* Specification fields: 
 *  @specfield value    : boolean // expression
*
* Rep Invariant:
* value is non-null. Only one true and one false constant may exist.(though more than one reference to 
* a given expression may exist)
* 
*/
public class Constant extends BooleanExp {
	private boolean value;
	//Holder for true and false Constants. This class only allows one reference to each.
	public static Constant[] Constants={new Constant(true),new Constant(false)};
	private Constant(boolean x) {value=x;}
	//Returns appropriate constant
	public static Constant create(boolean x) {
		if (x) return Constants[0];
		return Constants[1];
	}
	public boolean getValue() {return value;}
	public boolean evaluate(Context context) {
		return value;
	}
	//It's the same whether we print it preorder or inorder
	private String print() {
		if (value) return "true";
		return "false";
	}
	public String printPreorder() {
		return this.print();
	}
	public String printInorder() {
		return this.print();
	}
	@Override
	public void accept(Visitor V) {
		V.visit(this);
	}

}
