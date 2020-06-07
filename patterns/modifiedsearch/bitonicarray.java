package algorithms; 

/** 
Find the maximum value in a given Bitonic array. An array is considered bitonic if it is monotonically increasing and then monotonically decreasing. Monotonically increasing or decreasing means that for any index i in the array arr[i] != arr[i+1].
 */
 
class MaxInBitonicArray {

  public static int findMax(int[] arr) {
    
    int start = 0; 
    int end = arr.length - 1; 

    // 1, 3, 8, 12, 4, 2
    while (start < end) {  // break when start == end 
      int mid = (end + start) / 2; 
      if (arr[mid] > arr[mid + 1]) {
        end = mid;
      } else {
        start = mid + 1; 
      }
    }

    return arr[start];  
    // we may  use ether start or end since we have a solution when both pointers overlaps. 
  }

  public static void main(String[] args) {
    System.out.println(MaxInBitonicArray.findMax(new int[] { 1, 3, 8, 12, 4, 2 }));
    System.out.println(MaxInBitonicArray.findMax(new int[] { 3, 8, 3, 1 }));
    System.out.println(MaxInBitonicArray.findMax(new int[] { 1, 3, 8, 12 }));
    System.out.println(MaxInBitonicArray.findMax(new int[] { 10, 9, 8 }));
  }
}
