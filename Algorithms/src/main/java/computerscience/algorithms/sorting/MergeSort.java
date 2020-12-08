/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computerscience.algorithms.sorting;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author EduardoPC
 */
public class MergeSort {

    public static void main(String[] args) {

        int size = 1000000;
        int[] numbers = new int[size];

        Random generator = new Random();
        for (int i = 0; i < size - 1; i++) {
            numbers[i] = generator.nextInt(10000000);
        }

        long startTime = System.nanoTime();
        try {
            sort(numbers, 0, size - 1);
        } finally {
            long endTime = System.nanoTime() - startTime;
            double totalTime = TimeUnit.NANOSECONDS.toMillis(endTime);
            String sizeString = NumberFormat.getNumberInstance(Locale.US).format(size);
            System.out.println("Merge Sort: " + totalTime + " milliseconds to sort an array of " + sizeString + " elements.");
        }

        System.out.println(Arrays.toString(numbers));

    }

    public static void sort(int[] arr, int l, int r) {

        if (l < r) {

            int m = (l + r) / 2;

            sort(arr, l, m);
            sort(arr, m + 1, r);

            merge(arr, l, m, r);

        }

    }

    public static void merge(int[] arr, int l, int m, int r) {

        int nL = m - l + 1;
        int nR = r - m;

        int L[] = new int[nL];
        int R[] = new int[nR];

        for (int i = 0; i < nL; i++) {
            L[i] = arr[l + i];
        }
        
        for (int j = 0; j < nR; j++){
            R[j] = arr[m + 1 + j];
        }
        
        int i = 0; int j = 0;
        
        int k = l;
        
        while (i < nL && j < nR){
            
            if(L[i] <= R[j]){
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
            
        }
        
        while (i < nL){
            arr[k] = L[i];
            i++;
            k++;
        }
        
        while (j < nR){
            arr[k] = R[j];
            j++;
            k++;
        }

    }

}
