package algorithms; 

import java.util.*;

class Job {
  int start;
  int end;
  int cpuLoad;

  public Job(int start, int end, int cpuLoad) {
    this.start = start;
    this.end = end;
    this.cpuLoad = cpuLoad;
  }
};

class MaximumCPULoad {

    // Greedy approach 
  public static int findMaxCPULoad(List<Job> jobs) {

    if (jobs.size() == 0) {
      return 0; 
    }

    if (jobs.size() == 1) {
      return jobs.get(0).cpuLoad; 
    }

    Collections.sort(jobs, (a, b) -> Integer.compare(a.start, b.start)); 

    int i = 1;
    int loadSum = jobs.get(0).cpuLoad; 
    int maxJobLoad = 0; 
    int earliestEndTime = 0; 

    // 1,4,3], [2,5,4], [7,9,6] 
    // , [2,4,11], [6,7,10] [8,12,15]
    while (i < jobs.size()) {

      int currentLoad = jobs.get(i).cpuLoad;
      if (jobs.get(i).start < jobs.get(i - 1).end) {

        if (earliestEndTime > 0 && jobs.get(i).start < earliestEndTime) {
          loadSum += currentLoad;
        } else {
          loadSum = currentLoad + jobs.get(i-1).cpuLoad;
        }

         maxJobLoad = Math.max(maxJobLoad, loadSum);
         earliestEndTime = Math.min(jobs.get(i).end, jobs.get(i-1).end);

      } else {
        maxJobLoad = Math.max(maxJobLoad, Math.max(currentLoad, jobs.get(i-1).cpuLoad));
        loadSum = currentLoad; 
        earliestEndTime = 0; 
      }

      i++; 
    }

    return maxJobLoad; 
  }

  // , [2,4,11], [6,7,10] [8,12,15]
  public static int findMaxCPULoadUsingPQ(List<Job> jobs) {

    if (jobs.size() == 0) {
      return 0; 
    }

    if (jobs.size() == 1) {
      return jobs.get(0).cpuLoad; 
    }

    Collections.sort(jobs, (a, b) -> Integer.compare(a.start, b.start)); 

    PriorityQueue<Job> minHeap = new PriorityQueue<>(jobs.size(), (a, b) -> Integer.compare(a.start, b.start));

    // 1,4,3], [2,5,4], [7,9,6] 
    // , [2,4,11], [6,7,10] [8,12,15]
    int maxJobLoad = 0;
    int runningMax = 0; 

    for (Job job: jobs) {

      while (!minHeap.isEmpty() && job.start > minHeap.peek().end) {
        runningMax = runningMax - minHeap.poll().cpuLoad;
      }

      minHeap.offer(job);
      runningMax = runningMax + job.cpuLoad;
      maxJobLoad = Math.max(maxJobLoad, runningMax);
    }

    return maxJobLoad; 
  }

  public static void main(String[] args) {
    List<Job> input = new ArrayList<Job>(Arrays.asList(new Job(1, 4, 3), new Job(2, 5, 4), new Job(7, 9, 6)));
    System.out.println("Maximum CPU load at any time: " + MaximumCPULoad.findMaxCPULoadUsingPQ(input));

    input = new ArrayList<Job>(Arrays.asList(new Job(6, 7, 10), new Job(2, 4, 11), new Job(8, 12, 15)));
    System.out.println("Maximum CPU load at any time: " + MaximumCPULoad.findMaxCPULoadUsingPQ(input));

    input = new ArrayList<Job>(Arrays.asList(new Job(1, 4, 2), new Job(2, 4, 1), new Job(3, 6, 5)));
    System.out.println("Maximum CPU load at any time: " + MaximumCPULoad.findMaxCPULoadUsingPQ(input));
  }
}
