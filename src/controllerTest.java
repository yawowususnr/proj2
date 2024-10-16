import student.TestCase;
/**
 * 
 */

public class ControllerTest extends TestCase {

    private Controller controller;

    public void setUp() {
        this.controller = new Controller(100);
    }


    public void testInsert() {
        assertEquals(this.controller.getSize(), 100);
        String[] keywords = { "Java", "Programming" };
        controller.insert(1, "Java Seminar", "2024-10-01", 60, (short)10,
            (short)20, 500, keywords, keywords.length, "Learn Java");
        assertEquals(this.controller.getidBSTree().size(), 1);
//        assertNotNull(controller.costBST.find(500),
//            "Seminar should be found in the cost BST");
//        assertNotNull(controller.dateBST.find("2024-10-01"),
//            "Seminar should be found in the date BST");
    }

}
