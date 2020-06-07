/**
Given an array of numbers sorted in ascending order, find the range of a given number ‘key’. The range of the ‘key’ will be the first and last position of the ‘key’ in the array.

Write a function to return the range of the ‘key’. If the ‘key’ is not present return [-1, -1].

O(LgN) 
 */
class FindRange {

  public static int[] findRange(int[] arr, int key) {

    int startRange = -1; 
    int endRange = -1;

    if (keyExist(arr, key)) {
      startRange = searchRangeStart(arr, key);
      endRange = searchRangeEnd(arr, key);
    }

    return new int[]{startRange, endRange};
  }

  private static boolean keyExist(int[] arr, int key) {
    int start = 0;
    int end = arr.length - 1; 

    while (start <= end) {
      int mid = (end + start) / 2;

      if (arr[mid] == key) return true; 
      if (arr[mid] < key) {
        start = mid + 1;
      } else {
        end = mid - 1;
      }
    }

    return false; 
  }

  private static int searchRangeEnd(int[] arr, int key) {

    if (arr[arr.length - 1] < key) {
      return -1; 
    }

    int start = 0;
    int end = arr.length - 1; 

    while (start <= end) {
      int mid = (end + start) / 2;

      if (arr[mid] <= key) {
        start = mid + 1;
      } else {
        end = mid - 1;
      }
    }

    return start - 1; 
  }

  private static int searchRangeStart(int[] arr, int key) {

    if (arr[0] > key) {
      return -1; 
    }

        // 1, 3, 4, 4, 9
    int start = 0;
    int end = arr.length - 1;

    while (start <= end) {
      int mid = (end + start) / 2;

      if (arr[mid] >= key) {
        end = mid - 1;
      } else {
        start = mid + 1; 
      }
    }

    return end + 1;
  }

  public static void main(String[] args) {
    int[] result = FindRange.findRange(new int[] { 4, 6, 6, 6, 9 }, 6);
    System.out.println("Range: [" + result[0] + ", " + result[1] + "]");
    result = FindRange.findRange(new int[] { 1, 3, 8, 10, 15 }, 10);
    System.out.println("Range: [" + result[0] + ", " + result[1] + "]");
    result = FindRange.findRange(new int[] { 1, 3, 3, 3, 3, 8, 10, 15 }, 3);
    System.out.println("Range: [" + result[0] + ", " + result[1] + "]");
  }
}
