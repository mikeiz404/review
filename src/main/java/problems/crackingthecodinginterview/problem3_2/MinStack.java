package problems.crackingthecodinginterview.problem3_2;

import static com.google.common.base.Preconditions.checkState;
import java.util.ArrayDeque;

public class MinStack<T extends Comparable<T>> implements StackInterface<T>
{
	private class StackItem
	{
		public final T min;
		public  final T item;
		
		public StackItem( T item, T min )
		{
			this.item = item;
			this.min = min;
		}
	}
	
	private ArrayDeque<StackItem> stack;
	
	public MinStack( )
	{
		this.stack = new ArrayDeque<>();
	}
	
	/**
	 * Returns the current minimum item.
	 * 
	 * Approach: Since access order is fixed for a stack, we can keep track of the current min as items are pushed
	 * onto and popped off of the stack. When an item is at the head of the stack the current min is stored along side
	 * that item in the stack.
	 * 
	 * Time: O(1).
	 * Space: O(n) where n is the number of items in the stack.
	 * 
	 * @return the current minimum item.
	 */
	public T min( )
	{
		checkState(!this.isEmpty());
		
		return this.stack.getLast().min;
	}
	
	@Override
	public void push( T item )
	{
		T newMin;
		if( this.stack.isEmpty() )
		{
			newMin = item;
		}
		else // !this.stack.isEmpty()
		{
			T currentMin = this.stack.getLast().min;
			newMin = item.compareTo(currentMin) < 0 ? item : currentMin; 
		}
		
		this.stack.addLast(new StackItem(item, newMin));
	}

	@Override
	public T pop( )
	{
		checkState(!this.stack.isEmpty());
		
		return this.stack.removeLast().item;
	}

	@Override
	public T peek( )
	{
		checkState(!this.stack.isEmpty());
		
		return this.stack.getLast().item;
	}

	@Override
	public int size( )
	{
		return this.stack.size();
	}

	@Override
	public boolean isEmpty( )
	{
		return this.stack.isEmpty();
	}
}
