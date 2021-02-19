/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computerscience.algorithms.datastructures.stacks;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Eduardo
 * @param <Item>
 */
public class StackLinkedPractice<Item> implements Iterable<Item> {

    private Node first;
    private int n;

    private class Node {
        Node next;
        Item data;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void push(Item data) {
        Node oldFirst = first;
        first = new Node();
        first.data = data;
        first.next = oldFirst;
        n++;

    }
    
    public Item pop(){
        Item popped = first.data;
        first = first.next;
        n--;
        return popped;
    }
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        
        for (Item data : this){
            sb.append(data).append(" ");
        }
        
        return sb.toString();
    }

    @Override
    public Iterator<Item> iterator() {
        return new StackIterator();
    }

    private class StackIterator implements Iterator<Item> {

        private Node current = first;
        
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            
            Item item = current.data;
            current = current.next;
            return item;
        }
        
    }
    
    public static void main(String[] args) {
        StackLinkedPractice<Integer> stack = new StackLinkedPractice();

        stack.push(2);
        stack.push(4);
        stack.push(5);
        System.out.println(stack.pop());

        System.out.println(stack);
    }

}
