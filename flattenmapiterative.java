import java.io.*;
import java.util.*;


//Sometimes Recursion is just the best way to go. 
// Check if it can be solved using recursion easily without any serious space / time implications. 
// else use a stack / iteerative 

//Sample Input 
/**
input:  dict = {
            "Key1" : "1",
            "Key2" : {
                "a" : "2",
                "b" : "3",
                "c" : {
                    "d" : "3",
                    "e" : {
                        "" : "1"
                    }
                }
            }
        }

output: {
            "Key1" : "1",
            "Key2.a" : "2",
            "Key2.b" : "3",
            "Key2.c.d" : "3",
            "Key2.c.e" : "1"
        }
 */
class Solution {

    // Iterative approach 
    static HashMap<String, String> flattenDictionary(HashMap<String, Object> dict) {
     
     Deque<HashMap<String, HashMap<String, Object>>> stack = new ArrayDeque<HashMap<String, HashMap<String, Object>>>();
     
     HashMap<String, String> finalOutput = new HashMap<String, String>();
     
     HashMap<String, HashMap<String, Object>> c = new HashMap<String, HashMap<String, Object>>();
     c.put("", dict);
     stack.push(c);
     
     while (!stack.isEmpty()) {
       HashMap<String, HashMap<String, Object>> currentStackItem = stack.pop();
       
       for(Map.Entry<String, HashMap<String, Object>> en: currentStackItem.entrySet()) {
         for(Map.Entry<String, Object> entry: en.getValue().entrySet()) {
           String keyToUse = "";
           if (en.getKey() == "") {
             keyToUse = entry.getKey();
           } else if (entry.getKey() == "") {
             keyToUse = en.getKey();
           } else {
             keyToUse = en.getKey()+"."+entry.getKey();
           }
           
         if (! (entry.getValue() instanceof HashMap)) {
           finalOutput.put(keyToUse, (String) entry.getValue());
         } else {
           HashMap<String, HashMap<String, Object>> e = new HashMap<String, HashMap<String, Object>>();
           e.put(keyToUse, (HashMap) entry.getValue());
           stack.push(e);
         }
       }
       }
     }
     
     return finalOutput;
   }

  public static void main(String[] args) {
    
  }

}