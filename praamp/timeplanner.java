package algorithms; 

/**
Implement a function meetingPlanner that given the availability, slotsA and slotsB, of two people and a meeting duration dur, returns the earliest time slot that works for both of them and is of duration dur. If there is no common time slot that satisfies the duration requirement, return an empty array.

Time is given in a Unix format called Epoch, which is a nonnegative integer holding the number of seconds that have elapsed since 00:00:00 UTC, Thursday, 1 January 1970.

Each person’s availability is represented by an array of pairs. Each pair is an epoch array of size two. The first epoch in a pair represents the start time of a slot. The second epoch is the end time of that slot. The input variable dur is a positive integer that represents the duration of a meeting in seconds. The output is also a pair represented by an epoch array of size two.

In your implementation assume that the time slots in a person’s availability are disjointed, i.e, time slots in a person’s availability don’t overlap. Further assume that the slots are sorted by slots’ start time.

Implement an efficient solution and analyze its time and space complexities.

Examples:

input:  slotsA = [[10, 50], [60, 120], [140, 210]]
        slotsB = [[0, 15], [60, 70]]
        dur = 8
output: [60, 68]

 */
import java.io.*;
import java.util.*;

class Solution {

  static int[] meetingPlanner(int[][] slotsA, int[][] slotsB, int dur) {
    
    if (slotsA.length == 0 || slotsB.length == 0) return new int[]{}; 
    
    int firstPointer = 0; 
    int secondPointer = 0; 
    
    while (firstPointer < slotsA.length && secondPointer < slotsB.length) {
      
      int slotAStart = slotsA[firstPointer][0];
      int slotAEnd = slotsA[firstPointer][1];
      
      int slotBStart = slotsB[secondPointer][0];
      int slotBEnd = slotsB[secondPointer][1];
      
      int start = Math.max(slotAStart, slotBStart);
      int end = Math.min(slotBEnd, slotAEnd);
      
      if (slotAEnd >= slotBStart && slotBEnd >= slotAStart) {
        int difference = end - start; 
        if (difference >= dur) return new int[]{start, start+dur};
      }
      
      if (slotAEnd <= slotBEnd) {
        firstPointer++; 
      } else {
        secondPointer++; 
      }
    }
    
    return new int[]{}; 
  }
  
  public static void main(String[] args) {
    
  }
}
