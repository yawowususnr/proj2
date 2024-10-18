import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import student.TestCase;

/**
 * @author Yaw Owusu Snr
 */
public class SemSearchTest extends TestCase {
    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        // Nothing here
    }


    /**
     * Get code coverage of the class declaration.
     */
//    public void testMInitx()
//    {
//        SemSearch sem = new SemSearch();
//        assertNotNull(sem);
//        SemSearch.main(null);
//    }
    
    static String readFile(String path)
        throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded);
    }
    
    public void testSampleIO()
        throws Exception
    {

        String[] args = new String[2];
        args[0] = "128";
        args[1] = "solutionTestData/P2_sampleInput.txt";
//        args[1] = "solutionTestData/testInput.txt";
        

        String[] args2 = new String[1];
        args2[0] = "100";
        
        SemSearch.main(args2);

        // Invoke main method of our Graph Project
        SemSearch.main(args);


        // Actual output from your System console
        String actualOutput = systemOut().getHistory();

        // Expected output from file
        String expectedOutput =
            readFile("solutionTestData/P2_sampleOutput.txt");
//        String expectedOutput =
//            readFile("solutionTestData/testOutput.txt");

        // Compare the two outputs
        // once you have implemented your project
        assertFuzzyEquals(expectedOutput, actualOutput);

    }
}

