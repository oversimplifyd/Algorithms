import java.util.*; 

class TestListAsStack {

    public static void main(String[] args) {
        ListAsStack list = new ListAsStack();

        list.push(3, 1); 
        list.push(100, 1);
        list.push(120, 1);

        list.push(400, 2);
        list.push(4, 2);
        list.push(102, 2);
        list.push(940, 1);

        list.push(7,3);
        list.push(0,3);
        list.push(2,3);

        System.out.println(list.pop(1));
        System.out.println(list.pop(2));
        System.out.println(list.pop(3));

        list.push(14,3);

        System.out.println(list.pop(3));
        System.out.println(list.pop(3));
        System.out.println(list.pop(3));

        System.out.println(list.pop(3));

    }
}
class ListAsStack {

    /**
    Implement 3 stacks using a single list:

    class Stack:
        def __init__(self):
            self.list = []

        def pop(self, stack_number):
            pass

        def push(self, item, stack_number):
            pass

        // 1, 2, 4, 6, 8, 9, 10, 11, 13, 14 20

            List -> [1, 2 , 4,   11, 13, 14]
                     0+3  1+3  2+3 
                     3    4    5
                     3+3  4+3  5+3
                     6    7    8

            firstStackNextIndex = 0; 
            secondStackNextIndex = 1; 
            thirdStackNextIndex = 2; 

            Push: 
            // get the stacknumber 
            // get the nextAvailableIndexToAssign for the stack number 
            // put item in this position 
            // update nextSlot 

            Pop: 
            // get stackNumber  
            // get the value at nextSlot - 3; 
            // make nextSlot avaialble 


            stackNumber, 
            Stack 1 ->  1 2 4 
            Stack 2 ->  11, 13, 14 
            Stack 3 -> 
     */

     Map<Integer, Integer> map;
     int[] theList; 

     //List<Integer> theList;

     public ListAsStack() {

        map = new HashMap<>();
        map.put(1, 0);
        map.put(2, 1);
        map.put(3, 2);

        // This is an importatnt clarification to take care of 
        // An arraylist would have been the best candidate but we can't add at random positions in an ArrayList without first prefilling regardless of initial capacity specified or not 
        theList = new int[1000];

     }

     public int pop(int stackNumber) {

         if (map.containsKey(stackNumber)) {

             if (map.get(stackNumber) > stackNumber - 1) {
                int result = theList[map.get(stackNumber) - 3];
                map.put(stackNumber, map.get(stackNumber) - 3);

                return result; 
             }

             throw new IllegalArgumentException("Can not pop what doesn't exist");
         }

         throw new IllegalArgumentException("Invalid stack number");
     }

     public void push(int value, int stackNumber) {

         if (map.containsKey(stackNumber)) {
            theList[map.get(stackNumber)] = value;
            map.put(stackNumber, map.get(stackNumber) + 3);

            return;
         }

         throw new IllegalArgumentException("Invalid stack number");
     }
}
