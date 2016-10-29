package problems.interviews.company1.position0.problem1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.junit.Assert;
import org.junit.Test;
import problems.interviews.company1.position0.problem1.operations.Add;
import problems.interviews.company1.position0.problem1.operations.BinaryOperation;
import problems.interviews.company1.position0.problem1.operations.Divide;
import problems.interviews.company1.position0.problem1.operations.Multiply;
import problems.interviews.company1.position0.problem1.operations.Subtract;

public class TestComputationChecker
{
	protected static final Set<BinaryOperation> OPERATIONS;
	static
	{
		HashSet<BinaryOperation> operations = new HashSet<>();
		operations.add(new Add());
		operations.add(new Subtract());
		operations.add(new Multiply());
		operations.add(new Divide());
		
		OPERATIONS = Collections.unmodifiableSet(operations);
	}
	
	@Test(expected=NullPointerException.class)
	public void testNullOperationCollection( )
	{
		ComputationChecker.isComputable(null, Collections.emptyList(), 0);
	}
	
	@Test(expected=NullPointerException.class)
	public void testNullOperations( )
	{
		HashSet<BinaryOperation> operations = new HashSet<>();
		operations.add(null);
		
		ComputationChecker.isComputable(operations, Collections.emptyList(), 0);
	}
	
	@Test(expected=NullPointerException.class)
	public void testNullNumberCollection( )
	{
		ComputationChecker.isComputable(Collections.emptySet(), null, 0);
	}
	
	@Test(expected=NullPointerException.class)
	public void testNullNumbers( )
	{
		ArrayList<Integer> numbers = new ArrayList<>();
		numbers.add(null);
		
		ComputationChecker.isComputable(Collections.emptySet(), numbers, 0);
	}
	
	@Test(expected=NullPointerException.class)
	public void testNulltarget( )
	{
		ComputationChecker.isComputable(Collections.emptySet(), Collections.emptyList(), null);
	}
	
	@Test
	public void testBaseTrue( )
	{
		ArrayList<Integer> numbers = new ArrayList<>();
		numbers.add(1);
		
		boolean computable = ComputationChecker.isComputable(Collections.emptySet(), numbers, 1);
		
		Assert.assertTrue(computable);
	}
	
	@Test
	public void testBaseFalse( )
	{
		ArrayList<Integer> numbers = new ArrayList<>();
		numbers.add(1);
		
		boolean computable = ComputationChecker.isComputable(Collections.emptySet(), numbers, 0);
		
		Assert.assertFalse(computable);
	}
	
	@Test
	public void testTrue1( )
	{
		ArrayList<Integer> numbers = new ArrayList<>();
		numbers.add(1);
		numbers.add(1);
		numbers.add(1);
		
		Assert.assertTrue(ComputationChecker.isComputable(OPERATIONS, numbers, 0));
		Assert.assertTrue(ComputationChecker.isComputable(OPERATIONS, numbers, 1));
		Assert.assertTrue(ComputationChecker.isComputable(OPERATIONS, numbers, 3));
	}
	
	@Test
	public void testTrue2( )
	{
		ArrayList<Integer> numbers = new ArrayList<>();
		numbers.add(0);
		numbers.add(10);
		numbers.add(1);
		numbers.add(-5);
		
		Assert.assertTrue(ComputationChecker.isComputable(OPERATIONS, numbers, -50));
		Assert.assertTrue(ComputationChecker.isComputable(OPERATIONS, numbers, -16));
		Assert.assertTrue(ComputationChecker.isComputable(OPERATIONS, numbers, -15));
	}
	
	@Test
	public void testFalse( )
	{
		ArrayList<Integer> numbers = new ArrayList<>();
		numbers.add(1);
		numbers.add(0);
		numbers.add(1);
		
		Assert.assertFalse(ComputationChecker.isComputable(OPERATIONS, numbers, 3));
		Assert.assertFalse(ComputationChecker.isComputable(OPERATIONS, numbers, -2));
		Assert.assertFalse(ComputationChecker.isComputable(OPERATIONS, numbers, 20));
	}
	
}
