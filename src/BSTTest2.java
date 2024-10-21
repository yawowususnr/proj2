import student.TestCase;

/**
 * Unit tests for the Binary Search Tree (BST) implementation. Tests various
 * removal scenarios for nodes in the BST.
 * 
 * @author Yaw Agyemang
 * @author Yaw Owusu Jnr
 * @version 10/18/24
 */
public class BSTTest2 extends TestCase {

	private BSTNode<KVPair<Integer, Seminar>> root;
	private BSTNode<KVPair<Integer, Seminar>> node1;
	private BSTNode<KVPair<Integer, Seminar>> node2;
	private BSTNode<KVPair<Integer, Seminar>> node3;
	private BSTNode<KVPair<Integer, Seminar>> node4;
	private BSTNode<KVPair<Integer, Seminar>> node5;
	private BST<Integer, Seminar> bst;

	/**
	 * Initializes the BST with sample seminar nodes before each test. This setup
	 * allows the removal methods to be tested on a pre-defined tree structure.
	 */
	public void setUp() {
		bst = new BST<>();

		Seminar seminar1 = new Seminar(11, "Advanced AI", "2024-02-01", (int) 2.5, (short) 3.5, (short) 1.5,
				(int) 110.0, new String[] { "AI" }, "Advanced concepts in AI");
		Seminar seminar2 = new Seminar(12, "Machine Learning Basics", "2024-02-02", (int) 3.5, (short) 4.5, (short) 2.5,
				(int) 120.0, new String[] { "ML" }, "Introductory course on ML");
		Seminar seminar3 = new Seminar(13, "Cloud Computing", "2024-02-03", (int) 4.5, (short) 2.0, (short) 5.0,
				(int) 130.0, new String[] { "Cloud" }, "Understanding cloud infrastructure");
		Seminar seminar4 = new Seminar(14, "DevOps Practices", "2024-02-04", (int) 5.5, (short) 6.5, (short) 4.5,
				(int) 140.0, new String[] { "DevOps" }, "Essentials of DevOps");
		Seminar seminar5 = new Seminar(15, "Cybersecurity Essentials", "2024-02-05", (int) 6.5, (short) 5.5,
				(short) 6.5, (int) 150.0, new String[] { "Security" }, "Basics of cybersecurity");

		node1 = new BSTNode<>(new KVPair<>(11, seminar1));
		node2 = new BSTNode<>(new KVPair<>(12, seminar2));
		node3 = new BSTNode<>(new KVPair<>(13, seminar3));
		node4 = new BSTNode<>(new KVPair<>(14, seminar4));
		node5 = new BSTNode<>(new KVPair<>(15, seminar5));

		root = node3;
		root.setLeft(node2);
		root.setRight(node4);
		node2.setLeft(node1);
		node4.setRight(node5);
	}

	/**
	 * Test removing a leaf node (node1) from the tree. Verifies that node1 is
	 * removed correctly.
	 */
	public void testRemoveLeaf() {
		root = bst.removehelp(root, node1.getElement());

		assertNull(root.getLeft().getLeft());
		assertEquals(node2, root.getLeft());
	}

	/**
	 * Test removing a node (node2) that has only a left child (node1). Verifies
	 * that node1 takes node2's place.
	 */
	public void testRemoveOneChildLeft() {
		root = bst.removehelp(root, node2.getElement());

		assertEquals(node1, root.getLeft());
		assertEquals(node3, root);
		assertEquals(node4, root.getRight());
	}

	/**
	 * Test removing a node (node4) that has only a right child (node5). Verifies
	 * that node5 takes node4's place.
	 */
	public void testRemoveOneChildRight() {
		root = bst.removehelp(root, node4.getElement());

		assertEquals(node5, root.getRight());
		assertEquals(node3, root);
		assertEquals(node2, root.getLeft());
	}

	/**
	 * Test removing the root node (node3) that has two children. Verifies that it
	 * is replaced correctly.
	 */
	public void testRemoveTwoChildren() {
		root = bst.removehelp(root, node3.getElement());

		assertEquals(node2.getElement(), root.getElement());
		assertEquals(node1, root.getLeft());
		assertEquals(node4, root.getRight());
	}

	/**
	 * Test attempting to remove a non-existent node. Verifies that the tree remains
	 * unchanged.
	 */
	public void testRemoveNonExistent() {
		// Create a non-existent seminar.
		Seminar nonExistentSeminar = new Seminar(999, "Not Available Seminar", "2024-02-06", (int) 7.5, (short) 1.0,
				(short) 2.0, (int) 160.0, new String[] { "Quantum" }, "Details not found");
		KVPair<Integer, Seminar> nonExistentPair = new KVPair<>(999, nonExistentSeminar);

		root = bst.removehelp(root, nonExistentPair);

		assertEquals(node3, root);
	}

	/**
	 * Test removing a node with the same key but different value. Verifies that the
	 * original node remains in the tree.
	 */
	public void testRemoveSameKeyDifferentValue() {
		Seminar newSeminar = new Seminar(11, "New AI Seminar", "2024-02-07", (int) 8.0, (short) 8.0, (short) 8.0,
				(int) 170.0, new String[] { "AI" }, "Updated AI seminar description");
		KVPair<Integer, Seminar> newPair = new KVPair<>(11, newSeminar);

		root = bst.removehelp(root, newPair);


	}

	/**
	 * Test attempting to remove a node from an empty tree. Verifies that the result
	 * is null.
	 */
	public void testRemoveFromEmptyTree() {

		BSTNode<KVPair<Integer, Seminar>> result = bst.removehelp(null,
				new KVPair<>(11, node1.getElement().getValue()));
		assertNull(result);
	}
}
