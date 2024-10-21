import student.TestCase;

/**
 * Test suite for the EmptyNode class implementation.
 * 
 * This class validates the singleton pattern implementation and basic operations
 * of the EmptyNode class in the binary space partitioning tree. It ensures
 * proper behavior of node type checking, insertion, removal, and consistent
 * singleton instantiation.
 *
 * @author Yaw Agyemang
 * @author Yaw Owusu Jnr
 * @version 10/20/24
 */
public class EmptyNodeTest extends TestCase {
    private Seminar testSeminar;
    private EmptyNode emptyNode;
    private BoundingBox testBoundary;

    /**
     * Initializes the test environment before each test method.
     * Creates a test seminar with sample data and establishes a bounding box
     * for spatial testing. Obtains the singleton instance of EmptyNode.
     */
    public void setUp() {
        emptyNode = EmptyNode.getInstance();
        
        // Initialize test seminar with sample data
        testSeminar = new Seminar(
            1,                          // ID 
            "Test Seminar",            // Title
            "2032-11-06",             // Date
            60,                        // Length
            (short)40,                 // X coordinate
            (short)80,                 // Y coordinate
            150,                       // Cost
            new String[] { "Location" }, // Keywords
            "Test seminar description"   // Description
        );

        // Define test boundary for spatial operations
        testBoundary = new BoundingBox(12, 10, 50, 90);
    }

    /**
     * Verifies that an empty node correctly handles removal operations
     * by returning itself without modification.
     */
    public void testRemovalPreservesEmptyState() {
        BintreeNode resultNode = emptyNode.remove(testSeminar, 0, testBoundary);
        
        assertNotNull("Remove operation should return a node", resultNode);
        assertSame("Remove should return the original empty node", 
            emptyNode, resultNode);
    }

    /**
     * Validates the singleton pattern implementation by ensuring
     * multiple getInstance calls return the same object reference.
     */
    public void testGetInstanceReturnsSameObject() {
        EmptyNode firstInstance = EmptyNode.getInstance();
        EmptyNode secondInstance = EmptyNode.getInstance();
        EmptyNode thirdInstance = EmptyNode.getInstance();
        
        assertSame("Multiple getInstance calls should return same object", 
            firstInstance, secondInstance);
        assertSame("Singleton pattern violated", secondInstance, thirdInstance);
    }

    /**
     * Confirms that inserting a seminar into an empty node
     * creates and returns an appropriate leaf node.
     */
    public void testInsertCreatesLeafNode() {
        BintreeNode resultNode = emptyNode.insert(testSeminar, 0, testBoundary);
        
        assertNotNull("Insert should create a new node", resultNode);
        assertTrue("Insert should create a LeafNode", 
            resultNode instanceof LeafNode);
    }

    /**
     * Verifies that empty nodes correctly report their non-leaf status.
     */
    public void testEmptyNodeIsNotLeaf() {
        assertFalse("Empty node should not be classified as leaf", 
            emptyNode.isLeaf());
    }

    /**
     * Verifies that empty nodes correctly report their non-internal status.
     */
    public void testEmptyNodeIsNotInternal() {
        assertFalse("Empty node should not be classified as internal", 
            emptyNode.isInternal());
    }

    /**
     * Verifies that empty nodes correctly report their empty status.
     */
    public void testEmptyNodeIsEmpty() {
        assertTrue("Empty node should report as empty", 
            emptyNode.isEmpty());
    }
}