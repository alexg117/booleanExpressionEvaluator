package booleanExpressionEvaluator;

import java.util.HashMap;

/**
 * This class holds a single Variable Boolean expression.
*
* Specification fields: 
 *  @specfield value    : String // expression
*
* Rep Invariant:
* value is non-null. Only one VarExp of any given string may exist (though more than one reference to 
* a given expression may exist)
* 
*/
public class VarExp extends BooleanExp {
	private String value;
	public static HashMap<String,VarExp> VarExps;
	private VarExp(String x) {value=x;}
	public static VarExp create(String x) {
		if (x==null) throw new RuntimeException("ERROR: Attempted to create VarExp with null string");
		if (!(VarExps.containsKey(x))) VarExps.put(x, new VarExp(x));
		return VarExps.get(x);
	}
	static {
		VarExps=new HashMap<String,VarExp>();
	}
	public boolean evaluate(Context context) {
		return context.lookup(value);
	}
	//It's the same whether we print it preorder or inorder
	public String getValue() {return value;}
	private String print() {
		return value;
	}
	public String printPreorder() {
		return this.print();
	}

	public String printInorder() {
		return this.print();
	}
	@Override
	public boolean equals(Object O) {
		if (!(O instanceof VarExp)) return false;
		return value==((VarExp)O).getValue();
	}
	@Override
	public int hashCode() {
		return value.hashCode();
	}

	@Override
	public void accept(Visitor V) {
		V.visit(this);
	}
}
