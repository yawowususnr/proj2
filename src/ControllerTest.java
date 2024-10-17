import student.TestCase;

/**
 * 
 */

public class ControllerTest extends TestCase {

    private Controller controller;

    public void setUp() {
        this.controller = new Controller(100);
    }


// public void testInsert() {
// String[] keywords = { "Java", "Programming" };
// controller.insert(1, "Java Seminar", "2024-10-01", 60, (short)10,
// (short)20, 500, keywords, keywords.length, "Learn Java");
// controller.insert(1, "Java Seminar", "2024-10-01", 60, (short)210,
// (short)220, 500, keywords, keywords.length, "Learn Java");
// controller.insert(2, "Java Seminar", "2024-10-01", 60, (short)210,
// (short)220, 500, keywords, keywords.length, "Learn Java");
// String newStr = "Successfully inserted record with ID 1\r\n"
// + "ID: 1, Title: Java Seminar\r\n"
// + "Date: 2024-10-01, Length: 60, X: 10, Y: 20, Cost: 500\r\n"
// + "Description: Learn Java\r\n" + "Keywords: Java, Programming\r\n"
// + "Insert FAILED - There is already a record with ID 1\r\n"
// + "Insert FAILED - Bad x, y coordinates: 210, 220";
// assertFuzzyEquals(systemOut().getHistory(), newStr);
//
// }
    public void testDelete() {
        String[] keywords = { "Java", "Programming" };
        controller.insert(1, "Java Seminar", "2024-10-01", 60, (short)10,
            (short)20, 500, keywords, keywords.length, "Learn Java");
        controller.delete(1);
        controller.delete(1);
        String newStr = "Successfully inserted record with ID 1\r\n"
            + "ID: 1, Title: Java Seminar\r\n"
            + "Date: 2024-10-01, Length: 60, X: 10, Y: 20, Cost: 500\r\n"
            + "Description: Learn Java\r\n" + "Keywords: Java, Programming\r\n"
            + "Record with ID 1 successfully deleted from the database\r\n"
            + "Search FAILED -- There is no record with ID 1";
        assertFuzzyEquals(systemOut().getHistory(), newStr);

    }


    public void testSearchId() {

    }


    public void testSearchCost() {

    }


    public void testSearchdate() {

    }

}
