import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// -------------------------------------------------------------------------

/**
 * Processes commands from a file to perform operations.
 * 
 * @author Yaw Agyemang
 * @author Yaw Owusu Jnr
 * @version 10/18/24
 */
public class CommandProcessor {

    private String fileName;
    private Controller control;

    /**
     * Initializes CommandProcessor with specified file name and controller.
     * 
     * @param name
     *            the command file name
     * @param control
     *            the controller to perform operations
     */
    public CommandProcessor(String name, Controller control) {
        this.fileName = name;
        this.control = control;
    }


    // ----------------------------------------------------------
    /**
     * Processes input file and all its commands.
     */
    public void processFile() {
        try (Scanner fileScanner = new Scanner(new File(fileName))) {
            String operation;
            while (fileScanner.hasNext()) {
                operation = fileScanner.next();

                // Handle 'insert' operation
                if (operation.equals("insert")) {
                    String itemId = fileScanner.next().trim();
                    fileScanner.nextLine();
                    String itemTitle = fileScanner.nextLine();
                    String detailsLine = fileScanner.nextLine().trim();
                    String[] detailsArray = detailsLine.split("\\s+");
                    String keywordString = fileScanner.nextLine().trim();
                    String[] keywordList = keywordString.split("\\s+");
                    String itemDescription = fileScanner.nextLine().trim();

                    control.insert(Integer.parseInt(itemId), itemTitle,
                        detailsArray[0], Integer.parseInt(detailsArray[1]),
                        Short.parseShort(detailsArray[2]), Short.parseShort(
                            detailsArray[3]), Integer.parseInt(detailsArray[4]),
                        keywordList, keywordList.length, itemDescription);
                }
                // Handle 'delete' operation
                else if (operation.equals("delete")) {
                    String deleteId = fileScanner.next();
                    control.delete(Integer.parseInt(deleteId));
                }
                // Handle 'search' operation
                else if (operation.equals("search")) {
                    String searchType = fileScanner.next();

                    // Search by keyword
                    if (searchType.equals("keyword")) {
                        String searchKeyword = fileScanner.next();
                        control.searchkeyword(searchKeyword);
                    }
                    // Search by location
                    else if (searchType.equals("location")) {
                        String[] locationDetails = new String[3];
                        locationDetails[0] = fileScanner.next();
                        locationDetails[1] = fileScanner.next();
                        locationDetails[2] = fileScanner.next();
                        control.searchLocation(Short.parseShort(
                            locationDetails[0]), Short.parseShort(
                                locationDetails[1]), Short.parseShort(
                                    locationDetails[2]));
                    }
                    // Search by ID
                    else if (searchType.equals("ID")) {
                        String searchId = fileScanner.next();
                        control.searchId(Integer.parseInt(searchId));
                    }
                    // Search by date
                    else if (searchType.equals("date")) {
                        String[] dateRange = new String[2];
                        dateRange[0] = fileScanner.next();
                        dateRange[1] = fileScanner.next();
                        control.searchDate(dateRange[0], dateRange[1]);
                    }
                    // Search by cost
                    else if (searchType.equals("cost")) {
                        String[] costRange = new String[2];
                        costRange[0] = fileScanner.next();
                        costRange[1] = fileScanner.next();
                        int minCost = Integer.parseInt(costRange[0]);
                        int maxCost = Integer.parseInt(costRange[1]);
                        control.searchCost(minCost, maxCost);
                    }
                }
                // Handle 'print' operation
                else if (operation.equals("print")) {
                    String printCategory = fileScanner.next();
                    control.print(printCategory);
                }
                // Handle unknown operation
                else {
                    System.out.println("Unknown command: " + operation);
                }
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
