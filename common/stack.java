package algorithms;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.Stack;
import java.util.Iterator;

import static org.junit.Assert.*;

public class StackMax {

    // fill in the definitions for push(), pop(), and getMax()
    
    
    //Time O(N) -> WorstCase 
    // O(1) Average case 
    // Space -> O(1) 
    
    public static class MaxStack {
        
        // Another approach tht could optimize for time is using a stack to keep track of all the maximum values so far. 
        
        private enum Operation {
            POP,
            PUSH
        }
        
        private Stack<Integer> mainStack = new Stack<Integer>();
        
        private Operation lastOperation;
        
        private int max;
        private int lastPoppedItem; 
        
        public void push(int item) {
            mainStack.push(item);
            this.max = Math.max(this.max, item);
            
            this.lastOperation = Operation.PUSH;
        }

        public int pop() {
            this.lastOperation = Operation.POP;
            
            return lastPoppedItem = mainStack.pop();
        }

        public int getMax() {
            if (this.lastOperation == Operation.POP && lastPoppedItem == this.max) {
                this.max = this.getMaxAfterPop();
            }
            
            return this.max;
        }
        
        private int getMaxAfterPop() {
            int max = 0;
            for (Integer item: this.mainStack) {
                max = Math.max(max, item);
            }
            
            return max;
        }

    }
    
    //Icake Solution 
    // O(1) Time 
    // O(N) space due to maxesStack 
    //This essentially optimized for time over space 
    /*  import java.util.ArrayDeque;
    import java.util.Deque;
    
    public class MaxStack {
    
        private Deque<Integer> stack = new ArrayDeque<>();
        private Deque<Integer> maxesStack = new ArrayDeque<>();
    
        // Add a new item to the top of our stack. If the item is greater
        // than or equal to the last item in maxesStack, it's
        // the new max! So we'll add it to maxesStack.
        public void push(int item) {
            stack.push(item);
            if (maxesStack.isEmpty() || item >= maxesStack.peek()) {
                maxesStack.push(item);
            }
        }
    
        // Remove and return the top item from our stack. If it equals
        // the top item in maxesStack, they must have been pushed in together.
        // So we'll pop it out of maxesStack too.
        public int pop() {
            int item = stack.pop();
            if (item == maxesStack.peek()) {
                maxesStack.pop();
            }
            return item;
        }
    
        // The last item in maxesStack is the max item in our stack.
        public int getMax() {
            return maxesStack.peek();
        }
    }*/
    // tests

    @Test
    public void maxStackTest() {
        final MaxStack s = new MaxStack();
        s.push(5);
        assertEquals("check max after 1st push", 5, s.getMax());
        s.push(4);
        s.push(7);
        s.push(7);
        s.push(8);
        assertEquals("check before 1st pop", 8, s.getMax());
        assertEquals("check pop #1", 8, s.pop());
        assertEquals("check max after 1st pop", 7, s.getMax());
        assertEquals("check pop #2", 7, s.pop());
        assertEquals("check max after 2nd pop", 7, s.getMax());
        assertEquals("check pop #3", 7, s.pop());
        assertEquals("check max after 3rd pop", 5, s.getMax());
        assertEquals("check pop #4", 4, s.pop());
        assertEquals("check max after 4th pop", 5, s.getMax());
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(StackMax.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}