package datastructures.graphs;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotNull;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A graph backed by a |V|x|V| matrix with edges as entries.
 * @param <V> vertex type.
 * @param <E> edge type.
 */
public class AdjacencyMatrixGraph<V extends Vertex, E extends Edge<V>> implements Graph<V, E>
{
	private HashMap<V, Integer> vertexMap;
	private HashMap<Integer, V> indexMap;
	private Edge<?>[][] matrix;

	/**
	 * Creates a new instance.
	 */
	public AdjacencyMatrixGraph( )
	{
		this.vertexMap = new HashMap<>();
		this.indexMap = new HashMap<>();
		this.matrix = new Edge<?>[0][0];
	}
	
	/**
	 * Complexity: O(V^2) for new matrix creation.
	 * @see {@link Graph#addVertices(Vertex)}.
	 * @param vertices vertices to add to the graph. The array must not contain null values.
	 */
	@Override
	public void addVertices( @NotNull Collection<V> vertices )
	{
		checkNotNull(vertices);
		// check vertices
		for( V vertex : vertices )
		{
			checkNotNull(vertex);
			checkArgument(!this.vertexMap.containsKey(vertex), "Graph already contains the vertex: %s.", vertex);
		}
		
		int vertexCount = this.matrix.length;
		
		// create vertex indexes
		for( V vertex : vertices )
		{
			int vertexIndex = vertexCount;
			
			this.vertexMap.put(vertex, vertexIndex);
			this.indexMap.put(vertexIndex, vertex);
			
			vertexCount++;
		}
		
		// create resized matrix with data from previous matrix
		Edge<?>[][] newMatrix = new Edge<?>[vertexCount][];
		for( int i=0; i<this.matrix.length; i++ )
		{
			newMatrix[i] = Arrays.copyOf(this.matrix[i], vertexCount);
		}
		
		// add new vertices into new matrix
		for( int i=this.matrix.length; i<newMatrix.length; i++ )
		{
			newMatrix[i] = new Edge<?>[vertexCount];
		}
		
		// replace matrix with new matrix
		this.matrix = newMatrix;
	}
	
	/**
	 * Complexity: O(|V|^2) to copy old matrix into new matrix.
	 * @see {@link Graph#removeVertices(Vertex)}.
	 */
	@Override
	public void removeVertices( @NotNull Collection<V> vertices )
	{
		checkNotNull(vertices);
		// check vertices
		for( V vertex : vertices )
		{
			checkNotNull(vertex);
			checkArgument(this.vertexMap.containsKey(vertex), "Graph does not contain the vertex: %s.", vertex);
		}
		
		int vertexCount = this.matrix.length;
		
		// create a list of vertex indexes
		HashSet<Integer> removeIndexes = new HashSet<>();
		for(  V vertex : vertices  )
		{
			int vertexIndex = this.vertexMap.remove(vertex);
			
			removeIndexes.add(vertexIndex);
			
			vertexCount--;
		}
		
		// create a new matrix
		Edge<?>[][] newMatrix = new Edge<?>[vertexCount][];
		
		// copy non-removed edges from matrix into new matrix and fix up index mappings
		int newFromIndex = 0;
		for( int fromIndex=0; fromIndex<this.matrix.length; fromIndex++ )
		{
			if( !removeIndexes.contains(fromIndex) )
			{ // update and copy vertices
				newMatrix[newFromIndex] = new Edge<?>[vertexCount];
				
				// update vertex index
				int offset = fromIndex - newFromIndex;
				int vertexIndex = fromIndex;
				int newVertexIndex = vertexIndex - offset;
				V vertex = this.indexMap.get(vertexIndex);
				
				this.indexMap.remove(vertexIndex);
				this.indexMap.put(newVertexIndex, vertex);
				this.vertexMap.put(vertex, newVertexIndex);
				
				int newToIndex = 0;
				for( int toIndex = 0; toIndex<this.matrix.length; toIndex++ )
				{
					if( !removeIndexes.contains(toIndex) )
					{ // copy vertex
						newMatrix[newFromIndex][newToIndex] = this.matrix[fromIndex][toIndex];
						
						newToIndex++;
					}
				}
				
				newFromIndex++;
			}
		}
		
		// replace matrix with new matrix
		this.matrix = newMatrix;
	}

	/**
	 * Complexity: O(1) to retrieve key set.
	 * @see {@link Graph#getVertices()}.
	 */
	@Override
	public @NotNull Set<V> getVertices( )
	{
		return this.vertexMap.keySet();
	}
	

	/**
	 * Complexity: O(1) per edge to lookup source and destination vertex indexes, and set the edge.
	 * @see {@link Graph#addEdge(Edge)}.
	 */
	@Override
	public void addEdges( @NotNull Collection<E> edges )
	{
		checkNotNull(edges);
		// check edges
		for( E edge : edges )
		{
			checkNotNull(edge);
			Integer sourceIndex = this.vertexMap.get(edge.getSource());
			Integer destinationIndex = this.vertexMap.get(edge.getDestination());
			checkArgument(sourceIndex != null,
			              "Graph does not contain the edge source vertex.", edge.getSource());
			checkArgument(destinationIndex != null,
			              "Graph does not contain the edge destination vertex.", edge.getDestination());
			checkArgument(this.matrix[sourceIndex][destinationIndex] == null,
			             "Graph already contains an edge (%s) from vertex %s to vertex %s.",
			             this.matrix[sourceIndex][destinationIndex],
			             edge.getSource(),
			             edge.getDestination());
		}
		
		// add edges
		for( E edge : edges )
		{
			int sourceIndex = this.vertexMap.get(edge.getSource());
			int destinationIndex = this.vertexMap.get(edge.getDestination());
			
			this.matrix[sourceIndex][destinationIndex] = edge;
		}
	}

	/**
	 * Complexity: O(1) per edge to lookup source and destination vertex indexes, and remove the edge.
	 * @see {@link Graph#removeEdge(Edge)}.
	 */
	@Override
	public void removeEdges( @NotNull Collection<E> edges )
	{
		checkNotNull(edges);
		// check edges
		for( E edge : edges )
		{
			checkNotNull(edge);
			Integer sourceIndex = this.vertexMap.get(edge.getSource());
			Integer destinationIndex = this.vertexMap.get(edge.getDestination());
			checkArgument(sourceIndex != null,
			              "Graph does not contain the edge source vertex.", edge.getSource());
			checkArgument(destinationIndex != null,
			              "Graph does not contain the edge destination vertex.", edge.getDestination());
			checkArgument(this.matrix[sourceIndex][destinationIndex] != null,
	                      "Graph does not contain the edge: %s", edge);
			
			Edge<?> currentEdge = this.matrix[sourceIndex][destinationIndex];
			checkArgument(currentEdge.equals(edge),
			              "Graph contains a different edge (%s) than edge %s from vertex %s to vertex %s.",
			              currentEdge,
			              edge,
			              edge.getSource(),
			              edge.getDestination());
		}
		
		// remove edges
		for( E edge : edges )
		{
			int sourceIndex = this.vertexMap.get(edge.getSource());
			int destinationIndex = this.vertexMap.get(edge.getDestination());
			
			this.matrix[sourceIndex][destinationIndex] = null;
		}
		
	}

	/**
	 * Complexity: O(|V|) to iterate over all potential edges from the vertex.
	 * @see {@link Graph#getEdges(Vertex)}.
	 */
	@Override
	public @NotNull Set<E> getEdges( @NotNull V vertex )
	{
		checkNotNull(vertex);
		checkArgument(this.vertexMap.containsKey(vertex), "Graph does not contain the vertex: %s.", vertex);
		
		Integer sourceIndex = this.vertexMap.get(vertex);
		
		HashSet<E> edges = new HashSet<>();
		for( int i=0; i<this.matrix[sourceIndex].length; i++ )
		{
			int destinatationIndex = i;
			@SuppressWarnings("unchecked")
			E edge = (E) this.matrix[sourceIndex][destinatationIndex];
			
			if( edge != null )
			{
				edges.add((E) edge);
			}
		}
		
		return edges;
	}

	/**
	 * Complexity: O(1) to lookup source and destination indexes, and return edge from matrix.
	 * See {@link Graph#getEdge(Vertex, Vertex)}.
	 */
	@Override
	public @NotNull E getEdge( @NotNull V source, @NotNull V destination )
	{
		checkNotNull(source);
		checkNotNull(destination);
		Integer sourceIndex = this.vertexMap.get(source);
		Integer destinationIndex = this.vertexMap.get(destination);
		checkArgument(sourceIndex != null, "Graph does not contain the edge source vertex.", source);
		checkArgument(destinationIndex != null, "Graph does not contain the edge destination vertex.", destination);
		
		@SuppressWarnings("unchecked")
		E edge = (E) this.matrix[sourceIndex][destinationIndex];
		
		return edge;
	}
}
