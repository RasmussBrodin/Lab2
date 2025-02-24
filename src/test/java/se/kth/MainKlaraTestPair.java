package se.kth;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class MainKlaraTestPair {
    int ARRAY_LENGTH = 20;
    
    // This method represents our oracle: a simple linear search.
    public boolean validResult(int[] array, int key, boolean result) {
        for (int value : array) {
            if (value == key) {
                return result; // Must be true if key is found
            }
        }
        return !result; // If key not found must be false
    }
    
    // Generate an array filled with a default value (0) and override the two positions with v1 and v2.
    public int[] generatePairwiseArray(int pos1, int pos2, int v1, int v2) {
        int[] array = new int[ARRAY_LENGTH];
        // Fill with default value (0)
        Arrays.fill(array, 0);
        array[pos1] = v1;
        array[pos2] = v2;
        return array;
    }
    
    @Test
    public void pairWiseTestingAllPairs() {
        // File to write test cases
        try (FileWriter writer = new FileWriter("pairwiseAllPairsTests.txt")) {
            int testCaseCount = 0;
            // Loop over every distinct pair of indices in the array
            for (int i = 0; i < ARRAY_LENGTH; i++) {
                for (int j = i + 1; j < ARRAY_LENGTH; j++) {
                    // For each pair, consider all combinations for positions i and j using {0,1}
                    for (int v1 = 0; v1 <= 1; v1++) {
                        for (int v2 = 0; v2 <= 1; v2++) {
                            // Generate the array with the two positions set to v1 and v2.
                            int[] array = generatePairwiseArray(i, j, v1, v2);
                            
                            // Now, for each test array, test for both key values: 0 and 1.
                            for (int key = 0; key <= 1; key++) {
                                testCaseCount++;
                                // Call the memberUnsorted function from your Main class.
                                // Clone the array because sorting may modify it.
                                boolean result = Main.memberUnsorted(array.clone(), key);
                                boolean expected = computeExpected(array, key);
                                
                                // Write the test case details to the file.
                                writer.write("Test Case " + testCaseCount + ": Array = " + Arrays.toString(array)
                                        + ", Key = " + key + ", Expected = " + expected
                                        + ", Result = " + result + "\n");
                                
                                // If there is a discrepancy, log details and fail the test.
                                if (expected != result) {
                                    writer.write("BUG FOUND in Test Case " + testCaseCount + "\n");
                                    writer.write("Array: " + Arrays.toString(array) + "\n");
                                    writer.write("Key: " + key + "\n");
                                    writer.flush();
                                    fail("Bug found in test case " + testCaseCount);
                                }
                            }
                        }
                    }
                }
            }
            writer.write("Total test cases executed: " + testCaseCount + "\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
            fail("IOException occurred while writing test cases.");
        }
    }
    
    // The oracle implementation: a simple linear search.
    // Returns true if key is present in the array, false otherwise.
    public boolean computeExpected(int[] array, int key) {
        for (int value : array) {
            if (value == key) {
                return true;
            }
        }
        return false;
    }
}
