import java.io.*;
import java.util.*;


//Sometimes Recursion is just the best way to go. 
// Check if it can be solved using recursion easily without any serious space / time implications. 
// else use a stack / iteerative 
class Solution {
  
   static HashMap<String, String> flattenDictionary(HashMap<String, Object> dict) {
     
     HashMap<String, String> result = flattenRecursive("", dict, new HashMap<String, String>());
     
    return result;
     
   }
  
  static HashMap<String, String> flattenRecursive(String key, HashMap<String, Object> dict, HashMap<String, String> finalOutput) {
    
    for (Map.Entry<String, Object> entry: dict.entrySet()) {
      String keyToUse = key; 
        
        if (key == "") {
            keyToUse = entry.getKey();
        } else if (entry.getKey() == "") { 
            keyToUse = key;
        } else {
            keyToUse = key+"."+entry.getKey();
        }
      
      if (! (entry.getValue() instanceof HashMap)) {
         finalOutput.put(keyToUse, (String) entry.getValue());
      } else {
          flattenRecursive(keyToUse, (HashMap) entry.getValue(), finalOutput);
      }

    }
    
    return finalOutput; 
  }

  public static void main(String[] args) {
    
  }

}