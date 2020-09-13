import java.util.*;

class MergedIntervals {

    public static List<Interval> mergeOverlapping(List<Interval> intervals)
    {
        /**
         * Given a list of intervals, merge all the overlapping intervals to produce a list that has only mutually exclusive intervals.

            Example 1:

            Intervals: [[1,4], [2,5], [7,9]]
            Output: [[1,5], [7,9]]
            Explanation: Since the first two intervals [1,4] and [2,5] overlap, we merged them into 
            one [1,5].

            1,4 2,5 7,9
            1           4
                 2          5
            
            1           4
        0                   5

            1           4
                2   3

            1           4

            1           4
                        4   5
            overlap = a.end >= b.start && 
            upperBound = Math.max(a.end, b.end);
            lowerBound = Math.min(a.start, b.start)

            1           4
                2           5
            1               5
                                7       9
            
            [[1,4], [2,5], [7,9]]
              .       .      .
         */

         // if interval size is < 2 return interval 
         
         if (intervals.size() < 2) return intervals;
         
         Collections.sort(intervals, (a,b) -> Integer.compare(a.start, b.start)); // ONlogN

         List mergedIntervals = new LinkedList<Interval>();
         int start = intervals.get(0).start; 
         int end = intervals.get(0).end; 

         Interval currentInterval = new Interval(start, end);

         for (int i = 1; i < intervals.size(); i++) {  //ON
             if (currentInterval.end >= intervals.get(i).start) {
                 currentInterval.end = Math.max(currentInterval.end, intervals.get(i).end);
             } else {
                 mergedIntervals.add(currentInterval);  // O(1)
                 currentInterval = new Interval(intervals.get(i).start, intervals.get(i).end);
             }
         }

         mergedIntervals.add(currentInterval);

         return mergedIntervals;
    }

    public static boolean conflictingAppointment(Interval[] appointments) {

         /**
          * Given an array of intervals representing ‘N’ appointments, find out if a person can attend all the appointments.

            Example 1:

            Appointments: [[1,4], [2,5], [7,9]]
            Output: false
            Explanation: Since [1,4] and [2,5] overlap, a person cannot attend both of these appointments.
          */

          // Can attend an appointment IF
          // Ther are no overlapping intervals 

          if (appointments.length < 2) return true; 

          Arrays.sort(appointments, (a,b) -> Integer.compare(a.start, b.start));

          int start = appointments[0].start; 
          int end = appointments[0].end; 

          Interval currentInterval = new Interval(start, end);

          for (int i = 1; i < appointments.length; i++) {
              if (currentInterval.end >= appointments[i].start) return false; 
              currentInterval = new Interval(appointments[i].start, appointments[i].end);
          }

          return true; 
    }

    public static void main(String[] args){
        // List<Interval> input = new ArrayList<Interval>();
        // input.add(new Interval(1, 4));
        // input.add(new Interval(2, 5));
        // input.add(new Interval(7, 9));
        // System.out.print("Merged intervals: ");
        // for (Interval interval : MergedIntervals.mergeOverlapping(input))
        //   System.out.print("[" + interval.start + "," + interval.end + "] ");
        // System.out.println();
    
        // input = new ArrayList<Interval>();
        // input.add(new Interval(6, 7));
        // input.add(new Interval(2, 4));
        // input.add(new Interval(5, 9));
        // System.out.print("Merged intervals: ");
        // for (Interval interval : MergedIntervals.mergeOverlapping(input))
        //   System.out.print("[" + interval.start + "," + interval.end + "] ");
        // System.out.println();
    
        // input = new ArrayList<Interval>();
        // input.add(new Interval(1, 4));
        // input.add(new Interval(2, 6));
        // input.add(new Interval(3, 5));
        // System.out.print("Merged intervals: ");
        // for (Interval interval : MergedIntervals.mergeOverlapping(input))
        //   System.out.print("[" + interval.start + "," + interval.end + "] ");
        // System.out.println();

        Interval[] intervals = { new Interval(1, 4), new Interval(2, 5), new Interval(7, 9) };
        boolean result = MergedIntervals.conflictingAppointment(intervals);
        System.out.println("Can attend all appointments: " + result);
    
        Interval[] intervals1 = { new Interval(6, 7), new Interval(2, 4), new Interval(8, 12) };
        result = MergedIntervals.conflictingAppointment(intervals1);
        System.out.println("Can attend all appointments: " + result);
    
        Interval[] intervals2 = { new Interval(4, 5), new Interval(2, 3), new Interval(3, 6) };
        result = MergedIntervals.conflictingAppointment(intervals2);
        System.out.println("Can attend all appointments: " + result);
    }
}

class Interval {
    int start; 
    int end; 

    public Interval(int start, int end) {
        this.start = start; 
        this.end = end; 
    }
}
