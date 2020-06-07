package algorithms; 

/**
Given an array of numbers which is sorted in ascending order and is rotated ‘k’ times around a pivot, find ‘k’.

You can assume that the array does not have any duplicates.
 */
 
class RotationCountOfRotatedArray {

  public static int countRotations(int[] arr) {

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

    if (start == 0) return start; 

    if (start == arr.length - 1) return 0;
    
    return start % arr.length + 1;  // at this point star == end there fore we. can return either start or end
  }

  // 1, 2, 3, 4, 5, 6
  public static void main(String[] args) {
    System.out.println(RotationCountOfRotatedArray.countRotations(new int[] { 10, 15, 1, 3, 8 }));
    System.out.println(RotationCountOfRotatedArray.countRotations(new int[] { 4, 5, 7, 9, 10, -1, 2 }));
    System.out.println(RotationCountOfRotatedArray.countRotations(new int[] { 1, 3, 8, 10 }));
  }
}

