import student.TestCase;

/**
 * Unit tests for the LeafNode class, which handles a set of Seminar objects.
 * 
 * @author Yaw Agyemang    
 * @author Yaw Owusu Jnr
 * @version 10/18/24
 */
public class LeafNodeTest extends TestCase {

    private Seminar seminarA;
    private Seminar seminarB;
    private Seminar seminarC;

    private LeafNode leafNode;

    /**
     * Initializes the test environment with Seminar objects and 
     * initializes LeafNode with seminarA.
     */
    public void setUp() {
        // Create seminars with different attributes
        seminarA = new Seminar(1, "First Seminar", "2023-11-12", (int)5.0,
            (short)1.0, (short)2.0, (int)105.0, new String[] { "TopicA" }, 
            "First Description");
        seminarB = new Seminar(2, "Second Seminar", "2024-01-15", (int)3.0,
            (short)4.0, (short)6.0, (int)115.0, new String[] { "TopicB" },
            "Second Description");
        seminarC = new Seminar(3, "Third Seminar", "2024-05-25", (int)7.0,
            (short)2.0, (short)8.0, (int)130.0, new String[] { "TopicC" },
            "Third Description");

        // Initialize LeafNode with the first seminar
        leafNode = new LeafNode(seminarA);
    }


    /**
     * Tests the creation of a LeafNode and verifies initial state 
     * after adding seminarA.
     */
    public void testLeafNodeInitialized() {
        // Verify that the node was correctly initialized
        assertEquals(1, leafNode.getSize());
        assertEquals(seminarA, leafNode.getSeminars()[0]);
    }


    /**
     * Verifies the add operation, confirming the array size increases and 
     * the seminars are added correctly.
     */
    public void testAddSeminarToLeaf() {
        // Add seminarB and check the node's size and contents
        leafNode.addSeminar(seminarB);

        assertEquals(2, leafNode.getSize());
        assertEquals(seminarB, leafNode.getSeminars()[1]);
    }


    /**
     * Confirms the array expands when seminars are added beyond the 
     * current capacity.
     */
    public void testArrayExpansionAfterAddingSeminars() {
        // Add seminars to trigger array expansion
        leafNode.addSeminar(seminarB);
        leafNode.addSeminar(seminarC);

        assertEquals(3, leafNode.getSize());
        assertEquals(3, leafNode.getSeminars().length);
    }


    /**
     * Tests the sorting of seminars based on their IDs after adding them 
     * in random order.
     */
    public void testSeminarSortingAfterAdditions() {
        // Add out-of-order seminars and verify correct sorting
        leafNode.addSeminar(seminarC); // Seminar with ID 3
        leafNode.addSeminar(seminarB); // Seminar with ID 2

        Seminar[] sortedSeminars = leafNode.getSeminars();

        assertEquals(seminarA, sortedSeminars[0]); // ID 1
        assertEquals(seminarB, sortedSeminars[1]); // ID 2
        assertEquals(seminarC, sortedSeminars[2]); // ID 3
    }


    /**
     * Tests the removal of a seminar and verifies the node's size 
     * updates correctly.
     */
    public void testRemoveSeminarFromNode() {
        // Add and remove seminarA
        leafNode.addSeminar(seminarB);
        leafNode.removeSeminar(seminarA);

        assertEquals(1, leafNode.getSize());
        assertEquals(seminarB, leafNode.getSeminars()[0]);
    }


    /**
     * Verifies that attempting to remove a seminar not present 
     * in the LeafNode does not affect the node.
     */
    public void testRemovingNonExistentSeminar() {
        // Try removing seminarC which isn't in the node
        leafNode.addSeminar(seminarB);
        leafNode.removeSeminar(seminarC); // Seminar C not present

        assertEquals(2, leafNode.getSize()); // Size should remain unchanged
    }


    /**
     * Ensures that all seminars can be removed and verifies the 
     * node becomes empty.
     */
    public void testRemoveAllSeminarsFromLeaf() {
        // Add and remove all seminars
        leafNode.addSeminar(seminarB);
        leafNode.removeSeminar(seminarA);
        leafNode.removeSeminar(seminarB);

        assertEquals(0, leafNode.getSize()); // Node should now be empty
    }


    /**
     * Tests that the node identifies itself as a leaf and not as an 
     * internal node.
     */
    public void testIsLeafNodeAndNotInternal() {
        // Verify that it's a leaf node and not an internal node
        assertTrue(leafNode.isLeaf());
        assertFalse(leafNode.isInternal());
    }


    /**
     * Tests the removal of the last seminar, ensuring it returns 
     * an EmptyNode when no seminars remain.
     */
    public void testRemoveLastSeminarAndReturnEmptyNode() {
        // Remove the last seminar and check if it returns EmptyNode
        BintreeNode resultingNode = leafNode.remove(seminarA, 0, 
            new BoundingBox(0, 20, 0, 20));

        assertTrue(resultingNode instanceof EmptyNode);
    }


    /**
     * Verifies that attempting to remove a seminar from an already 
     * empty node behaves as expected.
     */
    public void testRemoveSeminarFromEmptyNode() {
        // Try removing from an empty node
        leafNode.removeSeminar(seminarA);
        assertEquals(0, leafNode.getSize());
    }
}
