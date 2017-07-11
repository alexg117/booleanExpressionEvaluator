package booleanExpressionEvaluator;

//Template for a visitor object

public interface Visitor {
	void visit(VarExp exp);
	void visit(Constant exp);
	void visit(NotExp exp);
	void visit(AndExp exp);
	void visit(OrExp exp);
}
