import student.TestCase;

/**
 * Test class for the BSTNode functionality.
 * This class extends TestCase and provides unit tests for the BSTNode class.
 * 
 * @author Chris Nicoue-Beglah
 * @author Yaw Owusu Snr
 * @version 10/19/24
 */
public class BSTNodeTest extends TestCase {

    private BSTNode<String> root;

    /**
     * Sets up the tests that follow.
     * This method is used for initialization before each test method.
     * It creates a root node with the element "a".
     */
    public void setUp() {
        this.root = new BSTNode<String>("a");
    }


    /**
     * Tests the getElement() method of the BSTNode.
     * Verifies that the root node contains the correct element.
     */
    public void testGetElement() {
        assertEquals(this.root.getElement(), "a");
    }


    /**
     * Tests the getRight() method of the BSTNode.
     * It creates a right child node, sets it to the root,
     * and verifies that the root's right child is correctly set.
     */
    public void testgetRight() {

        BSTNode<String> rightBSTNode = new BSTNode<String>("b");
        assertEquals(rightBSTNode.getElement(), "b");

        this.root.setRight(rightBSTNode);
        assertEquals(this.root.getRight(), rightBSTNode);
    }


    /**
     * Tests the getLeft() method of the BSTNode.
     * It creates a left child node, sets it to the root,
     * and verifies that the root's left child is correctly set.
     */
    public void testgetLeft() {
        BSTNode<String> leftBSTNode = new BSTNode<String>("c");
        assertEquals(leftBSTNode.getElement(), "c");
        this.root.setLeft(leftBSTNode);

        assertEquals(this.root.getLeft(), leftBSTNode);
    }
}
