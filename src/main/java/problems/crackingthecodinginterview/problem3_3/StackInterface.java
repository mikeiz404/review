package problems.crackingthecodinginterview.problem3_3;

public interface StackInterface<T>
{
	public void push( T val );
	public T peek( );
	public T pop( );
	public boolean isEmpty( );
	public int size( );
}
