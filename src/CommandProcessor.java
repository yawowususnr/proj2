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
                switch(command) {
                    case "insert":
                        String Id = sc.next();
                        String title = sc.nextLine();
                        String chunkOfInfo = sc.nextLine();
                        String[] singleLine = chunkOfInfo.split("\\s+");
                        String keyWords = sc.nextLine();
                        String[] arrayOfKeyWords = keyWords.split("\\s+");
                        String description = sc.nextLine();
                        control.insert(Integer.parseInt(Id), title, singleLine[0], Integer.parseInt(singleLine[1]), Short.parseShort(singleLine[2]), Short.parseShort(singleLine[3]), Integer.parseInt(singleLine[4]), arrayOfKeyWords, description);
                        
                    
                        
                        
                        
                        
                    
                }
                
            }
            
          
            
            
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }


  

}
