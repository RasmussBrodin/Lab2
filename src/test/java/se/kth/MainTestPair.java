package se.kth;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MainTestPair {
    int ARRAY_LENGHT = 20;
    int NUM_OF_TESTS = 20;

    /*
    Treating the array as an N + 1 seperate variables
    Parameters tested:
        1. Parameter key = {0, 1} 0 is default
        2. Parameter a[i] = {0, 1} 0 is defualt, 0 <= i < a.length
    */
    
    @Test
    public void pairWiseTesting(){
        int pos = 0;
        boolean result;
        boolean valid;
        int[] array = new int[ARRAY_LENGHT];
        for(int i = 0; i < ARRAY_LENGHT; i++){
            array = getArray(pos, 1, 0);
            pos++;
            result = Main.memberUnsorted(array, 1);
            valid = validResult(array, 1, result);
            if(!valid){
                System.out.println("Bug found at test number: " + i);
                System.out.println("result: " + result);
                System.out.println("valid: " + valid);
                System.out.println("key: " + 1);
                Main.printArray(array);
                fail();
            }
        }
        pos = 0;
        for(int i = 0; i < ARRAY_LENGHT; i++){
            array = getArray(pos, 0, 1);
            pos++;
            result = Main.memberUnsorted(array, 0);
            valid = validResult(array, 0, result);
            if(!valid){
                System.out.println("Bug found at test number: " + (i + ARRAY_LENGHT));
                System.out.println("result: " + result);
                System.out.println("valid: " + valid);
                System.out.println("key: " + 0);
                Main.printArray(array);
                fail();
            }
        }
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

    public boolean validResult(int[] original, int key, boolean result){
        for(int i = 0; i < ARRAY_LENGHT; i++){
            if(original[i] == key){
                if(result == false){
                    return false;
                } else{
                    return true;
                }
            }
        }
        return true;
    }
}