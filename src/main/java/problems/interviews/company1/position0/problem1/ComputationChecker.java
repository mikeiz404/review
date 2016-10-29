package problems.interviews.company1.position0.problem1;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Set;
import javax.validation.constraints.NotNull;
import problems.interviews.company1.position0.problem1.operations.BinaryOperation;
import static com.google.common.base.Preconditions.checkNotNull;

public class ComputationChecker
{	
	private static boolean isComputableHelper(
			@NotNull Set<BinaryOperation> operations,
			@NotNull ArrayDeque<Integer> numbers,
			@NotNull Number targetValue )
	{
		checkNotNull(operations);
		checkNotNull(numbers);
		checkNotNull(targetValue);
		
		if( numbers.size() == 0 )
		{ // list too short
			return false;
		}
		else if( numbers.size() == 1 )
		{ // base
			Number actualValue = numbers.getLast();
			
			return actualValue.equals(targetValue);
		}
		else // numbers.size() > 1
		{ // recurse
			Integer a = numbers.removeLast();
			Integer b = numbers.removeLast();
			
			for( BinaryOperation operation : operations )
			{
				try
				{
					Integer c = operation.apply(a, b);
					
					// add c
					numbers.addLast(c);
					
					// check remaining
					if( isComputableHelper(operations, numbers, targetValue) )
					{
						return true;
					}
					
					// remove c
					numbers.removeLast();
				}
				catch( ArithmeticException exception )
				{
					// skip
				}
			}
			
			// add a and b back
			numbers.addLast(b);
			numbers.addLast(a);
			
			return false;
		}
	}
	
	/**
	 * Determines if it is possible to reach the targetValue using the set of operations on the list of numbers with a
	 * right to left order of precedence.
	 * 
	 * Approach: Pop two numbers off the end, apply an operation from the set of operations, push the value on the end,
	 * and repeat recursively until one number is left on the list. When the list size is 1 check to see if the number
	 * matches the target value. Exhaust all operations for each "slot" until a solution is found, otherwise no solution
	 * is possible.
	 * 
	 * Time: O(n! * o) where n is the number of numbers and o is the number of operations.
	 * Space: O(n + n) => O(n).
	 * 
	 * @param operations the possible operations.
	 * @param numbers the list of numbers to check the operations on.
	 * @param targetValue the value to check if some combination of operations and numbers can reach.
	 * @return true if the target value can be reached, false otherwise.
	 */
	public static boolean isComputable(
			@NotNull Set<BinaryOperation> operations,
			@NotNull List<Integer> numbers,
			@NotNull Number targetValue )
	{
		checkNotNull(operations);
		for( BinaryOperation operation : operations )
		{
			checkNotNull(operation);
		}
		checkNotNull(numbers);
		for( Number number : numbers )
		{
			checkNotNull(number);
		}
		checkNotNull(targetValue);
		
		return isComputableHelper(operations, new ArrayDeque<>(numbers), targetValue);
	}
}
