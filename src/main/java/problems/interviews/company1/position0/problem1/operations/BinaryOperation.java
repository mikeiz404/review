package problems.interviews.company1.position0.problem1.operations;

public abstract class BinaryOperation
{
	public abstract String getName( );
	
	public abstract Integer apply( Integer a, Integer b );
	
	@Override
	public int hashCode( )
	{
		return this.getName().hashCode();
	}
	
	@Override
	public String toString( )
	{
		return this.getName();
	}
}
