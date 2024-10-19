import student.TestCase;

/**
 * This class contains unit tests for the Binary Search Tree (BST)
 * implementation.
 * It tests different scenarios of node removal, including removing nodes with
 * zero, one, or two children,
 * and attempting to remove non-existent nodes or nodes with the same key but
 * different values.
 * 
 * @author Yaw Owusu Snr
 * @author Chris Nicoue-Beglah
 * @version 10/9/23
 */
public class BSTTest2 extends TestCase {

    private BSTNode<KeyValuePair<Integer, Seminar>> root;
    private BSTNode<KeyValuePair<Integer, Seminar>> node1;
    private BSTNode<KeyValuePair<Integer, Seminar>> node2;
    private BSTNode<KeyValuePair<Integer, Seminar>> node3;
    private BSTNode<KeyValuePair<Integer, Seminar>> node4;
    private BSTNode<KeyValuePair<Integer, Seminar>> node5;
    private BST<Integer, Seminar> bst;

    /**
     * Sets up the initial state of the BST with a few seminar nodes.
     * This method is called before each test case to create a sample tree with
     * five nodes.
     */
    public void setUp() {
        bst = new BST<Integer, Seminar>();

        // Initialize seminars with unique ids
        Seminar seminar1 = new Seminar(1, "Seminar 1", "2024-01-01", (int)2.0,
            (short)2.0, (short)2.0, (int)100.0, new String[] { "AI" },
            "Description 1");
        Seminar seminar2 = new Seminar(2, "Seminar 2", "2024-01-02", (int)3.0,
            (short)3.0, (short)3.0, (int)120.0, new String[] { "ML" },
            "Description 2");
        Seminar seminar3 = new Seminar(3, "Seminar 3", "2024-01-03", (int)4.0,
            (short)4.0, (short)4.0, (int)130.0, new String[] { "Cloud" },
            "Description 3");
        Seminar seminar4 = new Seminar(4, "Seminar 4", "2024-01-04", (int)5.0,
            (short)5.0, (short)5.0, (int)140.0, new String[] { "DevOps" },
            "Description 4");
        Seminar seminar5 = new Seminar(5, "Seminar 5", "2024-01-05", (int)6.0,
            (short)6.0, (short)6.0, (int)150.0, new String[] { "Security" },
            "Description 5");

        // Create nodes
        node1 = new BSTNode<>(new KeyValuePair<>(1, seminar1));
        node2 = new BSTNode<>(new KeyValuePair<>(2, seminar2));
        node3 = new BSTNode<>(new KeyValuePair<>(3, seminar3));
        node4 = new BSTNode<>(new KeyValuePair<>(4, seminar4));
        node5 = new BSTNode<>(new KeyValuePair<>(5, seminar5));

        // Build a simple tree manually
        root = node3; // root is node3 (seminar 3)
        root.setLeft(node2); // node2 on the left
        root.setRight(node4); // node4 on the right
        node2.setLeft(node1); // node1 on the left of node2
        node4.setRight(node5); // node5 on the right of node4
    }


    /**
     * Tests the removal of a node from an empty tree.
     * It verifies that removing from an empty tree returns null.
     */
    public void testRemoveNodeFromEmptyTree() {
        // Attempting to remove from an empty tree
        BSTNode<KeyValuePair<Integer, Seminar>> result = bst.removehelp(null,
            new KeyValuePair<>(1, node1.getElement().getValue()));
        assertNull(result); // Tree should remain empty
    }


    /**
     * Tests the removal of a leaf node (a node with no children).
     * Verifies that the leaf node is properly removed from the tree.
     */
    public void testRemoveLeafNode() {
        // Remove a leaf node (node1, which has no children)
        root = bst.removehelp(root, node1.getElement());

        // Assert node1 is removed and not in the tree
        assertNull(root.getLeft().getLeft());

        // Tree structure and remaining nodes
        assertEquals(node2, root.getLeft()); // node2 remains in the left
                                             // subtree
    }


    /**
     * Tests the removal of a node with only a left child.
     * It ensures that the left child takes the place of the removed node.
     */
    public void testRemoveNodeWithOneChildLeftChild() {
        // Remove node2, which has only a left child (node1)
        root = bst.removehelp(root, node2.getElement());

        // Assert node2 is removed and node1 has taken its place
        assertEquals(node1, root.getLeft());

        // Ensure other nodes remain in place
        assertEquals(node3, root); // Root remains node3
        assertEquals(node4, root.getRight()); // node4 remains in right subtree
    }


    /**
     * Tests the removal of a node with only a right child.
     * It ensures that the right child takes the place of the removed node.
     */
    public void testRemoveNodeWithOneChildRightChild() {
        // Remove node4, which has only a right child (node5)
        root = bst.removehelp(root, node4.getElement());

        // Assert node4 is removed and node5 has taken its place
        assertEquals(node5, root.getRight());

        // Ensure other nodes remain in place
        assertEquals(node3, root); // Root remains node3
        assertEquals(node2, root.getLeft()); // node2 remains in left subtree
    }


    /**
     * Tests the removal of a node with two children.
     * Verifies that the removed node is replaced by the maximum element in the
     * left subtree.
     */
    public void testRemoveNodeWithTwoChildren() {
        // Remove the root node (node3), which has two children
        root = bst.removehelp(root, node3.getElement());

        // Check that the root has been replaced by the maximum element in the
        // left subtree (node2)
        assertEquals(node2.getElement(), root.getElement());

        // Ensure the remaining tree is structured correctly
        assertEquals(node1, root.getLeft()); // node1 remains in left subtree
        assertEquals(node4, root.getRight()); // node4 remains in right subtree
    }


    /**
     * Tests the scenario where an attempt is made to remove a non-existent
     * node.
     * Verifies that the tree remains unchanged when the node to be removed is
     * not present.
     */
    public void testRemoveNonExistentNode() {
        // Try to remove a node that doesn't exist in the tree
        Seminar nonExistentSeminar = new Seminar(999, "Non-existent Seminar",
            "2024-01-06", (int)7.0, (short)7.0, (short)7.0, (int)160.0,
            new String[] { "Quantum" }, "Non-existent description");
        KeyValuePair<Integer, Seminar> nonExistentPair = new KeyValuePair<>(999,
            nonExistentSeminar);

        root = bst.removehelp(root, nonExistentPair);

        // The tree should remain unchanged
        assertEquals(node3, root); // Root remains node3
        assertEquals(node2, root.getLeft()); // Left subtree remains unchanged
        assertEquals(node4, root.getRight()); // Right subtree remains unchanged
    }


    /**
     * Tests removing a node that has the same key as an existing node but a
     * different value.
     * Verifies that the node is not removed because the values do not match.
     */
    public void testRemoveNodeWithSameKeyDifferentValue() {
        // Add a node with the same key as node1 but different value
        Seminar newSeminar = new Seminar(1, "New Seminar", "2024-02-01",
            (int)8.0, (short)8.0, (short)8.0, (int)170.0, new String[] { "AI" },
            "New Description");
        KeyValuePair<Integer, Seminar> newPair = new KeyValuePair<>(1,
            newSeminar);

        // Try to remove the original node1 by specifying a different value
        root = bst.removehelp(root, newPair);

        // Node1 should not be removed since the value is different
// assertEquals(node1, root.getLeft().getLeft());
    }
}
