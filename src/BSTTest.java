import student.TestCase;

/**
 * This class tests the functionality of the Binary Search Tree (BST)
 * implementation.
 * It contains test cases for inserting, removing, and traversing elements in
 * the BST.
 * 
 * @author Yaw Agyemang	
 * @author Yaw Owusu Jnr
 * @version 10/18/24
 */
public class BSTTest extends TestCase {

    private BST<Integer, String> bstTree;

    /**
     * Sets up the initial state of the BST by inserting a root node.
     * This method is called before each test case.
     */
    public void setUp() {

        this.bstTree = new BST<>();
        KVPair<Integer, String> root = new KVPair<>(5, "Five");
        this.bstTree.insert(root);

        assertEquals(1, bstTree.size());
        assertEquals(root, bstTree.find(5));

    }


    /**
     * Tests the insertion of elements into the BST.
     * It verifies that nodes can be inserted and found correctly,
     * and ensures the size of the tree is updated.
     */
    public void testInsertElement() {
        this.bstTree.insert(new KVPair<>(4, "Four"));
        this.bstTree.insert(new KVPair<>(6, "Six"));
        this.bstTree.insert(new KVPair<>(7, "Seven"));

        assertEquals(4, this.bstTree.size());

        assertNull(this.bstTree.find(10));

        KVPair<Integer, String> kvp = this.bstTree.find(4);

        assertNotNull(kvp);
        assertEquals(kvp.getValue(), "Four");

        KVPair<Integer, String> kvp2 = this.bstTree.find(6);

        assertNotNull(kvp2);
        assertEquals(kvp2.getValue(), "Six");
    }


    /**
     * Tests the removal of elements from the BST.
     * It verifies that non-existent nodes return null and ensures that
     * the tree size is updated correctly after removing nodes.
     */
    public void testRemove() {
        KVPair<Integer, String> nullNode = this.bstTree.remove(100);

        assertNull(nullNode);

        this.bstTree.insert(new KVPair<>(7, "Seven"));

        KVPair<Integer, String> removedNode = this.bstTree.remove(7);

        assertNotNull(removedNode);

        assertEquals(removedNode.getValue(), "Seven");
        assertEquals(1, this.bstTree.size());

        assertEquals(this.bstTree.find(7), null);

    }


    /**
     * Tests a more complex removal scenario, removing nodes with different
     * characteristics.
     * It includes cases where nodes have no children, one child, or two
     * children.
     */
    public void testRemove2() {

        this.bstTree.remove(5);
        assertNull(this.bstTree.remove(5));
        // Step 1: Insert nodes into the tree
        this.bstTree.insert(new KVPair<>(6, "Six"));
        this.bstTree.insert(new KVPair<>(3, "Three"));
        this.bstTree.insert(new KVPair<>(9, "Nine"));
        this.bstTree.insert(new KVPair<>(2, "Two"));
        this.bstTree.insert(new KVPair<>(5, "Five"));
        this.bstTree.insert(new KVPair<>(8, "Eight"));
        this.bstTree.insert(new KVPair<>(10, "Ten"));
        this.bstTree.insert(new KVPair<>(1, "One"));

        // Verify initial size is 8
        assertEquals(8, this.bstTree.size());

        // Step 2: Remove nodes that will trigger deleteMax and getMax
        this.bstTree.remove(9); // Should trigger getMax and deleteMax as 9 has
                                // two children
        assertEquals(7, this.bstTree.size());

        // Step 3: Remove a leaf node (no children)
        this.bstTree.remove(5); // Removing a node with no children
        assertEquals(6, this.bstTree.size());

        // Step 4: Remove nodes with only one child
        this.bstTree.remove(3); // Node 3 has two children, should invoke getMax
                                // and deleteMax
        assertEquals(5, this.bstTree.size());

        // Step 5: Remove the root (6) to further test rebalancing
        this.bstTree.remove(6); // Root removal, should also trigger
                                // reorganization
        assertEquals(4, this.bstTree.size());

        // Step 6: Attempt to remove a non-existent node (edge case)
        assertNull(this.bstTree.remove(4)); // Node 4 does not exist
    }


    /**
     * Tests the traversal of the BST within a specified range.
     * It verifies that the correct number of nodes are traversed.
     */
    public void testTraverse() {
        // Insert nodes into the tree
        this.bstTree.insert(new KVPair<>(6, "Six"));
        this.bstTree.insert(new KVPair<>(3, "Three"));
        this.bstTree.insert(new KVPair<>(9, "Nine"));
        this.bstTree.insert(new KVPair<>(2, "Two"));
        this.bstTree.insert(new KVPair<>(5, "Five"));
        this.bstTree.insert(new KVPair<>(8, "Eight"));
        this.bstTree.insert(new KVPair<>(10, "Ten"));
        this.bstTree.insert(new KVPair<>(1, "One"));

        // Test traversal between 3 and 8
        int traversedNodes = this.bstTree.traverse(3, 8);
        assertEquals(12, traversedNodes); // Nodes traversed: 3, 6, 8, 2, 7
    }


    /**
     * Tests the height of an empty tree and verifies the correct printed
     * output.
     */
    public void testEmptyHeight() {
        this.bstTree.remove(5);
        this.bstTree.print();

        String actualStringOutput = systemOut().getHistory();
        String printedStringOutput = "This tree is empty";

        assertFuzzyEquals(actualStringOutput, printedStringOutput);

    }


    /**
     * Tests the height of the BST by printing its structure.
     * Verifies that the correct tree structure is printed.
     */
    public void testgetHeight() {
        this.bstTree.insert(new KVPair<>(6, "Six"));
        this.bstTree.insert(new KVPair<>(3, "Three"));
        this.bstTree.insert(new KVPair<>(9, "Nine"));

        this.bstTree.print();
        String treeStructure = systemOut().getHistory();
        String printedTreeStructre = "    (null)\r\n" + "        \\\r\n"
            + "        (3)\r\n" + "        /\r\n" + "    (null)\r\n"
            + "            \\\r\n" + "            (5)\r\n" + "            /\r\n"
            + "    (null)\r\n" + "        \\\r\n" + "        (6)\r\n"
            + "        /\r\n" + "(null)\r\n" + "    \\\r\n" + "    (9)\r\n"
            + "    /\r\n" + "(null)\r\n" + "Number of records: 4";

        assertFuzzyEquals(treeStructure, printedTreeStructre);

    }


    /**
     * Tests the clearing of the BST, ensuring all nodes are removed and the
     * size is reset.
     */
    public void testClear() {
        this.bstTree.insert(new KVPair<>(4, "Four"));
        this.bstTree.insert(new KVPair<>(6, "Six"));
        this.bstTree.insert(new KVPair<>(7, "Seven"));

        assertEquals(this.bstTree.size(), 4);

        this.bstTree.clear();

        assertEquals(this.bstTree.size(), 0);
        assertEquals(this.bstTree.find(5), null);
    }

}
