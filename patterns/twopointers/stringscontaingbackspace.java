package algorithms; 

/**
Given two strings containing backspaces (identified by the character ‘#’), check if the two strings are equal.
 */

class BackspaceCompare {

  public static boolean compare(String str1, String str2) {

    int str1Pointer = str1.length() - 1; 
    int str2Pointer = str2.length() - 1; 

    while (str1Pointer >= 0 || str2Pointer >= 0) {

      int str1NextValidPointer = getNextValidIndex(str1, str1Pointer);
      int str2NextValidPointer = getNextValidIndex(str2, str2Pointer);

      if (str1NextValidPointer < 0 && str2NextValidPointer < 0) {
        return true;
      }

      if (str1NextValidPointer < 0 || str2NextValidPointer < 0) {
        return false; 
      }

      if (str1.charAt(str1NextValidPointer) != str2.charAt(str2NextValidPointer)) {
        return false; 
      }
      
      str1Pointer = str1NextValidPointer - 1; 
      str2Pointer = str2NextValidPointer - 1; 
    }

    return true;
  }

  private static int getNextValidIndex(String str, int index) {

    int backSpaceCount = 0; 
    
    while (index >= 0) {
      if (str.charAt(index) == '#') {
        backSpaceCount++;
      } else if (backSpaceCount > 0 && str.charAt(index) != '#') {
        backSpaceCount--;
      } else {
        break; //it's a valid index, backspace count = 0 and currenchChar is not '#'
      }

      index--; 
    }

    return index; 
  }
}
