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
        KeyValuePair<Integer, Seminar> foundNode = controller.getidBST.find(5);
        String[] keywords = { "Java", "Programming" };
        controller.insert(1, "Java Seminar", "2024-10-01", 60, (short)10,
            (short)20, 500, keywords, keywords.length, "Learn Java");
        assertEquals(this.controller.getidBSTree().size(), 1);
        assertNotNull(controller.getcostBSTree().find(500));
        assertNotNull(controller.getdateBSTree().find("2024-10-01"));
        controller.insert(1, "Java Seminar", "2024-10-01", 60, (short)210,
            (short)220, 500, keywords, keywords.length, "Learn Java");
        assertNull(foundNode);
        assertFalse(controller.checkIfValid((short)210, (short)220));
        
   
    }

}
