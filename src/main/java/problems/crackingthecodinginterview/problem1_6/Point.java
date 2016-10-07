package problems.crackingthecodinginterview.problem1_6;

class Point
{
	private int x;
	private int y;
	
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
	
	@Override
	public String toString( )
	{
		return String.format("{Point (%d, %d)}", this.x, this.y);
	}
}
