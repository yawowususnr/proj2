import student.TestCase;

/**
 * Unit tests for the empty node class, extending the
 * 
 * 
 * 
 * 
 * Each test method is designed to validate a specific aspect
 * of theempty ndoe methods.
 * 
 * @author Yaw Agyemang	
 * @author Yaw Owusu Jnr
 * @version 10/18/24
 */

public class EmptyNodeTest extends student.TestCase {
    private Seminar sem;
    private EmptyNode emptyNode;
    private BoundingBox boundry;

    /**
     * Set up the test environment before each test.
     * Initializes the EmptyNode instance and sets up a seminar
     * and bounding box for testing.
     */
    public void setUp() {
        // Use the singleton pattern to get an EmptyNode instance
        emptyNode = EmptyNode.getInstance();

        // Set up a seminar and bounding box for testing
        sem = new Seminar(1, "Sem", "2032-11-06", 60, (short)40, (short)80, 150,
            new String[] { "Here" }, "here sem");

        boundry = new BoundingBox(12, 12, 90, 90); // Example bounding box
    }


    /**
     * Test the remove method in EmptyNode.
     * Verifies that removing from an EmptyNode returns the
     * same EmptyNode instance, maintaining its state.
     */
    public void testRemove() {
        // Removing from an empty node should still return the EmptyNode
        // instance
        BintreeNode result = emptyNode.remove(sem, 0, boundry);
        assertNotNull(result);
        assertSame(emptyNode, result); // Should return the same EmptyNode
                                       // instance
    }


    /**
     * Test the singleton pattern of EmptyNode.
     * Verifies that only one instance of EmptyNode is created
     * and that it consistently returns the same object reference.
     */
    public void testSingletonPattern() {
        // Ensure that only one instance of EmptyNode is created
        EmptyNode anotherInstance = EmptyNode.getInstance();
        assertSame(emptyNode, anotherInstance); // Both should refer to the same
                                                // object
    }


    /**
     * Test the insert method in EmptyNode.
     * Verifies that inserting into an EmptyNode replaces it with a
     * new LeafNode containing the specified seminar.
     */
    public void testInsert() {
        // Insert should return a new LeafNode since we are replacing the empty
        // node
        BintreeNode result = emptyNode.insert(sem, 0, boundry);
        assertNotNull(result);
        assertTrue(result instanceof LeafNode); // Ensure it returns a LeafNode
    }


    /**
     * Test the isLeaf method.
     * Verifies that an EmptyNode is never classified as a leaf node.
     */
    public void testIsLeaf() {
        assertFalse(emptyNode.isLeaf());
    }


    /**
     * Test the isInternal method.
     * Verifies that an EmptyNode is never classified as an internal node.
     */
    public void testIsInternal() {
        assertFalse(emptyNode.isInternal());
    }

}
