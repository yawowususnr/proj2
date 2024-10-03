import student.TestCase;

public class BSTNodeTest extends TestCase {

	private BSTNode<String> root;

	public void setUp() {
		this.root = new BSTNode<String>("a");
	}

	public void testGetElement() {
		assertEquals(this.root.getElement(), "a");
	}

	public void testgetRight() {

		BSTNode<String> rightBSTNode = new BSTNode<String>("b");
		assertEquals(rightBSTNode.getElement(), "b");

		this.root.setRight(rightBSTNode);
		assertEquals(this.root.getRight(), rightBSTNode);
	}

	public void testgetLeft() {
		BSTNode<String> leftBSTNode = new BSTNode<String>("c");
		assertEquals(leftBSTNode.getElement(), "c");
		this.root.setLeft(leftBSTNode);

		assertEquals(this.root.getLeft(), leftBSTNode);
	}
}
