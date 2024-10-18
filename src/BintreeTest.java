import student.TestCase;

public class BintreeTest
    extends TestCase
{
    private Bintree tree;
    private Seminar seminar1;
    private Seminar seminar2;
    private Seminar seminar3;
    private Seminar seminar4;
    private Seminar seminar5;
    private Bintree emptyTree;
    private Bintree smallTree; // small tree
    private Bintree tree2; // regular tree
    private Seminar s1; // seminar 1
    private Seminar s2; // seminar 2
    private Seminar s3; // seminar 3
    private Seminar s4; // seminar 4

    /**
     * Sets up the test environment by initializing the Bintree and some Seminar
     * objects.
     */
    public void setUp()
    {
        tree = new Bintree(100); // Creates a Bintree with a world size of 100

        // Seminar 1 at coordinates (10, 20)
        seminar1 = new Seminar(
            1,
            "Seminar One",
            "2023-01-01",
            60,
            (short)10,
            (short)20,
            100,
            new String[] { "Tech" },
            "A tech seminar.");

        // Seminar 2 at coordinates (50, 60)
        seminar2 = new Seminar(
            2,
            "Seminar Two",
            "2023-02-01",
            45,
            (short)50,
            (short)60,
            150,
            new String[] { "Business" },
            "A business seminar.");

        // Seminar 3 near the middle point of the bounding box at (25, 25)
        seminar3 = new Seminar(
            3,
            "Seminar Three",
            "2023-03-01",
            30,
            (short)25,
            (short)25,
            120,
            new String[] { "Education" },
            "An educational seminar.");

        // Seminar 4 at coordinates (75, 80)
        seminar4 = new Seminar(
            4,
            "Seminar Four",
            "2023-04-01",
            45,
            (short)75,
            (short)80,
            130,
            new String[] { "Finance" },
            "A finance seminar.");

        // Seminar 5 at coordinates (90, 90)
        seminar5 = new Seminar(
            5,
            "Seminar Five",
            "2023-05-01",
            60,
            (short)90,
            (short)90,
            110,
            new String[] { "Health" },
            "A health seminar.");

        //////////////////////////////////

        emptyTree = new Bintree(128);
        smallTree = new Bintree(32);
        tree2 = new Bintree(128);

        s1 = new Seminar(
            1,
            "Title",
            "0610051600",
            90,
            (short)10,
            (short)10,
            45,
            new String[] { "keyword" },
            "Description");
        s2 = new Seminar(
            2,
            "Title",
            "0610071600",
            60,
            (short)10,
            (short)10,
            30,
            new String[] { "keyword" },
            "Description");
        s3 = new Seminar(
            10,
            "Title",
            "0701250830",
            30,
            (short)30,
            (short)10,
            17,
            new String[] { "keyword" },
            "Description");
        s4 = new Seminar(
            3,
            "Title",
            "1203301125",
            35,
            (short)0,
            (short)0,
            25,
            new String[] { "keyword" },
            "Description");
    }


    /**
     * Test method for search
     */
    public void testSearch2()
    {
        smallTree.insert(s1);
        smallTree.insert(s2);
        smallTree.insert(s3);
        smallTree.insert(s4);
        smallTree.insert(
            new Seminar(
                3,
                "Title",
                "1203301125",
                35,
                (short)0,
                (short)70,
                25,
                new String[] { "keyword" },
                "Description"));
        smallTree.insert(
            new Seminar(
                3,
                "Title",
                "1203301125",
                35,
                (short)70,
                (short)0,
                25,
                new String[] { "keyword" },
                "Description"));

        tree2.search(
            new Seminar(
                0,
                "",
                "",
                0,
                (short)0,
                (short)0,
                0,
                new String[] { "" },
                ""),
            0);

        smallTree.search(
            new Seminar(
                0,
                "",
                "",
                0,
                (short)10,
                (short)10,
                0,
                new String[] { "" },
                ""),
            0);

        smallTree.search(
            new Seminar(
                0,
                "",
                "",
                0,
                (short)30,
                (short)10,
                0,
                new String[] { "" },
                ""),
            0);

        smallTree.search(
            new Seminar(
                0,
                "",
                "",
                0,
                (short)60,
                (short)60,
                0,
                new String[] { "" },
                ""),
            -40);

        smallTree.search(
            new Seminar(
                0,
                "",
                "",
                0,
                (short)60,
                (short)60,
                0,
                new String[] { "" },
                ""),
            40);

        smallTree.search(
            new Seminar(
                0,
                "",
                "",
                0,
                (short)0,
                (short)0,
                0,
                new String[] { "" },
                ""),
            0);

        smallTree.search(
            new Seminar(
                0,
                "",
                "",
                0,
                (short)0,
                (short)0,
                0,
                new String[] { "" },
                ""),
            100);

        smallTree.search(
            new Seminar(
                0,
                "",
                "",
                0,
                (short)0,
                (short)0,
                0,
                new String[] { "" },
                ""),
            -100);

        smallTree.search(
            new Seminar(
                0,
                "",
                "",
                0,
                (short)0,
                (short)80,
                0,
                new String[] { "" },
                ""),
            10);

        smallTree.search(
            new Seminar(
                0,
                "",
                "",
                0,
                (short)80,
                (short)0,
                0,
                new String[] { "" },
                ""),
            10);

        smallTree.search(
            new Seminar(
                0,
                "",
                "",
                0,
                (short)80,
                (short)80,
                0,
                new String[] { "" },
                ""),
            10);

        smallTree.search(
            new Seminar(
                0,
                "",
                "",
                0,
                (short)0,
                (short)80,
                0,
                new String[] { "" },
                ""),
            -10);

        smallTree.search(
            new Seminar(
                0,
                "",
                "",
                0,
                (short)80,
                (short)0,
                0,
                new String[] { "" },
                ""),
            -10);

        smallTree.search(
            new Seminar(
                0,
                "",
                "",
                0,
                (short)80,
                (short)80,
                0,
                new String[] { "" },
                ""),
            -10);

        smallTree.search(
            new Seminar(
                0,
                "",
                "",
                0,
                (short)100,
                (short)0,
                0,
                new String[] { "" },
                ""),
            -10);

        smallTree.search(
            new Seminar(
                0,
                "",
                "",
                0,
                (short)100,
                (short)100,
                0,
                new String[] { "" },
                ""),
            -10);

        smallTree.search(
            new Seminar(
                0,
                "",
                "",
                0,
                (short)0,
                (short)100,
                0,
                new String[] { "" },
                ""),
            -10);

        smallTree.search(
            new Seminar(
                0,
                "",
                "",
                0,
                (short)100,
                (short)0,
                0,
                new String[] { "" },
                ""),
            10);

        smallTree.search(
            new Seminar(
                0,
                "",
                "",
                0,
                (short)100,
                (short)100,
                0,
                new String[] { "" },
                ""),
            10);

        smallTree.search(
            new Seminar(
                0,
                "",
                "",
                0,
                (short)0,
                (short)100,
                0,
                new String[] { "" },
                ""),
            10);

        assertFuzzyEquals(
            systemOut().getHistory(),
            "1 nodes visited in this search\r\n"
                + "found a record with key value 1 at 10 10\r\n"
                + "found a record with key value 2 at 10 10\r\n"
                + "4 nodes visited in this search\r\n"
                + "found a record with key value 10 at 30 10\r\n"
                + "5 nodes visited in this search\r\n"
                + "3 nodes visited in this search\r\n"
                + "3 nodes visited in this search\r\n"
                + "found a record with key value 3 at 0 0\r\n"
                + "4 nodes visited in this search\r\n"
                + "found a record with key value 3 at 0 0\r\n"
                + "found a record with key value 1 at 10 10\r\n"
                + "found a record with key value 2 at 10 10\r\n"
                + "found a record with key value 3 at 0 70\r\n"
                + "found a record with key value 3 at 70 0\r\n"
                + "found a record with key value 10 at 30 10\r\n"
                + "13 nodes visited in this search\r\n"
                + "1 nodes visited in this search\r\n"
                + "found a record with key value 3 at 0 70\r\n"
                + "3 nodes visited in this search\r\n"
                + "found a record with key value 3 at 70 0\r\n"
                + "6 nodes visited in this search\r\n"
                + "3 nodes visited in this search\r\n"
                + "3 nodes visited in this search\r\n"
                + "4 nodes visited in this search\r\n"
                + "3 nodes visited in this search\r\n"
                + "4 nodes visited in this search\r\n"
                + "3 nodes visited in this search\r\n"
                + "3 nodes visited in this search\r\n"
                + "6 nodes visited in this search\r\n"
                + "3 nodes visited in this search\r\n"
                + "3 nodes visited in this search");
    }


    /**
     * Test the insert functionality of Bintree.
     */
    public void testInsert()
    {
        tree.insert(seminar1);
        tree.insert(seminar2);
        tree.insert(seminar3);
        tree.insert(seminar4);
        tree.insert(seminar5);

        tree.print();
    }


    /**
     * Test the remove functionality of Bintree.
     */
    public void testRemove()
    {
        tree.insert(seminar1);
        tree.insert(seminar2);
        tree.insert(seminar3);
        tree.remove(seminar1);

        tree.print();
    }


    /**
     * Test the searchRecursive method where x and y boundaries and radius
     * conditions are hit.
     */
    public void testSearch()
    {
        // Insert seminars into the tree
        tree.insert(seminar1);
        tree.insert(seminar2);
        tree.insert(seminar3);
        tree.insert(seminar4);
        tree.insert(seminar5);

        // Search for seminar1 within a radius where x + radius crosses midX
        tree.search(seminar1, 30.0); // Expect this to trigger x + radius >=
                                     // midX

        // Search for seminar2 within a smaller radius that does not cover midX
        tree.search(seminar2, 5.0); // Expect seminar2 to be found within a
                                    // small radius

        // Search for seminar3 which should hit the case where the seminar is
        // near the midpoint
        tree.search(seminar3, 10.0); // Expect seminar3 to be found around the
                                     // midpoint (25, 25)

        // Search for seminar4 which should only be found in the right subtree
        tree.search(seminar4, 10.0); // Expect seminar4 to be found in the right
                                     // part of the tree

        // Search for seminar5 which should be found at the far right side of
        // the tree
        tree.search(seminar5, 20.0); // Expect seminar5 to be found at the far
                                     // right boundary (90, 90)
    }


    /**
     * Test edge case of searching with radius 0.
     */
    public void testSearchRadiusZero()
    {
        // Insert seminars into the tree
        tree.insert(seminar1);
        tree.insert(seminar2);

        // Search for seminar1 with radius 0 (should only find exact match)
        tree.search(seminar1, 0.0); // Expect seminar1 to be found exactly
    }


    /**
     * Test search where no seminar is within the radius.
     */
    public void testSearchNoMatch()
    {
        // Insert seminars into the tree
        tree.insert(seminar1);
        tree.insert(seminar2);

        // Search for seminar3 which is not in the tree
        tree.search(seminar3, 5.0); // Expect no seminars to be found
    }


    /**
     * Place a description of your method here.
     */
    public void testGetWorld()
    {
        BoundingBox world = tree.getWorld();

        // Verify the bounding box's minimum and maximum x and y values
        assertEquals(0.0, world.getxMin(), 0.001);   // Min x value
        assertEquals(0.0, world.getyMin(), 0.001);   // Min y value
        assertEquals(99.0, world.getxMax(), 0.001);  // Max x value (worldSize -
                                                     // 1)
        assertEquals(99.0, world.getyMax(), 0.001);  // Max y value (worldSize -
                                                     // 1)
    }


    /**
     * Tests the search where the radius is exactly at midX.
     */
    public void testSearchRadiusExactlyAtMidX()
    {
        // Insert seminars on both sides of the midpoint (50 in a 100x100 world)
        Seminar leftSeminar = new Seminar(
            1,
            "Left",
            "2023-01-01",
            60,
            (short)45,
            (short)50,
            100,
            new String[] { "Test" },
            "Test");
        Seminar rightSeminar = new Seminar(
            2,
            "Right",
            "2023-01-01",
            60,
            (short)55,
            (short)50,
            100,
            new String[] { "Test" },
            "Test");

        tree.insert(leftSeminar);
        tree.insert(rightSeminar);

        // Search seminar exactly at x=45 with radius=5
        // This makes searchSeminar.x() + radius exactly equal to midX (50)
        Seminar searchSeminar = new Seminar(
            3,
            "Search",
            "2023-01-01",
            60,
            (short)45,
            (short)50,
            100,
            new String[] { "Test" },
            "Test");

        tree.search(searchSeminar, 5.0);

    }


    /**
     * Tests searching where the radius slightly exceeds the midX boundary.
     */
    public void testSearchRadiusSlightlyBeyondMidX()
    {
        // Insert seminars on both sides of the midpoint
        Seminar leftSeminar = new Seminar(
            1,
            "Left",
            "2023-01-01",
            60,
            (short)45,
            (short)50,
            100,
            new String[] { "Test" },
            "Test");
        Seminar rightSeminar = new Seminar(
            2,
            "Right",
            "2023-01-01",
            60,
            (short)55,
            (short)50,
            100,
            new String[] { "Test" },
            "Test");

        tree.insert(leftSeminar);
        tree.insert(rightSeminar);

        // Search seminar at x=45 with radius=5.1
        // This makes searchSeminar.x() + radius slightly greater than midX
        Seminar searchSeminar = new Seminar(
            3,
            "Search",
            "2023-01-01",
            60,
            (short)45,
            (short)50,
            100,
            new String[] { "Test" },
            "Test");

        tree.search(searchSeminar, 5.1);

    }


    /**
     * Tests a search with a radius just short of reaching the midpoint of the
     * tree.
     */
    public void testSearchRadiusJustShortOfMidX()
    {
        // Insert seminars on both sides of the midpoint
        Seminar leftSeminar = new Seminar(
            1,
            "Left",
            "2023-01-01",
            60,
            (short)45,
            (short)50,
            100,
            new String[] { "Test" },
            "Test");
        Seminar rightSeminar = new Seminar(
            2,
            "Right",
            "2023-01-01",
            60,
            (short)55,
            (short)50,
            100,
            new String[] { "Test" },
            "Test");

        tree.insert(leftSeminar);
        tree.insert(rightSeminar);

        // Search seminar at x=45 with radius=4.9
        // This makes searchSeminar.x() + radius slightly less than midX
        Seminar searchSeminar = new Seminar(
            3,
            "Search",
            "2023-01-01",
            60,
            (short)45,
            (short)50,
            100,
            new String[] { "Test" },
            "Test");

        tree.search(searchSeminar, 4.9);

    }


    /**
     * Tests a search with a radius that extends well beyond the midpoint.
     */
    public void testSearchRadiusWellBeyondMidX()
    {
        // Insert seminars on both sides of the midpoint
        Seminar leftSeminar = new Seminar(
            1,
            "Left",
            "2023-01-01",
            60,
            (short)45,
            (short)50,
            100,
            new String[] { "Test" },
            "Test");
        Seminar rightSeminar = new Seminar(
            2,
            "Right",
            "2023-01-01",
            60,
            (short)55,
            (short)50,
            100,
            new String[] { "Test" },
            "Test");

        tree.insert(leftSeminar);
        tree.insert(rightSeminar);

        // Search seminar at x=45 with radius=10
        // This makes searchSeminar.x() + radius well beyond midX
        Seminar searchSeminar = new Seminar(
            3,
            "Search",
            "2023-01-01",
            60,
            (short)45,
            (short)50,
            100,
            new String[] { "Test" },
            "Test");

        tree.search(searchSeminar, 10.0);

    }


    /**
     * Tests the edge case where the search radius is exactly at the midpoint.
     */
    public void testSearchRadiusAtMidXEdgeCase()
    {
        // Insert a seminar exactly at the midpoint
        Seminar midSeminar = new Seminar(
            1,
            "Mid",
            "2023-01-01",
            60,
            (short)50,
            (short)50,
            100,
            new String[] { "Test" },
            "Test");

        tree.insert(midSeminar);

        // Search seminar at x=50 with radius=0
        // This tests the edge case where searchSeminar.x() + radius exactly
        // equals midX
        Seminar searchSeminar = new Seminar(
            2,
            "Search",
            "2023-01-01",
            60,
            (short)50,
            (short)50,
            100,
            new String[] { "Test" },
            "Test");

        tree.search(searchSeminar, 0.0);

    }


    /**
     * Tests a search across multiple levels of the tree with a large radius.
     */
    public void testSearchRadiusMultipleLevels()
    {
        // Create a tree with multiple levels by inserting seminars that force
        // splits
        Seminar sem1 = new Seminar(
            1,
            "S1",
            "2023-01-01",
            60,
            (short)25,
            (short)25,
            100,
            new String[] { "Test" },
            "Test");
        Seminar sem2 = new Seminar(
            2,
            "S2",
            "2023-01-01",
            60,
            (short)75,
            (short)25,
            100,
            new String[] { "Test" },
            "Test");
        Seminar sem3 = new Seminar(
            3,
            "S3",
            "2023-01-01",
            60,
            (short)25,
            (short)75,
            100,
            new String[] { "Test" },
            "Test");
        Seminar sem4 = new Seminar(
            4,
            "S4",
            "2023-01-01",
            60,
            (short)75,
            (short)75,
            100,
            new String[] { "Test" },
            "Test");

        tree.insert(sem1);
        tree.insert(sem2);
        tree.insert(sem3);
        tree.insert(sem4);

        // Search with a radius that crosses multiple quadrants
        Seminar searchSeminar = new Seminar(
            5,
            "Search",
            "2023-01-01",
            60,
            (short)45,
            (short)45,
            100,
            new String[] { "Test" },
            "Test");

        tree.search(searchSeminar, 35.0);

    }


    /**
     * Tests the insertion of a single seminar followed by its removal.
     */
    public void testInsertThenRemoveToEmpty()
    {
        // Insert a single seminar
        Seminar seminar = new Seminar(
            1,
            "Test Seminar",
            "2023-01-01",
            60,
            (short)50,
            (short)50,
            100,
            new String[] { "Test" },
            "Test description");

        tree.insert(seminar);

        // Verify insertion was successful
        assertFalse(tree.getRoot() instanceof EmptyNode);

        // Remove the seminar
        tree.remove(seminar);

        // Verify tree returned to empty state
        assertTrue(tree.getRoot() instanceof EmptyNode);

    }


    /**
     * Tests tree restructuring after removing a node, ensuring no empty nodes
     * remain.
     */
    public void testEmptyNodeRemovalDuringRestructure()
    {
        // Create three seminars that will force a split in x-dimension
        // All with same y-coordinate to ensure x-dimension split
        Seminar leftSeminar = new Seminar(
            1,
            "Left",
            "2023-01-01",
            60,
            (short)25,
            (short)50,
            100,
            new String[] { "Test" },
            "Test");
        Seminar middleSeminar = new Seminar(
            2,
            "Middle",
            "2023-01-01",
            60,
            (short)50,
            (short)50,
            100,
            new String[] { "Test" },
            "Test");
        Seminar rightSeminar = new Seminar(
            3,
            "Right",
            "2023-01-01",
            60,
            (short)75,
            (short)50,
            100,
            new String[] { "Test" },
            "Test");

        // Insert all three seminars
        tree.insert(leftSeminar);
        tree.insert(middleSeminar);
        tree.insert(rightSeminar);

        // Print initial tree state
        System.out.println("Initial tree structure:");
        tree.print();

        // Remove the middle seminar - this should trigger restructuring
        tree.remove(middleSeminar);

        // Print and capture the restructured tree
        System.out.println("Tree structure after removal:");
        tree.print();

        // 3. No empty nodes remain in the structure

        // Additional verification: try to insert a new seminar where the middle
        // one was
        Seminar newMiddleSeminar = new Seminar(
            4,
            "New Middle",
            "2023-01-01",
            60,
            (short)50,
            (short)50,
            100,
            new String[] { "Test" },
            "Test");

        tree.insert(newMiddleSeminar);

        tree.print();

    }

}
