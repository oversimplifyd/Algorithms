import java.util.*; 

class TaskScheduling {

    public static boolean canSchedule(int tasks, int[][] pairs){

        /**
         * There are ‘N’ tasks, labeled from ‘0’ to ‘N-1’. Each task can have some prerequisite tasks which need to be completed before it can be scheduled. Given the number of tasks and a list of prerequisite pairs, find out if it is possible to schedule all the tasks.

            Example 1:

            Input: Tasks=3, Prerequisites=[0, 1], [1, 2]
            Output: true
         */
        List<Integer> sortOrder = new ArrayList<>();

        if (tasks <= 0) return false; 

        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> indigrees = new HashMap<>();

        for (int i = 0; i < tasks; i++) {
            indigrees.put(i, 0);
            graph.put(i, new ArrayList<Integer>());
        }

        for (int i = 0; i < pairs.length; i++) {
            int parent = pairs[i][0];
            int child = pairs[i][1];

            graph.get(parent).add(child);
            indigrees.put(child, indigrees.get(child) + 1);
        }

        Queue<Integer> sources =  new LinkedList<Integer>();

        for (Map.Entry<Integer, Integer> entry: indigrees.entrySet()) {
            if (entry.getValue() == 0) {
                sources.add(entry.getKey());
            }
        }

        while (!sources.isEmpty()) {
            int currentSource = sources.poll();
            List<Integer> children = graph.get(currentSource);

            sortOrder.add(currentSource);

            for (int child: children) {
                indigrees.put(child, indigrees.get(child) - 1);

                if (indigrees.get(child) == 0) {
                    sources.add(child);
                }
            }
        }

        return sortOrder.size() == tasks ? true : false; 
    }

    public static void main(String[] args) {
        boolean result = TaskScheduling.canSchedule(3, new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 } });
        System.out.println("Tasks execution possible: " + result);
    
        result = TaskScheduling.canSchedule(3,
            new int[][] { new int[] { 0, 1 }, new int[] { 1, 2 }, new int[] { 2, 0 } });
        System.out.println("Tasks execution possible: " + result);
    
        result = TaskScheduling.canSchedule(6, new int[][] { new int[] { 2, 5 }, new int[] { 0, 5 },
            new int[] { 0, 4 }, new int[] { 1, 4 }, new int[] { 3, 2 }, new int[] { 1, 3 } });
        System.out.println("Tasks execution possible: " + result);
    }
}