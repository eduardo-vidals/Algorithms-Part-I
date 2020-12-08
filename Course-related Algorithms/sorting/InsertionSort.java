/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computerscience.algorithms.sorting;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author EduardoPC
 */
public class InsertionSort {

    public static void main(String[] args) {
        
        
        int size = 10000;
        int[] numbers = new int[size];

        Random generator = new Random();
        for (int i = 0; i < size - 1; i++) {
            numbers[i] = generator.nextInt(size);
        }
        
        long startTime = System.nanoTime();
        try {
            sort(numbers);
        } finally {
            long endTime = System.nanoTime() - startTime;
            double totalTime = TimeUnit.NANOSECONDS.toMillis(endTime);
            System.out.println("Insertion Sort: " + totalTime + " milliseconds to sort an array of " + size + " elements.");
        }
        System.out.println(Arrays.toString(numbers));
    }

    public static void sort(int[] array) {
        for (int i = 1; i < array.length; i ++){
            
            int key = array[i];
            int j = i - 1;
            
            while (j >= 0 && array[j] > key){
                
                array[j + 1] = array[j];
                j--;
                
            }
            
            array[j + 1] = key;
            
        }
    }

}
