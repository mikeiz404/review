package problems.interviews.company1.position0.problem1.operations;

public class Add extends BinaryOperation
{
	@Override
	public Integer apply( Integer a, Integer b )
	{
		return a + b;
	}
	
	@Override
	public String getName( )
	{
		return "+";
	}
}
