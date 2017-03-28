package problems.crackingthecodinginterview.problem3_7;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

/**
 * Approach: Use two queues to store the cats and dogs. In order to maintain the requirement that the oldest animal is
 * adopted when no preference is given on type, the two queues are peeked and the animal which entered the earliest is
 * popped. The count of the animals which have entered the shelter is used as the entry number and is stored along side
 * the animal upon pushing.
 * 
 * Push: O(1).
 * Pop: O(1).
 *
 * @param <T> item type which will be enqueued.
 */
public class ABQue<T>
{
	private class ItemInfo<T>
	{
		public final T item;
		public final int age;
		
		public ItemInfo( T item, int age )
		{
			this.item = item;
			this.age = age;
		}
		
		public T getItem( )
		{
			return this.item;
		}
		
		public int getEntryNumber( )
		{
			return this.age;
		}
	}
	
	private final Deque<ItemInfo<T>> aQue;
	private final Deque<ItemInfo<T>> bQue;
	private int age;
	
	public ABQue( )
	{
		// note: the problem statement does not allow arrays but we won't be using any of the features unique to an array
		// implementation
		this.aQue = new ArrayDeque<>();
		this.bQue = new ArrayDeque<>();
		
		this.age = 0;
	}
	
	private void push( Deque<ItemInfo<T>> que, T item )
	{
		ItemInfo<T> info = new ItemInfo<>(item, this.age);
		que.addLast(info);
		
		this.age++;
	}
	
	public void pushA( T a )
	{
		this.push(this.aQue, a);
	}
	
	public void pushB( T b )
	{
		this.push(this.bQue, b);
	}
	
	public T popAB( )
	{
		ItemInfo<T> info;
		
		if( this.aQue.isEmpty() && !this.bQue.isEmpty() )
		{
			info = this.bQue.pop();
		}
		else if( !this.aQue.isEmpty() && this.bQue.isEmpty() )
		{
			info = this.aQue.pop();
		}
		else if( !this.aQue.isEmpty() && !this.bQue.isEmpty() )
		{
			ItemInfo<T> aInfo = this.aQue.peekFirst();
			ItemInfo<T> bInfo = this.bQue.peekFirst();
			
			if( aInfo.getEntryNumber() < bInfo.getEntryNumber() )
			{
				info = this.aQue.removeFirst();
			}
			else // aInfo.getEntryNumber() >= bInfo.getEntryNumber()
			{
				info = this.bQue.removeFirst();
			}
		}
		else // this.aQue.isEmpty() && this.bQue.isEmpty()
		{
			throw new NoSuchElementException();
		}

		return info.getItem();
	}
	
	public T popA( )
	{
		ItemInfo<T> info = this.aQue.removeFirst();
		return info.getItem();
	}
	
	public T popB( )
	{
		ItemInfo<T> info = this.bQue.removeFirst();
		return info.getItem();
	}
}
