package problems.interviews.company0.position0.problem0;

public class Pair
{
	private int a;
	private int b;
	
	public Pair( int a, int b )
	{
		this.a = a;
		this.b = b;
	}
	
	public int getA( )
	{
		return this.a;
	}
	
	public int getB( )
	{
		return this.b;
	}
	
	public int calcDistance( int k )
	{
		return Math.abs(k - (this.getA() + this.getB()));
	}
	
	@Override
	public int hashCode( )
	{
		return this.getA() + this.getB() * 13;
	}
	
	@Override
	public boolean equals( Object o )
	{
		if( o instanceof Pair )
		{
			Pair p = (Pair) o;
			
			return (p.getA() == this.getA() && p.getB() == this.getB()) ||
			       (p.getB() == this.getA() && p.getA() == this.getB());
		}
		else // ! o instanceof Pair
		{
			return false;
		}
	}
	
	@Override
	public String toString( )
	{
		return String.format("<Pair a:%d b:%d>", this.getA(), this.getB());
	}
}

