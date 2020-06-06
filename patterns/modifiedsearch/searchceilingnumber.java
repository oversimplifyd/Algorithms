package algorithms; 

class CeilingOfANumber {

/**
Given an array of numbers sorted in an ascending order, find the ceiling of a given number ‘key’. The ceiling of the ‘key’ will be the smallest element in the given array greater than or equal to the ‘key’.

Write a function to return the index of the ceiling of the ‘key’. If there isn’t any ceiling return -1.

O(LgN) 
**/ 
  // My solution, worked,
  // Theirs look cleaner and more intuitive 
  // public static int searchCeilingOfANumber(int[] arr, int key) {
    
  //   int start = 0; 
  //   int end = arr.length - 1; 

  //   int minLargest = arr.length; 

  //   while (start <= end) {
  //     int distance = end + start; 
  //     int mid = distance / 2; 

  //     if (arr[mid] == key) return mid; 

  //     if (arr[mid] < key) {
  //       start = mid + 1; 
  //     } else {
  //       end = mid; 
  //       minLargest = Math.min(minLargest, mid);
  //       if (start == end) return minLargest;
  //     }
  //   }

  //   return minLargest < arr.length ? minLargest : -1;
  // }

  public static int searchCeilingOfANumber(int[] arr, int key) {
    
    if (key > arr[arr.length - 1]) return -1; 

    int start = 0; 
    int end = arr.length - 1; 

    while (start <= end) {
      int distance = end + start; 
      int mid = distance / 2; 

      if (arr[mid] == key) return mid; 

      if (arr[mid] < key) {
        start = mid + 1;  // move start to the next biggest number after this 
      } else {
        end = mid - 1;  // move end to the next small number after this number bigger than key 
      }
    }

    return start; 
  }

  // Similar problem  findFllor 
  public static int searchFloorNumber(int[] arr, int key) {
    
    if (key > arr[arr.length - 1]) return -1; 

    int start = 0; 
    int end = arr.length - 1; 

    while (start <= end) {
      int distance = end + start; 
      int mid = distance / 2; 

      if (arr[mid] == key) return mid; 

      if (arr[mid] < key) {
        start = mid + 1;  // move start to the next biggest number after this 
      } else {
        end = mid - 1;  // move end to the next small number after this number bigger than key 
      }
    }

    return end;
  }

  public static void main(String[] args) {
    System.out.println(CeilingOfANumber.searchCeilingOfANumber(new int[] { 4, 6, 10 }, 6));
    System.out.println(CeilingOfANumber.searchFloorNumber(new int[] { 1, 3, 8, 10, 15 }, 12));
    System.out.println(CeilingOfANumber.searchCeilingOfANumber(new int[] { 4, 6, 10 }, 17));
    System.out.println(CeilingOfANumber.searchCeilingOfANumber(new int[] { 4, 6, 10 }, -1));
  }
}
