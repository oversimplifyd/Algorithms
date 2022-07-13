/**
 * Suffix Array: 
 * An efficient algorithm used to solve pattern search problems, by preprocessing the text unlike 
 * Rabin-Karp, Bayer Moore that preprocesses the pattern. Also compared to Suffix Tree using Compressed Trie, 
 * it's more efficient. 
 * 
 * Given: "banana"
 * Preprocessing Text: 
 * Time Complexity: O(k*nlogn)
 * Where: K*NLOGN is the cost of sorting. 
 * K -> String comparison during sort, max length of input string. 
 * Space -> O(n)
 * 
 * index    Suffix      Sorted Suffix   Sorted Array
 * 0        banana      a               5
 * 1         anana      ana             3
 * 2          nana      anana           1
 * 3           ana      banana          0
 * 4            na      na              4
 * 5             a      nana            2
 * 
 * 
 * Search for the first match or single occurence: 
 * Pattern = "nana"
 * Time: O(NLOGN).  Log N -> Cost of binary search and N -> String comparison at each mid. 
 * Space: O(1)
 * Using binary search, check if the pattern matches or is less or greater than current mid. 
 * Iteratively do this until a mathch is found or -1 otherwise.
 * 
 * a.compareTo(b); -> Case Sensitive 
 * a.compareToIgnore(b) -> Case Insensitive
 * 
 */
import java.util.*;

 class Solution {

    public static void main(String[] args) {
        SuffixArrayFirst instance = new SuffixArrayFirst();
        System.out.println(instance.search("ba", "banana"));
        System.out.println(instance.search("ana", "banana"));
    }
 }

 class SuffixArrayFirst {

    private String[] unsortedSuffixArray; 
    private String[] sortedSuffixArray;

    private int[] sortedIndexes; 

    private void preProcessText(String text){

        this.unsortedSuffixArray = this.buildSuffixes(text);
        this.sortedSuffixArray = this.unsortedSuffixArray.clone(); 

        Arrays.sort(this.sortedSuffixArray); // N*K*LOGN

        this.sortedIndexes = this.buildSuffixIndex();
    }

    private int[] buildSuffixIndex(){

        int[] suffixIndexes = new int[this.sortedSuffixArray.length];

        for (int i = 0; i < this.sortedSuffixArray.length; i++) {
            for (int j = 0; j < this.unsortedSuffixArray.length; j++) {
                if (this.sortedSuffixArray[i] == this.unsortedSuffixArray[j]) {
                    suffixIndexes[i] = j;
                    break;
                }
            }
        }
        
        return suffixIndexes;
    }

    private String[] buildSuffixes(String text) {

        int i = 0; 

        String[] suffixes = new String[text.length()];

        while (i < text.length()) {
            suffixes[i] = text.substring(i);
            i++;
        }

        return suffixes;
    }

    private boolean binarySearch(String pattern) {

        int  start = 0; 
        int end = this.sortedIndexes.length - 1;

        while (start <= end) {

            // 5 3 1 0 4 2 
            int mid = start + ((end - start) / 2);

            int currentIndex = this.sortedIndexes[mid];

            int compare = (this.unsortedSuffixArray[currentIndex]).compareToIgnoreCase(pattern);
            
            if (compare == 0) {
                return true;
            } else if (compare > 0) {
                end = mid - 1;
            } else {
                start = mid + 1; 
            }
        }

        return false;
    }

    public boolean search(String pattern, String text){
        this.preProcessText(text);
        return this.binarySearch(pattern);
    }
 }
