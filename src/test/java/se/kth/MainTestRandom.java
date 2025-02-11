package se.kth;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import java.util.Random;


import static org.junit.jupiter.api.Assertions.*;

public class MainTestRandom {
    int ARRAY_LENGHT = 10;
    int MIN_VALUE = -200;
    int MAX_VALUE = 200;
    
    @RepeatedTest(100)
    public void testTrueMember(){
        int key = randomNumber(MIN_VALUE, MAX_VALUE);
        int pos = randomNumber(0, ARRAY_LENGHT - 1);
        int[] randomArray = randomArrayWithKeyAtPos(pos, key);
        int result = Main.memberUnsorted(randomArray, key);
        
        assertNotEquals(-1, result);
    }

    @RepeatedTest(100)
    public void testFalseMember(){
        int key = randomNumber(MIN_VALUE, MAX_VALUE);
        int[] randomArray = randomArrayWithoutKey(key);
        int result;
        result = Main.memberUnsorted(randomArray, key);
        
        assertEquals(-1, result);
    }

    public int[] randomArrayWithKeyAtPos(int pos, int key){
        Random rnd = new Random();
        int[] array = new int[ARRAY_LENGHT];
        for(int i = 0; i < ARRAY_LENGHT; i++){
            array[i] = rnd.nextInt(MIN_VALUE, MAX_VALUE);
        }
        array[pos] = key;
        return array;
    }

    public int[] randomArrayWithoutKey(int key){
        Random rnd = new Random();
        int[] array = new int[ARRAY_LENGHT];
        for(int i = 0; i < ARRAY_LENGHT; i++){
            int temp = rnd.nextInt(MIN_VALUE, MAX_VALUE);
            if(temp == key){temp++;}
            array[i] = temp;
        }
        return array;
    }

    public int randomNumber(int lower, int higher){
        Random rnd = new Random();
        return(rnd.nextInt(lower, higher));
    }
}