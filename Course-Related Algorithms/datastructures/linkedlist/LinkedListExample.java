/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computerscience.algorithms.datastructures.linkedlist;

/**
 *
 * @author EduardoPC
 */
import java.util.LinkedList;

public class LinkedListExample {

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.addLast(2);
        list.addFirst(3);
        System.out.println(list);
    }
}
