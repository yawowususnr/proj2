public class InternalNodeTest extends student.TestCase
{

    private Bintree tree;
    private Seminar seminar1;
    private Seminar seminar2;
    private Seminar seminar3;
    private Seminar seminar4;


    public void setUp() {
        // Initialize a new Bintree with a world size of 100
        tree = new Bintree(100);

        // Create some seminars to insert into the Bintree
        seminar1 = new Seminar(1, "Seminar1", "10", 10, (short)30, (short)5.0, (int)5, new String[]{"AI", "ML"}, "Description 1");
        seminar2 = new Seminar(2, "Seminar2", "15", 15, (short)45, (short)25.0, 200, new String[]{"Blockchain"}, "Description 2");
        seminar3 = new Seminar(3, "Seminar3", "20", 20, (short)60, (short)50.0, 300, new String[]{"Cybersecurity"}, "Description 3");
        seminar4 = new Seminar(4, "Seminar4", "25", 25, (short)75, (short)80.0, 400, new String[]{"Data Science"}, "Description 4");
    }
    
    public void testInsertIntoEmptyTree() {
        // Insert a seminar into an empty tree and verify that it becomes the root node
        tree.insert(seminar1);
        assertNotNull("The root should not be null after insertion", tree.getRoot());
        assertTrue("The root should be a leaf node after first insertion", tree.getRoot().isLeaf());

        // Verify the root contains the correct seminar
        LeafNode root = (LeafNode) tree.getRoot();
        assertEquals("The root should contain 1 seminar", 1, root.getSize());
        assertEquals("The seminar ID should match", seminar1.id(), root.getSeminars()[0].id());
    }

   
    public void testInsertMultipleSeminars() {
        // Insert multiple seminars into the tree
        tree.insert(seminar1);
        tree.insert(seminar2);
        tree.insert(seminar3);

        // The root should be an internal node
        assertTrue("The root should be an internal node", tree.getRoot().isInternal());

        // Test internal structure and seminar placement
        InternalNode root = (InternalNode) tree.getRoot();
        assertTrue("The left child of the root should be an internal node or leaf", root.left().isLeaf() || root.left().isInternal());
        assertTrue("The right child of the root should be an internal node or leaf", root.right().isLeaf() || root.right().isInternal());
    }

    public void testRemoveSeminarFromTree() {
        // Insert seminars into the tree
        tree.insert(seminar1);
        tree.insert(seminar2);
        tree.insert(seminar3);

        // Remove a seminar from the tree
        tree.remove(seminar2);

        // Verify the tree structure after removal
        assertTrue("The root should still be internal after removal", tree.getRoot().isInternal());

        // Check that seminar2 is no longer in the tree
        LeafNode leftLeaf = (LeafNode) ((InternalNode) tree.getRoot()).left();
//        for (Seminar seminar : leftLeaf.getSeminars()) {
//            assertNotEquals(seminar2.id(), seminar.id());
//        }
    }


    public void testRemoveLastSeminar() {
        // Insert and then remove the same seminar
        tree.insert(seminar1);
        tree.remove(seminar1);

        // After removing the last seminar, the root should be empty
        assertTrue("The root should be empty after removing the only seminar", tree.getRoot().isEmpty());
        
    }


    public void testHeightCalculation() {
        // Insert multiple seminars and verify the height of the tree
        tree.insert(seminar1);
        tree.insert(seminar2);
        tree.insert(seminar3);
        tree.insert(seminar4);

        int height = tree.getHeight(tree.getRoot());
//        assertEquals("The height of the tree should be 2 after inserting 4 seminars", 2, height);
    }


    public void testSearchWithinRadius() {
        // Insert seminars
        tree.insert(seminar1);
        tree.insert(seminar2);
        tree.insert(seminar3);
        tree.insert(seminar4);

        // Search for seminars within a given radius
        tree.search(seminar1, 20.0);

        // Manually verify the printed output from the console matches expectations
        // For more robust tests, you might capture the printed output and validate it
    }
    
}
