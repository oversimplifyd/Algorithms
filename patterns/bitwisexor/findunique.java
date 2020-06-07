package algorithms; 

class SingleNumber {
  public static int findSingleNumber(int[] arr) {
    
    int n = 0; 
    
    for (int i = 0; i < arr.length; i++) {
      n = n ^ arr[i];
    }

    return n;
  }

  public static void main( String args[] ) {
    System.out.println(findSingleNumber(new int[]{1, 4, 2, 1, 3, 2, 3}));
  }
}
