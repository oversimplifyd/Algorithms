import java.util.*;

class SubsetDiff {

    /**
     * Given an array of positive integers, divide the array into two subsets such that the difference 
     * between the sum of the subsets is as small as possible.
     * For example, given [5, 10, 15, 20, 25], return the sets {10, 25} and {5, 15, 20}, 
     * which has a difference of 5, which is the smallest possible difference.
     * 
     *                              5 10 15 20 25
     *                                  5           theRest
     *                                  5, 10       theRest 
     * 
     *                                  10          theRest 
     *                                  10,5        theRest 
     *                              
     *                                                5
     *                        10 15 20                             10 15 20
     *                           10                                    10
     *                 15 , 20           15,20                 15,20         15,20
     *                    15               15                    15             15
     *              20          20     20       20          20      20      20      20
     * 
     *      5,10        therest                         
     *      5,10,15,20
     *      5 10 15     20
     *      5 10 20     15
     *      5, 15, 20   10
     *      5, 15,      10, 20
     *      
     * 
     */

     public static List<List<integer>> subsets(int[] items) {

        int sum = 0; 
        int min = Integer.MIN_VALUE; 

        List<List<Integer>> result = new ArrayList<>();

        for(int item: items) {
            sum += item; 
        }

        List<Integer> firstSubset = new ArrayList<>();
        List<Integer> secondSubset = new ArrayList<>();

        result.add(firstSubset, secondSubet);

        subsetRecurisve(items, 0, firstSubset, secondSubset, result); 

        return result;
     }

     private static void subsetRecurisve(int[] items, int currentIndex, List<Integer> firstSubset, List<Integer> secondSubset, List<List<Integer>> result) {

        if (currentIndex == items.length - 1) {
            //sumFirstSubset 
            //sumSecondSuset 

            //secondSubet - firstSubset 
            // if (miimum) 
            // result.set(0, firstSubset)
            // reuslt.set(1, secondSubset)

            // return;
        }

        

        subsetRecurisve(items, currentIndex, firstSubset, secondSubet, result);
        subsetRecurisve(items, currentIndex + 1, firstSubset, secondSubet, result)
     }
}