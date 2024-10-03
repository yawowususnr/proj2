import student.TestCase;

public class BSTNodeTest extends TestCase {

	private BSTNode<String> root;

	public void setUp() {
		this.root = new BSTNode('a');
	}

	public void testGetElement() {
		assertEquals(this.root.getElement(), 'a');
	}

	public void testgetRight() {

		BSTNode rightBSTNode = new BSTNode('b');
		assertEquals(rightBSTNode.getElement(), 'b');

		this.root.setRight(rightBSTNode);
		assertEquals(this.root.getRight(), rightBSTNode);
	}

	public void testgetLeft() {
		BSTNode leftBSTNode = new BSTNode('c');
		assertEquals(leftBSTNode.getElement(), 'c');
		this.root.setLeft(leftBSTNode);

		assertEquals(this.root.getLeft(), leftBSTNode);
	}
}
