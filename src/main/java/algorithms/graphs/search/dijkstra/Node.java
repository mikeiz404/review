package algorithms.graphs.search.dijkstra;

import java.security.InvalidParameterException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Node
{
	private String name;
	private LinkedList<Edge> edges;
	private boolean explored;
	private boolean seen;
	
	public Node( String name )
	{
		this.name = name;
		this.edges = new LinkedList<>();
		this.explored = false;
		this.seen = false;
	}
	
	public List<Edge> getEdges( )
	{
		return Collections.<Edge>unmodifiableList(this.edges);
	}
	
	public void addEdge( Edge edge )
	{
		if( !edge.getSource().equals(this) )
		{
			throw new InvalidParameterException("Edge must originate from this node " + this);
		}
		
		this.edges.add(edge);
	}
	
	public void addEdge( Node dst, int cost )
	{
		addEdge(new Edge(this, dst, cost));
	}
	
	public boolean isExplored( )
	{
		return this.explored;
	}
	
	public void markExplored( )
	{
		this.explored = true;
	}
	
	public boolean isSeen( )
	{
		return this.seen;
	}
	
	public void markSeen( )
	{
		this.seen = true;
	}
	
	public String getName( )
	{
		return this.name;
	}
	
	@Override
	public String toString( )
	{
		return String.format("<Node %s>", getName());
	}
	
	@Override
	public boolean equals( Object o )
	{
		if( o instanceof Node )
		{
			Node node = (Node) o;
			
			return getName().equals(node.getName());
		}
		else // ! o instanceof Node
		{
			return false;
		}
		
	}
}
