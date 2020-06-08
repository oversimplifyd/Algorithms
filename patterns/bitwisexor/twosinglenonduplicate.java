package algorithms; 

/**
In a non-empty array of numbers, every number appears exactly twice except two numbers that appear only once. Find the two numbers that appear only once.
O(N) 
 */

class TwoSingleNumbers {

  public static int[] findSingleNumbers(int[] nums) {
    int n1xn2 = 0; 

    // XOR all numbers. to get the non duplicate ones. 
    // That is, the duplicate ones cancels out. 
    for (int n: nums) {
      n1xn2 = n1xn2 ^ n;
    }

    //Get the rightmostsetbit of the non-duplicate ones 
    // check xor.txt 
    int rightmostsetbit = n1xn2 & ~(n1xn2 - 1); 

    //Our tow numbers will be such that after bitanding each number by the rightmostset bit
    // one will be greater than 0 and the other 0. These numbers are our answers.
    int firstMissingNumber = 0; 
    int secondMissingNumber = 0; 
    for (int number: nums) {
      if ((rightmostsetbit & number) != 0) {
        firstMissingNumber = firstMissingNumber ^ number;
      } else {
        secondMissingNumber = secondMissingNumber ^ number;
      }
    }

    return new int[] { firstMissingNumber, secondMissingNumber};
  }

  public static void main(String[] args) {
    int[] arr = new int[] { 1, 4, 2, 1, 3, 5, 6, 2, 3, 5 };
    int[] result = TwoSingleNumbers.findSingleNumbers(arr);
    System.out.println("Single numbers are: " + result[0] + ", " + result[1]);

    arr = new int[] { 2, 1, 3, 2 };
    result = TwoSingleNumbers.findSingleNumbers(arr);
    System.out.println("Single numbers are: " + result[0] + ", " + result[1]);
  }
}
