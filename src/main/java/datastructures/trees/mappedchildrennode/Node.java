package datastructures.trees.mappedchildrennode;

public class Node<K, V> extends MappedChildrenNode<Node<K, V>, K, V>
{

	public Node( V value )
	{
		super(value);
	}

	@Override
	protected Node<K, V> getSelf( )
	{
		return this;
	}
	
	public Node<K, V> setChild( K key, V value )
	{
		Node<K, V> child = new Node<>(value);
		
		this.setChild(key, child);
		
		return child;
	}
}
