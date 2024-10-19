import student.TestCase;

/**
 * Unit tests for the LeafNode class, which manages a collection of Seminar
 * objects.
 * 
 * @author Yaw Owusu Snr 
 * @author Chris Nicoue-Beglah
 * @version 10/18/24
 */
public class LeafNodeTest extends TestCase {

    private Seminar seminar1;
    private Seminar seminar2;
    private Seminar seminar3;
    private Seminar seminar4;
    private LeafNode leafNode;

    /**
     * Sets up the test environment by initializing Seminar objects and
     * the LeafNode with seminar1.
     */
    public void setUp() {
        // Initialize seminars with unique coordinates and IDs
        seminar1 = new Seminar(1, "Seminar 1", "2024-01-01", (int)2.0,
            (short)2.0, (short)2.0, (int)100.0, new String[] { "AI" },
            "Description 1");
        seminar2 = new Seminar(2, "Seminar 2", "2024-01-02", (int)3.0,
            (short)3.0, (short)3.0, (int)120.0, new String[] { "ML" },
            "Description 2");
        seminar3 = new Seminar(3, "Seminar 3", "2024-01-03", (int)2.0,
            (short)2.0, (short)4.0, (int)130.0, new String[] { "Cloud" },
            "Description 3");
        seminar4 = new Seminar(4, "Seminar 4", "2024-01-04", (int)5.0,
            (short)5.0, (short)5.0, (int)150.0, new String[] { "FinTech" },
            "Description 4");

        // Initialize the LeafNode with seminar1
        leafNode = new LeafNode(seminar1);
    }


    /**
     * Tests the creation of a LeafNode to ensure it is initialized correctly
     * with a seminar.
     */
    public void testLeafNodeCreation() {
        // Check that a new leaf node is created with seminar1
        assertEquals(1, leafNode.getSize());
        assertEquals(seminar1, leafNode.getSeminars()[0]);
    }


    /**
     * Tests adding a seminar to a LeafNode and verifies that the size
     * increases appropriately.
     */
    public void testAddSeminarToLeafNode() {
        // Add a seminar and check that it's added and array expands correctly
        leafNode.addSeminar(seminar2);

        assertEquals(2, leafNode.getSize());
        assertEquals(seminar2, leafNode.getSeminars()[1]); // seminar2 should
                                                           // be second in the
                                                           // array
    }


    /**
     * Tests the array expansion of seminars when adding additional seminars.
     */
    public void testExpandArray() {
        // Add seminars to trigger expansion of the array
        leafNode.addSeminar(seminar2);
        leafNode.addSeminar(seminar3); // This should trigger an array
                                       // expansion

        assertEquals(3, leafNode.getSize());
        assertEquals(3, leafNode.getSeminars().length);
    }


    /**
     * Tests that adding seminars in an out-of-order fashion sorts them
     * correctly by seminar ID.
     */
    public void testSortArrayAfterAdditions() {
        // Add out-of-order seminars and check that the array is sorted by
        // seminar ID
        leafNode.addSeminar(seminar3); // Seminar with ID 3
        leafNode.addSeminar(seminar2); // Seminar with ID 2

        Seminar[] seminars = leafNode.getSeminars();

        assertEquals(seminar1, seminars[0]); // ID 1
        assertEquals(seminar2, seminars[1]); // ID 2
        assertEquals(seminar3, seminars[2]); // ID 3
    }


    /**
     * Tests removing a seminar from a LeafNode and checks if the size
     * is updated correctly.
     */
    public void testRemoveSeminarFromLeafNode() {
        // Add and remove a seminar
        leafNode.addSeminar(seminar2);
        leafNode.removeSeminar(seminar1);

        assertEquals(1, leafNode.getSize());
        assertEquals(seminar2, leafNode.getSeminars()[0]);
    }


    /**
     * Tests removing a seminar that is not present in the LeafNode
     * to ensure no changes occur.
     */
    public void testRemoveSeminarNotPresent() {
        // Remove a seminar not in the array and verify nothing changes
        leafNode.addSeminar(seminar2);
        leafNode.removeSeminar(seminar3); // Seminar3 not present

        assertEquals(2, leafNode.getSize()); // Size should remain the same
    }


    /**
     * Tests the removal of all seminars from the LeafNode, ensuring
     * that it is empty afterward.
     */
    public void testRemoveAllSeminars() {
        // Add and remove seminars, leaving the node empty
        leafNode.addSeminar(seminar2);
        leafNode.removeSeminar(seminar1);
        leafNode.removeSeminar(seminar2);

        assertEquals(0, leafNode.getSize()); // Size should be 0
        assertTrue(leafNode.getSeminars().length > 0); // Array should still
                                                       // exist but be empty
    }


    /**
     * Tests that a LeafNode correctly identifies itself as not an internal
     * node.
     */
    public void testIsInternal() {
        // Test that a leaf node correctly identifies itself as not internal
        assertFalse(leafNode.isInternal());
    }


    /**
     * Tests that a LeafNode correctly identifies itself as a leaf node.
     */
    public void testIsLeaf() {
        // Test that a leaf node correctly identifies itself as a leaf
        assertTrue(leafNode.isLeaf());
    }


    /**
     * Tests attempting to remove a seminar from an empty LeafNode
     * and checks the resulting size.
     */
    public void testRemoveSeminarFromEmptyLeaf() {
        // Attempt to remove a seminar from an empty leaf node
        leafNode.removeSeminar(seminar1); // This should result in an empty
                                          // node

        assertEquals(0, leafNode.getSize());
    }


    /**
     * Tests that removing all seminars from the LeafNode returns an
     * EmptyNode.
     */
    public void testRemoveSeminarAndReturnEmptyNode() {
        // Test that removing all seminars from the node returns an EmptyNode
        BintreeNode resultNode = leafNode.remove(seminar1, 0, new BoundingBox(0,
            10, 0, 10));

        assertTrue(resultNode instanceof EmptyNode); // Should return EmptyNode
    }
}
