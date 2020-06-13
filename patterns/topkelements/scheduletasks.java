package algorithms; 

/**
You are given a list of tasks that need to be run, in any order, on a server. Each task will take one CPU interval to execute but once a task has finished, it has a cooling period during which it can’t be run again. If the cooling period for all tasks is ‘K’ intervals, find the minimum number of CPU intervals that the server needs to finish all tasks.

If at any time the server can’t execute any task then it must stay idle.

Runtime O(NLoN)
Space (O(N))
 */
 
import java.util.*;

class TaskScheduler {

  public static int scheduleTasks(char[] tasks, int k) {
     if (tasks.length < 1)
      return 0;

    Map<Character, Integer> charFrequencyMap = new HashMap<>();

    for (char chr : tasks)
      charFrequencyMap.put(chr, charFrequencyMap.getOrDefault(chr, 0) + 1);

    PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<Map.Entry<Character, Integer>>(
        (e1, e2) -> e2.getValue() - e1.getValue());

    // add all characters to the max heap
    maxHeap.addAll(charFrequencyMap.entrySet());
    int intervalCount = 0; 

    while (!maxHeap.isEmpty()) {

      int n = k + 1; // number of tasks that must executed before the next waitList  is added back to the heap 
      List<Map.Entry<Character, Integer>> waitList = new ArrayList<>();

      for (; n > 0 && !maxHeap.isEmpty(); n--) {
        Map.Entry<Character, Integer> currentEntry = maxHeap.poll();

        if (currentEntry.getValue() > 1) {
          currentEntry.setValue(currentEntry.getValue() - 1);
          waitList.add(currentEntry);
        }
        intervalCount++; 
      }

      maxHeap.addAll(waitList);
      if (!maxHeap.isEmpty()) {
        intervalCount += n; // This adds the idel time to intervals. We have idle time if n doesn't get to 0 in the above iteration. 
      }
    }

    return intervalCount;
  }

  public static void main(String[] args) {
    char[] tasks = new char[] { 'a', 'a', 'a', 'b', 'c', 'c' };
    System.out.println("Minimum intervals needed to execute all tasks: " + TaskScheduler.scheduleTasks(tasks, 2));

    tasks = new char[] { 'a', 'b', 'a' };
    System.out.println("Minimum intervals needed to execute all tasks: " + TaskScheduler.scheduleTasks(tasks, 3));
  }
}
