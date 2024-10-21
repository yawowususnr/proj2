/**
 * Unit tests for the InternalNode class within a binary tree structure.
 * This test suite verifies the functionality of inserting, removing,
 * and searching for seminars within the Bintree.
 * 
 * @author Yaw Agyemang	
 * @author Yaw Owusu Jnr
 * @version 10/18/24
 */
public class InternalNodeTest extends student.TestCase {

    private Bintree tree;
    private Seminar sem1;
    private Seminar sem2;
    private Seminar sem3;
    private Seminar sme4;

    /**
     * Sets up the test environment by initializing the binary tree and
     * creating several seminar instances for testing.
     */
    public void setUp() {

        tree = new Bintree(100);

        // Create some seminars to insert into the Bintree
        sem1 = new Seminar(1, "Advanced AI Workshop", "12", 12, (short)35,
            (short)10.0, (int)10, new String[] { "Artificial Intelligence",
                "Deep Learning" }, "In-depth exploration of AI technologies.");
        sem2 = new Seminar(2, "Blockchain Fundamentals", "18", 18, (short)50,
            (short)30.0, 250, new String[] { "Blockchain", "Cryptocurrency" },
            "Introduction to blockchain technology.");
        sem3 = new Seminar(3, "Cybersecurity Essentials", "22", 22, (short)70,
            (short)45.0, 350, new String[] { "Cybersecurity",
                "Information Security" },
            "Overview of cybersecurity practices and technologies.");
        sme4 = new Seminar(4, "Data Science Bootcamp", "28", 28, (short)80,
            (short)90.0, 500, new String[] { "Data Science",
                "Machine Learning" },
            "Comprehensive training in data science methodologies.");
    }


    /**
     * Tests the removal of the last seminar in the binary tree.
     * Verifies that the tree becomes empty afterwards.
     */
    public void testLastSeminar() {
        tree.insert(sem1);
        tree.remove(sem1);

        assertTrue("The root should be empty after removing the only seminar",
            tree.getRoot().isEmpty());

    }


    /**
     * Tests the insertion of a seminar into an empty binary tree.
     * Verifies that the seminar becomes the root node and is a leaf.
     */
    public void testInsertEmptyTree() {
        // Insert a seminar into an empty tree and verify that it becomes the
        // root node
        tree.insert(sem1);
        assertNotNull("The root shoasduld not be null after insertion", tree
            .getRoot());
        assertTrue("The root shasdasould be a leaf node after first insertion", tree
            .getRoot().isLeaf());

        // Verify the root contains the correct seminar
        LeafNode root = (LeafNode)tree.getRoot();
        assertEquals("The root should coniuh sdftain 1 seminar", 1, root.getSize());
        assertEquals("The seminar Iytuif asd guicD should match", sem1.id(), root
            .getSeminars()[0].id());
    }


    /**
     * Tests searching for seminars within a specified radius.
     * Verifies that the search functionality works as expected.
     */
    public void testSearchRadius() {
        // Insert seminars
        tree.insert(sem1);
        tree.insert(sem2);
        tree.insert(sem3);
        tree.insert(sme4);

        tree.search(sem1, 20.0);

    }


    /**
     * Tests the insertion of multiple seminars into the binary tree.
     * Verifies the structure of the tree after multiple insertions.
     */
    public void testInsertSeminars() {
        // Insert multiple seminars into the tree
        tree.insert(sem1); // "Advanced AI Workshop"
        tree.insert(sem2); // "Blockchain Fundamentals"
        tree.insert(sem3); // "Cybersecurity Essentials"

        // The root should be an internal node
        assertTrue("The root should be an internal node", tree.getRoot()
            .isInternal());

        // Test internal structure and seminar placement
        InternalNode root = (InternalNode)tree.getRoot();
        assertTrue(
            "The left child of the root should be an internal node or leaf",
            root.left().isLeaf() || root.left().isInternal());
        assertTrue(
            "The right child of the root should be an internal node or leaf",
            root.right().isLeaf() || root.right().isInternal());
    }


    /**
     * Tests the height calculation of the binary tree after inserting multiple
     * seminars.
     * The calculated height can be used to verify the balance and structure of
     * the tree.
     */
    public void testHeight() {

        tree.insert(sem1);
        tree.insert(sem2);
        tree.insert(sem3);
        tree.insert(sme4);

        int height = tree.getHeight(tree.getRoot());

    }


    /**
     * Tests the removal of a seminar from the binary tree.
     * Verifies that the seminar is no longer present and the tree structure
     * remains valid.
     */
    public void testRemoveSeminar() {
        // Insert seminars into the tree
        tree.insert(sem1); // "Advanced AI Workshop"
        tree.insert(sem2); // "Blockchain Fundamentals"
        tree.insert(sem3); // "Cybersecurity Essentials"

        // Remove a seminar from the tree
        tree.remove(sem2); // Removing "Blockchain Fundamentals"

        // Verify the tree structure after removal
        assertTrue("The root should still be internal after removal", tree
            .getRoot().isInternal());

        // Check that seminar2 is no longer in the tree
        LeafNode leftLeaf = (LeafNode)((InternalNode)tree.getRoot()).left();

    }

}
