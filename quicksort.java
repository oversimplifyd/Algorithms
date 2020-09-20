import java.util.*; 

class QuickSort {

    public static int[] sort(int[] items) {

        /**
         * 
         * Select a pivot 
         * Parition: 
         * Move all bigger elements to right of Pivot 
         * Move all smaller elements to left of pivot 
         * 
         * quickSort(leftPivot)
         * quickSort(rightPivot)
         * 
         * Partitioning Logic 
         * We will have two indexes, starting from  START  namely 
         * pIndex, iterator 
         * once item[iterator] <= pivot 
         * We want to swap element[pindex] with elment[iterator], move iterator and pIndex forward 
         * else move iterator till the end of the array 
         * 
         * Once, we are done, swap the pivot with element[pIndex] -> this creates the final partition and place pivot to the right place 
         * 
         * 7 3 1 6 8 5 3 4 
         * p             P
         *   i
         * 3 7 1 6 8 5 3 4
         *   p
         *     i         P
         * 3 1 7 6 8 5 3 4 
         *     p
         *             i P
         * 3 1 3 6 8 5 7 4 
         *       p       iP
         * Reposition Pivot: 
         * swap[pInex] with swap[pivot]
         * 3 1 3 4 8 5 7 6 
         * 
         * 
         * 
         * 
         * 
         */

        quickSort(items, 0, items.length - 1);

        return items;
    }

    private static void quickSort(int[] items, int start, int end) {

        // if start == end 
        // aray probably contains just one element, which means it is sorted 
        // if start > end, we shouldn't do anything. 

        if (start < end) {

            int pivot =  end;
            int partitionIndex = start; 

            // partitioning
            for (int i = start; i < end; i++) {
                if (items[i] <= items[pivot]) {                   
                    // put in a swap function()
                    swap(items, i, partitionIndex);
                    partitionIndex++;
                }
            }

            // put in a swap function()
            //System.out.println(partitionIndex);
            swap(items, partitionIndex, pivot);

           quickSort(items, start, partitionIndex - 1);
           quickSort(items, partitionIndex+1, end);
        }
    }

    private static void swap(int[] items, int firstIndex, int lastIndex) {
            int temp = items[firstIndex];
            items[firstIndex] = items[lastIndex];
            items[lastIndex] = temp; 
    }

    public static void main(String[] args) {
        int[] array = {7,3,1,6,8,5,3,4};
        int[] result = QuickSort.sort(array);

        for (int item: result) {
            System.out.print(item);
        }
    }
}
