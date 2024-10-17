import student.TestCase;

public class BintreeTest
    extends TestCase
{
    private Bintree tree;
    private Seminar seminar1;
    private Seminar seminar2;
    private Seminar seminar3;

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
    }


    /**
     * Test the insert functionality of Bintree.
     */
    public void testInsert()
    {
        tree.insert(seminar1);
        tree.insert(seminar2);
        tree.insert(seminar3);
        
        
        tree.print();
    }


    /**
     * Test the remove functionality of Bintree.
     */
    public void testRemove()
    {
        tree.insert(seminar1);
        tree.insert(seminar2);
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
    }
}
