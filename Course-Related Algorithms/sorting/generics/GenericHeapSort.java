/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computerscience.algorithms.sorting.generics;

import java.util.Arrays;

/**
 *
 * @author Eduardo
 */
public class GenericHeapSort {
    
    public static void main(String[] args){
        Integer[] arr = {4, 6, 7, 2, 1, 7, 1, 8, 3};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
    
    public static void sort(Comparable[] pq){
        int n = pq.length;
        for (int k = n/2; k >= 1; k--){
            sink(pq, k, n);
        }
        
        System.out.println(Arrays.toString(pq));
        
        int k = n;
        while (k > 1){
            exch(pq, 1, k--);
            sink(pq, 1, k);
        }
    }
    
    private static void sink(Comparable[] pq, int k, int n){
        while(2 * k <= n){
            int j = 2 * k;
            if (j < n && less(pq, j, j + 1)){
                j++;
            } 
            if (!less(pq, k, j)){
                break;
            }
            exch(pq, k, j);
            k = j;
        }
    }
    
    private static boolean less(Comparable[] pq, int i, int j){
        return pq[i - 1].compareTo(pq[j - 1]) < 0;
    }
    
    private static void exch(Object[] pq, int i, int j){
        Object temp = pq[i - 1];
        pq[i - 1] = pq[j - 1];
        pq[j - 1] = temp;
    }
    
}
