import student.TestCase;

/**
 * Unit tests for the LeafNode class, which manages a collection of Seminar
 * objects.
 * 
 * @author Yaw Agyemang	
 * @author Yaw Owusu Jnr
 * @version 10/18/24
 */
public class LeafNodeTest extends TestCase {

    private Seminar sem1;
    private Seminar sem2;
    private Seminar sem3;

    private LeafNode node;

    /**
     * Sets up the test environment by initializing Seminar objects and
     * the LeafNode with seminar1.
     */
    public void setUp() {
        // Initialize seminars with unique coordinates and IDs
        sem1 = new Seminar(1, "Sem1", "20221112", (int)2.0, (short)2.0,
            (short)2.0, (int)100.0, new String[] { "HERE" }, "Descrip");
        sem2 = new Seminar(2, "Seminar 2", "2024-01-02", (int)3.0, (short)3.0,
            (short)3.0, (int)120.0, new String[] { "AI" }, "Descrip");
        sem3 = new Seminar(3, "Sem3", "20240203", (int)2.0, (short)2.0,
            (short)4.0, (int)130.0, new String[] { "here" }, "Descrip");

        // Initialize the LeafNode with seminar1
        node = new LeafNode(sem1);
    }


    /**
     * Tests adding a seminar to a LeafNode and verifies that the size
     * increases appropriately.
     */
    public void testAddSeminarToLeafNode() {
        // Add a seminar and check that it's added and array expands correctly
        node.addSeminar(sem2);

        assertEquals(2, node.getSize());
        assertEquals(sem2, node.getSeminars()[1]);

    }


    /**
     * Tests the creation of a LeafNode to ensure it is initialized correctly
     * with a seminar.
     */
    public void testLeafNodeCreation() {
        // Check that a new leaf node is created with seminar1
        assertEquals(1, node.getSize());
        assertEquals(sem1, node.getSeminars()[0]);
    }


    /**
     * Tests the array expansion of seminars when adding additional seminars.
     */
    public void testExpandArray() {
        // Add seminars to trigger expansion of the array
        node.addSeminar(sem2);
        node.addSeminar(sem3);

        assertEquals(3, node.getSize());
        assertEquals(3, node.getSeminars().length);
    }


    /**
     * Tests that adding seminars in an out-of-order fashion sorts them
     * correctly by seminar ID.
     */
    public void testSortArrayAfterAdditions() {

        node.addSeminar(sem3); // Seminar with ID 3
        node.addSeminar(sem2); // Seminar with ID 2

        Seminar[] seminars = node.getSeminars();

        assertEquals(sem1, seminars[0]); // ID 1
        assertEquals(sem2, seminars[1]); // ID 2
        assertEquals(sem3, seminars[2]); // ID 3
    }


    /**
     * Tests removing a seminar from a LeafNode and checks if the size
     * is updated correctly.
     */
    public void testRemoveSeminarFromLeafNode() {
        // Add and remove a seminar
        node.addSeminar(sem2);
        node.removeSeminar(sem1);

        assertEquals(1, node.getSize());
        assertEquals(sem2, node.getSeminars()[0]);
    }


    /**
     * Tests removing a seminar that is not present in the LeafNode
     * to ensure no changes occur.
     */
    public void testRemoveSeminarNotPresent() {
        // Remove a seminar not in the array and verify nothing changes
        node.addSeminar(sem2);
        node.removeSeminar(sem3); // Seminar3 not present

        assertEquals(2, node.getSize()); // Size should remain the same
    }


    /**
     * Tests the removal of all seminars from the LeafNode, ensuring
     * that it is empty afterward.
     */
    public void testRemoveAllSeminars() {
        // Add and remove seminars, leaving the node empty
        node.addSeminar(sem2);
        node.removeSeminar(sem1);
        node.removeSeminar(sem2);

        assertEquals(0, node.getSize()); // Size should be 0
        assertTrue(node.getSeminars().length > 0); // Array should still
                                                   // exist but be empty
    }


    /**
     * Tests that a LeafNode correctly identifies itself as not an internal
     * node.
     */
    public void testIsInternal() {
        // Test that a leaf node correctly identifies itself as not internal
        assertFalse(node.isInternal());
    }


    /**
     * Tests that a LeafNode correctly identifies itself as a leaf node.
     */
    public void testIsLeaf() {

        assertTrue(node.isLeaf());
    }


    /**
     * Tests attempting to remove a seminar from an empty LeafNode
     * and checks the resulting size.
     */
    public void testRemoveSeminarFromEmptyLeaf() {

        node.removeSeminar(sem1);
        assertEquals(0, node.getSize());
    }


    /**
     * Tests that removing all seminars from the LeafNode returns an
     * EmptyNode.
     */
    public void testRemoveSeminarAndReturnEmptyNode() {

        BintreeNode resultNode = node.remove(sem1, 0, new BoundingBox(0, 10, 0,
            10));

        assertTrue(resultNode instanceof EmptyNode);
    }
}
