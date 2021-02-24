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
 * @param <Item>
 */
public class GenericQuickSort {
    
    public static <T extends Comparable<? super T>> void sort(T[] arr, int l, int h){
        
        if (l < h){
            int pivot = partition(arr, l, h);
            sort(arr, l, pivot);
            sort(arr, pivot + 1, h);
        }
        
    }

    private static <T extends Comparable<? super T>> int partition(T[] arr, int l, int h){
        
        int m = (l + h)/2;
        T pivot = arr[m];
        int i = l - 1;
        int j = h + 1;
        
        while (true){
            
            do{
                i++;
            } while(arr[i].compareTo(pivot) < 0);
            
            do {
                j--;
            } while(arr[j].compareTo(pivot) > 0);
            
            if (i >= j){
                return j;
            }
            
            swap(arr, i, j);
            
        }
        
    }

    private static <Item> void swap(Item[] arr, int i, int j) {
        Item temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        String[] arrayString = {"Jason", "Bob", "Aaron", "Mary", "Jessica", "Eduardo", "Sharon", "Dominik", "Carlos"};
        GenericQuickSort.sort(arrayString, 0, arrayString.length - 1);
        System.out.println(Arrays.toString(arrayString));

    }
}
