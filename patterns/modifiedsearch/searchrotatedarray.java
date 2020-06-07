/**
Given an array of numbers which is sorted in ascending order and also rotated by some arbitrary number, find if a given ‘key’ is present in it.

Write a function to return the index of the ‘key’ in the rotated array. If the ‘key’ is not present, return -1. You can assume that the given array does not have any duplicates.
O(logN)
 */
class SearchRotatedArray {

  public static int search(int[] arr, int key) {

    int pivot = findRotationPoint(arr); 

    int firstPart = bSearch(arr, 0, pivot, key);
    if (firstPart != -1) return firstPart; 

    return bSearch(arr, pivot, arr.length - 1, key); 
  }

  private static int bSearch(int[] arr, int startIndex, int endIndex, int key) {
    int start = startIndex;
    int end = endIndex;

    while (start <= end) {
      int mid = (end + start) / 2; 

      if (arr[mid] == key) return mid; 

      if (arr[mid] > key) {
        end = mid - 1; 
      } else {
        start = mid + 1; 
      }
    }

    return -1; 
  }

  private static int findRotationPoint(int[] arr) {

    int start = 0; 
    int end = arr.length - 1; 

    while (start < end) {
      int mid = (end + start) / 2; 

      if (arr[mid] > arr[mid + 1]) return mid; 

      if (arr[mid] >= arr[0]) {
        start = mid + 1; 
      } else {
        end = mid - 1; 
      }
    }

    return start;  // at this point star == end there fore we. can return either start or end
  }
  public static void main(String[] args) {
    System.out.println(SearchRotatedArray.search(new int[] { 10, 15, 1, 3, 8 }, 15));
    System.out.println(SearchRotatedArray.search(new int[] { 4, 5, 7, 9, 10, -1, 2 }, 10));
    System.out.println(SearchRotatedArray.search(new int[] { 3, 4, 5, 7, 0, 1, 2, 3, 3, 3 }, 5));
  }
}

