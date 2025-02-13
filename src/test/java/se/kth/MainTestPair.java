package se.kth;

import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

public class MainTestPair {
    int ARRAY_LENGHT = 10;

    /*
    Treating the array as an N + 1 seperate variables
    Parameters tested:
        1. Parameter key = {0, 1} 0 is default
        2. Parameter a[i] = {0, 1} 0 is defualt, 0 <= i < a.length
    */
    
    @RepeatedTest(1)
    public void pairWiseTesting(){
        int[] array = getArray(3, 1, 0);
        Main.printArray(array);
    }

    public int[] getArray(int pos, int val, int notVal){
        int[] array = new int[ARRAY_LENGHT];
        for(int i = 0; i < ARRAY_LENGHT; i++){
            array[i] = notVal;
        }
        array[pos] = val;
        return array;
    }
}