import student.TestCase;

/**
 * Test class for the BSTNode functionality. This class extends TestCase and
 * provides unit tests for the BSTNode class.
 * 
 * @author Yaw Agyemang
 * @author Yaw Owusu Jnr
 * @version 10/18/24
 */
public class BSTNodeTest extends TestCase {

	private BSTNode<String> rootNode;

	/**
	 * Sets up the tests that follow. This method is used for initialization before
	 * each test method. It creates a root node with the element "x".
	 */
	public void setUp() {
		this.rootNode = new BSTNode<String>("x");
	}

	/**
	 * Tests the retrieval of the element in the BSTNode. Verifies that the root
	 * node contains the correct element.
	 */
	public void testRetrieveElement() {
		assertEquals(this.rootNode.getElement(), "x");
	}

	/**
	 * Tests the retrieval of the right child of the BSTNode. It creates a right
	 * child node with element "y", sets it to the root, and verifies that the
	 * root's right child is correctly set.
	 */
	public void testRetrieveRightChild() {
		BSTNode<String> rightChildNode = new BSTNode<String>("y");
		this.rootNode.setRight(rightChildNode);

		assertEquals(this.rootNode.getRight().getElement(), "y");
	}

	/**
	 * Tests the retrieval of the left child of the BSTNode. It creates a left child
	 * node with element "z", sets it to the root, and verifies that the root's left
	 * child is correctly set.
	 */
	public void testRetrieveLeftChild() {
		BSTNode<String> leftChildNode = new BSTNode<String>("z");
		this.rootNode.setLeft(leftChildNode);

		assertEquals(this.rootNode.getLeft().getElement(), "z");
	}
}
