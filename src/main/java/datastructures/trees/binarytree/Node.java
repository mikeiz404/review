package datastructures.trees.binarytree;

import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import javax.validation.constraints.NotNull;
import datastructures.trees.OrderedChildren;
import datastructures.trees.mappedchildrennode.MappedChildrenNode;

public class Node<V> extends MappedChildrenNode<Node<V>, Node.ChildType, V> implements OrderedChildren<Node<V>, V>
{	
	public enum ChildType
	{
		LEFT,
		RIGHT
	}
	
	public Node( V value )
	{
		super(new EnumMap<>(ChildType.class), value);
	}
	
	@Override
	protected Node<V> getSelf( )
	{
		return this;
	}

	public void setLeft( @NotNull Node<V> child )
	{
		this.setChild(ChildType.LEFT, child);
	}
	
	public @NotNull Node<V> setLeft( V value )
	{
		Node<V> child = new Node<>(value);
		this.setLeft(child);
		return child;
	}
	
	public @NotNull Node<V> getLeft( )
	{
		return this.getChild(ChildType.LEFT);
	}
	
	public Node<V> removeLeft( )
	{
		return this.removeChild(ChildType.LEFT);
	}
	
	public boolean hasLeft( )
	{
		return this.hasChild(ChildType.LEFT);
	}
	
	public void setRight( @NotNull Node<V> child )
	{
		this.setChild(ChildType.RIGHT, child);
	}
	
	public @NotNull Node<V> setRight( V value )
	{
		Node<V> child = new Node<>(value);
		this.setRight(child);
		return child;
	}
	
	public @NotNull Node<V> getRight( )
	{
		return this.getChild(ChildType.RIGHT);
	}
	
	public Node<V> removeRight( )
	{
		return this.removeChild(ChildType.RIGHT);
	}
	
	public boolean hasRight( )
	{
		return this.hasChild(ChildType.RIGHT);
	}

	@Override
	public List<Node<V>> getOrderedChildren( )
	{
		// note: EnumMap guarantees natural ordering
		return new LinkedList<>(this.getChildren());
	}
}
