import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import student.TestCase;

/**
 * Test class for the SemSearch functionality. This class extends TestCase and
 * provides unit tests for the SemSearch class. It includes methods to set up
 * tests, read file contents, and validate the input-output functionality of the
 * SemSearch application.
 * 
 * @author Yaw Agyemang
 * @author Yaw Owusu Jnr
 * @version 10/18/24
 */
public class SemSearchTest extends TestCase {

	/**
	 * Sets up the tests that follow. This method is used for initialization before
	 * each test method. Currently, it does not perform any setup actions.
	 */
	public void setUp() {
		// No initialization required
	}

	/**
	 * Reads the contents of a file specified by the given path.
	 *
	 * @param path the path to the file to be read
	 * @return the contents of the file as a String
	 * @throws IOException if an I/O error occurs while reading from the file
	 */
	static String readFile(String path) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded);
	}

	/**
	 * Tests the sample input and output for the SemSearch class. This method
	 * invokes the main method of SemSearch with predefined arguments and compares
	 * the actual output to the expected output read from a specified output file.
	 *
	 * @throws Exception if an error occurs during the test execution
	 */
	public void testSampleIO() throws Exception {

		String[] args = new String[2];
		args[0] = "128";
		args[1] = "solutionTestData/P2_sampleInput.txt";

		String[] args2 = new String[1];
		args2[0] = "100";

		SemSearch.main(args2);
		SemSearch.main(args);

		String actualOutput = systemOut().getHistory();
		String expectedOutput = readFile("solutionTestData/P2_sampleOutput.txt");

		assertFuzzyEquals(expectedOutput, actualOutput);
	}
}
