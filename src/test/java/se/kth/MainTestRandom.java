package se.kth;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import java.util.Random;


import static org.junit.jupiter.api.Assertions.*;

public class MainTestRandom {
    int ARRAY_LENGHT = 20;
    int MIN_VALUE = -20;
    int MAX_VALUE = 20;
     
    @RepeatedTest(100)
    public void testTrueMember(){
        int iteration = 0;
        int key = randomNumber(MIN_VALUE, MAX_VALUE);
        int pos = randomNumber(0, ARRAY_LENGHT - 1);
        int[] randomArray = randomArrayWithKeyAtPos(pos, key);
        boolean result = Main.memberUnsorted(randomArray, key);
        
        if(!result){
            System.out.println("bug found at iteration: " + iteration);
            fail();
        }

        key = randomNumber(MIN_VALUE, MAX_VALUE);
        randomArray = randomArrayWithoutKey(key);
        result = Main.memberUnsorted(randomArray, key);
        
        if(result){
            System.out.println("bug found at iteration: " + iteration);
            fail();
        }
        iteration++;
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