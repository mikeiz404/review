package problems.crackingthecodinginterview.problem3_6;

import java.util.Deque;

public interface StackSorter
{
	public <T extends Comparable<T>> void sort( Deque<T> stack );
}
