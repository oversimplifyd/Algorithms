/**
Given an array of numbers sorted in ascending order, find the element in the array that has the minimum difference with the given ‘key’.
O(LgN) 
 */
class MinimumDifference {

  public static int searchMinDiffElement(int[] arr, int key) {

    if (key < arr[0])
      return arr[0];
    if (key > arr[arr.length - 1])
      return arr[arr.length - 1];

    int start = 0; 
    int end = arr.length - 1; 

    while (start <= end) {
      int distance = end + start; 
      int mid = distance / 2; 

      if (arr[mid] == key) return key; 

      if (arr[mid] < key) {
        start = mid + 1; 
      } else {
        end = mid - 1; 
      }
    }

    // { 1, 3, 8, 10
    if (end < arr.length - 1) {
      return key - arr[end] < arr[end + 1] - key ? arr[end] : arr[end + 1];
    } else {
      return arr[end]; 
    }

  }

  public static void main(String[] args) {
    System.out.println(MinimumDifference.searchMinDiffElement(new int[] { 4, 6, 10 }, 7));
    System.out.println(MinimumDifference.searchMinDiffElement(new int[] { 4, 6, 10 }, 4));
    System.out.println(MinimumDifference.searchMinDiffElement(new int[] { 1, 3, 8, 10, 15 }, 12));
    System.out.println(MinimumDifference.searchMinDiffElement(new int[] { 4, 6, 10 }, 17));
  }
}
