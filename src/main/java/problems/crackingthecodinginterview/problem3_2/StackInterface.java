package problems.crackingthecodinginterview.problem3_2;

public interface StackInterface<T>
{
	public void push( T item );
	public T pop( );
	public T peek( );
	public int size( );
	public boolean isEmpty( );
}
