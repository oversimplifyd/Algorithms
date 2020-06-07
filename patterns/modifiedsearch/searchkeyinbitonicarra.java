package algorithms; 

/** 
Given a Bitonic array, find if a given ‘key’ is present in it. An array is considered bitonic if it is monotonically increasing and then monotonically decreasing. Monotonically increasing or decreasing means that for any index i in the array arr[i] != arr[i+1].

Write a function to return the index of the ‘key’. If the ‘key’ is not present, return -1.

For Bitonic arrays, the general idea is to first find the maximum element in the array 
Then do an orderagnositc search for the key in the first part leading to maximum and maximum leading to end 

O(LogN) 

**/ 
class SearchBitonicArray {

  public static int search(int[] arr, int key) {

    int maxIndex = findMaxInBitonic(arr); 

    int indexInFirstHalf = orderAgnosticSearch(arr, 0, maxIndex, key);
    if (indexInFirstHalf < 0) {
      return orderAgnosticSearch(arr, maxIndex, arr.length - 1, key);
    }

    return indexInFirstHalf;
  }

  private static int findMaxInBitonic(int[] arr) {

    int start = 0; 
    int end = arr.length -1; 

    while (start < end) {  //as soon as start equals mid, we have the greater ele
    int mid = (end + start) / 2;
      if (arr[mid] > arr[mid + 1]) {
        end = mid;  
      } else {
        start = mid + 1; 
      }
    }

    return start; 
  }

  private static int orderAgnosticSearch(int[] arr, int startIndex, int endIndex, int key) {
    int start = startIndex;
    int end = endIndex;

    boolean isAscending = arr[start] < arr[end];

    while (start <= end) {
      int mid = (end + start) / 2;

      if (arr[mid] == key) return mid; 

      if (isAscending) {
        if (arr[mid] > key)
          end = mid - 1; 
        else
          start = start + 1; 
      } else {
        if (arr[mid] > key) 
          start = start + 1; 
        else 
          end = mid - 1;  
      }
    }

    return -1; 
  }

  public static void main(String[] args) {
    System.out.println(SearchBitonicArray.search(new int[] { 1, 3, 8, 4, 3 }, 4));
    System.out.println(SearchBitonicArray.search(new int[] { 3, 8, 3, 1 }, 8));
    System.out.println(SearchBitonicArray.search(new int[] { 1, 3, 8, 12 }, 12));
    System.out.println(SearchBitonicArray.search(new int[] { 10, 9, 8 }, 10));
  }
}
