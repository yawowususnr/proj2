import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// -------------------------------------------------------------------------

/**
 * Processes commands from a file to perform operations.
 * 
 * @author
 * @author Yaw Owusu Snr
 * @version <2024-09-20>
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

    public void processFile() {
        try (Scanner sc = new Scanner(new File(fileName))) {
            String command;
            while (sc.hasNext()) {
                command = sc.next();
                switch (command) {
                    case "insert":
                        String Id = sc.next().trim();
                        sc.nextLine();
                        String title = sc.nextLine();
//                        sc.nextLine();
                        String chunkOfInfo = sc.nextLine().trim(); 
                        String[] singleLine = chunkOfInfo.split("\\s+");   
                        
                        String keywords = sc.nextLine().trim();
                   
                        String[] keywordArray = keywords.split("\\s+");   
                        

  
                            String description = sc.nextLine().trim();
                            control.insert(Integer.parseInt(Id), title,
                                singleLine[0], Integer.parseInt(singleLine[1]),
                                Short.parseShort(singleLine[2]), 
                                Short.parseShort(singleLine[3]),
                                Integer.parseInt(singleLine[4]), 
                                keywordArray, keywordArray.length, description);
                        break;
                    case "delete":
                        String ID = sc.next();
                        control.delete(Integer.parseInt(ID));
                        break;
                    case "search":
                        String type = sc.next();
                        switch (type) {
                            case "keyword":
                                String newWord = sc.next();
                                control.searchkeyword(newWord);
                                break;
                            case "location":
                                String[] location = new String[3];  
                                location[0] = sc.next();
                                location[1] = sc.next();
                                location[2] = sc.next();
                                 control.searchLocation(Short.parseShort(location[0]), 
                                    Short.parseShort(location[1]), Short.parseShort(location[2]));
                                break;
                            case "ID":
                                String id = sc.next();
                                control.searchId(Integer.parseInt(id));
                                break;
                            case "date":
                                String[] dates = new String[2];
                                dates[0] = sc.next();
                                dates[1] = sc.next();
                                control.searchDate(dates[0], dates[1]);
                                break;
                            case "cost":
                                String[] cost = new String[2]; 
                                cost[0] = sc.next();
                                cost[1] = sc.next();
                                int lowerCost = Integer.parseInt(cost[0]);
                                int upperCost = Integer.parseInt(cost[1]);
                                control.searchCost(lowerCost, upperCost);
                                break;
                        }
                        break;
                    case "print":
                        String printType = sc.next();
                        control.print(printType);
                        break;
                    default:
                        System.out.println("Unknown command: " + command);
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
