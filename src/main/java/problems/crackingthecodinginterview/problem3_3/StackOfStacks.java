package problems.crackingthecodinginterview.problem3_3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

public class StackOfStacks<T> implements StackInterface<T>
{
	private int maxSize;
	private ArrayList<ArrayDeque<T>>  stacks;
	
	public StackOfStacks( int maxSize )
	{
		checkArgument(maxSize > 0);
		
		this.maxSize = maxSize;
		this.stacks = new ArrayList<>();
	}
	
	/**
	 * Time: O(1).
	 * Space: O(1).
	 */
	@Override
	public void push( T val )
	{
		ArrayDeque<T> stack;
		if( stacks.isEmpty() ||
			stacks.get(this.getCurrentIndex()).size() == this.maxSize )
		{ // no current stack or current stack is full
			stack = new ArrayDeque<T>();
			this.stacks.add(stack);
		}
		else // !stacks.isEmpty() && stacks.get(this.getCurrentIndex()).size() != this.maxSize
		{ // current stacks exists and is not full
			stack = this.stacks.get(this.getCurrentIndex());
		}
		
		stack.addLast(val);
	}

	public T peek( )
	{
		checkState(!this.isEmpty());
		
		return this.stacks.get(this.getCurrentIndex()).getLast();
	}
	
	/**
	 * Time: O(1).
	 * Space: O(1).
	 */
	@Override
	public T pop( )
	{
		return popAt(this.getCurrentIndex());
	}
	
	/**
	 * Time: O(n) where n is the number of stacks.
	 * Space: O(n) @see {@link #reballanceFrom(int)}.
	 */
	public T popAt( int index )
	{
		checkState(!this.isEmpty());
		checkArgument(index >= 0 && index <= this.getCurrentIndex());
		
		ArrayDeque<T> stack = this.stacks.get(index);
		
		// note: impossible for an arbitrary stack to be empty unless the stack of stacks is empty
		T val = stack.removeLast();
		
		reballanceFrom(index);
		
		return val;
	}

	@Override
	public boolean isEmpty( )
	{
		return this.stacks.isEmpty();
	}

	@Override
	public int size( )
	{
		return this.stacks.size();
	}
	
	protected int getCurrentIndex( )
	{
		return this.stacks.size() - 1;
	}
	
	/**
	 * Reballances the stacks starting from index by removing the bottom of the next stack and pushing it onto the current
	 * stack at index, and proceeding until the head stack is reached. Also cleans up empty stacks.
	 * 
	 * Time: O(n) where n is the number of stacks.
	 * Space: O(n) for recursive calls but this could be remedied (tail recursive).
	 * 
	 * @param index the stack to start reballancing from.
	 */
	private void reballanceFrom( int index )
	{
		checkArgument(index >= 0 && index <= this.getCurrentIndex());
		
		ArrayDeque<T> stack = this.stacks.get(index);
		if( index == this.getCurrentIndex() )
		{
			// note: only the current stack can become empty on an arbitrary pop due to reballancing
			if( stack.isEmpty() )
			{
				this.stacks.remove(index);
			}
		}
		else // index != this.getCurrentIndex()
		{
			ArrayDeque<T> nextStack = this.stacks.get(index + 1);
			
			stack.addLast(nextStack.removeFirst());
			
			reballanceFrom(index + 1);
		}
	}
}
