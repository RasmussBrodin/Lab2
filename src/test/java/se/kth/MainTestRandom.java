package se.kth;

// For judning testcases
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import static org.junit.jupiter.api.Assertions.fail;

// For reading and writing testcases 
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainTestRandom {
    static int ARRAY_LENGHT = 20;
    static int MIN_VALUE = -20;
    static int MAX_VALUE = 20;
    final static int NUM_OF_TESTS = 100;

    @BeforeAll
    public static void setUp(){
        randomArray(ARRAY_LENGHT, MIN_VALUE, MAX_VALUE, NUM_OF_TESTS);
    }

    @RepeatedTest(NUM_OF_TESTS)
    public void testRandom(RepetitionInfo testNumber) {
        String fileName = "src/test/java/se/kth/random.txt";
        List<String> lines = new ArrayList<>();
        
        // Read all lines (arrays) from the file.
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            fail(e.getMessage());
        }
        
        // Get the array for the current repetition.
        int index = testNumber.getCurrentRepetition() - 1;
        
        String line = lines.get(index);

        // Split the line by space
        String[] currentTest = line.split(" ");
        int[] array = new int[ARRAY_LENGHT];
        for (int i = 0; i < ARRAY_LENGHT; i++) {
                try {
                    array[i] = (Integer.parseInt(currentTest[i]));
                } catch (NumberFormatException e) {
                    fail(e.getMessage());
                }
        }
    
        // Test 1: Pick a random index as key from the array and test memberUnsorted
        Random rnd = new Random();
        int key = rnd.nextInt(MIN_VALUE, MAX_VALUE*2);
        boolean result = Main.memberUnsorted(array, key);
        boolean expectedResult = computeExpected(array, key);

        if(result != expectedResult){
            System.out.println("Bug found at iteration: " + index);
            fail();
        }
    }

    public static void randomArray(int ARRAY_LENGTH, int MIN_VALUE, int MAX_VALUE, int NUM_OF_TESTS) {
        Random rnd = new Random();
        String fileName = "src/test/java/se/kth/random.txt";
        
        try (PrintWriter out = new PrintWriter(new FileWriter(fileName))) {
            for (int test = 0; test < NUM_OF_TESTS; test++) {
                for (int i = 0; i < ARRAY_LENGTH; i++) {
                    out.print(rnd.nextInt(MIN_VALUE, MAX_VALUE) + " ");
                }

                out.println();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
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