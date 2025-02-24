package se.kth;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MainTestPair {
    int ARRAY_LENGTH = 20;
    
    // This method represents our oracle. Returns true if key is found in the array, false otherwise.
    public boolean validResult(int[] array, int key, boolean result) {
        for (int value : array) {
            if (value == key) {
                return result;
            }
        }
        return !result; 
    }
    
    // Generate an array filled with a default value(0) and change the two positions with v1 and v2.
    public int[] generatePairwiseArray(int pos1, int pos2, int v1, int v2) {
        int[] array = new int[ARRAY_LENGTH];
        array[pos1] = v1;
        array[pos2] = v2;
        return array;
    }
    
    @Test
    public void pairWiseTestingAllPairs() {
        // File to write test cases
        try (FileWriter writer = new FileWriter("src/test/java/se/kth/pairWise.txt")) {
            int testCaseCount = 0;

            // Set to track unique test cases
            Set<String> uniqueTestCases = new HashSet<>();
            
            // Loop over every pair of indices in the array
            for (int i = 0; i < ARRAY_LENGTH; i++) {
                for (int j = i + 1; j < ARRAY_LENGTH; j++) {
                    // For each pair, consider all combinations for positions i and j using {0,1}
                    for (int v1 = 0; v1 <= 1; v1++) {
                        for (int v2 = 0; v2 <= 1; v2++) {
                            // Generate the array with the two positions set to v1 and v2.
                            int[] array = generatePairwiseArray(i, j, v1, v2);
                            
                            // Now, for each test array, test for both key values: 0 and 1.
                            for (int key = 0; key <= 1; key++) {
                                // For uniqueness.
                                String testCaseRepresentation = Arrays.toString(array) + " Key:" + key;

                                // Skip if we already processed this test case.
                                if (uniqueTestCases.contains(testCaseRepresentation)) {
                                    continue;
                                }
                                uniqueTestCases.add(testCaseRepresentation);
                                
                                testCaseCount++;

                                // Call the memberUnsorted function
                                boolean result = Main.memberUnsorted(array, key);
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
            writer.write("Total unique test cases executed: " + testCaseCount + "\n");
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