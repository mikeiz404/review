package algorithms.graphs.search.dijkstra;

public class Edge
{
	private Node src;
	private Node dst;
	private int cost;
	
	public Edge( Node src, Node dst, int cost )
	{
		this.src = src;
		this.dst = dst;
		this.cost = cost;
	}
	
	public Node getSource( )
	{
		return this.src;
	}
	
	public Node getDestination( )
	{
		return this.dst;
	}
	
	public int getCost( )
	{
		return this.cost;
	}
	
	@Override
	public String toString( )
	{
		return String.format("<Edge (%s, %s) cost=%d>", src, dst, cost);
	}
}
