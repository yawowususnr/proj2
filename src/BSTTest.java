import student.TestCase;

/**
 * This class tests the functionality of the Binary Search Tree (BST)
 * implementation.
 * 
 * @author Yaw Agyemang	
 * @author Yaw Owusu Jnr
 * @version 10/18/24
 */
public class BSTTest extends TestCase {

    private BST<Integer, String> bstTree;

    /**
     * Creates a new instance of a BST
     */
    public void setUp() {
        this.bstTree = new BST<>();
        KVPair<Integer, String> root = new KVPair<>(10, "Ten");
        this.bstTree.insert(root);

        assertEquals(1, bstTree.size());
        assertEquals(root, bstTree.find(10));
    }

    /**
     * Test insert method of the BST class
     */
    public void testInsert() {
        this.bstTree.insert(new KVPair<>(9, "Nine"));
        this.bstTree.insert(new KVPair<>(11, "Eleven"));
        this.bstTree.insert(new KVPair<>(12, "Twelve"));

        assertEquals(4, this.bstTree.size());
        assertNull(this.bstTree.find(20));

        KVPair<Integer, String> kvp = this.bstTree.find(9);
        assertNotNull(kvp);
        assertEquals(kvp.getValue(), "Nine");

        KVPair<Integer, String> kvp2 = this.bstTree.find(11);
        assertNotNull(kvp2);
        assertEquals(kvp2.getValue(), "Eleven");
    }

    /**
     * Test remove method of the BST class
     */
    public void testRemoveSingle() {
        KVPair<Integer, String> nullNode = this.bstTree.remove(100);
        assertNull(nullNode);

        this.bstTree.insert(new KVPair<>(12, "Twelve"));
        KVPair<Integer, String> removedNode = this.bstTree.remove(12);
        assertNotNull(removedNode);
        assertEquals(removedNode.getValue(), "Twelve");
        assertEquals(1, this.bstTree.size());
        assertEquals(this.bstTree.find(12), null);
    }

    /**
     * Test remove method of the BST class for edge cases
     */
    public void testRemoveComplex() {
        this.bstTree.remove(10);
        assertNull(this.bstTree.remove(10));

        this.bstTree.insert(new KVPair<>(11, "Eleven"));
        this.bstTree.insert(new KVPair<>(8, "Eight"));
        this.bstTree.insert(new KVPair<>(12, "Twelve"));
        this.bstTree.insert(new KVPair<>(7, "Seven"));
        this.bstTree.insert(new KVPair<>(10, "Ten"));
        this.bstTree.insert(new KVPair<>(13, "Thirteen"));
        this.bstTree.insert(new KVPair<>(15, "Fifteen"));
        this.bstTree.insert(new KVPair<>(6, "Six"));

        assertEquals(8, this.bstTree.size());

        this.bstTree.remove(12);
        assertEquals(7, this.bstTree.size());

        this.bstTree.remove(10);
        assertEquals(6, this.bstTree.size());

        this.bstTree.remove(8);
        assertEquals(5, this.bstTree.size());

        this.bstTree.remove(11);
        assertEquals(4, this.bstTree.size());

        assertNull(this.bstTree.remove(9));
    }

    /**
     * Test traverse method of BST class
     */
    public void testTraverseNodes() {
        this.bstTree.insert(new KVPair<>(11, "Eleven"));
        this.bstTree.insert(new KVPair<>(8, "Eight"));
        this.bstTree.insert(new KVPair<>(12, "Twelve"));
        this.bstTree.insert(new KVPair<>(7, "Seven"));
        this.bstTree.insert(new KVPair<>(10, "Ten"));
        this.bstTree.insert(new KVPair<>(13, "Thirteen"));
        this.bstTree.insert(new KVPair<>(15, "Fifteen"));
        this.bstTree.insert(new KVPair<>(6, "Six"));

        int traversedNodes = this.bstTree.traverse(8, 12);
        assertEquals(11, traversedNodes); 
    }

    /**
     * Test getHeight method of the BST class
     */
    public void testHeightEmpty() {
        this.bstTree.remove(10);
        this.bstTree.print();

        String actualStringOutput = systemOut().getHistory();
        String printedStringOutput = "This tree is empty";
        assertFuzzyEquals(actualStringOutput, printedStringOutput);
    }

    /**
     * test print method of the BST class
     */
    public void testHeightStructure() {
        this.bstTree.insert(new KVPair<>(11, "Eleven"));
        this.bstTree.insert(new KVPair<>(8, "Eight"));
        this.bstTree.insert(new KVPair<>(12, "Twelve"));

        this.bstTree.print();

    }

    /**
     * Test clear method of the BST class
     */
    public void testClearTree() {
        this.bstTree.insert(new KVPair<>(9, "Nine"));
        this.bstTree.insert(new KVPair<>(11, "Eleven"));
        this.bstTree.insert(new KVPair<>(12, "Twelve"));

        assertEquals(this.bstTree.size(), 4);
        this.bstTree.clear();

        assertEquals(this.bstTree.size(), 0);
        assertEquals(this.bstTree.find(10), null);
    }
}
