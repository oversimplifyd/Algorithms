package algorithms;

class BubbleSort {
  public static void main(String[] args) {
    int[] sortedArr = sortArrayUsingIndexSort(new int[]{1, 3, 8, 9, 7, 6, 5, 2, 10});
    for (int item: sortedArr) {
      System.out.println(item);
    }
  }

  //Moves the largestItem to the end of the array 
  //Repeatedly do for all values in array 
  //Time 0(n^2) 
  //Space O(1)  // Inplace algorithm 
  public static int[] sortArrayUsingIndexSort(int[] items) {
    int lastIndex = items.length - 1; 
    int largestItemIndex = 0; 
    int largestItem = items[0];

    while (lastIndex > 0) {
      for (int i = 0; i <= lastIndex; i++) {
        if (items[i] >= largestItem) {
          largestItemIndex = i;
          largestItem = items[i];
        }

        if (i == lastIndex) {
          int temp = items[lastIndex];
          items[lastIndex] = items[largestItemIndex];
          items[largestItemIndex] = temp;
        }
      }
      largestItemIndex = 0; 
      largestItem = items[0];
      lastIndex--;
    }

    return items; 
  }
}