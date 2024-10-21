import student.TestCase;

/**
 * This class is designed to test Bintree functionality.
 * 
 * @author Kwame Asare
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
    private Bintree nullTree;
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

        eventA = new Seminar(1, "Seminar Alpha", "2023-11-10", 40, (short)15,
            (short)25, 80, new String[] { "Tech" }, "A tech-related event.");
        eventB = new Seminar(2, "Seminar Bravo", "2024-03-22", 55, (short)45,
            (short)75, 120, new String[] { "Health" }, "A healthcare event.");
        eventC = new Seminar(3, "Seminar Charlie", "2025-05-13", 25, (short)20,
            (short)30, 100, new String[] { "Education" },
            "An educational seminar.");
        eventD = new Seminar(4, "Seminar Delta", "2023-12-05", 60, (short)85,
            (short)95, 140, new String[] { "Business" },
            "A business-related event.");
        eventE = new Seminar(5, "Seminar Echo", "2021-07-08", 50, (short)60,
            (short)70, 110, new String[] { "Science" }, "A scientific event.");

        nullTree = new Bintree(100);
        smallTree = new Bintree(50);
        secondaryTree = new Bintree(200);

        evt1 = new Seminar(1, "Sample", "202301012000", 50, (short)5, (short)10,
            35, new String[] { "tag" }, "Description 1");
        evt2 = new Seminar(2, "Sample", "202207161000", 40, (short)10, (short)5,
            30, new String[] { "keyword" }, "Description 2");
        evt3 = new Seminar(3, "Sample", "202101011200", 25, (short)40,
            (short)15, 25, new String[] { "education" }, "Description 3");
        evt4 = new Seminar(4, "Sample", "201908071000", 35, (short)0, (short)5,
            20, new String[] { "business" }, "Description 4");
    }


    /**
     * Test searching for an event with a radius of zero.
     */
    public void testZeroRadiusSearch() {
        mainTree.insert(eventA);
        mainTree.insert(eventB);

        mainTree.search(eventA, 0.0);
    }


    /**
     * Test various search scenarios in the smallTree and secondaryTree.
     */
    public void testComplexSearch() {
        smallTree.insert(evt1);
        smallTree.insert(evt2);
        smallTree.insert(evt3);
        smallTree.insert(evt4);
        smallTree.insert(new Seminar(5, "Sample", "203201010930", 45, (short)0,
            (short)40, 30, new String[] { "tech" }, "Sample description"));
        smallTree.insert(new Seminar(6, "Sample", "202303110745", 35, (short)60,
            (short)0, 20, new String[] { "finance" }, "Another description"));

        secondaryTree.search(new Seminar(0, "", "", 0, (short)0, (short)0, 0,
            new String[] { "" }, ""), 0);

        smallTree.search(new Seminar(0, "", "", 0, (short)15, (short)5, 0,
            new String[] { "" }, ""), 0);

        smallTree.search(new Seminar(0, "", "", 0, (short)50, (short)20, 0,
            new String[] { "" }, ""), 10);
    }


    /**
     * Test the insertion of multiple events into mainTree.
     */
    public void testInsertSeminars() {
        mainTree.insert(eventA);
        mainTree.insert(eventB);
        mainTree.insert(eventC);
        mainTree.insert(eventD);
        mainTree.insert(eventE);

        mainTree.print();
    }


    /**
     * Test removing an event from mainTree.
     */
    public void testRemoveSeminar() {
        mainTree.insert(eventA);
        mainTree.insert(eventB);
        mainTree.insert(eventC);

        mainTree.remove(eventA);

        mainTree.print();
    }


    /**
     * Test getting the bounding box of the world.
     */
    public void testWorldBounds() {
        BoundingBox bounds = mainTree.getWorld();

        assertEquals(0.0, bounds.getxMin(), 0.001);
        assertEquals(0.0, bounds.getyMin(), 0.001);
        assertEquals(149.0, bounds.getxMax(), 0.001);
        assertEquals(149.0, bounds.getyMax(), 0.001);
    }


    /**
     * Test a recursive search hitting boundary conditions in mainTree.
     */
    public void testRecursiveSearch() {
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
     * Test Bintree restructuring after a node removal, ensuring proper
     * reorganization of the tree structure.
     */
    public void testTreeRestructuring() {
        Seminar leftSeminar = new Seminar(1, "Left Seminar", "2024-07-15", 40,
            (short)15, (short)30, 60, new String[] { "category" },
            "Details about left event.");
        Seminar midSeminar = new Seminar(2, "Middle Seminar", "2022-03-21", 35,
            (short)50, (short)30, 50, new String[] { "category" },
            "Details about middle event.");
        Seminar rightSeminar = new Seminar(3, "Right Seminar", "2019-09-01", 55,
            (short)80, (short)30, 70, new String[] { "category" },
            "Details about right event.");

        mainTree.insert(leftSeminar);
        mainTree.insert(midSeminar);
        mainTree.insert(rightSeminar);

        mainTree.remove(midSeminar);

        mainTree.print();

        Seminar newMid = new Seminar(4, "New Middle", "2021-05-09", 45,
            (short)50, (short)30, 65, new String[] { "category" },
            "Details about new middle event.");
        mainTree.insert(newMid);

        mainTree.print();
    }
}