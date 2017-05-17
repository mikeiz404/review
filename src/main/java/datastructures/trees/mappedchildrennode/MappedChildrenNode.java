package datastructures.trees.mappedchildrennode;

import java.util.Collection;
import java.util.Map;
import javax.validation.constraints.NotNull;
import datastructures.trees.TreeNode;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkArgument;

public abstract class MappedChildrenNode<N extends MappedChildrenNode<N, K, V>, K, V> implements TreeNode<N, V>
{
	private N parent;
	private K key;
	private V value;
	private Map<K, N> children;
	
	public MappedChildrenNode( Map<K, N> map, @NotNull V value )
	{
		this.value = value;
		this.children = map;
		
		this.clearInfo();
	}
	
	protected abstract N getSelf( );
	
	protected void setInfo( @NotNull K key, @NotNull N parent )
	{
		checkNotNull(key);
		checkNotNull(parent);
		
		this.parent = parent;
		this.key = key;
	}
	
	protected void clearInfo( )
	{
		this.parent = null;
		this.key = null;
	}
	
	protected K getKey( )
	{
		return this.key;
	}
	
	@Override
	public V getValue( )
	{
		return this.value;
	}
	
	@Override
	public void setValue( V value )
	{
		this.value = value;
	}
	
	public @NotNull N getChild( @NotNull K key )
	{
		checkNotNull(key);
		checkArgument(this.hasChild(key), "Child with key {} does not exist.");
		
		return checkNotNull(this.children.get(key));
	}
	
	public void setChild( @NotNull K key, @NotNull N child )
	{
		checkNotNull(key);
		checkNotNull(child);
		
		child.setInfo(key, this.getSelf());
		
		this.children.put(key, child);
	}
	
	public @NotNull N removeChild( @NotNull K key )
	{
		checkNotNull(key);
		checkArgument(this.hasChild(key), "Child at key ({}) is not present.", key);
		
		N child = this.children.remove(key);
		child.clearInfo();
		
		return checkNotNull(child);
	}
	
	public boolean hasChild( @NotNull K key )
	{
		checkNotNull(key);
		
		return this.children.containsKey(key);
	}
	

	@Override
	public @NotNull Collection<N> getChildren( )
	{
		return this.children.values();
	}
	
	@Override
	public @NotNull N getParent( )
	{
		
		return checkNotNull(this.parent);
	}
	
	@Override
	public boolean hasParent( )
	{
		return this.parent != null;
	}
	
	@Override
	public boolean isRoot( )
	{
		return this.parent == null;
	}
	
	@Override
	public boolean isLeaf( )
	{
		return this.children.size() == 0;
	}
	
	public @NotNull N remove( )
	{
		checkArgument(!this.isRoot(), "The root node cannot be removed.");
		
		return checkNotNull(this.getParent().removeChild(this.getKey()));
	}
	
	public @NotNull N replace( @NotNull N node )
	{
		checkNotNull(node);
		// note: could replace children and value but that would also result in unexpected value changes at other
		// references to the current root node. This is the safest option.
		checkArgument(!this.isRoot(), "The root node cannot be replaced since the root node would need to be removed.");
		
		this.getParent().setChild(this.getKey(), node);
		this.clearInfo();
		
		return checkNotNull(this.getSelf());
	}
}
