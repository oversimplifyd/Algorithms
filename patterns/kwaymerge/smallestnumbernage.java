/**
Given ‘M’ sorted arrays, find the smallest range that includes at least one number from each of the ‘M’ lists.
Runtime -> O(NLogM) N = total items, M = size of input lists 
Space => O(M)
 */
import java.util.*;

class Item {
  int itemIndex; 
  int listIndex;

  public Item(int itemIndex, int listIndex) {
    this.listIndex = listIndex;
    this.itemIndex = itemIndex; 
  }
}

class SmallestRange {

  public static int[] findSmallestRange(List<Integer[]> lists) {
    // [1, 5, 8]
    // [4, 12], 
    // [7, 8, 10]

    // 1 4 7   6 
    // 4 5 7.  3
    // 
    // 5 7 12. 7

    // 7 8 12  4 

    // 7 8 12. 5
    // 8 8 12. 4
    // 8 10 12 4 

    // largest, smallest 
    // smallestRangeSoFar
    // range - largest - smallest 
    // Item, value, index
    // TODO: Write your code here

    PriorityQueue<Item> minHeap = new PriorityQueue<>((a,b) -> lists.get(a.listIndex)[a.itemIndex] - lists.get(b.listIndex)[b.itemIndex]);
    
    int largestSofar = Integer.MIN_VALUE;
    int smallestSoFar = Integer.MAX_VALUE;
    int smallestRangeSoFar = Integer.MAX_VALUE;

    int[] range = new int[]{-1, -1};

    for (int i = 0; i < lists.size(); i++) {
      smallestSoFar = Math.min(smallestSoFar, lists.get(i)[0]);
      largestSofar = Math.max(largestSofar, lists.get(i)[0]);
      minHeap.offer(new Item(0, i));
    }

    smallestRangeSoFar = Math.min(smallestRangeSoFar, largestSofar - smallestSoFar);
    range[0] = smallestSoFar;
    range[1] = largestSofar;

    while (!minHeap.isEmpty()) {
      Item currentItem = minHeap.poll();

      // check if the next item to pop is still in range 
      if (lists.get(currentItem.listIndex).length > currentItem.itemIndex + 1) {
        int nextValue = lists.get(currentItem.listIndex)[currentItem.itemIndex + 1];
        largestSofar = Math.max(largestSofar, lists.get(currentItem.listIndex)[currentItem.itemIndex + 1]);
        
        minHeap.offer(new Item(currentItem.itemIndex + 1, currentItem.listIndex));
        
        smallestSoFar = lists.get(minHeap.peek().listIndex)[minHeap.peek().itemIndex];
      }

      if (minHeap.size() < lists.size()) return range; 

      if (smallestRangeSoFar > largestSofar - smallestSoFar) {
          range[0] = smallestSoFar;
          range[1] = largestSofar;

          smallestRangeSoFar = largestSofar - smallestSoFar;
      }
    }

    return range; 
  }

  public static void main(String[] args) {
    // Integer[] l1 = new Integer[] { 1, 5, 8 };
    // Integer[] l2 = new Integer[] { 4, 12 };
    // Integer[] l3 = new Integer[] { 7, 8, 10 };
    Integer[] l1 = new Integer[] { 1, 9 };
    Integer[] l2 = new Integer[] { 4, 12 };
    Integer[] l3 = new Integer[] { 7, 10, 16 };
    List<Integer[]> lists = new ArrayList<Integer[]>();
    lists.add(l1);
    lists.add(l2);
    lists.add(l3);
    int[] result = SmallestRange.findSmallestRange(lists);
    System.out.print("Smallest range is: [" + result[0] + ", " + result[1] + "]");
  }
}
