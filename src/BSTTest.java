import student.TestCase;

public class BSTTest
    extends TestCase
{

    private BST<Integer, String> bstTree;

    public void setUp()
    {

        this.bstTree = new BST<>();
        KeyValuePair<Integer, String> root = new KeyValuePair<>(5, "Five");
        this.bstTree.insert(root);

        assertEquals(1, bstTree.size());
        assertEquals(root, bstTree.find(5));
    }
    
//    public void testEasyInsert() {
//        bstTree.remove(new KeyValuePair<>(5, "Five"));
//        assertEquals(0, bstTree.size());
//    }


    public void testInsertElement()
    {
        this.bstTree.insert(new KeyValuePair<>(4, "Four"));
        this.bstTree.insert(new KeyValuePair<>(6, "Six"));
        this.bstTree.insert(new KeyValuePair<>(7, "Seven"));

        assertEquals(4, this.bstTree.size());

        assertNull(this.bstTree.find(10));

        KeyValuePair<Integer, String> kvp = this.bstTree.find(4);

        assertNotNull(kvp);
        assertEquals(kvp.getValue(), "Four");

        KeyValuePair<Integer, String> kvp2 = this.bstTree.find(6);

        assertNotNull(kvp2);
        assertEquals(kvp2.getValue(), "Six");
    }


    public void testRemove()
    {
        KeyValuePair<Integer, String> nullNode = this.bstTree.remove(100);

        assertNull(nullNode);

        this.bstTree.insert(new KeyValuePair<>(7, "Seven"));

        KeyValuePair<Integer, String> removedNode = this.bstTree.remove(7);

        assertNotNull(removedNode);

        assertEquals(removedNode.getValue(), "Seven");
        assertEquals(1, this.bstTree.size());

        assertEquals(this.bstTree.find(7), null);

    }


    public void testRemove2()
    {

        this.bstTree.remove(5);
        assertNull(this.bstTree.remove(5));
        // Step 1: Insert nodes into the tree
        this.bstTree.insert(new KeyValuePair<>(6, "Six"));
        this.bstTree.insert(new KeyValuePair<>(3, "Three"));
        this.bstTree.insert(new KeyValuePair<>(9, "Nine"));
        this.bstTree.insert(new KeyValuePair<>(2, "Two"));
        this.bstTree.insert(new KeyValuePair<>(5, "Five"));
        this.bstTree.insert(new KeyValuePair<>(8, "Eight"));
        this.bstTree.insert(new KeyValuePair<>(10, "Ten"));
        this.bstTree.insert(new KeyValuePair<>(1, "One"));

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


    public void testTraverse()
    {
        // Insert nodes into the tree
        this.bstTree.insert(new KeyValuePair<>(6, "Six"));
        this.bstTree.insert(new KeyValuePair<>(3, "Three"));
        this.bstTree.insert(new KeyValuePair<>(9, "Nine"));
        this.bstTree.insert(new KeyValuePair<>(2, "Two"));
        this.bstTree.insert(new KeyValuePair<>(5, "Five"));
        this.bstTree.insert(new KeyValuePair<>(8, "Eight"));
        this.bstTree.insert(new KeyValuePair<>(10, "Ten"));
        this.bstTree.insert(new KeyValuePair<>(1, "One"));

        // Test traversal between 3 and 8
        int traversedNodes = this.bstTree.traverse(3, 8);
        assertEquals(12, traversedNodes);  // Nodes traversed: 3, 6, 8, 2, 7
    }


    public void testEmptyHeight()
    {
        this.bstTree.remove(5);
        this.bstTree.print();

        String actualStringOutput = systemOut().getHistory();
        String printedStringOutput = "This tree is empty";

        assertFuzzyEquals(actualStringOutput, printedStringOutput);

    }


    public void testgetHeight()
    {
        this.bstTree.insert(new KeyValuePair<>(6, "Six"));
        this.bstTree.insert(new KeyValuePair<>(3, "Three"));
        this.bstTree.insert(new KeyValuePair<>(9, "Nine"));

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


    public void testClear()
    {
        this.bstTree.insert(new KeyValuePair<>(4, "Four"));
        this.bstTree.insert(new KeyValuePair<>(6, "Six"));
        this.bstTree.insert(new KeyValuePair<>(7, "Seven"));

        assertEquals(this.bstTree.size(), 4);

        this.bstTree.clear();

        assertEquals(this.bstTree.size(), 0);
        assertEquals(this.bstTree.find(5), null);
    }

}
