package problems.crackingthecodinginterview.problem3_5;

public interface QueueInterface<T>
{
	public void push( T item );
	public T pop( );
	public T peek( );
	public int size( );
	public boolean isEmpty( );
}
