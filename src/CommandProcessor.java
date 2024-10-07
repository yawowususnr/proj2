import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// -------------------------------------------------------------------------

/**
 * Processes commands from a file to perform operations.
 * 
 * @author
 * @author Chris Nicoue-Beglah
 * @version <2024-09-20>
 */
public class CommandProcessor {

    private static String fileName;

    /**
     * Initializes CommandProcessor with specified hash table size and file
     * name.
     * 
     * @param size
     *            the size of the hash table
     * @param name
     *            the command file name
     */
    public CommandProcessor(String name, Controller control) {
        try {
            fileName = name;
            Scanner sc = new Scanner(new File(fileName));
            String command;
            String arg0;
            while (sc.hasNext()) {
                command = sc.next();
                switch (command) {
                    case "insert":
                        String Id = sc.next().trim();
                        String title = sc.nextLine();
                        String chunkOfInfo = sc.nextLine();
                        String[] singleLine = chunkOfInfo.split("\\s+");
                        Scanner keyScan = new Scanner(new File(sc.nextLine()));
                        String[] keyWords = new String[100];
                        int len = 0;
                        while (keyScan.hasNext()) {
                            keyWords[len] = keyScan.next();
                            len++;
                        }
                        String description = sc.nextLine();
                        control.insert(Integer.parseInt(Id), title,
                            singleLine[0], Integer.parseInt(singleLine[1]),
                            Short.parseShort(singleLine[2]), Short.parseShort(
                                singleLine[3]), Integer.parseInt(singleLine[4]),
                            keyWords, len, description);
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

                            case "location":
                                String[] location = new String[3];
                                location[0] = sc.next();
                                location[1] = sc.next();
                                location[2] = sc.next();
                                control.searchLocation(Short.parseShort(
                                    location[0]), Short.parseShort(location[0]),
                                    Short.parseShort(location[0]));

                            case "ID":
                                String id = sc.next();
                                control.searchId(Integer.parseInt(id));

                            case "date":
                                String[] dates = new String[3];
                                dates[0] = sc.next();
                                dates[1] = sc.next();
                                control.searchDate(dates[0], dates[1]);
                            case "cost":
                                String[] cost = new String[3];
                                cost[0] = sc.next();
                                cost[1] = sc.next();
                                int lowerCost = Integer.parseInt(cost[0]);
                                int upperCost = Integer.parseInt(cost[1]);
                                control.searchCost(lowerCost, upperCost);
                        }

                    case "print":
                        String printType = sc.next();
                        control.print(printType);

                }

            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

}
