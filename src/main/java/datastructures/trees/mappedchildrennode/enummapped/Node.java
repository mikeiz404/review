package datastructures.trees.mappedchildrennode.enummapped;

import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import datastructures.trees.OrderedChildren;
import datastructures.trees.mappedchildrennode.MappedChildrenNode;

public class Node<E extends Enum<E>, V> extends MappedChildrenNode<Node<E, V>, E, V>  implements OrderedChildren<Node<E, V>, V>
{
	private final Class<E> enumClazz;
	
	public Node( Class<E> enumClazz, V value )
	{
		super(new EnumMap<>(enumClazz), value);
		
		this.enumClazz = enumClazz;
	}

	@Override
	protected Node<E, V> getSelf( )
	{
		return this;
	}
	
	public Node<E, V> setChild( E key, V value )
	{
		Node<E, V> child = new Node<>(this.enumClazz, value);
		
		this.setChild(key, child);
		
		return child;
	}

	@Override
	public List<Node<E, V>> getOrderedChildren( )
	{
		// note: EnumMap guarantees natural ordering
		return new LinkedList<>(this.getChildren());
	}
}
