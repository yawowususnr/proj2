import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import student.TestCase;

/**
 * Test class for the SemSearch functionality.
 * This class extends TestCase and provides unit tests for the SemSearch class.
 * 
 * @author Yaw Owusu Snr
 * @author Chris Nicoue-Beglah
 * @version 10/19/24
 */
public class SemSearchTest extends TestCase {
    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        // Nothing here
    }


    /**
     * Reads the content of a file specified by the given path.
     *
     * @param path
     *            the path to the file to be read
     * @return the contents of the file as a String
     * @throws IOException
     *             if an I/O error occurs reading from the file
     */
    static String readFile(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded);
    }


    /**
     * Tests the sample input and output for the SemSearch class.
     * This method invokes the main method of SemSearch with predefined
     * arguments and compares the actual output to the expected output
     * read from a file.
     *
     * @throws Exception
     *             if an error occurs during the test execution
     */
    public void testSampleIO() throws Exception {

        String[] args = new String[2];
        args[0] = "128";
        args[1] = "solutionTestData/P2_sampleInput.txt";
// args[1] = "solutionTestData/testInput.txt";

        String[] args2 = new String[1];
        args2[0] = "100";

        SemSearch.main(args2);

        // Invoke main method of our Graph Project
        SemSearch.main(args);

        // Actual output from your System console
        String actualOutput = systemOut().getHistory();

        // Expected output from file
        String expectedOutput = readFile(
            "solutionTestData/P2_sampleOutput.txt");
// String expectedOutput =
// readFile("solutionTestData/testOutput.txt");

        // Compare the two outputs
        // once you have implemented your project
        assertFuzzyEquals(expectedOutput, actualOutput);

    }
}
