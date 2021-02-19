/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computerscience.algorithms.datastructures.queues;

import computerscience.algorithms.datastructures.stacks.StackLinked;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Eduardo
 */
public class QueueLinkedPractice<Item> implements Iterable<Item> {

    private Node head;
    private Node tail;
    private int n;

    private class Node {

        Node next;
        Node prev;
        Item data;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void enqueue(Item data) {
        Node oldTail = tail;
        tail = new Node();
        tail.data = data;

        if (isEmpty()) {
            head = tail;
        } else {
            oldTail.next = tail;
        }
        n++;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Node oldHead = head;

        if (size() > 1) {
            head = head.next;
        } else {
            head = null;
            tail = null;
        }
        
        n--;
        return oldHead.data;
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        
        for (Item data : this){
            sb.append(data).append(" ");
        }
        
        return sb.toString();
    }

    @Override
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }
    
    private class QueueIterator implements Iterator<Item> {
        private Node current = head;
        
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            
            Item data = current.data;
            current = current.next;
            return data;
        }
 
    }

    public static void main(String[] args) {

        QueueLinkedPractice<Integer> queue = new QueueLinkedPractice();
        queue.enqueue(2);
        queue.enqueue(4);
        queue.enqueue(5);
        System.out.println("dequeued: " + queue.dequeue());
        System.out.println("queue:" + queue);

    }

}
