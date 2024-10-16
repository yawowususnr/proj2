
/**
 * 
 */

public class controllerTest extends student.TestCase {
    private BST<Integer, Seminar> idBST;

    private BST<Integer, Seminar> costBST;

    private BST<String, Seminar> dateBST;

    private BST<String, Seminar> keywordsBST;

    private Controller controller;

    public void setUp() {
        controller = new Controller(100);
        idBST = new BST<Integer, Seminar>();
        costBST = new BST<Integer, Seminar>();
        dateBST = new BST< String, Seminar>();
        keywordsBST = new BST<String, Seminar>();
    }


    public void testInsert() {
        String[] keywords = { "Java", "Programming" };
        controller.insert(1, "Java Seminar", "2024-10-01", 60, (short)10,
            (short)20, 500, keywords, keywords.length, "Learn Java");
        assertNotNull(controller.idBST.find(1),
            "Seminar should be found in the ID BST");
        assertNotNull(controller.costBST.find(500),
            "Seminar should be found in the cost BST");
        assertNotNull(controller.dateBST.find("2024-10-01"),
            "Seminar should be found in the date BST");
    }

}
