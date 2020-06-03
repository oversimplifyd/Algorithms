/**
Design a class to calculate the median of a number stream. The class should have the following two methods:

insertNum(int num): stores the number in the class
findMedian(): returns the median of all numbers inserted in the class
If the count of numbers inserted in the class is even, the median will be the average of the middle two numbers.

Runtime O(LogN) cost of inserting into a Heap 
Space O(N) total number of inpus


// A Naive approach to this problem will take at least O(NLogN) which is the cost of sorting the input array at any point time 
 */
import java.util.*;

class MedianOfAStream {

  private PriorityQueue<Integer> minHeap;
  private PriorityQueue<Integer> maxHeap; 

  public MedianOfAStream() {
    this.minHeap = new PriorityQueue<Integer>((a,b) -> a - b);
    this.maxHeap = new PriorityQueue<Integer>((a,b) -> b - a);
  }

  public void insertNum(int num) {
    
    // incoming number is smaller than the number in maxHeap 
    // it can go there 
    if (maxHeap.isEmpty() || maxHeap.peek() >= num) {
      maxHeap.add(num);
    } else {
      minHeap.add(num);
    }

    // let's balance out the heaps, 
    // Such that we have maxHeap having not more than 1 element greater than minHeap 
    if (maxHeap.size() > minHeap.size() + 1) {
      minHeap.add(maxHeap.poll());
    } else if (maxHeap.size() < minHeap.size()) {
       // the minHeap should not be greater than the maxHeap 
      // since the median for odd numbers is the middle biggest number
      // which is similar to the top element of the maxHeap 
      maxHeap.add(minHeap.poll());
    }
  }

  public double findMedian() {
    // they are both even 
    // take the average of the two middle numbers
    if (maxHeap.size() == minHeap.size()) {
      return maxHeap.peek() / 2.0 + minHeap.peek() / 2.0;
    }

    //They are odd and certainly, maxHeap is at least 1 greater than minHeap 
    // which is the median we need. 
    return maxHeap.peek();
  }

  public static void main(String[] args) {
    MedianOfAStream medianOfAStream = new MedianOfAStream();
    medianOfAStream.insertNum(3);
    medianOfAStream.insertNum(1);
    System.out.println("The median is: " + medianOfAStream.findMedian());
    medianOfAStream.insertNum(5);
    System.out.println("The median is: " + medianOfAStream.findMedian());
    medianOfAStream.insertNum(4);
    System.out.println("The median is: " + medianOfAStream.findMedian());
  }
}
