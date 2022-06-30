/**
Implement a queue ↴ with 2 stacks. ↴ Your queue should have an enqueue and a dequeue method and it should be "first in first out" (FIFO).

Optimize for the time cost of mm calls on your queue. These can be any mix of enqueue and dequeue calls.

Assume you already have a stack implementation and it gives O(1)O(1) time push and pop.

 */
package algorithms;

import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

public class QueueAsStack {

    // fill in the definitions for enqueue() and dequeue()
    
    private static class NoSuchItemException extends RuntimeException {
        
        public NoSuchItemException() {
            super("No such item");
        }
    }

    public static class QueueTwoStacks {

        private Deque<Integer> inStack = new ArrayDeque<>();
        private Deque<Integer> outStack = new ArrayDeque<>();

        public void enqueue(int item) {
            inStack.push(item);
        }

        public int dequeue() {
            if (!outStack.isEmpty()) {
                return outStack.pop();
            }
            
            if (inStack.isEmpty()) {
                throw new NoSuchItemException();
            }
            
            populateOutStack();
            
            return outStack.pop();
        }
        
        private void populateOutStack()
        {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
    }

    // tests

    @Test
    public void basicQueueOperationsTest() {
        final QueueTwoStacks q = new QueueTwoStacks();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        assertEquals("dequeue #1", 1, q.dequeue());
        assertEquals("dequeue #2", 2, q.dequeue());
        q.enqueue(4);
        assertEquals("dequeue #3", 3, q.dequeue());
        assertEquals("dequeue #4", 4, q.dequeue());
    }

    @Test(expected = Exception.class)
    public void exceptionWhenDequeueFromNewQueueTest() {
        final QueueTwoStacks q = new QueueTwoStacks();
        q.dequeue();
    }

    @Test(expected = Exception.class)
    public void exceptionWhenDequeueFromEmptyQueueTest() {
        final QueueTwoStacks q = new QueueTwoStacks();
        q.enqueue(1);
        q.enqueue(2);
        q.dequeue();
        q.dequeue();
        q.dequeue();
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(QueueAsStack.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }

}