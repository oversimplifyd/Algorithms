package algorithms; 

/** 
Runtime -> O(LogN)
**/ 

class HappyNumber {

  public static boolean find(int num) {

    int slow = num;
    int fast = num;

    do {
      slow = getSquare(slow);
      fast = getSquare(getSquare(fast)); 

    } while (slow != fast);

    return slow == 1;
  }

  private static int getSquare(int digit) {

    int sum = 0;

    while (digit > 0) {

      int leftOver = digit % 10;
      sum += leftOver * leftOver; 

      digit = digit / 10; 
    }

    /** String digits = String.valueOf(digit);
    
    int square = 0; 
    for (char ch: digits.toCharArray()) {
      int d = Character.getNumericValue(ch);
      square += (d * d);
    } **/ 
    
    return sum; 
  }

  public static void main(String[] args) {
    System.out.println(HappyNumber.find(23));
    System.out.println(HappyNumber.find(12));
  }
}