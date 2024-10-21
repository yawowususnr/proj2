import student.TestCase;

/**
 * Unit tests for the Controller class, which manages seminar records.
 * Tests cover CRUD operations and validation logic including:
 * - Record insertion
 * - Search operations (by ID, cost, keywords, date, location)
 * - Coordinate validation
 * 
 * @author Yaw Agyemang    
 * @author Yaw Owusu Jnr
 * @version 10/18/24
 */
public class ControllerTest extends TestCase {

    private Controller controller;

    /**
     * Sets up a fresh Controller instance before each test.
     * Initializes with a capacity of 100 records.
     */
    public void setUp() {
        this.controller = new Controller(100);
    }

    /**
     * Tests record insertion functionality including:
     * - Successful insertion of a valid record
     * - Handling of duplicate ID attempts
     * - Verification of stored record details
     */
    public void testInsertRecordAndVerifyDetails() {
        String[] keywords = { "Balloon", "Popping" };

        // Test successful insertion
        controller.insert(1, "Balloon Seminar", "2024-10-01", 60, (short)10,
            (short)20, 200, keywords, keywords.length, "Learn Balloon");
        
        String expectedFirstInsert = "Successfully inserted record with ID 1\r\n"
            + "ID: 1, Title: Balloon Seminar\r\n"
            + "Date: 2024-10-01, Length: 60, X: 10, Y: 20, Cost: 200\r\n"
            + "Description: Learn Balloon\r\n"
            + "Keywords: Balloon, Popping\r\n";
        
        assertFuzzyEquals(systemOut().getHistory(), expectedFirstInsert);

        // Test duplicate ID handling
        controller.insert(1, "Duplicate Seminar", "2024-10-02", 30, (short)15,
            (short)25, 300, keywords, keywords.length, "Duplicate Balloon Seminar");
    }

    /**
     * Tests the record retrieval by ID functionality including:
     * - Finding an existing record
     * - Handling non-existent ID searches
     * - Verifying returned record details
     */
    public void testFindRecordById() {
        String[] keywords = { "Balloon", "Popping" };
        controller.insert(1, "Balloon Seminar", "2024-10-01", 60, (short)10,
            (short)20, 200, keywords, keywords.length, "Learn Balloon");
        controller.searchId(1);
        controller.searchId(2);
        
        String expected = "Successfully inserted record with ID 1\r\n"
            + "ID: 1, Title: Balloon Seminar\r\n"
            + "Date: 2024-10-01, Length: 60, X: 10, Y: 20, Cost: 200\r\n"
            + "Description: Learn Balloon\r\n"
            + "Keywords: Balloon, Popping\r\n"
            + "Found record with ID 1:\r\n"
            + "ID: 1, Title: Balloon Seminar\r\n"
            + "Date: 2024-10-01, Length: 60, X: 10, Y: 20, Cost: 200\r\n"
            + "Description: Learn Balloon\r\n"
            + "Keywords: Balloon, Popping\r\n"
            + "Search FAILED -- There is no record with ID 2";
        assertFuzzyEquals(systemOut().getHistory(), expected);
    }

    /**
     * Tests searching records within a specified cost range:
     * - Finding records within the given cost bounds
     * - Verifying the search traversal count
     * - Checking the format and content of returned results
     */
    public void testFindRecordsInCostRange() {
        String[] keywords = { "Balloon", "Popping" };
        controller.insert(1, "Balloon Seminar", "20241001", 60, (short)10,
            (short)20, 200, keywords, keywords.length, "Learn Balloon");
        controller.searchCost(200, 600);
        
        String expected = "Successfully inserted record with ID 1\r\n"
            + "ID: 1, Title: Balloon Seminar\r\n"
            + "Date: 20241001, Length: 60, X: 10, Y: 20, Cost: 200\r\n"
            + "Description: Learn Balloon\r\n"
            + "Keywords: Balloon, Popping\r\n"
            + "Seminars with costs in range 200 to 600:\r\n"
            + "ID: 1, Title: Balloon Seminar\r\n"
            + "Date: 20241001, Length: 60, X: 10, Y: 20, Cost: 200\r\n"
            + "Description: Learn Balloon\r\n"
            + "Keywords: Balloon, Popping\r\n"
            + "3 nodes visited in this search";
        assertFuzzyEquals(systemOut().getHistory(), expected);
    }

    /**
     * Tests the keyword-based search functionality:
     * - Finding records matching specific keywords
     * - Case sensitivity handling
     * - Output format verification
     */
    public void testFindRecordsByKeyword() {
        String[] keywords = { "Balloon", "Popping" };
        controller.insert(1, "Balloon Seminar", "20241001", 60, (short)10,
            (short)20, 200, keywords, keywords.length, "Learn Balloon");
        controller.searchkeyword("Balloon");
        
        String expected = "Successfully inserted record with ID 1\r\n"
            + "ID: 1, Title: Balloon Seminar\r\n"
            + "Date: 20241001, Length: 60, X: 10, Y: 20, Cost: 200\r\n"
            + "Description: Learn Balloon\r\n"
            + "Keywords: Balloon, Popping\r\n"
            + "Seminars matching keyword Balloon:\r\n"
            + "ID: 1, Title: Balloon Seminar\r\n"
            + "Date: 20241001, Length: 60, X: 10, Y: 20, Cost: 200\r\n"
            + "Description: Learn Balloon\r\n"
            + "Keywords: Balloon, Popping";
        assertFuzzyEquals(systemOut().getHistory(), expected);
    }

    /**
     * Tests searching records within a date range:
     * - Finding records between start and end dates
     * - Date format handling
     * - Verifying search traversal count
     */
    public void testFindRecordsInDateRange() {
        String[] keywords = { "Balloon", "Popping" };
        controller.insert(1, "Balloon Seminar", "20241001", 60, (short)10,
            (short)20, 200, keywords, keywords.length, "Learn Balloon");
        controller.insert(1, "Balloon Seminar", "20231001", 60, (short)10,
            (short)20, 200, keywords, keywords.length, "Learn Balloon");
        controller.searchDate("20231001", "20241001");
        
        String expected = "Successfully inserted record with ID 1\r\n"
            + "ID: 1, Title: Balloon Seminar\r\n"
            + "Date: 20241001, Length: 60, X: 10, Y: 20, Cost: 200\r\n"
            + "Description: Learn Balloon\r\n"
            + "Keywords: Balloon, Popping\r\n"
            + "Insert FAILED - There is already a record with ID 1\r\n"
            + "Seminars with dates in range 20231001 to 20241001:\r\n"
            + "ID: 1, Title: Balloon Seminar\r\n"
            + "Date: 20241001, Length: 60, X: 10, Y: 20, Cost: 200\r\n"
            + "Description: Learn Balloon\r\n"
            + "Keywords: Balloon, Popping\r\n"
            + "2 nodes visited in this search";
        assertFuzzyEquals(systemOut().getHistory(), expected);
    }

    /**
     * Tests searching records within a specified radius of coordinates:
     * - Finding records within distance of given x,y coordinates
     * - Distance calculation verification
     * - Search traversal counting
     */
    public void testFindRecordsNearLocation() {
        String[] keywords = { "Balloon", "Popping" };
        controller.insert(1, "Balloon Seminar", "2024-10-01", 60, (short)10,
            (short)20, 200, keywords, keywords.length, "Learn Balloon");
        controller.searchLocation(10, 20, 100);
        
        String expected = "Successfully inserted record with ID 1\r\n"
            + "ID: 1, Title: Balloon Seminar\r\n"
            + "Date: 2024-10-01, Length: 60, X: 10, Y: 20, Cost: 200\r\n"
            + "Description: Learn Balloon\r\n"
            + "Keywords: Balloon, Popping\r\n"
            + "Seminars within 100 units of 10, 20:\r\n"
            + "Found a record with key value 1 at 10, 20\r\n"
            + "1 nodes visited in this search";
        assertFuzzyEquals(systemOut().getHistory(), expected);
    }

    /**
     * Tests coordinate validation logic with various boundary cases:
     * - Valid coordinates within bounds
     * - Invalid coordinates (negative values)
     * - Edge cases at coordinate boundaries
     * - Combined invalid x,y coordinates
     */
    public void testValidateCoordinates() {
        // Test valid coordinates
        assertTrue(controller.checkIfValid(1, 3));
        assertTrue(controller.checkIfValid(98, 3));
        
        // Test invalid coordinates
        assertFalse(controller.checkIfValid(102, 3));
        assertFalse(controller.checkIfValid(-2, 3));
        
        // Test boundary cases
        assertTrue(controller.checkIfValid(3, 1));
        assertTrue(controller.checkIfValid(3, 8));
        assertFalse(controller.checkIfValid(3, 102));
        assertFalse(controller.checkIfValid(3, -2));
        
        // Test combined invalid cases
        assertFalse(controller.checkIfValid(-2, -2));
        assertFalse(controller.checkIfValid(101, 101));
        assertFalse(controller.checkIfValid(-2, 3));
        assertFalse(controller.checkIfValid(3, -2));
        assertFalse(controller.checkIfValid(1002, 0));
        assertFalse(controller.checkIfValid(0, 1002));
    }
}