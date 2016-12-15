package problems.interviews.company2.position0.problem5;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class Solution1
{
	public final int ID_MAX = 60000;
	ArrayList<HashSet<Integer>> graph;
	
	public Solution1( )
	{
		this.graph = new ArrayList<>(ID_MAX);
		
		for( int i=0; i<ID_MAX; i++ )
		{
			this.graph.add(null);
		}
	}
	
	public void addNode( int nodeId )
	{
		if( nodeId < 0 )
		{
			throw new IllegalArgumentException(String.format("Node (%d) must be >= 0.", nodeId));
		}
		
		if( this.graph.get(nodeId) != null )
		{
			throw new IllegalArgumentException(String.format("Node (%d) already added.", nodeId));
		}
		
		this.graph.set(nodeId, new HashSet<>());
	}
	
	/**
	 * Modified version of dfs which searches for a target node, starting from a start node.
	 * It is assumed that no cycles are present in the graph.
	 * 
	 * Time: O(|E| + |V|).
	 * Space: O(|V|).
	 * 
	 * @param startId node id to start from
	 * @param targetId node id to search for
	 * @return true if targetId can be reached from startId, false otherwise.
	 */
	private boolean find( int startId, int targetId )
	{
		Deque<Integer> stack = new ArrayDeque<>();
		stack.addFirst(startId);
		
		while( !stack.isEmpty() )
		{
			int currentId = stack.removeFirst();
			
			if( currentId == targetId )
			{
				return true;
			}
			
			Set<Integer> edges = this.graph.get(currentId);
			for( int edge : edges )
			{
				stack.addFirst(edge);
			}
		}
		
		return false;
	}
	
	/**
	 * Time: O(|E| + |V|).
	 * Space: O(|V|).
	 * 
	 * @param srcId edge source node id.
	 * @param dstId edge destination node id.
	 */
	public void addEdge( int srcId, int dstId )
	{
		if( srcId < 0 )
		{
			throw new IllegalArgumentException(String.format("Node (%d) must be >= 0.", srcId));
		}
		
		if( dstId < 0 )
		{
			throw new IllegalArgumentException(String.format("Edge (%d) must be >= 0.", dstId));
		}
		
		Set<Integer> edges = this.graph.get(srcId);
		
		if( edges == null )
		{
			throw new IllegalArgumentException(String.format("Node (%d) does not exist.", srcId));
		}
		
		if( edges.contains(dstId) )
		{
			throw new IllegalArgumentException(String.format("Node (%d) already has edge (%d).", srcId, dstId));
		}
		
		if( srcId == dstId )
		{
			throw new IllegalArgumentException(String.format("Node (%d) cannot have a direct cycle.", srcId));
		}
		
		if( find(dstId, srcId) )
		{
			throw new IllegalArgumentException(String.format("Node (%d) cannot have an indirect cycle.", srcId));
		}
		
		edges.add(dstId);
	}
	
	@Override
	public String toString( )
	{
		StringBuilder sb = new StringBuilder();
		
		for( int i=0; i<this.graph.size(); i++ )
		{
			int nodeId = i;
			Set<Integer> edges = this.graph.get(nodeId);
			
			if( edges != null )
			{
				sb.append(String.format("%d -> %s\n", nodeId, edges));
			}
		}
		
		return sb.toString();
	}
	
	public void print( )
	{
		System.out.println(this.toString());
	}
}
