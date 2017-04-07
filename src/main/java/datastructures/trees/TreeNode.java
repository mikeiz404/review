package datastructures.trees;

import java.util.Collection;
import javax.validation.constraints.NotNull;

public interface TreeNode<N extends TreeNode<N, V>, V>
{
	public V getValue( );
	
	public void setValue( V value );
	
	public @NotNull N getParent( );
	
	public boolean hasParent( );
	
	public @NotNull Collection<N> getChildren( );
	
	public boolean isRoot( );
	
	public boolean isLeaf( );
	
	public @NotNull N remove( );
	
	public @NotNull N replace( @NotNull N node );
}

