import java.util.*; 

class CompatibleJobs {

    public static List<List<Integer>> compJobs(List<List<Integer>> jobs) {
        /**
         * This problem was asked by Microsoft.

            You are given a list of jobs to be done, where each job is represented by a start time and end time. Two jobs are compatible if they don't overlap. Find the largest subset of compatible jobs.

            For example, given the following jobs (there is no guarantee that jobs will be sorted):

            [(0, 6),
            (1, 4),
            (3, 5),
            (3, 8),
            (4, 7),
            (5, 9),
            (6, 10),
            (8, 11)]
            Return:
            0             6
              1       4
                 3       5
                 3              8
                      4       7
                          5         9
                             6          10
                                 8            11

            [(1, 4),
            (4, 7),
            (8, 11)]

            - sort the jobs 
            - start at the first job 
            - A job overlaps if it's endTime > starTime of next job

            1    4              Add to list 
            1           7 
              2 3 
              2         7 
                 4    8 
         */

         Collections.sort(jobs, (a, b) -> a.get(0) - b.get(0));
        
         List<Integer> result = new ArrayList<>();
         List<List<Integer>> finalResult = new ArrayList<>();

         for (int i = 0; i < jobs.size(); i++) {
             List<Integer> currentResult = getCompatible(jobs, i);
             if (currentResult.size() >= result.size()) {
                 result = currentResult;
             }
         }

         if (result.size() > 0) {
             for (int index: result) {
                 finalResult.add(jobs.get(index));
             }
         }

         return finalResult;
    }

    private static List<Integer> getCompatible(List<List<Integer>> jobs, int start) {

        List<Integer> currentList = jobs.get(start);

        List<Integer> indexes = new ArrayList<>();

         for (int i = start + 1; i < jobs.size(); i++) {
             if (currentList.get(1) <= jobs.get(i).get(0)) {
                 currentList = jobs.get(i);
                 indexes.add(i);
             }
         }

         if (indexes.size() > 0) {
             indexes.add(0, start);
         }

         return indexes;
    }

    public static void main(String[] args) {

        List<List<Integer>> jobs = new ArrayList<>();

        jobs.add(new ArrayList<Integer>(Arrays.asList(0,6)));
        jobs.add(new ArrayList<Integer>(Arrays.asList(1,4)));
        jobs.add(new ArrayList<Integer>(Arrays.asList(3,5)));
        jobs.add(new ArrayList<Integer>(Arrays.asList(3,8)));
        jobs.add(new ArrayList<Integer>(Arrays.asList(4,7)));
        jobs.add(new ArrayList<Integer>(Arrays.asList(5,9)));
        jobs.add(new ArrayList<Integer>(Arrays.asList(5,10)));
        jobs.add(new ArrayList<Integer>(Arrays.asList(8,11)));

        System.out.println(compJobs(jobs));
    }
}