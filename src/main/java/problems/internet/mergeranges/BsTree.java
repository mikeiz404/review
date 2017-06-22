package problems.internet.mergeranges;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BsTree<V>
{
	private Node root;
	private final Comparator<V> comparator;
	
	public BsTree( Comparator<V> comparator )
	{
		this.comparator = comparator;
	}
	
	public void insert( V value )
	{
		if( root == null )
		{
			this.root = new Node(null, value);
		}
		else
		{
			this.root.insert(value);
		}
	}
	
	protected Node findHelper( V value )
	{
		return this.root.findHelper( value );
	}
	
	public Node findNodeOrNextSmaller( V value )
	{
		return this.root.findNodeOrNextSmaller(value);
	}
	
	public Node findNodeOrNextLarger( V value )
	{
		return this.root.findNodeOrNextLarger(value);
	}
	
	public List<V> inOrder( )
	{
		return this.root.inOrder();
	}
	
	public class Node
	{
		private Node parent;
		private Node left;
		private Node right;
		private V value;
		
		public Node( Node parent, V value )
		{
			this.parent = parent;
			this.value = value;
		}
		
		public Node( V value )
		{
			this(null, value);
		}
		
		public Node findNodeOrNextLarger( V value )
		{
			Node node = findHelper(value);
			int diff = comparator.compare(node.getValue(), value);
			if( diff == 0 )
			{
				return node;
			}
			else if( diff < 0 )
			{
				return node;
			}
			else
			{
				while( node.hasParent() && node.getParent().getRight() == node ) node = node.getParent();
				// note: node must be a left child or root
				return node.getParent();
			}
		}
		
		public Node findNodeOrNextSmaller( V value )
		{
			Node node = findHelper(value);
			int diff = comparator.compare(node.getValue(), value);
			if( diff == 0 )
			{
				return node;
			}
			else if( diff > 0 )
			{
				return node;
			}
			else
			{
				while( node.hasParent() && node.getParent().getLeft() == node ) node = node.getParent();
				// note: node must be a right child or root
				return node.getParent();
			}
		}
		
		protected Node findHelper( V value )
		{
			int diff = comparator.compare(getValue(), value);
			if( diff == 0 )
			{
				return this;
			}
			else if( diff < 0 )
			{
				if( hasLeft() )
				{
					return getLeft().findHelper(value);
				}
				else
				{
					return this;
				}
			}
			else
			{
				if( hasRight() )
				{
					return getRight().findHelper(value);
				}
				else
				{
					return this;
				}
			}
		}
		
		public V getValue( )
		{
			return this.value;
		}
		
		public void setValue( V value )
		{
			this.value = value;
		}
		
		public boolean isRoot( )
		{
			return this.parent == null;
		}
		
		public void insert( V value )
		{
			insert(new Node(null, value));
		}
		
		public void insert( Node node )
		{
			if( comparator.compare(this.getValue(), node.getValue()) < 0 )
			{
				if( hasLeft() )
				{
					getLeft().insert(node);
				}
				else
				{
					setLeft(node);
				}
			}
			else // >=
			{
				if( hasRight() )
				{
					getRight().insert(node);
				}
				else
				{
					setRight(node);
				}
			}
		}
		
		public boolean hasLeft( )
		{
			return this.left != null;
		}
		
		public boolean hasRight( )
		{
			return this.right != null;
		}
		
		public Node getLeft( )
		{
			return this.left;
		}
		
		public Node getRight( )
		{
			return this.right;
		}
		
		public void setLeft( Node node )
		{
			node.parent = this;
			this.left = node;
		}
		
		public void setRight( Node node )
		{
			node.parent = this;
			this.right = node;
		}
		
		public boolean hasParent( )
		{
			return this.parent != null;
		}
		
		public Node getParent( )
		{
			return this.parent;
		}
		
		public void remove( )
		{
			if( isRoot() )
			{
				root = null;
			}
			else
			{
				if( hasLeft() && hasRight() )
				{
					// replace with largest min
					Node node = getLeft();
					while( node.hasRight() ) node = node.getRight();
					
					setValue(node.getValue());
					node.remove();
				}
				else if( hasLeft() ) // && !hasRight()
				{
					replaceChild(this, getLeft());
				}
				else if( hasRight() ) // && !hasLeft()
				{
					replaceChild(this, getRight());
				}
				else
				{
					replaceChild(this, null);
				}
			}
		}
		
		protected void replaceChild( Node child, Node node )
		{
			if( hasLeft() && getLeft() == child )
			{
				setLeft(node);
			}
			else
			{
				setRight(node);
			}
		}

		public List<V> inOrder( )
		{
			ArrayList<V> values = new ArrayList<>();
			inOrderRec(values);
			return values;
		}
		
		protected void inOrderRec( List<V> values )
		{
			if( hasLeft() ) getLeft().inOrderRec(values);
			values.add(getValue());
			if( hasRight() ) getRight().inOrderRec(values);
		}
	}

}
