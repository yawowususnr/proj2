import student.TestCase;

/**
 * 
 */

public class ControllerTest extends TestCase {

    private Controller controller;

    public void setUp() {
        this.controller = new Controller(100);
    }


    // ----------------------------------------------------------
    /**
     * tests insert
     */
    public void testInsert() {
        String[] keywords = { "Java", "Programming" };
        controller.insert(1, "Java Seminar", "2024-10-01", 60, (short)10,
            (short)20, 500, keywords, keywords.length, "Learn Java");
        controller.insert(1, "Java Seminar", "2024-10-01", 60, (short)210,
            (short)220, 500, keywords, keywords.length, "Learn Java");
        controller.insert(2, "Java Seminar", "2024-10-01", 60, (short)210,
            (short)220, 500, keywords, keywords.length, "Learn Java");
        String newStr = "Successfully inserted record with ID 1\r\n"
            + "ID: 1, Title: Java Seminar\r\n"
            + "Date: 2024-10-01, Length: 60, X: 10, Y: 20, Cost: 500\r\n"
            + "Description: Learn Java\r\n" + "Keywords: Java, Programming\r\n"
            + "Insert FAILED - There is already a record with ID 1\r\n"
            + "Insert FAILED - Bad x, y coordinates: 210, 220";
        assertFuzzyEquals(systemOut().getHistory(), newStr);

    }


    // ----------------------------------------------------------
    /**
     * tests search ID
     */
    public void testSearchId() {
        String[] keywords = { "Java", "Programming" };
        controller.insert(1, "Java Seminar", "2024-10-01", 60, (short)10,
            (short)20, 500, keywords, keywords.length, "Learn Java");
        controller.searchId(1);
        controller.searchId(2);
        String str = "Successfully inserted record with ID 1\r\n"
            + "ID: 1, Title: Java Seminar\r\n"
            + "Date: 2024-10-01, Length: 60, X: 10, Y: 20, Cost: 500\r\n"
            + "Description: Learn Java\r\n" + "Keywords: Java, Programming\r\n"
            + "Found record with ID 1:\r\n" + "ID: 1, Title: Java Seminar\r\n"
            + "Date: 2024-10-01, Length: 60, X: 10, Y: 20, Cost: 500\r\n"
            + "Description: Learn Java\r\n" + "Keywords: Java, Programming\r\n"
            + "Search FAILED -- There is no record with ID 2";
        assertFuzzyEquals(systemOut().getHistory(), str);

    }


   


    // ----------------------------------------------------------
    /**
     * Tests search Cost
     */
    public void testSearchCost() {
        String[] keywords = { "Java", "Programming" };
        controller.insert(1, "Java Seminar", "20241001", 60, (short)10,
            (short)20, 500, keywords, keywords.length, "Learn Java");
        controller.searchCost(200, 600);
        String str = "Successfully inserted record with ID 1\r\n"
            + "ID: 1, Title: Java Seminar\r\n"
            + "Date: 20241001, Length: 60, X: 10, Y: 20, Cost: 500\r\n"
            + "Description: Learn Java\r\n" + "Keywords: Java, Programming\r\n"
            + "Seminars with costs in range 200 to 600:\r\n"
            + "ID: 1, Title: Java Seminar\r\n"
            + "Date: 20241001, Length: 60, X: 10, Y: 20, Cost: 500\r\n"
            + "Description: Learn Java\r\n" + "Keywords: Java, Programming\r\n"
            + "3 nodes visited in this search";
        assertFuzzyEquals(systemOut().getHistory(), str);

    }


    // ----------------------------------------------------------
    /**
     * test Search keywords
     */
    public void testSearchKeyWords() {
        String[] keywords = { "Java", "Programming" };
        controller.insert(1, "Java Seminar", "20241001", 60, (short)10,
            (short)20, 500, keywords, keywords.length, "Learn Java");
        controller.searchkeyword("Java");
        String str = "Successfully inserted record with ID 1\r\n"
            + "ID: 1, Title: Java Seminar\r\n"
            + "Date: 20241001, Length: 60, X: 10, Y: 20, Cost: 500\r\n"
            + "Description: Learn Java\r\n" + "Keywords: Java, Programming\r\n"
            + "Seminars matching keyword Java:\r\n"
            + "ID: 1, Title: Java Seminar\r\n"
            + "Date: 20241001, Length: 60, X: 10, Y: 20, Cost: 500\r\n"
            + "Description: Learn Java\r\n" + "Keywords: Java, Programming";
        assertFuzzyEquals(systemOut().getHistory(), str);

    }


    // ----------------------------------------------------------
    /**
     * test search date
     */
    public void testSearchdate() {
        String[] keywords = { "Java", "Programming" };
        controller.insert(1, "Java Seminar", "20241001", 60, (short)10,
            (short)20, 500, keywords, keywords.length, "Learn Java");
        String[] keyword = { "Java", "Programming" };
        controller.insert(1, "Java Seminar", "20231001", 60, (short)10,
            (short)20, 500, keywords, keywords.length, "Learn Java");
        controller.searchDate("20231001", "20241001");
        String str = "Successfully inserted record with ID 1\r\n"
            + "ID: 1, Title: Java Seminar\r\n"
            + "Date: 20241001, Length: 60, X: 10, Y: 20, Cost: 500\r\n"
            + "Description: Learn Java\r\n" + "Keywords: Java, Programming\r\n"
            + "Insert FAILED - There is already a record with ID 1\r\n"
            + "Seminars with dates in range 20231001 to 20241001:\r\n"
            + "ID: 1, Title: Java Seminar\r\n"
            + "Date: 20241001, Length: 60, X: 10, Y: 20, Cost: 500\r\n"
            + "Description: Learn Java\r\n" + "Keywords: Java, Programming\r\n"
            + "2 nodes visited in this search";
        assertFuzzyEquals(systemOut().getHistory(), str);

    }


    // ----------------------------------------------------------
    /**
     * test serach location
     */
    public void testSearchLocation() {
        String[] keywords = { "Java", "Programming" };
        controller.insert(1, "Java Seminar", "2024-10-01", 60, (short)10,
            (short)20, 500, keywords, keywords.length, "Learn Java");
        controller.searchLocation(10, 20, 100);
        String str = "Successfully inserted record with ID 1\r\n"
            + "ID: 1, Title: Java Seminar\r\n"
            + "Date: 2024-10-01, Length: 60, X: 10, Y: 20, Cost: 500\r\n"
            + "Description: Learn Java\r\n" + "Keywords: Java, Programming\r\n"
            + "Seminars within 100 units of 10, 20:\r\n"
            + "Found a record with key value 1 at 10, 20\r\n"
            + "1 nodes visited in this search";
        assertFuzzyEquals(systemOut().getHistory(), str);

    }


    // ----------------------------------------------------------
    /**
     * test checks valid
     */
    public void testcheckValid() {
        assertTrue(controller.checkIfValid(0, 5)); // Lower boundary for x
        assertTrue(controller.checkIfValid(99, 5)); // Upper boundary for x
        assertFalse(controller.checkIfValid(101, 5)); // Out of bounds for x
        assertFalse(controller.checkIfValid(-1, 5)); // Negative x
        assertTrue(controller.checkIfValid(5, 0)); // Lower boundary for y
        assertTrue(controller.checkIfValid(5, 9)); // Upper boundary for y
        assertFalse(controller.checkIfValid(5, 101)); // Out of bounds for y
        assertFalse(controller.checkIfValid(5, -1)); // Negative y
        assertFalse(controller.checkIfValid(-1, -1)); // Both x and y out of
                                                      // bounds
        assertFalse(controller.checkIfValid(100, 100)); // Both x and y out of
                                                        // bounds
        assertFalse(controller.checkIfValid(-1, 5)); // Invalid x, valid y
        assertFalse(controller.checkIfValid(5, -1)); // Valid x, invalid y
        assertFalse(controller.checkIfValid(1001, 0)); // Invalid x, valid y
        assertFalse(controller.checkIfValid(0, 1001)); // Valid x, invalid y

    }

}
