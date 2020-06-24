package algorithms; 

import java.util.*; 

class Subsets  {

    //Just the way we do BFS with queues 
    // O(2^N) 
    //O(2^N) 
    
    public static List<String> getSubsets(String s) {
        List<String> subsets = new ArrayList<>();
        subsets.add("");

        for (char c: s.toCharArray()) {
            int n = subsets.size();

            for (int i = 0; i < n; i++) {
                StringBuilder perm = new StringBuilder();
                perm.append(subsets.get(i));
                perm.append(c);
                subsets.add(perm.toString());
            }
        }

        return subsets;
    }

    public static void main(String[] args) {
        String s = "abc";
        System.out.println(getSubsets(s));
    }
}
