package algorithms; 

import java.util.*;

class Interval {
  int start;
  int end;

  public Interval(int start, int end) {
    this.start = start;
    this.end = end;
  }
};

class ConflictingAppointments {

  public static boolean canAttendAllAppointments(Interval[] intervals) {
    Arrays.sort(intervals, (a, b) -> Integer.compare(a.start, b.start)); 

    if (intervals.length == 0) return false; 
    if (intervals.length == 1) return true; 

    int i = 0; 

    while (i < intervals.length - 1) {
      if (intervals[i + 1].start < intervals[i].end)
      // please note the comparison above, it is "<" and not "<="
        // while merging we needed "<=" comparison, as we will be merging the two
        // intervals having condition "intervals[i].start == intervals[i - 1].end" but
        // such intervals don't represent conflicting appointments as one starts right
        // after the other
        return false;
      i++; 
    }

    return true;
  }

  public static void main(String[] args) {
    Interval[] intervals = { new Interval(1, 4), new Interval(2, 5), new Interval(7, 9) };
    boolean result = ConflictingAppointments.canAttendAllAppointments(intervals);
    System.out.println("Can attend all appointments: " + result);

    Interval[] intervals1 = { new Interval(6, 7), new Interval(2, 4), new Interval(8, 12) };
    result = ConflictingAppointments.canAttendAllAppointments(intervals1);
    System.out.println("Can attend all appointments: " + result);

    Interval[] intervals2 = { new Interval(4, 5), new Interval(2, 3), new Interval(3, 6) };
    result = ConflictingAppointments.canAttendAllAppointments(intervals2);
    System.out.println("Can attend all appointments: " + result);
  }
}
