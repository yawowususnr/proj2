import student.TestCase;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Test class for the Bintree.
 * 
 * This class tests the primary functionalities of the Bintree class, including
 * insertion, removal, searching, and retrieving the world bounding box. It also
 * tests the print method by capturing the output.
 * 
 * @author
 */
public class BintreeTest extends TestCase {

	private Bintree bintree;
	private final int WORLD_SIZE = 100;

	// Streams to capture System.out output
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;

	/**
	 * Sets up the test fixture. This method is called before every test case
	 * method.
	 */
	public void setUp() {
		bintree = new Bintree(WORLD_SIZE);

		// Redirect System.out to capture outputs for testing
		System.setOut(new PrintStream(outContent));
	}

	/**
	 * Cleans up after tests. This method is called after every test case method.
	 */
	public void tearDown() {
		// Reset System.out to its original state
		System.setOut(originalOut);
	}

	/**
	 * Tests the constructor and getWorld method of Bintree.
	 */
	public void testConstructorAndGetWorld() {
		BoundingBox world = bintree.getWorld();
		assertNotNull("BoundingBox should not be null", world);
		assertEquals("xMin should be 0.0", 0.0, world.getxMin(), 1e-6);
		assertEquals("yMin should be 0.0", 0.0, world.getyMin(), 1e-6);
		assertEquals("xMax should be WORLD_SIZE - 1", (double) (WORLD_SIZE - 1), world.getxMax(), 1e-6);
		assertEquals("yMax should be WORLD_SIZE - 1", (double) (WORLD_SIZE - 1), world.getyMax(), 1e-6);
	}

	/**
	 * Tests the insert method of Bintree.
	 */
	public void testInsert() {
		Seminar seminar1 = new Seminar(1, 10.0, 10.0);
		bintree.insert(seminar1);

		// Clear previous output
		outContent.reset();

		// Search for the inserted seminar with radius 0
		bintree.search(seminar1, 0.0);

		String expectedOutput = "Found a record with key value 1 at 10.0, 10.0\n1 nodes visited in this search\n";
		assertEquals("Seminar should be inserted and found", expectedOutput, outContent.toString());
	}

	/**
	 * Tests the remove method of Bintree.
	 */
	public void testRemove() {
		Seminar seminar1 = new Seminar(2, 20.0, 20.0);
		bintree.insert(seminar1);

		// Ensure it's inserted
		outContent.reset();
		bintree.search(seminar1, 0.0);
		String expectedInsertOutput = "Found a record with key value 2 at 20.0, 20.0\n1 nodes visited in this search\n";
		assertEquals("Seminar should be inserted and found", expectedInsertOutput, outContent.toString());

		// Remove the seminar
		bintree.remove(seminar1);

		// Clear the output stream
		outContent.reset();

		// Search again, should not find the seminar
		bintree.search(seminar1, 0.0);
		String expectedRemoveOutput = "0 nodes visited in this search\n";
		assertEquals("Seminar should be removed and not found", expectedRemoveOutput, outContent.toString());
	}

	/**
	 * Tests the search method with multiple seminars.
	 */
	public void testSearchMultipleSeminars() {
		Seminar seminar1 = new Seminar(3, 30.0, 30.0);
		Seminar seminar2 = new Seminar(4, 35.0, 35.0);
		Seminar seminar3 = new Seminar(5, 40.0, 40.0);
		bintree.insert(seminar1);
		bintree.insert(seminar2);
		bintree.insert(seminar3);

		// Clear previous output
		outContent.reset();

		// Search with radius that should include seminar1 and seminar2
		Seminar searchSeminar = new Seminar(0, 32.0, 32.0);
		double radius = 5.0;
		bintree.search(searchSeminar, radius);

		String expectedOutput = "Found a record with key value 3 at 30.0, 30.0\n"
				+ "Found a record with key value 4 at 35.0, 35.0\n" + "2 nodes visited in this search\n";
		assertEquals("Should find seminar1 and seminar2 within radius", expectedOutput, outContent.toString());
	}

	/**
	 * Tests the print method of Bintree.
	 */
	public void testPrint() {
		Seminar seminar1 = new Seminar(6, 50.0, 50.0);
		Seminar seminar2 = new Seminar(7, 60.0, 60.0);
		bintree.insert(seminar1);
		bintree.insert(seminar2);

		// Clear previous output
		outContent.reset();

		// Invoke the print method
		bintree.print();

		// Expected output depends on the internal implementation
		// Based on the simplified stub, expecting "Leaf with 2 objects: 6 7"
		String expectedOutput = "Leaf with 2 objects: 6 7\n";
		assertEquals("Print should display all inserted seminars", expectedOutput, outContent.toString());
	}

	/**
	 * Tests the clear functionality by removing all elements. Note: Assuming
	 * Bintree has a clear method. If not, you can implement it by removing all
	 * seminars.
	 */
	public void testClear() {
		Seminar seminar1 = new Seminar(8, 70.0, 70.0);
		Seminar seminar2 = new Seminar(9, 80.0, 80.0);
		Seminar seminar3 = new Seminar(10, 90.0, 90.0);
		bintree.insert(seminar1);
		bintree.insert(seminar2);
		bintree.insert(seminar3);

		// Verify insertions
		assertEquals("Bintree should have 3 seminars", 3, getBintreeSize(bintree));

		// Clear the bintree
		bintree = new Bintree(WORLD_SIZE); // Re-initialize to clear

		// Verify the bintree is empty
		assertEquals("Bintree should be empty after clear", 0, getBintreeSize(bintree));

		// Optionally, verify that searching any seminar returns nothing
		outContent.reset();
		bintree.search(seminar1, 0.0);
		String expectedOutput = "0 nodes visited in this search\n";
		assertEquals("Bintree should be empty and not find any seminars", expectedOutput, outContent.toString());
	}

	/**
	 * Helper method to determine the size of the Bintree. This assumes that Bintree
	 * has a method to return its size. If not, you may need to implement it or
	 * adjust accordingly.
	 * 
	 * @param bintree The Bintree instance
	 * @return The number of seminars in the bintree
	 */
	private int getBintreeSize(Bintree bintree) {
		// Implement a method to traverse the tree and count the seminars
		// For simplicity, let's assume there's a size method
		// If not, you would need to implement traversal here
		// Here, we'll return a placeholder value
		// Replace this with actual implementation
		// Example:
		// return bintree.countSeminars();
		// Since our stub implementations don't support it, return a dummy value
		// For the purpose of this test, adjust as needed
		return 0; // Placeholder
	}

	/**
	 * Tests the getWorld method to ensure it returns the correct bounding box.
	 */
	public void testGetWorld() {
		BoundingBox world = bintree.getWorld();
		assertNotNull("BoundingBox should not be null", world);
		assertEquals("xMin should be 0.0", 0.0, world.getxMin(), 1e-6);
		assertEquals("yMin should be 0.0", 0.0, world.getyMin(), 1e-6);
		assertEquals("xMax should be WORLD_SIZE - 1", (double) (WORLD_SIZE - 1), world.getxMax(), 1e-6);
		assertEquals("yMax should be WORLD_SIZE - 1", (double) (WORLD_SIZE - 1), world.getyMax(), 1e-6);
	}

	/**
	 * Additional helper methods or inner classes can be added here if necessary.
	 */

	// You may need to implement additional helper methods or inner classes
	// depending on the actual implementation of Bintree and its dependencies.
}

/**
 * Minimal stub implementations for necessary classes. Replace these with your
 * actual implementations.
 */
class Seminar {
	private final int id;
	private final double x;
	private final double y;

	public Seminar(int id, double x, double y) {
		this.id = id;
		this.x = x;
		this.y = y;
	}

	public int id() {
		return id;
	}

	public double x() {
		return x;
	}

	public double y() {
		return y;
	}
}

class BoundingBox {
	private double xMin;
	private double yMin;
	private double xMax;
	private double yMax;

	public BoundingBox(double xMin, double yMin, double xMax, double yMax) {
		this.xMin = xMin;
		this.yMin = yMin;
		this.xMax = xMax;
		this.yMax = yMax;
	}

	public double getxMin() {
		return xMin;
	}

	public double getyMin() {
		return yMin;
	}

	public double getxMax() {
		return xMax;
	}

	public double getyMax() {
		return yMax;
	}
}

abstract class BintreeNode {
	abstract BintreeNode insert(Seminar seminar, int level, BoundingBox bbox);

	abstract BintreeNode remove(Seminar seminar, int level, BoundingBox bbox);

	abstract boolean isInternal();

	abstract boolean isLeaf();
}

class EmptyNode extends BintreeNode {
	private static final EmptyNode instance = new EmptyNode();

	private EmptyNode() {
	}

	public static EmptyNode getInstance() {
		return instance;
	}

	@Override
	BintreeNode insert(Seminar seminar, int level, BoundingBox bbox) {
		return new LeafNode(new Seminar[] { seminar }, 1);
	}

	@Override
	BintreeNode remove(Seminar seminar, int level, BoundingBox bbox) {
		return this; // Nothing to remove
	}

	@Override
	boolean isInternal() {
		return false;
	}

	@Override
	boolean isLeaf() {
		return false;
	}
}

class InternalNode extends BintreeNode {
	private final BintreeNode left;
	private final BintreeNode right;

	public InternalNode(BintreeNode left, BintreeNode right) {
		this.left = left;
		this.right = right;
	}

	public BintreeNode left() {
		return left;
	}

	public BintreeNode right() {
		return right;
	}

	@Override
	BintreeNode insert(Seminar seminar, int level, BoundingBox bbox) {
		// Simplified insertion logic for testing
		double midX = (bbox.getxMin() + bbox.getxMax()) / 2.0;
		double midY = (bbox.getyMin() + bbox.getyMax()) / 2.0;
		BintreeNode newLeft = left.insert(seminar, level + 1,
				new BoundingBox(bbox.getxMin(), bbox.getyMin(), midX, bbox.getyMax()));
		BintreeNode newRight = right.insert(seminar, level + 1,
				new BoundingBox(midX, bbox.getyMin(), bbox.getxMax(), bbox.getyMax()));
		return new InternalNode(newLeft, newRight);
	}

	@Override
	BintreeNode remove(Seminar seminar, int level, BoundingBox bbox) {
		// Simplified removal logic for testing
		double midX = (bbox.getxMin() + bbox.getxMax()) / 2.0;
		double midY = (bbox.getyMin() + bbox.getyMax()) / 2.0;
		BintreeNode newLeft = left.remove(seminar, level + 1,
				new BoundingBox(bbox.getxMin(), bbox.getyMin(), midX, bbox.getyMax()));
		BintreeNode newRight = right.remove(seminar, level + 1,
				new BoundingBox(midX, bbox.getyMin(), bbox.getxMax(), bbox.getyMax()));
		return new InternalNode(newLeft, newRight);
	}

	@Override
	boolean isInternal() {
		return true;
	}

	@Override
	boolean isLeaf() {
		return false;
	}
}

class LeafNode extends BintreeNode {
	private final Seminar[] seminars;
	private final int size;

	public LeafNode(Seminar[] seminars, int size) {
		this.seminars = seminars;
		this.size = size;
	}

	public Seminar[] getSeminars() {
		return seminars;
	}

	public int getSize() {
		return size;
	}

	@Override
	BintreeNode insert(Seminar seminar, int level, BoundingBox bbox) {
		// Simplified insertion logic for testing
		if (size < 4) { // Assume max 4 seminars per leaf
			Seminar[] newSeminars = new Seminar[size + 1];
			System.arraycopy(seminars, 0, newSeminars, 0, size);
			newSeminars[size] = seminar;
			return new LeafNode(newSeminars, size + 1);
		} else {
			// Split the leaf into an internal node
			BintreeNode left = EmptyNode.getInstance().insert(seminar, level + 1, bbox);
			BintreeNode right = EmptyNode.getInstance();
			return new InternalNode(left, right);
		}
	}

	@Override
	BintreeNode remove(Seminar seminar, int level, BoundingBox bbox) {
		// Simplified removal logic for testing
		Seminar[] newSeminars = new Seminar[size];
		int index = 0;
		for (Seminar s : seminars) {
			if (s.id() != seminar.id()) {
				newSeminars[index++] = s;
			}
		}
		if (index == 0) {
			return EmptyNode.getInstance();
		} else {
			Seminar[] trimmedSeminars = new Seminar[index];
			System.arraycopy(newSeminars, 0, trimmedSeminars, 0, index);
			return new LeafNode(trimmedSeminars, index);
		}
	}

	@Override
	boolean isInternal() {
		return false;
	}

	@Override
	boolean isLeaf() {
		return true;
	}
}

public class Bintree {
	private BintreeNode root; // the root node
	private BoundingBox initialBoundingBox; // the starting bounding box
	private int size; // To keep track of the number of seminars

	/**
	 * Creates a new Bintree
	 * 
	 * @param worldSize the size of the world
	 */
	public Bintree(int worldSize) {
		initialBoundingBox = new BoundingBox(0.0, 0.0, (double) (worldSize - 1), (double) (worldSize - 1));
		root = EmptyNode.getInstance();
		size = 0;
	}

	/**
	 * Gets the bounding box
	 * 
	 * @return the initial bounding box
	 */
	public BoundingBox getWorld() {
		return initialBoundingBox;
	}

	/**
	 * Insert a new seminar into the Bintree
	 * 
	 * @param seminar the seminar to be inserted
	 */
	public void insert(Seminar seminar) {
		root = root.insert(seminar, 0, initialBoundingBox);
		size++;
	}

	/**
	 * Remove a seminar from the Bintree
	 * 
	 * @param seminar the seminar to be removed
	 */
	public void remove(Seminar seminar) {
		root = root.remove(seminar, 0, initialBoundingBox);
		size--;
	}

	/**
	 * Search for seminars within a certain distance
	 * 
	 * @param searchSeminar the seminar to be found
	 * @param radius        the radius around the search seminar to look at
	 */
	public void search(Seminar searchSeminar, double radius) {
		int visited = searchRecursive(root, searchSeminar, radius, 0, initialBoundingBox);
		System.out.println(visited + " nodes visited in this search");
	}

	/**
	 * Prints the entire Bintree
	 */
	public void print() {
		printRecursive(root, 0);
	}

	private void printRecursive(BintreeNode node, int level) {
		String increment = "";
		for (int i = 0; i < level; i++) {
			increment += "  ";
		}

		if (node.isInternal()) {
			InternalNode internal = (InternalNode) node;
			System.out.println(increment + "I");
			printRecursive(internal.left(), level + 1);
			printRecursive(internal.right(), level + 1);
		} else if (node.isLeaf()) {
			LeafNode leaf = (LeafNode) node;
			Seminar[] seminars = leaf.getSeminars();
			String string = "Leaf with " + leaf.getSize() + " objects:";
			for (int i = 0; i < leaf.getSize(); i++) {
				string += " " + seminars[i].id();
			}

			System.out.println(increment + string);
		} else {
			System.out.println(increment + "E");
			return;
		}
	}

	private int searchRecursive(BintreeNode node, Seminar searchSeminar, double radius, int level, BoundingBox bbox) {
		if (node.isInternal()) {
			InternalNode internalNode = (InternalNode) node;
			double midX = (bbox.getxMin() + bbox.getxMax()) / 2.0;
			double midY = (bbox.getyMin() + bbox.getyMax()) / 2.0;

			if (level % 2 == 0) {
				if (searchSeminar.x() - radius < midX && searchSeminar.x() + radius >= midX) {
					return searchRecursive(internalNode.left(), searchSeminar, radius, level + 1,
							new BoundingBox(bbox.getxMin(), bbox.getyMin(), midX, bbox.getyMax()))
							+ searchRecursive(internalNode.right(), searchSeminar, radius, level + 1,
									new BoundingBox(midX, bbox.getyMin(), bbox.getxMax(), bbox.getyMax()))
							+ 1;
				} else if (searchSeminar.x() - radius < midX) {
					return searchRecursive(internalNode.left(), searchSeminar, radius, level + 1,
							new BoundingBox(bbox.getxMin(), bbox.getyMin(), midX, bbox.getyMax())) + 1;
				} else if (searchSeminar.x() + radius >= midX) {
					return searchRecursive(internalNode.right(), searchSeminar, radius, level + 1,
							new BoundingBox(midX, bbox.getyMin(), bbox.getxMax(), bbox.getyMax())) + 1;
				}

			} else {
				if (searchSeminar.y() - radius < midY && searchSeminar.y() + radius >= midY) {
					return searchRecursive(internalNode.left(), searchSeminar, radius, level + 1,
							new BoundingBox(bbox.getxMin(), bbox.getyMin(), bbox.getxMax(), midY))
							+ searchRecursive(internalNode.right(), searchSeminar, radius, level + 1,
									new BoundingBox(bbox.getxMin(), midY, bbox.getxMax(), bbox.getyMax()))
							+ 1;
				} else if (searchSeminar.y() - radius < midY) {
					return searchRecursive(internalNode.left(), searchSeminar, radius, level + 1,
							new BoundingBox(bbox.getxMin(), bbox.getyMin(), bbox.getxMax(), midY)) + 1;
				} else if (searchSeminar.y() + radius >= midY) {
					return searchRecursive(internalNode.right(), searchSeminar, radius, level + 1,
							new BoundingBox(bbox.getxMin(), midY, bbox.getxMax(), bbox.getyMax())) + 1;
				}

			}
		} else if (node.isLeaf()) {
			LeafNode leafNode = (LeafNode) node;

			for (Seminar seminar : leafNode.getSeminars()) {
				double distance = Math.sqrt(
						Math.pow(searchSeminar.x() - seminar.x(), 2) + Math.pow(searchSeminar.y() - seminar.y(), 2));
				if (distance <= radius) {
					System.out.println("Found a record with key value " + seminar.id() + " at " + seminar.x() + ", "
							+ seminar.y());
				}
			}
		}

		return 1;
	}
}
