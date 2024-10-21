import student.TestCase;

/**
 * This class is designed to test Bintree functionality.
 * 
 * @author Yaw Agyemang
 * @author Yaw Owusu Jnr
 * @version 10/20/24
 */
public class BintreeTest extends TestCase {
	private Bintree mainTree;
	private Seminar eventA;
	private Seminar eventB;
	private Seminar eventC;
	private Seminar eventD;
	private Seminar eventE;
	private Bintree smallTree;
	private Bintree secondaryTree;
	private Seminar evt1;
	private Seminar evt2;
	private Seminar evt3;
	private Seminar evt4;

	/**
	 * Initializes the test environment with Bintree and Seminar objects.
	 */
	public void setUp() {
		mainTree = new Bintree(150);

		eventA = new Seminar(1, "Tech Innovations", "2023-11-10", 40, (short) 15, (short) 25, 80,
				new String[] { "Tech" }, "Exploring the latest in tech.");
		eventB = new Seminar(2, "Healthcare Trends", "2024-03-22", 55, (short) 45, (short) 75, 120,
				new String[] { "Health" }, "Current trends in healthcare.");
		eventC = new Seminar(3, "Educational Strategies", "2025-05-13", 25, (short) 20, (short) 30, 100,
				new String[] { "Education" }, "Strategies for effective learning.");
		eventD = new Seminar(4, "Business Development", "2023-12-05", 60, (short) 85, (short) 95, 140,
				new String[] { "Business" }, "Key skills for business growth.");
		eventE = new Seminar(5, "Scientific Discoveries", "2021-07-08", 50, (short) 60, (short) 70, 110,
				new String[] { "Science" }, "Recent advancements in science.");

		smallTree = new Bintree(50);
		secondaryTree = new Bintree(200);

		evt1 = new Seminar(1, "Sample Event 1", "202301012000", 50, (short) 5, (short) 10, 35, new String[] { "tag" },
				"Description for event 1.");
		evt2 = new Seminar(2, "Sample Event 2", "202207161000", 40, (short) 10, (short) 5, 30,
				new String[] { "keyword" }, "Description for event 2.");
		evt3 = new Seminar(3, "Sample Event 3", "202101011200", 25, (short) 40, (short) 15, 25,
				new String[] { "education" }, "Description for event 3.");
		evt4 = new Seminar(4, "Sample Event 4", "201908071000", 35, (short) 0, (short) 5, 20,
				new String[] { "business" }, "Description for event 4.");
	}

	/**
	 * Tests the insertion of multiple Seminar objects into the main Bintree. This
	 * verifies that the seminars are correctly added to the tree structure.
	 */
	public void testAddSeminars() {
		mainTree.insert(eventA);
		mainTree.insert(eventB);
		mainTree.insert(eventC);
		mainTree.insert(eventD);
		mainTree.insert(eventE);

		mainTree.print();
	}

	/**
	 * Tests searching for a specific Seminar using a search radius of zero. This
	 * checks if the tree can locate an exact match when no radius is provided.
	 */
	public void testEventSearchWithZeroRadius() {
		mainTree.insert(eventA);
		mainTree.insert(eventB);

		mainTree.search(eventA, 0.0);
	}

	/**
	 * Tests the removal of a Seminar object from the main Bintree. This checks if
	 * the tree correctly removes the seminar and updates its structure.
	 */
	public void testDeleteSeminar() {
		mainTree.insert(eventA);
		mainTree.insert(eventB);
		mainTree.insert(eventC);

		mainTree.remove(eventA);

		mainTree.print();
	}

	/**
	 * Tests various search scenarios in the smallTree and secondaryTree. This
	 * ensures that the search function works correctly under different conditions.
	 */
	public void testDiverseSearch() {
		smallTree.insert(evt1);
		smallTree.insert(evt2);
		smallTree.insert(evt3);
		smallTree.insert(evt4);
		smallTree.insert(new Seminar(5, "Sample Event 5", "203201010930", 45, (short) 0, (short) 40, 30,
				new String[] { "tech" }, "Additional description."));
		smallTree.insert(new Seminar(6, "Sample Event 6", "202303110745", 35, (short) 60, (short) 0, 20,
				new String[] { "finance" }, "Another sample description."));

		secondaryTree.search(new Seminar(0, "", "", 0, (short) 0, (short) 0, 0, new String[] { "" }, ""), 0);

		smallTree.search(new Seminar(0, "", "", 0, (short) 15, (short) 5, 0, new String[] { "" }, ""), 0);

		smallTree.search(new Seminar(0, "", "", 0, (short) 50, (short) 20, 0, new String[] { "" }, ""), 10);
	}

	/**
	 * Tests the retrieval of the bounding box dimensions of the Bintree. This
	 * verifies that the world boundaries are correctly established.
	 */
	public void testBoundaryDimensions() {
		BoundingBox bounds = mainTree.getWorld();

		assertEquals(0.0, bounds.getxMin(), 0.001);
		assertEquals(0.0, bounds.getyMin(), 0.001);
		assertEquals(149.0, bounds.getxMax(), 0.001);
		assertEquals(149.0, bounds.getyMax(), 0.001);
	}

	/**
	 * Tests a recursive search in the main Bintree with various radius values. This
	 * checks the search functionality under different boundary conditions.
	 */
	public void testRecursiveSearchBoundary() {
		mainTree.insert(eventA);
		mainTree.insert(eventB);
		mainTree.insert(eventC);
		mainTree.insert(eventD);
		mainTree.insert(eventE);

		mainTree.search(eventA, 25.0);
		mainTree.search(eventB, 10.0);
		mainTree.search(eventC, 5.0);
	}

	/**
	 * Tests the restructuring of the Bintree after the removal of a node. This
	 * ensures that the tree maintains its structure and integrity post-removal.
	 */
	public void testRestructureAfterRemoval() {
		Seminar leftSeminar = new Seminar(1, "Left Event", "2024-07-15", 40, (short) 15, (short) 30, 60,
				new String[] { "category" }, "Details about the left event.");
		Seminar midSeminar = new Seminar(2, "Middle Event", "2022-03-21", 35, (short) 50, (short) 30, 50,
				new String[] { "category" }, "Details about the middle event.");
		Seminar rightSeminar = new Seminar(3, "Right Event", "2019-09-01", 55, (short) 80, (short) 30, 70,
				new String[] { "category" }, "Details about the right event.");

		mainTree.insert(leftSeminar);
		mainTree.insert(midSeminar);
		mainTree.insert(rightSeminar);

		mainTree.remove(midSeminar);

		mainTree.print();

		Seminar newMid = new Seminar(4, "New Middle Event", "2021-05-09", 45, (short) 50, (short) 30, 65,
				new String[] { "category" }, "Details about the new middle event.");
		mainTree.insert(newMid);

		mainTree.print();
	}
}
