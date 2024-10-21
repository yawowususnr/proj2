/**
 * Unit tests for the SeminarNode class within a binary tree structure.
 * This test suite verifies the functionality of inserting, removing,
 * and searching for courses within the Bintree .
 * 
 * @author Yaw Agyemang 
 * @author Yaw Owusu Jnr
 * @version 10/18/24
 */
public class InternalNodeTest extends student.TestCase {

    private Bintree courseTree;
    private Seminar introToProgramming;
    private Seminar webDevelopment;
    private Seminar dataStructures;
    private Seminar algorithms;

    /**
     * Sets up the test environment by initializing the binary tree and
     * creating several course instances for testing.
     */
    @Override
    public void setUp() {
        courseTree = new Bintree (100);

        // Create course instances
        introToProgramming = new Seminar(101, "Introduction to Programming", "CS101", 1, (short)35, (short)10.0, (int)10,
            new String[] { "Programming", "Computer Science" }, "Fundamental concepts of programming.");
        
        webDevelopment = new Seminar(102, "Web Development", "CS102", 18, (short)50,
            (short)30.0, 250,
            new String[] { "HTML", "CSS", "JavaScript" }, "Building responsive websites.");
        
        dataStructures = new Seminar(103, "Data Structures", "CS201", 22, (short)70,
            (short)45.0, 350,
            new String[] { "Algorithms", "Data Management" }, "Introduction to data structures and algorithms.");
        
        algorithms = new Seminar(104, "Algorithms", "CS202", 28, (short)80,
            (short)90.0, 500,
            new String[] { "Algorithm Design", "Complexity" }, "Understanding algorithmic problem-solving techniques.");
    }
    

    /**
     * Tests the insertion of a course into an empty course tree.
     * Verifies that the course becomes the root node and is a leaf.
     */
    public void testInsertIntoEmptyTree() {
        courseTree.insert(introToProgramming);
        
        assertNotNull("Root node should not be null after insertion", courseTree.getRoot());
        assertTrue("Root node should be a leaf after first insertion", courseTree.getRoot().isLeaf());

        LeafNode rootNode = (LeafNode) courseTree.getRoot();
        assertEquals("Root should contain 1 course", 1, rootNode.getSize());
        assertEquals("Seminar ID should match", introToProgramming.id(), rootNode.getSeminars()[0].id());
    }

    /**
     * Tests the insertion of multiple courses into the course tree.
     * Verifies the structure of the tree after multiple insertions.
     */
    public void testInsertMultipleSeminars() {
        courseTree.insert(introToProgramming);
        courseTree.insert(webDevelopment);
        courseTree.insert(dataStructures);

        assertTrue("Root node should be an internal node", courseTree.getRoot().isInternal());

        InternalNode rootNode = (InternalNode) courseTree.getRoot();
        assertTrue("Left child of root should be an internal node or leaf", 
            rootNode.left().isLeaf() || rootNode.left().isInternal());
        assertTrue("Right child of root should be an internal node or leaf", 
            rootNode.right().isLeaf() || rootNode.right().isInternal());
    }

    // Group: Tests for removal

    /**
     * Tests the removal of the last course in the course tree.
     * Verifies that the tree becomes empty afterwards.
     */
    public void testRemoveLastSeminar() {
        courseTree.insert(introToProgramming);
        courseTree.remove(introToProgramming);

        assertTrue("The tree should be empty after removing the only course", 
            courseTree.getRoot().isEmpty());
    }

    /**
     * Tests the removal of a course from the course tree.
     * Verifies that the course is no longer present and the tree structure remains valid.
     */
    public void testRemoveSeminar() {
        courseTree.insert(introToProgramming);
        courseTree.insert(webDevelopment);
        courseTree.insert(dataStructures);

        courseTree.remove(webDevelopment);

        assertTrue("Root node should still be internal after removal", courseTree.getRoot().isInternal());

        LeafNode leftLeaf = (LeafNode) ((InternalNode) courseTree.getRoot()).left();
        // Add assertions to check that webDevelopment is no longer in the tree
    }

    // Group: Tests for search and height

    /**
     * Tests searching for courses within a specified radius.
     * Verifies that the search functionality works as expected.
     */
    public void testSearchSeminarsWithinRadius() {
        courseTree.insert(introToProgramming);
        courseTree.insert(webDevelopment);
        courseTree.insert(dataStructures);
        courseTree.insert(algorithms);

        courseTree.search(introToProgramming, 20.0);
        // Add assertions to validate search results if applicable
    }

    /**
     * Tests the height calculation of the course tree after inserting multiple
     * courses. The calculated height can be used to verify the balance and structure of the tree.
     */
    public void testCalculateTreeHeight() {
        courseTree.insert(introToProgramming);
        courseTree.insert(webDevelopment);
        courseTree.insert(dataStructures);
        courseTree.insert(algorithms);

        int height = courseTree.getHeight(courseTree.getRoot());
        // Add assertions to validate height if applicable
    }
}
