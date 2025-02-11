package se.kth;

import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

public class MainTestPair {
    int ARRAY_LENGHT = 10;
    int NO_KEY = 5;

    /*
    Treating the array as an input variable of length N
    Parameters tested:
        1. Sorting of the array (sorted, reverse, unsorted)
        2. Location of key (start, middle, end, not present)
    */

    String[][] testCases = {
        { "sorted", "start"},
        { "reverse", "middle"},
        { "unsorted", "end"},
        { "sorted", "not present"},
        { "reverse", "start"},
        { "unsorted", "middle"}
    };
    @RepeatedTest(1)
    public void pairWiseTesting(){
        for(int i = 0; i < testCases.length; i++){
            int [] array = getArray(testCases[i][0], testCases[i][1]);

            switch (testCases[i][1]) {
                case "start":
                    assertNotEquals(-1, Main.memberUnsorted(array, array[0]));
                case "end":
                    assertNotEquals(-1, Main.memberUnsorted(array, array[9]));
                case "middle":
                    assertNotEquals(-1, Main.memberUnsorted(array, array[4]));
                case "not present":
                    assertEquals(-1, Main.memberUnsorted(array, NO_KEY));
            }
        }
    }

    public int[] getArray(String sorting, String keyPos){
        switch (sorting) {
            case "sorted":
                int[] sortedArray = new int[]{-2,-1,0,2,3,4,6,7,8,9};
                return sortedArray;
            case "reverse":
                int[] reverseArray = new int[]{9,8,7,6,4,3,2,0,-1,-2};
                return reverseArray;
            default:
                int[] unsortedArray = new int[]{4,-1,4,9,-3,1,0,-2,6,8};
                return unsortedArray;
        }
    }
}