package algorithms;
/**
* Binary Search 
* Worst Case 0(logn)
* floorIndex, CeilingIndex borders the input size for conveneince.  #Bang
 */

class BinarySearch {

     public static void main(String[] args) {

         int[] items =  {1, 2, 3, 4, 8, 11, 13, 0, 9, 7};

         int searchValue = 3;

         System.out.println(bSearch(items, searchValue));
     }

     public static boolean bSearch(int[] items, int searchValue) {

         int floorIndex = -1;
         int ceilingIndex = items.length;

         while ((floorIndex + 1) < ceilingIndex) {

             int distance = ceilingIndex - floorIndex;
             int halfDistance = distance / 2; //Splits problem in half at every iteration. Gives of Logn 

             int guessIndex = halfDistance + floorIndex; // Removes borders created by floor and ceilindexes.

             if (items[guessIndex] == searchValue) return true; 

             if (items[guessIndex] > searchValue) {

                 ceilingIndex = guessIndex;
             } else {

                 floorIndex = guessIndex;
             }
         }

         return false; 
     }
 }