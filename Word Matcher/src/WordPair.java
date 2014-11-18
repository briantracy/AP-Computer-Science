
public class WordPair {
	private String a;
	private String b;
	
	public WordPair(String a, String b)
	{
		this.a = a;
		this.b = b;
	}
	
	public boolean equals(Object other)
	{
		if (!(other instanceof WordPair)) return false;
		
		WordPair otherPair = (WordPair)other;
		
		return ( (a.equals(otherPair.getA()) && b.equals(otherPair.getB()) ||
				  a.equals(otherPair.getB()) && b.equals(otherPair.getA())) );
	}
	
	public String getA() { return a; }
	public String getB() { return b; }
}
