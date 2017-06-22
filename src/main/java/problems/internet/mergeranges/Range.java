package problems.internet.mergeranges;

import static com.google.common.base.Preconditions.checkArgument;


public class Range
{
	private final int from;
	private final int width;
	
	public Range( int from, int width )
	{
		checkArgument(width >= 0);
		
		this.from = from;
		this.width = width;
	}
	
	public int getFrom( )
	{
		return this.from;
	}
	
	public int getWidth( )
	{
		return this.width;
	}
	
	public int getTo( )
	{
		return this.from + this.width;
	}
	
	public boolean overlaps( Range other )
	{
		if( getFrom() >= other.getFrom() )
		{
			return other.overlaps(getFrom());
		}
		else
		{
			return overlaps(other.getFrom());
		}
	}
	
	public boolean overlaps( int point )
	{
		return point >= getFrom() && point < getTo();
	}
	
	public String toString( )
	{
		return String.format("{Range: from=%d, width=%d, to=%d}", getFrom(), getWidth(), getTo());
	}

	@Override
	public int hashCode( )
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + this.from;
		result = prime * result + this.width;
		return result;
	}

	@Override
	public boolean equals( Object o )
	{
		if( o != null && o instanceof Range )
		{
			Range r = (Range) o;
			return getFrom() == r.getFrom() && getWidth() == r.getWidth();
		}
		else
		{
			return false;
		}
	}
}
