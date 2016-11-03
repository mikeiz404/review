package problems.crackingthecodinginterview.problem3_5;

import java.util.ArrayDeque;
import static com.google.common.base.Preconditions.checkState;

/**
 * A queue implementation using two stacks.
 * 
 * Approach: Use one stack for push operations and another stack for pop operations. Convert between the two when
 * alternating operations.
 * When pushing, convert to push stack and push onto the push stack. When popping, convert to pop stack and pop from the
 * pop stack. To convert from a push stack to a pop stack, reverse the order by popping all items from the push stack
 * onto the pop stack; do similarly for converting from a pop stack to a push stack.
 * 
 * @param <T> item type.
 */
public class MyQueue<T> implements QueueInterface<T>
{
	private static enum StackType
	{
		PUSH,
		POP,
	}
	
	private StackType stackType = StackType.PUSH;
	private ArrayDeque<T> stack = new ArrayDeque<>();
	
	/**
	 * Time: O(n) where n is the number of items in the queue.
	 */
	protected void convertStackTo( StackType type )
	{
		if( this.stackType == type )
		{
			// stack type is correct
		}
		else if( this.stack.size() == 0 )
		{
			// conversion is unnecessary
		}
		else if( type == StackType.POP && this.stack.size() == 1 )
		{
			// conversion is unnecessary
		}
		else // stackType != stackType && (this.stack.size() > 0 || (type == StackType.POP && this.stack.size() > 1))
		{
			// convert
			ArrayDeque<T> newStack = new ArrayDeque<>();
			while( !this.stack.isEmpty() )
			{
				newStack.addLast(this.stack.removeLast());
			}
			
			this.stack = newStack;
		}
		
		this.stackType = type;
	}
	
	/**
	 * Time: O(n) where n is the number of items in the queue.
	 */
	@Override
	public void push( T item )
	{
		this.convertStackTo(StackType.PUSH);
		
		this.stack.addLast(item);
	}

	/**
	 * Time: O(n) where n is the number of items in the queue.
	 */
	@Override
	public T pop( )
	{
		checkState(!this.stack.isEmpty(), "Queue must not be empty.");
		
		this.convertStackTo(StackType.POP);
		
		return this.stack.removeLast();
	}

	/**
	 * Time: O(n) where n is the number of items in the queue.
	 */
	@Override
	public T peek( )
	{
		checkState(!this.stack.isEmpty(), "Queue must not be empty.");
		
		this.convertStackTo(StackType.POP);
		
		return this.stack.getLast();
	}

	/**
	 * Time: O(1).
	 */
	@Override
	public int size( )
	{
		return this.stack.size();
	}

	/**
	 * Time: O(1).
	 */
	@Override
	public boolean isEmpty( )
	{
		return this.stack.isEmpty();
	}
	
}
