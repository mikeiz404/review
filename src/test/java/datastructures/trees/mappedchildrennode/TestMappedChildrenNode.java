package datastructures.trees.mappedchildrennode;

import org.junit.Test;
import datastructures.trees.mappedchildrennode.Node;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

public class TestMappedChildrenNode
{
	@Test
	public void testGetValue( )
	{
		String rootValue = "root";
		Node<String, String> root = new Node<>(rootValue);
		
		assertEquals(rootValue, root.getValue());
	}
	
	@Test
	public void testSetValue( )
	{
		String setValue = "second";
		Node<String, String> root = new Node<>("first");
		root.setValue(setValue);
		
		assertEquals(setValue, root.getValue());
	}
	
	@Test
	public void testIsRoot( )
	{
		Node<String, String> root = new Node<>("root");
		Node<String, String> a = root.setChild("a", "a");
		Node<String, String> b = root.setChild("b", "b");
		
		assertTrue(root.isRoot());
		assertFalse(a.isRoot());
		assertFalse(b.isRoot());
	}
	
	@Test
	public void testIsLeaf( )
	{
		Node<String, String> root = new Node<>("root");
		Node<String, String> a = root.setChild("a", "a");
		Node<String, String> b = root.setChild("b", "b");
		
		assertFalse(root.isLeaf());
		assertTrue(a.isLeaf());
		assertTrue(b.isLeaf());
	}
	
	@Test
	public void testGetChildren( )
	{
		Node<String, String> root = new Node<>("root");
		Node<String, String> a = root.setChild("a", "a");
		Node<String, String> b = root.setChild("b", "b");
		
		assertTrue(root.getChildren().contains(a));
		assertTrue(root.getChildren().contains(b));
	}
	
	@Test
	public void testHasChild( )
	{
		Node<String, String> root = new Node<>("root");
		root.setChild("a", "a");
		root.setChild("b", "b");
		
		assertTrue(root.hasChild("a"));
		assertTrue(root.hasChild("b"));
		assertFalse(root.hasChild("c"));
	}
	
	@Test
	public void testGetChild( )
	{
		Node<String, String> root = new Node<>("root");
		Node<String, String> a = root.setChild("a", "a");
		Node<String, String> b = root.setChild("b", "b");
		
		assertEquals(a, root.getChild("a"));
		assertEquals(b, root.getChild("b"));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGetChildAtMissingKey( )
	{
		Node<String, String> root = new Node<>("root");
		
		root.getChild("a");
	}
	
	@Test
	public void testHasParent( )
	{
		Node<String, String> root = new Node<>("root");
		Node<String, String> child = root.setChild("child", "child");
		
		assertTrue(child.hasParent());
		assertFalse(root.hasParent());
	}
	
	@Test
	public void testGetParent( )
	{
		Node<String, String> root = new Node<>("root");
		Node<String, String> child = root.setChild("child", "child");
		
		assertEquals(root, child.getParent());
	}
	
	@Test(expected=NullPointerException.class)
	public void testGetParentWithNoParent( )
	{
		Node<String, String> root = new Node<>("root");
		
		root.getParent();
	}
	
	@Test
	public void testReplace( )
	{
		Node<String, String> root = new Node<>("root");
		String aKey = "a";
		String aValue = "a";
		Node<String, String> a = root.setChild(aKey, aValue);
		Node<String, String> b = new Node<>("b");
		
		a.replace(b);
		
		assertEquals(aValue, a.getValue());
		assertFalse(a.hasParent());
		assertEquals(0, a.getChildren().size());
		
		assertEquals(1, root.getChildren().size());
		assertEquals(b, root.getChild(aKey));
		assertTrue(b.hasParent());
		assertEquals(root, b.getParent());
	}
	
	@Test
	public void testRemove( )
	{
		Node<String, String> root = new Node<>("root");
		Node<String, String> child = root.setChild("child", "child");
		
		child.remove();
		
		assertEquals(0, root.getChildren().size());
		
		assertFalse(child.hasParent());
	}
}
