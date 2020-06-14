package algorithms;
/**
* Binary Search 
* Worst Case 0(logn)
* floorIndex, CeilingIndex borders the input size for conveneince.  #Bang
 */

class ShiftedArraySearch {

     public static void main(String[] args) {

         int[] items =  {1, 2};

         int searchValue = 2;

         System.out.println(searchShiftedAray(items, searchValue));
     }

     public static int searchShiftedAray(int[] items, int value) {
         int rotationPoint = findRotationPoint(items, 0, items.length - 1);

         if (rotationPoint == 0 || value < items[0]) {
             return bsearch(items, rotationPoint, items.length - 1, value);
         }

         return bsearch(items, 0, rotationPoint - 1, value);

     }

     //O(Logn) 
     private static int findRotationPoint(int[] items, int start, int end) {
         int floorIndex = start; 
         int ceilingIndex = end; 

         while (floorIndex <= ceilingIndex) {
             int distance = floorIndex + ceilingIndex;
             int midpoint = distance /2; 

             if (midpoint == 0 || items[midpoint] < items[midpoint-1]) {
                 return midpoint; 
             }

             if (items[midpoint] > items[0]) {
                 floorIndex = midpoint + 1;
             } else {
                 ceilingIndex = midpoint - 1; 
             }
         }

         return 0;
     }

     //O(LogN) 
     private static int bsearch(int[] items, int start, int end, int searchValue) {

         int floorIndex = start; 
         int ceilingIndex = end;

         while (floorIndex <= ceilingIndex) {

             int distance = ceilingIndex + floorIndex;
             int midpoint = distance / 2; 

             if (items[midpoint] == searchValue) return midpoint;

             if (items[midpoint] > searchValue) {
                 ceilingIndex = midpoint - 1; 
             } else {
                 floorIndex = midpoint + 1; 
             }
         }

         return -1; 
     }
 }