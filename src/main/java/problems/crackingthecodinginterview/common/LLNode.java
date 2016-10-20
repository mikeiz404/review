package problems.crackingthecodinginterview.common;

import javax.validation.constraints.NotNull;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Note: hasNext and safety checks have been omitted since concurrent modification is allowed.
 *
 * @param <T>
 */
public class LLNode<T>
{
	LLNode<T> next;
	LLNode<T> prev;
	T data;
	
	public LLNode( T data )
	{
		this.data = data;
		this.next = null;
		this.prev = null;
	}
	
	public void setData( T data )
	{
		this.data = data;
	}
	
	public T getData( )
	{
		return this.data;
	}
	
	public void setNext( LLNode<T> next )
	{
		this.next = checkNotNull(next);
		next.prev = this;
	}
	
	public LLNode<T> getNext( )
	{
		return this.next;
	}
	
	public void removeAllNext( )
	{
		this.next = null;
	}
	
	public void setPrevious( @NotNull LLNode<T> prev )
	{
		this.prev = checkNotNull(prev);
		prev.next = this;
	}
	
	public LLNode<T> getPrevious( )
	{
		return this.prev;
	}
	
	public LLNode<T> remove( )
	{
		// update previous next reference
		if( this.getPrevious() != null )
		{
			if( this.getNext() == null )
			{
				this.getPrevious().removeAllNext();
			}
			else // this.getNext() != null
			{
				this.getPrevious().setNext(this.getNext());
			}
		}
		
		// update next previous references
		if( this.getNext() != null )
		{
			if( this.getPrevious() == null )
			{
				this.getNext().removeAllPrevious();
			}
			else // this.getPrevious() != null
			{
				this.getNext().setPrevious(this.getPrevious());
			}
		}
		
		// update next and prev references
		this.removeAllNext();
		this.removeAllPrevious();
		
		return this;
	}
	
	public void removeAllPrevious( )
	{
		this.prev = null;
	}
	
	@Override
	public boolean equals( Object o )
	{
		if( o instanceof LLNode<?> )
		{
			LLNode<?> node = (LLNode<?>) (o);
			
			return this.getData().equals(node.getData());
		}
		else // !o instanceof LLNode<?>
		{
			return false;
		}
	}
	
	@Override
	public int hashCode( )
	{
		return this.data.hashCode();
	}
}
