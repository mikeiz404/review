package problems.interviews.company1.position0.problem0;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import static com.google.common.base.Preconditions.checkNotNull;

/*
 * Approach: Store a list of iterators which have not reached the end of their lists and loop over them. When next is
 * called get the value of the iterator at the current list index, advance the index if necessary, remove the iterator
 * if it is empty, and return the value.
 */
public class MultiListIterator<T> implements Iterator<T>
{
	private ArrayList<Iterator<T>> iterators;
	private int lastIteratorIndex;

	/*
	 * time: O(l) where l is the number of lists
	 * space: O(l)
	 */
	public MultiListIterator( List<List<T>> lists )
	{
		checkNotNull(lists);

		this.lastIteratorIndex = 0;
		
		// store internal list of iterators
		this.iterators = new ArrayList<>(lists.size());
		for( List<T> list : lists )
		{
			checkNotNull(list);
			
			// only add non-empty lists
			if( list.size() > 0 )
			{ 
				this.iterators.add(list.iterator());
			}
		}
	}

	/*
	 *  time/space: O(1)
	 */
	@Override
	public boolean hasNext( )
	{
		return this.iterators.size() != 0;
	}

	/*
	 * time: O(1)
	 */
	@Override
	public T next( )
	throws NoSuchElementException
	{
		if( !this.hasNext() )
		{
			throw new NoSuchElementException("next() must not be called when all list iterators have no more elemennts.");
		}
		
		Iterator<T> iterator = this.iterators.get(this.lastIteratorIndex);
		T value = iterator.next();
		
		// advance iterator index and remove empty iterator
		if( iterator.hasNext() )
		{
			// increment index
			this.lastIteratorIndex = (this.lastIteratorIndex + 1) % this.iterators.size();
		}
		else // !iterator.hasNext()
		{
			// removing an iterator will effectively increment the index
			this.iterators.remove(this.lastIteratorIndex);
		}
		
		return value;
	}
}
