package booleanExpressionEvaluator;


//Simple holder for an int and a String
public class PairIntString implements Comparable<PairIntString> {
	public final int first;
	public final String second;
	public PairIntString(int first,String second) {
		this.first=first; this.second=second;
	}
	@Override
	public int compareTo(PairIntString p2) {
		if (first<p2.first) return -1;
		if (first>p2.first) return 1;
		return second.compareTo(p2.second);
	}
	public boolean equals(PairIntString p2) {
		return first==p2.first&&second.equals(p2.second);
	}
	@Override
	public int hashCode() {
		return (first*1337)+second.hashCode();
	}
	
}