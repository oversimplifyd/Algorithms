class SortedArraySquares {

    //O(N) 
    public static int[] makeSquares(int[] arr) {
    int n = arr.length;
    int[] squares = new int[n];
    int highestSquareIdx = n - 1;
    int left = 0, right = arr.length - 1;
    while (left <= right) {
      int leftSquare = arr[left] * arr[left];
      int rightSquare = arr[right] * arr[right];
      if (leftSquare > rightSquare) {
        squares[highestSquareIdx--] = leftSquare;
        left++;
      } else {
        squares[highestSquareIdx--] = rightSquare;
        right--;
      }
    }
    return squares;
  }

  public static void main(String[] args) {

    int[] result = SortedArraySquares.makeSquares(new int[] { -2, -1, 0, 2, 3 });
    for (int num : result)
      System.out.print(num + " ");
    System.out.println();

    result = SortedArraySquares.makeSquares(new int[] { -3, -1, 0, 1, 2 });
    for (int num : result)
      System.out.print(num + " ");
    System.out.println();
  }
    // O(NLogN) 
//   public static int[] makeSquares(int[] arr) {
//     int[] squares = new int[arr.length];

//     int pivotPoint = findNegativeIndex(arr); 
//     if (pivotPoint >= 0) {
//       int leftPointer = pivotPoint; 
//       int rightPointer = pivotPoint + 1; 

//       int i = 0; 
//       while (i < arr.length) {

//         if (leftPointer >= 0 && rightPointer < arr.length) {
//           int leftSquare = arr[leftPointer] * arr[leftPointer];
//           int rightSquare = arr[rightPointer] * arr[rightPointer];
//           if (leftSquare > rightSquare) {
//             squares[i] = rightSquare;
//             rightPointer++; 
//           } else {
//             squares[i] = leftSquare;
//             leftPointer--; 
//           }
//         } else if (leftPointer >= 0) {
//           int leftSquare = arr[leftPointer] * arr[leftPointer];
//           squares[i] = leftSquare;
//           leftPointer--; 
//         } else {
//           int rightSquare = arr[rightPointer] * arr[rightPointer];
//           squares[i] = rightSquare;
//           rightPointer++; 
//         }

//         i++;
//       }
//     } else {
//       //All Negative
//       //All Positive 
//       for (int i = 0; i < arr.length; i++) {
//         squares[i] = arr[i] * arr[i];
//       }
//     }
//     return squares;
//   }
//   private static int findNegativeIndex(int[] arr) {

//     int start = 0; 
//     int end = arr.length - 1; 

//     while (start <= end) {
//       int distance = start + end; 
//       int midpoint = distance / 2; 
      
//       if (arr[midpoint] <= 0 && arr[midpoint + 1] > 0) {
//         return midpoint; 
//       }
//       if (arr[midpoint] > 0) {
//         end = midpoint - 1;
//       } else {
//         start = midpoint + 1; 
//       }
//     }

//     return -1;
//   }
}
