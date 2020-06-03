/**
Given an array of intervals, find the next interval of each interval. In a list of intervals, for an interval ‘i’ its next interval ‘j’ will have the smallest ‘start’ greater than or equal to the ‘end’ of ‘i’.

Write a function to return an array containing indices of the next interval of each input interval. If there is no next interval of a given interval, return -1. It is given that none of the intervals have the same start point.

Runtime O(NLogN) 

Space O(N) 
 */
import java.util.*;

class Interval {
  int start = 0;
  int end = 0;

  Interval(int start, int end) {
    this.start = start;
    this.end = end;
  }
}

class NextInterval {
  public static int[] findNextInterval(Interval[] intervals) {
    int[] result = new int[intervals.length];

    int intervalLength = intervals.length;

    // put the start time in MaxHeap 
    // put the end time in MaxHeap 
    // compare each endtime to the maxstart heap 
    // remove everything that matches it except the last one since they are no longer 
    // resuable for other items below this heap

    PriorityQueue<Integer> maxStartHeap = new PriorityQueue<>(intervalLength, (i1, i2) -> intervals[i2].start - intervals[i1].start);
    PriorityQueue<Integer> maxEndHeap = new PriorityQueue<>(intervalLength, (i1, i2) -> intervals[i2].end - intervals[i1].end);

    for (int i = 0; i < intervalLength; i++) {
      // add their indices instead of the actual interval 
      // But the insertion of course makes use of the Comparator defined above every time. 
      maxEndHeap.offer(i);
      maxStartHeap.offer(i);
    }

    for (int i = 0; i < intervalLength; i++) {
      int intervalTopEnd = maxEndHeap.poll();
      result[intervalTopEnd] = -1; // default value 

      if (!maxStartHeap.isEmpty() && intervals[maxStartHeap.peek()].start >= intervals[intervalTopEnd].end) {
        int intervalTopStart = maxStartHeap.poll();

        while (!maxStartHeap.isEmpty() && intervals[maxStartHeap.peek()].start >= intervals[intervalTopEnd].end) {
          // if there is a start greater than the current end 
          intervalTopStart = maxStartHeap.poll(); // remove from the heap 
        }

        result[intervalTopEnd] = intervalTopStart;
        maxStartHeap.add(intervalTopStart); // restore the last item matched in case we have other end in the maxendInterval with the samve value as the curent end 
      }
    }

    return result;
  }

  public static void main(String[] args) {
    Interval[] intervals = new Interval[] { new Interval(2, 3), new Interval(3, 4), new Interval(5, 6) };
    int[] result = NextInterval.findNextInterval(intervals);
    System.out.print("Next interval indices are: ");
    for (int index : result)
      System.out.print(index + " ");
    System.out.println();

    intervals = new Interval[] { new Interval(3, 4), new Interval(1, 5), new Interval(4, 6) };
    result = NextInterval.findNextInterval(intervals);
    System.out.print("Next interval indices are: ");
    for (int index : result)
      System.out.print(index + " ");
  }
}
