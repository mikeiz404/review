package problems.crackingthecodinginterview.problem9_2;

import static com.google.common.base.Preconditions.checkNotNull;


public class Point
{
	private final int x;
	private final int y;
	
	public Point( int x, int y )
	{
		this.x = x;
		this.y = y;
	}
	
	public int getX( )
	{
		return this.x;
	}
	
	public int getY( )
	{
		return this.y;
	}
	
	public Point add( Point p )
	{
		int x = getX() + p.getX();
		int y = getY() + p.getY();
		return new Point(x, y);
	}
	
	public boolean equals( Point p )
	{
		checkNotNull(p);
		
		return getX() == p.getX() && getY() == p.getY();
	}

	public Point subtract( Point p )
	{
		int x = getX() - p.getX();
		int y = getY() - p.getY();
		
		return new Point(x, y);
	}
	
	@Override
	public int hashCode( )
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + this.x;
		result = prime * result + this.y;
		return result;
	}

	@Override
	public boolean equals( Object o )
	{
		if( o instanceof Point )
		{
			Point p = (Point) o;
			
			return equals(p);
		}
		
		return false;
	}
	
	@Override
	public String toString( )
	{
		return String.format("(%d, %d)", getX(), getY());
	}
}
