package algorithms; 

/**
Given an array of integers arr, write a function absSort(arr), that sorts the array according to the absolute values of the numbers in arr. If two numbers have the same absolute value, sort them according to sign, where the negative numbers come before the positive numbers.
*/
import java.io.*;
import java.util.*;

class Solution { 
  
  /**
  // I am afraid this wouldhave worked but Arrays.sort() only allows Comparator on Object
  // This means, we may have to first cnvert the primitives to Objets, sort and the back to primitives 
  static int[] absSort(int[] arr) {
    
    Integer[] intObject = new Integer[arr.length];
    
    for(int i = 0; i < arr.length; i++) {
      intObject[i] = Integer.valueOf(arr[i]);
    }
    
    Arrays.sort(intObject, new AbsoluteValueComparator());
    return intObject;
  }**/
  
	 static int[] absSort(int[] arr) {
		
    if (arr.length == 1) return arr;
    
    int arrLength = arr.length; 
    int midPoint = arrLength / 2; 
    
    int[] leftPart = absSort(Arrays.copyOfRange(arr, 0, midPoint));
    int[] rightPart = absSort(Arrays.copyOfRange(arr, midPoint, arr.length));
    
    return merge (leftPart, rightPart);
	}
  
  private static int[] merge(int[] a, int[] b) {
    int leftPointer = 0; 
    int rightPointer = 0; 
    
    int[] result = new int[a.length + b.length];
    
    int index = 0; 
    
    while (leftPointer < a.length || rightPointer < b.length) {
      
      if (leftPointer < a.length && rightPointer < b.length) {
        
          int absValueLeft = Math.abs(a[leftPointer]);
          int absValueRight = Math.abs(b[rightPointer]);
        
          if (absValueLeft < absValueRight) {
            result[index] = a[leftPointer];
            leftPointer++; 
          } else if (absValueLeft == absValueRight) {
             if (a[leftPointer] < 0) {
                result[index] = a[leftPointer];
                leftPointer++; 
             } else {
                result[index] = b[rightPointer];
                rightPointer++; 
             }
         } else {
            result[index] = b[rightPointer];
            rightPointer++; 
         }
        
      } else if (leftPointer < a.length) {
         result[index] = a[leftPointer];
         leftPointer++;
      } else {
         result[index] = b[rightPointer];
         rightPointer++; 
      }
    
    index++;                   
    }
        return result;
  } 
   
  public static void main(String[] args) {
    
  }
}

class AbsoluteValueComparator implements Comparator<Integer> {
    public int compare(Integer a, Integer b) {
      //if (Math.abs(a) > Math.abs(b)) return 1; 
      //if (Math.abs(a) < Math.abs(b)) return -1; 
      
      // if math.abs of the two are the same 
      // we need to check for negatives to bring it before the positives 
      //if (a < b) return -1; 
      //if (a > b) return 1; 
      
      return 0; 
    }
}
