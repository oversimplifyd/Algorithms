import java.util.*; 

class LinkedListAsNumber {

    /**
     * Let's represent an integer in a linked list format by having each node represent a digit in the number. The nodes make up the number in reversed order.

        For example, the following linked list:

        1 -> 2 -> 3 -> 4 -> 5
        is the number 54321.

        Given two linked lists in this format, return their sum in the same linked list format.

        For example, given

        9 -> 9
        5 -> 2
        return 124 (99 + 25) as:

        4 -> 2 -> 1

        // iteatie over the first linkedlist  
        // Buld a sring rom linkedList in that order 
        // Get the interger value of the sring 
        // Add integers. 
        // 
        // String.valueOf(integer);  convert string to Linkedlist   

        Integer.valueOf() works for String 
        Character.getNumericValue() character 

        StringBuilder.indert(position, string)
        .append doesn't work in this context 
     */

     public static  LinkedList<Integer> sum( LinkedList<Integer> firstList,  LinkedList<Integer> secondList) {

        if (firstList == null || secondList == null) {
            throw new IllegalArgumentException("Can not add null");
        }

        StringBuilder firstString = new StringBuilder();
        StringBuilder secondString = new StringBuilder();

        // 9 -> 4
        // 5 -> 2 

        while (firstList.size() > 0 || secondList.size() > 0) {

            if (firstList.size() > 0) {
                firstString.insert(0, String.valueOf(firstList.poll()));
            }

            if (secondList.size() > 0) {
                secondString.insert(0, String.valueOf(secondList.poll()));
            }
        }

        int result = Integer.valueOf(firstString.toString()) + Integer.valueOf(secondString.toString());

        String toLinkedList = String.valueOf(result);

        LinkedList<Integer> newList = new LinkedList<>();

        // 49 + 25 -> 7 4 
        // 4 7 

        for (char character: toLinkedList.toCharArray()) {
            newList.add(0, Character.getNumericValue(character));
        }

        return newList; 
     }

     public static void main(String[] args) {

        LinkedList<Integer> firstList = new LinkedList<>();
        LinkedList<Integer> secondList = new LinkedList<>();

        firstList.add(0);
        firstList.add(9);
        firstList.add(9);

        secondList.add(5);
        secondList.add(2);

        System.out.println(sum(firstList, secondList));
     }
}
