package algorithms;

/**
* MergeSort => Best Worst Case => O(nlogn) 
* Logn because we plan to recursively divide problem in half each time sort 
* n because we merge the sorted values using comparison. 
* For bounded input values, that is with smaller size input sorting can be done in O(n) 
* This can also be provved using Master's Theorem with the recurence equations such that 2T(n/2) + n 
* Use of two separate index and how they were being advanced. #Bang 
*/

import java.util.Arrays; 

class MergeSort {

    public static void main(String[] args) {

        int[] toSort = {1, 4, 7, 9, 2, 11, 9, 8, 7, 6, 5, 3, 19, 0, 25};

        int[] sortedA = mSort(toSort);

        printArray(sortedA);
    }

    public static int[] mSort(int[] items) {

        //Base Case
        if (items.length < 2) {
            return items; 
        }

        int middleIndex = items.length / 2;

        int[] leftPair = Arrays.copyOfRange(items, 0, middleIndex);
        int[] rightPair = Arrays.copyOfRange(items, middleIndex, items.length);

        int[] sortedLeftPair = mSort(leftPair);
        int[] sortedRightPair = mSort(rightPair);

        int[] sortedArray = new int[items.length];

        int leftSortedIndex = 0;
        int rightSortedIndex = 0;

        for (int currentSortedArrayIndex = 0; currentSortedArrayIndex < items.length; currentSortedArrayIndex++) {
            //Now we want to merge by comparison. This happens at every level of recursion. 
            //Starting at the leftSortedIndex, IF there are still more items in the left pair 
            //AND (there are no more items in the right pair OR the item in the current index of 
            //the leftSortedPair is less than the item in the current index of the rightSortedPiar, 
            //pick that item and advance the pointer of the leftSortedIndex) ELSE pick the item in the 
            //rightPair and advance ONLY the index of the RightPair

            //sortedLeftPair[leftSortedIndex] > sortedRightPair[rightSortedIndex]) -> switch comparisn sign to revert sort order. 
            
            if (leftSortedIndex < sortedLeftPair.length 
                && (rightSortedIndex >= sortedRightPair.length 
                || sortedLeftPair[leftSortedIndex] > sortedRightPair[rightSortedIndex])) {
                    sortedArray[currentSortedArrayIndex] = sortedLeftPair[leftSortedIndex];
                    leftSortedIndex++;
                } else {
                    sortedArray[currentSortedArrayIndex] = sortedRightPair[rightSortedIndex];
                    rightSortedIndex++;
                }
        }

        return sortedArray;
    }

    public static void printArray(int[] items) {
        for (int item: items) {
            System.out.println(item);
        }
    }
}