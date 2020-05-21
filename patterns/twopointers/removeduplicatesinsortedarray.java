package algorithms;

class RemoveDuplicates {

  public static int remove(int[] arr) {

    if (arr.length < 2) {
      return arr.length; 
    }

    int slowPointer = 0; 
    int fastPointer = 1;

    int duplicates = 0; 

    while (fastPointer < arr.length) {
      if (arr[slowPointer] == arr[fastPointer]) {
        duplicates++; 
      }
      slowPointer++;
      fastPointer++;
    }
    return arr.length - duplicates;
  }
}
