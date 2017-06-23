package problems.internet.rectangleoverlap;

import static com.google.common.base.Preconditions.*;

public class Rectangle
{
	private final int x;
	private final int y;
	private final int width;
	private final int height;
	
	public Rectangle( int x, int y, int width, int height )
	{
		checkArgument(width > 0);
		checkArgument(height > 0);
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public boolean overlapsX( Rectangle other )
	{
		checkNotNull(other);
		
		return containsX(other.getX()) || other.containsX(getX());
	}
	
	public boolean containsX( int otherX )
	{
		return (otherX >= getX()) && (otherX < getX() + getWidth());
	}
	
	public boolean overlapsY( Rectangle other )
	{
		checkNotNull(other);
		
		return containsY(other.getY()) || other.containsY(getY());
	}
	
	public boolean containsY( int otherY )
	{
		return (otherY >= getY()) && (otherY < getY() + getHeight());
	}
	
	public boolean overlaps( Rectangle other )
	{
		checkNotNull(other);
		
		return overlapsX(other) && overlapsY(other);
	}
	
	public int getX( )
	{
		return this.x;
	}
	
	public int getY( )
	{
		return this.y;
	}
	
	public int getWidth( )
	{
		return this.width;
	}
	
	public int getHeight( )
	{
		return this.height;
	}
}
