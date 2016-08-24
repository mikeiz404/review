package algorithms.graphs.search.dijkstra;

import java.security.InvalidParameterException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Path implements Comparable<Path>
{
	private LinkedList<Node> nodes;
	private int cost;
	private Node dst;
	
	public Path( Path path )
	{
		this.nodes = new LinkedList<>(path.getNodes());
		this.cost = path.getCost();
		this.dst = path.getDestination();
	}
	
	public Path( Node destination )
	{
		this.cost = 0;
		this.dst = destination;
		
		this.nodes = new LinkedList<>();
	}
	
	public void update( Path path )
	{
		nodes = new LinkedList<>(path.getNodes());
		cost = path.getCost();
		dst = path.getDestination();
	}
	
	public List<Node> getNodes( )
	{
		return Collections.unmodifiableList(nodes);
	}
	
	public void addEdge( Edge edge )
	{
		if( !edge.getSource().equals(dst) )
		{
			throw new InvalidParameterException("Edge must have a source from " + dst);
		}
		
		this.cost += edge.getCost();
		
		// add previous destination to path list
		nodes.add(this.dst);
		
		// update destination
		Node dst = edge.getDestination();
		this.dst = dst;
	}
	
	public int getCost( )
	{
		return this.cost;
	}
	
	public Node getDestination( )
	{
		return this.dst;
	}
	
	@Override
	public boolean equals( Object o )
	{
		if( o instanceof Path )
		{
			Path path = (Path) o;
			return path.getCost() == this.getCost() &&
			       path.getNodes().equals(this.getNodes()) &&
			       path.getDestination().equals(this.getDestination());
		}
		else
		{
			return false;
		}
	} 

	@Override
	public int compareTo( Path other )
	{
		return getCost() - other.getCost();
	}
	
	@Override
	public String toString( )
	{
		return String.format("<Path to=%s cost=%d %s>", getDestination(), getCost(), getNodes());
	}
	
	
	/* methods below are to support mocking class */
	
	protected Path( )
	{
		cost = 0;
		dst = null;
		nodes = null;
	}

	protected void setNodes( List<Node> nodes )
	{
		this.nodes = new LinkedList<>(nodes);
	}

	protected void setCost( int cost )
	{
		this.cost = cost;
	}
	
	protected void setDestination( Node destination )
	{
		this.dst = destination;
	}
}
