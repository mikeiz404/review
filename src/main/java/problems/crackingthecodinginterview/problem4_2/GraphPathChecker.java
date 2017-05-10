package problems.crackingthecodinginterview.problem4_2;

import javax.validation.constraints.NotNull;
import datastructures.graphs.Edge;
import datastructures.graphs.Graph;
import datastructures.graphs.Vertex;
import static com.google.common.base.Preconditions.checkNotNull;
import java.util.ArrayDeque;
import java.util.HashSet;

public class GraphPathChecker
{
	/**
	 * Uses BFS to search for a path from a to b, returning true if b is found, false otherwise.
	 * @param g graph which might contain vertices a and b.
	 * @param a vertex to start from.
	 * @param b vertex to search for.
	 * @return true if path exists from a to b, false otherwise
	 * 
	 * Time: Graph implementation specific.
	 *   Adjacency List: O(|V| + |E|)
	 *   Adjacency Matrix: O(|V|^2)
	 *   
	 * Space: O(|V|) for queue and visited set.
	 */
	public static <V extends Vertex, E extends Edge<V>> boolean hasPath( @NotNull Graph<V, E> g, @NotNull V a, @NotNull V b )
	{
		checkNotNull(g);
		checkNotNull(a);
		checkNotNull(b);
		
		ArrayDeque<V> q = new ArrayDeque<>();
		HashSet<V> visited = new HashSet<>();
		
		// init
		q.addLast(a);
		visited.add(a);
		
		// explore paths
		while( !q.isEmpty() )
		{
			V v = q.removeFirst();
			
			// check
			if( v.equals(b) )
			{
				return true;
			}
			
			// visit neighbors
			for( V n : g.getNeighbors(v) )
			{
				if( !visited.contains(n) )
				{
					visited.add(n);
					q.add(n);
				}
			}
		}
		
		return false;
	}
}
