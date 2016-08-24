package algorithms.graphs.search.dijkstra;

import java.util.List;

import algorithms.graphs.search.dijkstra.Node;
import algorithms.graphs.search.dijkstra.Path;

public class MockPath extends Path
{
	public void setNodes( List<Node> nodes )
	{
		super.setNodes(nodes);
	}
	
	public void setCost( int cost )
	{
		super.setCost(cost);
	}
	
	public void setDestination( Node destination )
	{
		super.setDestination(destination);
	}
}
