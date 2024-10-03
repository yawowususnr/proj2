import student.TestCase;

public class BSTTest extends TestCase {

	private BST<Integer, String> bstTree;

	public void setUp() {

		BST<Integer, String> bst = new BST<>();
		KeyValuePair<Integer, String> root = new KeyValuePair<>(5, "Five");
		this.bstTree.insert(root);

		assertEquals(1, bst.size());
		assertEquals(root, bst.find(5));
	}
	
	public void testInsertElement() {
		this.bstTree.insert(new KeyValuePair<>(4, "Four"));
		this.bstTree.insert(new KeyValuePair<>(6, "Six"));
		this.bstTree.insert(new KeyValuePair<>(7, "Seven"));
		
		assertEquals(4, this.bstTree.size());
		
		assertNull(this.bstTree.find(10));
		
		KeyValuePair<Integer, String> kvp = this.bstTree.find(4);
		
		assertNotNull(kvp);
		assertEquals(kvp.getValue(), "Four");
		
		KeyValuePair<Integer, String> kvp2 = this.bstTree.find(6);
		
		assertNotNull(kvp2);
		assertEquals(kvp2.getValue(), "Six");
	}
	
	public void testRemove() {
		KeyValuePair<Integer, String> nullNode = this.bstTree.remove(100);
		
		assertNull(nullNode);
		
		KeyValuePair<Integer, String> removedNode = this.bstTree.remove(7);
		
		assertNotNull(removedNode);
		
		assertEquals(removedNode.getValue(), "Seven");
		asserEquals(3, this.bstTree.size());
		
		assertEquals(this.bstTree.find(7), null);
		
	}
	
	public void testClear() {
		assertEquals(this.bstTree.size(), 3);
		
		this.bstTree.clear();
		
		assertEquals(this.bstTree.size(), 0);
		assertEquals(this.bstTree.find(5), null);
	}

}