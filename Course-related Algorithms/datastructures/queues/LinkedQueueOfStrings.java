/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computerscience.algorithms.datastructures.queues;

/**
 *
 * @author EduardoPC
 */
public class LinkedQueueOfStrings {
    
    public class Node{
        String item;
        Node next;
    }
    
    private Node first, last;
    
    public boolean isEmpty(){
        return first == null;
    }
    
    public void enqueue(String item){
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()){
            first = last;
        } else {
            oldLast.next = last;
        }
    }
    
    public String dequeue(){
        String item = first.item;
        first = first.next;
        if (isEmpty()){
            last = null;
        } 
        
        return item;
        
    }
    
}
