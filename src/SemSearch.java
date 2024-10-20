/**
 * Main entry point for the SemSearch application.
 * This class contains the main method that initializes the application
 * and processes commands based on the provided input parameters.
 * 
 * The application is designed to perform spatial searches for seminar 
 * records within a specified world size, utilizing a controller to 
 * manage the application's state and a command processor to handle 
 * input commands from a specified file.
 * 
 * @author Yaw Agyemang	
 * @author Yaw Owusu Jnr
 * @version 10/18/24
 */

// On my honor:
// - I have not used source code obtained from another current or
// former student, or any other unauthorized source, either
// modified or unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors, or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

public class SemSearch {
    /**
     * The main method that serves as the entry point for the SemSearch application.
     * 
     * @param args Command line parameters where args[0] specifies the world size 
     *             and args[1] specifies the filename from which commands will 
     *             be processed.
     */
    public static void main(String[] args) {
        if (args.length == 2) {
            int worldSize = Integer.parseInt(args[0]);
            String filename = args[1];

            Controller controller = new Controller(worldSize);

            CommandProcessor processor = new CommandProcessor(filename,
                controller);
            processor.processFile();
        }
    }
}
