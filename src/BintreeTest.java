import student.TestCase;

/**
 * test Binary tree clas
 * 
 * @author Yaw Owusu Snr
 * @author Chris Nicoue-Beglah
 * @version 10/9/23
 */
public class BintreeTest extends TestCase {
    private Bintree tree;
    private Seminar sem1;
    private Seminar sem2;
    private Seminar sem3;
    private Seminar sem4;
    private Seminar sem5;
    private Bintree emptyTree;
    private Bintree tinyTree; // small tree
    private Bintree tree2; // regular tree
    private Seminar se1; // seminar 1
    private Seminar se2; // seminar 2
    private Seminar se3; // seminar 3
    private Seminar se4; // seminar 4

    /**
     * Sets up the test environment by initializing the Bintree and some Seminar
     * objects.
     */
    public void setUp() {
        tree = new Bintree(100); // Creates a Bintree with a world size of 100

        // Seminar 1 at coordinates (10, 20)
        sem1 = new Seminar(1, "Sem one", "2024-10-19", 60, (short)10, (short)20,
            100, new String[] { "Virginia" }, "A Virginia sem.");

        // Seminar 2 at coordinates (50, 60)
        sem2 = new Seminar(2, "Sem Two", "2023-02-01", 45, (short)50, (short)60,
            150, new String[] { "Hair" }, "A hair seminar.");

        // Seminar 3 near the middle point of the bounding box at (25, 25)
        sem3 = new Seminar(3, "Sem Three", "2024-06-02", 30, (short)25,
            (short)25, 120, new String[] { "Science" }, "An Scieence seminar.");

        // Seminar 4 at coordinates (75, 80)
        sem4 = new Seminar(4, "Sem Four", "2024-08-21", 45, (short)75,
            (short)80, 130, new String[] { "Stock" }, "A stock seminar.");

        // Seminar 5 at coordinates (90, 90)
        sem5 = new Seminar(5, "Sem Five", "2022-04-08", 60, (short)90,
            (short)90, 110, new String[] { "Medicine" }, "A medicine seminar.");

        //////////////////////////////////

        emptyTree = new Bintree(128);
        tinyTree = new Bintree(32);
        tree2 = new Bintree(128);

        se1 = new Seminar(1, "Name", "0610051600", 90, (short)10, (short)10, 45,
            new String[] { "words" }, "Descrip");
        se2 = new Seminar(2, "Name", "0910371670", 60, (short)10, (short)10, 30,
            new String[] { "word" }, "Descrip");
        se3 = new Seminar(10, "Name", "07321230860", 30, (short)30, (short)10,
            17, new String[] { "word" }, "Descrip");
        se4 = new Seminar(3, "Name", "2243509777", 35, (short)0, (short)0, 25,
            new String[] { "word" }, "Descrip");
    }


    /**
     * Test edge case of searching with radius 0.
     */
    public void testSearchRadiusZero() {

        tree.insert(sem1);
        tree.insert(sem2);

        tree.search(sem1, 0.0);
    }


    /**
     * Test method for search
     */
    public void testSearch2() {
        tinyTree.insert(se1);
        tinyTree.insert(se2);
        tinyTree.insert(se3);
        tinyTree.insert(se4);
        tinyTree.insert(new Seminar(2, "Name", "1005302645", 35, (short)0,
            (short)70, 25, new String[] { "words" }, "descprip"));
        tinyTree.insert(new Seminar(2, "Title", "1203301125", 35, (short)70,
            (short)0, 25, new String[] { "keyword" }, "Description"));

        tree2.search(new Seminar(0, "", "", 0, (short)0, (short)0, 0,
            new String[] { "" }, ""), 0);

        tinyTree.search(new Seminar(0, "", "", 0, (short)9, (short)9, 0,
            new String[] { "" }, ""), 0);

        tinyTree.search(new Seminar(0, "", "", 0, (short)40, (short)10, 0,
            new String[] { "" }, ""), 0);

        tinyTree.search(new Seminar(0, "", "", 0, (short)70, (short)70, 0,
            new String[] { "" }, ""), -30);

        tinyTree.search(new Seminar(0, "", "", 0, (short)60, (short)60, 0,
            new String[] { "" }, ""), 30);

        tinyTree.search(new Seminar(0, "", "", 0, (short)0, (short)0, 0,
            new String[] { "" }, ""), 0);

        tinyTree.search(new Seminar(0, "", "", 0, (short)0, (short)0, 0,
            new String[] { "" }, ""), 100);

        tinyTree.search(new Seminar(0, "", "", 0, (short)0, (short)0, 0,
            new String[] { "" }, ""), -100);

        tinyTree.search(new Seminar(0, "", "", 0, (short)0, (short)90, 0,
            new String[] { "" }, ""), 10);

        tinyTree.search(new Seminar(0, "", "", 0, (short)80, (short)0, 0,
            new String[] { "" }, ""), 10);

        tinyTree.search(new Seminar(0, "", "", 0, (short)70, (short)70, 0,
            new String[] { "" }, ""), 9);

        tinyTree.search(new Seminar(0, "", "", 0, (short)0, (short)70, 0,
            new String[] { "" }, ""), -10);

        tinyTree.search(new Seminar(0, "", "", 0, (short)70, (short)0, 0,
            new String[] { "" }, ""), -11);

        tinyTree.search(new Seminar(0, "", "", 0, (short)70, (short)70, 0,
            new String[] { "" }, ""), -11);

        tinyTree.search(new Seminar(0, "", "", 0, (short)100, (short)0, 0,
            new String[] { "" }, ""), -20);

        tinyTree.search(new Seminar(0, "", "", 0, (short)100, (short)100, 0,
            new String[] { "" }, ""), -20);

        tinyTree.search(new Seminar(0, "", "", 0, (short)0, (short)100, 0,
            new String[] { "" }, ""), -10);

        tinyTree.search(new Seminar(0, "", "", 0, (short)100, (short)0, 0,
            new String[] { "" }, ""), 10);

        tinyTree.search(new Seminar(0, "", "", 0, (short)100, (short)100, 0,
            new String[] { "" }, ""), 10);

        tinyTree.search(new Seminar(0, "", "", 0, (short)0, (short)100, 0,
            new String[] { "" }, ""), 10);

        assertFuzzyEquals(systemOut().getHistory(),
            "1 nodes visited in this search\r\n"
                + "4 nodes visited in this search\r\n"
                + "5 nodes visited in this search\r\n"
                + "3 nodes visited in this search\r\n"
                + "3 nodes visited in this search\r\n"
                + "Found a record with key value 3 at 0, 0\r\n"
                + "4 nodes visited in this search\r\n"
                + "Found a record with key value 3 at 0, 0\r\n"
                + "Found a record with key value 1 at 10, 10\r\n"
                + "Found a record with key value 2 at 10, 10\r\n"
                + "Found a record with key value 2 at 0, 70\r\n"
                + "Found a record with key value 2 at 70, 0\r\n"
                + "Found a record with key value 10 at 30, 10\r\n"
                + "13 nodes visited in this search\r\n"
                + "1 nodes visited in this search\r\n"
                + "3 nodes visited in this search\r\n"
                + "Found a record with key value 2 at 70, 0\r\n"
                + "6 nodes visited in this search\r\n"
                + "3 nodes visited in this search\r\n"
                + "3 nodes visited in this search\r\n"
                + "4 nodes visited in this search\r\n"
                + "3 nodes visited in this search\r\n"
                + "2 nodes visited in this search\r\n"
                + "3 nodes visited in this search\r\n"
                + "3 nodes visited in this search\r\n"
                + "6 nodes visited in this search\r\n"
                + "3 nodes visited in this search\r\n"
                + "3 nodes visited in this search");
    }


    /**
     * Test the insert of Bintree.
     */
    public void testInsert() {
        tree.insert(sem1);
        tree.insert(sem2);
        tree.insert(sem3);
        tree.insert(sem4);
        tree.insert(sem5);

        tree.print();
        String equals = "     (I)\r\n" + "                (I)\r\n"
            + "            (I)\r\n" + "        (I)\r\n" + "    (I)\r\n"
            + "(Leaf with 1 objects: 5)\r\n" + "(Leaf with 1 objects: 4)\r\n"
            + "    (E)\r\n" + "        (Leaf with 1 objects: 2)\r\n"
            + "            (E)\r\n" + "                (I)\r\n"
            + "            (E)\r\n" + "            (I)\r\n"
            + "        (Leaf with 1 objects: 3)\r\n"
            + "        (Leaf with 1 objects: 1)";
        assertFuzzyEquals(systemOut().getHistory(), equals);
    }


    /**
     * Test the remove functionality of Bintree.
     */
    public void testRemove() {
        tree.insert(sem1);
        tree.insert(sem2);
        tree.insert(sem3);
        tree.remove(sem1);

        tree.print();
        String equals = " (I)\r\n" + "(Leaf with 1 objects: 2)\r\n"
            + "(Leaf with 1 objects: 3)";
        assertFuzzyEquals(systemOut().getHistory(), equals);

    }


    /**
     * test gets the boudnry box
     */
    public void testGetWorld() {
        BoundingBox world = tree.getWorld();

        // Verify the bounding box's minimum and maximum x and y values
        assertEquals(0.0, world.getxMin(), 0.001); // Min x value
        assertEquals(0.0, world.getyMin(), 0.001); // Min y value
        assertEquals(99.0, world.getxMax(), 0.001); // Max x value (worldSize -
        // 1)
        assertEquals(99.0, world.getyMax(), 0.001); // Max y value (worldSize -
        // 1)
    }


    /**
     * Test the searchRecursive method where x and y boundaries and radius
     * conditions are hit.
     */
    public void testSearch() {
        // Insert seminars into the tree
        tree.insert(sem1);
        tree.insert(sem2);
        tree.insert(sem3);
        tree.insert(sem4);
        tree.insert(sem5);

        tree.search(sem1, 30.0);

        tree.search(sem2, 5.0);

        tree.search(sem3, 10.0);

        tree.search(sem4, 10.0);

        tree.search(sem5, 20.0);

    }


    /**
     * Tests tree restructuring after removing a node, ensuring no empty nodes
     * remain.
     */
    public void testDuringRestructure() {
        // Create three seminars that will force a split in x-dimension
        // All with same y-coordinate to ensure x-dimension split
        Seminar left = new Seminar(1, "Left", "2023-01-01", 60, (short)25,
            (short)50, 100, new String[] { "here" }, "here");
        Seminar middle = new Seminar(2, "Middle", "2021-81-09", 60, (short)50,
            (short)50, 100, new String[] { "here" }, "here");
        Seminar right = new Seminar(3, "Right", "2018-11-11", 60, (short)75,
            (short)50, 100, new String[] { "here" }, "here");

        tree.insert(left);
        tree.insert(middle);
        tree.insert(right);

        System.out.println("Initial tree structure:");
        tree.print();

        tree.remove(middle);

        System.out.println("Tree structure after removal:");
        tree.print();

        Seminar newMiddleSeminar = new Seminar(4, "mid", "2023-31-11", 60,
            (short)50, (short)50, 100, new String[] { "here" }, "here");

        tree.insert(newMiddleSeminar);

        tree.print();

    }


    /**
     * Test the search when radius are same
     */
    public void testSearchRadiusMultipleLevels() {

        Seminar semir1 = new Seminar(1, "Sem1", "2022-32-07", 60, (short)25,
            (short)25, 100, new String[] { "Here" }, "hjere");
        Seminar semir2 = new Seminar(2, "Sem2", "2021-06-07", 60, (short)75,
            (short)25, 100, new String[] { "Test" }, "Test");
        Seminar semir3 = new Seminar(3, "Sem3", "2028-02-02", 60, (short)25,
            (short)75, 100, new String[] { "Test" }, "Test");
        Seminar semir4 = new Seminar(4, "Sem4", "2028-11-11",

            60, (short)75, (short)75, 100, new String[] { "Hre" }, "Hre");

        tree.insert(semir1);
        tree.insert(semir2);
        tree.insert(semir3);
        tree.insert(semir4);

        Seminar search = new Seminar(5, "find", "2032-22-11", 60, (short)45,
            (short)45, 100, new String[] { "here" }, "here");

        tree.search(search, 36.0);

    }


    /**
     * Test search where no seminar is within the radius.
     */
    public void testSearch4() {
        // Insert seminars into the tree
        tree.insert(sem1);
        tree.insert(sem2);

        // Search for seminar3 which is not in the tree
        tree.search(sem3, 10.0); // Expect no seminars to be found
    }


    /**
     * Tests the search where the radius is exactly at midX.
     */
    public void testSearchRadiusAttMidX() {

        Seminar first = new Seminar(1, "hi", "2024-22-11", 60, (short)45,
            (short)50, 100, new String[] { "Test" }, "Test");
        Seminar sec = new Seminar(2, "hi", "2025-02-11", 60, (short)55,
            (short)50, 100, new String[] { "here" }, "here");

        tree.insert(first);
        tree.insert(sec);

        // Search seminar exactly at x=45 with radius=5
        // This makes searchSeminar.x() + radius exactly equal to midX (50)
        Seminar search = new Seminar(3, "finsd", "2024-31-02", 60, (short)45,
            (short)50, 100, new String[] { "here" }, "here");

        tree.search(search, 5.0);

    }


    /**
     * Tests the insertion of a single seminar followed by its removal.
     */
    public void testInsertEmpty() {
        // Insert a single seminar
        Seminar sem = new Seminar(1, "test", "2032-21-11", 60, (short)50,
            (short)50, 100, new String[] { "Here" }, "Test description");

        tree.insert(sem);

        // Verify insertion was successful
        assertFalse(tree.getRoot() instanceof EmptyNode);

        // Remove the seminar
        tree.remove(sem);

        // Verify tree returned to empty state
        assertTrue(tree.getRoot() instanceof EmptyNode);

    }


    /**
     * Tests searching where the radius exceeds the midX boundary.
     */
    public void testSearchRadiusSBeyondMidX() {
        Seminar first = new Seminar(1, "hi", "2024-22-11", 60, (short)45,
            (short)50, 100, new String[] { "Test" }, "Test");
        Seminar sec = new Seminar(2, "hi", "2025-02-11", 60, (short)55,
            (short)50, 100, new String[] { "here" }, "here");

        tree.insert(first);
        tree.insert(sec);

        // Search seminar exactly at x=45 with radius=5
        // This makes searchSeminar.x() + radius exactly equal to midX (50)
        Seminar search = new Seminar(3, "finsd", "2024-31-02", 60, (short)45,
            (short)50, 100, new String[] { "here" }, "here");

        tree.search(search, 5.2);

    }


    /**
     * Tests a search with a radius just short of reaching the midpoint of the
     * tree.
     */
    public void testSearchRadius5() {
        Seminar first = new Seminar(1, "hi", "2024-22-11", 60, (short)45,
            (short)50, 100, new String[] { "Test" }, "Test");
        Seminar sec = new Seminar(2, "hi", "2025-02-11", 60, (short)55,
            (short)50, 100, new String[] { "here" }, "here");

        tree.insert(first);
        tree.insert(sec);

        // Search seminar exactly at x=45 with radius=5
        // This makes searchSeminar.x() + radius exactly equal to midX (50)
        Seminar search = new Seminar(3, "finsd", "2024-31-02", 60, (short)45,
            (short)50, 100, new String[] { "here" }, "here");

        tree.search(search, 4.8);

    }


    /**
     * Tests a search with a radius that extends well beyond the midpoint.
     */
    public void testSearchRadiusBeyondMidX() {
        Seminar first = new Seminar(1, "hi", "2024-22-11", 60, (short)45,
            (short)50, 100, new String[] { "Test" }, "Test");
        Seminar sec = new Seminar(2, "hi", "2025-02-11", 60, (short)55,
            (short)50, 100, new String[] { "here" }, "here");

        tree.insert(first);
        tree.insert(sec);

        Seminar search = new Seminar(3, "finsd", "2024-31-02", 60, (short)45,
            (short)50, 100, new String[] { "here" }, "here");

        tree.search(search, 11.0);

    }


    /**
     * Tests the edge case where the search radius is exactly at the midpoint.
     */
    public void testSearchRadiusAtMidXEdgeCase() {
        Seminar first = new Seminar(1, "hi", "2024-22-11", 60, (short)45,
            (short)50, 100, new String[] { "Test" }, "Test");
        Seminar sec = new Seminar(2, "hi", "2025-02-11", 60, (short)55,
            (short)50, 100, new String[] { "here" }, "here");

        tree.insert(first);
        tree.insert(sec);

        // Search seminar exactly at x=45 with radius=5
        // This makes searchSeminar.x() + radius exactly equal to midX (50)
        Seminar search = new Seminar(3, "finsd", "2024-31-02", 60, (short)45,
            (short)50, 100, new String[] { "here" }, "here");

        tree.search(search, 0.0);

    }

}
