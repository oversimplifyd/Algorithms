package algorithms; 

/**
Runtime O(NLogN) Time it takes to sort. 
Sorting could be mergeSort ot TimSort depending on language implementation 

Space O(N) time used by sorting. 


If we do not sort. 
We get a runtime for O(N^2) 

 */
 
import java.util.*;

class Interval {
  int start;
  int end;

  public Interval(int start, int end) {
    this.start = start;
    this.end = end;
  }
};

class MergeIntervals {

  public static List<Interval> merge(List<Interval> intervals) {
    List<Interval> mergedIntervals = new LinkedList<Interval>();

    if (intervals.size() < 2) {
      return intervals; 
    }

    Collections.sort(intervals, (a, b) -> Integer.compare(a.start, b.start)); 

    Iterator<Interval> intervalItr = intervals.iterator(); 

    Interval interval = intervalItr.next(); 
    int start = interval.start; 
    int end = interval.end; 

    while (intervalItr.hasNext()) {
      interval = intervalItr.next(); 
      if (interval.start <= end) {
        // make this the new end, continue. 
        //This means we hae overlapped interval, DO not merge yet but compare to the next 
        end = Math.max(end, interval.end);
      } else {
        //add previous start and end 
        mergedIntervals.add(new Interval(start, end)); 
        // reset start and end to the current interval 
        start = interval.start; 
        end = interval.end; 
      }
    }

    //If we are not able to merge the last the Interval 
    mergedIntervals.add(new Interval(start, end)); 

    return mergedIntervals;
  }

  public static void main(String[] args) {
    List<Interval> input = new ArrayList<Interval>();
    input.add(new Interval(1, 4));
    input.add(new Interval(2, 5));
    input.add(new Interval(7, 9));
    System.out.print("Merged intervals: ");
    for (Interval interval : MergeIntervals.merge(input))
      System.out.print("[" + interval.start + "," + interval.end + "] ");
    System.out.println();

    input = new ArrayList<Interval>();
    input.add(new Interval(6, 7));
    input.add(new Interval(2, 4));
    input.add(new Interval(5, 9));
    System.out.print("Merged intervals: ");
    for (Interval interval : MergeIntervals.merge(input))
      System.out.print("[" + interval.start + "," + interval.end + "] ");
    System.out.println();

    input = new ArrayList<Interval>();
    input.add(new Interval(1, 4));
    input.add(new Interval(2, 6));
    input.add(new Interval(3, 5));
    System.out.print("Merged intervals: ");
    for (Interval interval : MergeIntervals.merge(input))
      System.out.print("[" + interval.start + "," + interval.end + "] ");
    System.out.println();
  }
}