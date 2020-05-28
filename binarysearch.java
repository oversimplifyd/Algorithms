package algorithms;
/**
* Binary Search 
* Worst Case 0(logn)
* floorIndex, CeilingIndex borders the input size for conveneince.  #Bang
 */

class BinarySearch {

     public static void main(String[] args) {

         int[] items =  {1, 2, 3, 4, 8, 11, 13, 30, 77, 94};

         int searchValue = 133;

         System.out.println(bSearch(items, searchValue));
     }

     public static boolean bSearch(int[] items, int searchValue) {

         int floorIndex = 0; 
         int ceilingIndex = items.length - 1; 

         while (floorIndex <= ceilingIndex) {

             int distance = ceilingIndex + floorIndex;
             int midpoint = distance / 2; 

             if (items[midpoint] == searchValue) return true; 

             if (items[midpoint] > searchValue) {
                 ceilingIndex = midpoint - 1; 
             } else {
                 floorIndex = midpoint + 1; 
             }
         }

         return false; 
     }

     /**
     * ICake's Solution 
      */
    //  public static boolean bSearch2(int[] items, int searchValue) {

    //      int floorIndex = -1;
    //      int ceilingIndex = items.length;

    //      while ((floorIndex + 1) < ceilingIndex) {

    //          int distance = ceilingIndex - floorIndex;
    //          int halfDistance = distance / 2; //Splits problem in half at every iteration. Gives of Logn 

    //          int guessIndex = halfDistance + floorIndex; // Removes borders created by floor and ceilindexes.

    //          if (items[guessIndex] == searchValue) return true; 

    //          if (items[guessIndex] > searchValue) {

    //              ceilingIndex = guessIndex;
    //          } else {

    //              floorIndex = guessIndex;
    //          }
    //      }

    //      return false; 
    //  }
 }