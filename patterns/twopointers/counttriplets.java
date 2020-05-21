import java.util.*;

/**
Given an array arr of unsorted numbers and a target sum, count all triplets in it such that arr[i] + arr[j] + arr[k] < target where i, j, and k are three different indices. Write a function to return the count of such triplets.
Runtime -> O(NLogN + N^2) - Cost of worting and iteration -> O(N^2) 
Space O(N) gotten from the Array.sort() 
**/
class TripletWithSmallerSum {

  public static int searchTriplets(int[] arr, int target) {
    int count = 0;
    
    Arrays.sort(arr);

    for (int i = 0; i < arr.length - 2; i++) {

      int left = i + 1;
      int right = arr.length - 1; 
      int countPerItem = 0; 
      int targetToCheck = target - arr[i];  // check any X + Y < targetToCheck 

      while (left < right) {
        if (arr[left] + arr[right] < targetToCheck) {
          // This means if we have range of a,b less than target 
          // then, all numbers from b - (b-1), b - (b-2) .. (b - a) is less than targetToCheck. 
          countPerItem += right - left;  
          left++; 
        } else {
          right--; 
        }
      }

      count += countPerItem;

    }
    return count;
  }
}
