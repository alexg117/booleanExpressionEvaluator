package booleanExpressionEvaluator;

import java.util.Stack;
/**
 * This class is a Visitor that returns a boolean expression to its original string form.
 *  It does not evaluate it.
*
* Specification fields: 
 *  @specfield result    : Stack<Pair> // Holds intermediate expressions. After evaluation, this should hold
 *  a single expression.
*
* 
*/
public class PrintVisitor implements Visitor {
	private Stack<PairIntString> result;
	public PrintVisitor(Context context) {
		result=new Stack<PairIntString>();
	}
	//Returns result, if one is available and nothing else went wrong..
	public String getResult() {
		if (result.size()>1) throw new RuntimeException("PrintVisitor error: Either it doesn't work or you need to make a separate visitor for each expression you want to print.");
		if (result.size()==0) throw new RuntimeException("PrintVisitor error: Either it doesn't work or you need to give it an expression before you ask for the result.");
		return result.peek().second;
	}
	@Override
	public void visit(VarExp exp) {
		result.push(new PairIntString(0,exp.getValue()));
	}
	@Override
	public void visit(Constant exp) {
		if (exp.getValue()) result.push(new PairIntString(0,"true"));
		else result.push(new PairIntString(0,"false"));
	}

	@Override
	public void visit(NotExp exp) {
		if (result.peek().first>5) result.push(new PairIntString(5,"NOT ("+result.pop().second+")"));
		else result.push(new PairIntString(5,"NOT "+result.pop().second));
	}

	@Override
	public void visit(AndExp exp) {
		String newExp="";
		//Add first Exp
		if (result.peek().first>10) newExp+="("+result.pop().second+")";
		else newExp+=result.pop().second;
		//Add second Exp
		if (result.peek().first>10) newExp="("+result.pop().second+") AND "+newExp;
		else newExp=result.pop().second+" AND "+newExp;
		result.push(new PairIntString(10,newExp));
	}

	@Override
	public void visit(OrExp exp) {
		String newExp="";
		//Add first Exp
		newExp=result.pop().second;
		//Add second Exp
		newExp=result.pop().second+" OR "+newExp;
		result.push(new PairIntString(100,newExp));
	}

}
