package booleanExpressionEvaluator;
import java.util.Stack;

/**
 * This class is a Visitor that evaluates a single boolean expression.
*
* Specification fields: 
 *  @specfield result    : Stack<Boolean> // Holds intermediate results. After evaluation, this should hold 
 *  a single boolean.
*
* 
*/
public class EvaluateVisitor implements Visitor {
	Stack<Boolean> stack;
	private Context context;
	public EvaluateVisitor(Context context) {
		this.context=context;
		stack=new Stack<Boolean>();
	}
	public boolean getResult() {
		if (stack.size()>1) throw new RuntimeException("EvaluateVisitor error: Either it doesn't work or you need to make a separate visitor for each expression you want to evaluate.");
		if (stack.size()==0) throw new RuntimeException("EvaluateVisitor error: Either it doesn't work or you need to give it an expression before you ask for the result.");
		return stack.peek();
	}
	@Override
	public void visit(VarExp exp) {
		stack.add(context.lookup(exp.getValue()));
	}
	@Override
	public void visit(Constant exp) {
		stack.add(exp.getValue());
	}
	@Override
	public void visit(NotExp exp) {
		stack.add(!(stack.pop()));
	}
	@Override
	public void visit(OrExp exp) {
		boolean shortCircuit=stack.peek();
		stack.add(stack.pop()||stack.pop());
		if (shortCircuit) stack.pop();
	}
	@Override
	public void visit(AndExp exp) {
		boolean shortCircuit=!(stack.peek());
		stack.add(stack.pop()&&stack.pop());
		if (shortCircuit) stack.pop();
	}

}
