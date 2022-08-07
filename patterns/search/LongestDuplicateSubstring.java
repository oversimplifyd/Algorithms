import java.util.*;


/**
 *  Long read, dont know what' all about. 
 * https://leetcode.com/problems/longest-duplicate-substring/discuss/1548642/Java-or-18ms-100-(By-far-the-fastest)-or-No-binary-search-No-sliding-window-No-Rabin-Karp
 * https://leetcode.com/problems/longest-duplicate-substring/discuss/327643/Step-by-step-to-understand-the-binary-search-solution
 */
class Solution {
    public static void main(String[] args) {
        LongestDuplicateSubstring l = new LongestDuplicateSubstring();
        System.out.println(new LongestDuplicateSubstring().longestDupSubstring("ababdaebdabedeabbdddbcebaccececbccccebbcaaabaadcadccddaedaacaeddddeceedeaabbbbcbacdaeeebaabdabdbaebadcbdebaaeddcadebedeabbbcbeadbaacdebceebceeccddbeacdcecbcdbceedaeebdaeeabccccbcccbceabedaedaacdbbdbadcdbdddddcdebbcdbcabbebbeabbdccccbaaccbbcecacaebebecdcdcecdeaccccccdbbdebaaaaaaeaaeecdecedcbabedbabdedbaebeedcecebabedbceecacbdecabcebdcbecedccaeaaadbababdccedebeccecaddeabaebbeeccabeddedbeaadbcdceddceccecddbdbeeddabeddadaaaadbeedbeeeaaaeaadaebdacbdcaaabbacacccddbeaacebeeaabaadcabdbaadeaccaecbeaaabccddabbeacdecadebaecccbabeaceccaaeddedcaecddaacebcaecebebebadaceadcaccdeebbcdebcedaeaedacbeecceeebbdbdbaadeeecabdebbaaebdddeeddabcbaaebeabbbcaaeecddecbbbebecdbbbaecceeaabeeedcdecdcaeacabdcbcedcbbcaeeebaabdbaadcebbccbedbabeaddaecdbdbdccceeccaccbdcdadcccceebdabccaebcddaeeecddddacdbdbeebdabecdaeaadbadbebecbcacbbceeabbceecaabdcabebbcdecedbacbcccddcecccecbacddbeddbbbadcbdadbecceebddeacbeeabcdbbaabeabdbbbcaeeadddaeccbcdabceeabaacbeacdcbdceebeaebcceeebdcdcbeaaeeeadabbecdbadbebbecdceaeeecaaaedeceaddedbedbedbddbcbabeadddeccdaadaaeaeeadebbeabcabbdebabeedeeeccadcddaebbedadcdaebabbecedebadbdeacecdcaebcbdababcecaecbcbcdadacaebdedecaadbaaeeebcbeeedaaebbabbeeadadbacdedcbabdaabddccedbeacbecbcccdeaeeabcaeccdaaaddcdecadddabcaedccbdbbccecacbcdecbdcdcbabbeaacddaeeaabccebaaaebacebdcdcbbbdabadeebbdccabcacaacccccbadbdebecdaccabbecdabdbdaebeeadaeecbadedaebcaedeedcaacabaccbbdaccedaedddacbbbdbcaeedbcbecccdbdeddcdadacccdbcdccebdebeaeacecaaadccbbaaddbeebcbadceaebeccecabdadccddbbcbaebeaeadacaebcbbbdbcdaeadbcbdcedebabbaababaacedcbcbceaaabadbdcddadecdcebeeabaadceecaeccdeeabdbabebdcedceaeddaecedcdbccbbedbeecabaecdbba"));
        System.out.println(new LongestDuplicateSubstring().longestDupSubstring("zxcvdqkfawuytt"));
        System.out.println(new LongestDuplicateSubstring().longestDupSubstring("nnpxouomcofdjuujloanjimymadkuepightrfodmauhrsy"));
        System.out.println(new LongestDuplicateSubstring().longestDupSubstring("okmzpmxzwjbfssktjtebhhxfphcxefhonkncnrumgduoaeltjvwqwydpdsrbxsgmcdxrthilniqxkqzuuqzqhlccmqcmccfqddncchadnthtxjruvwsmazlzhijygmtabbzelslebyrfpyyvcwnaiqkkzlyillxmkfggyfwgzhhvyzfvnltjfxskdarvugagmnrzomkhldgqtqnghsddgrjmuhpgkfcjkkkaywkzsikptkrvbnvuyamegwempuwfpaypmuhhpuqrufsgpiojhblbihbrpwxdxzolgqmzoyeblpvvrnbnsdnonhpmbrqissifpdavvscezqzclvukfgmrmbmmwvzfpxcgecyxneipexrzqgfwzdqeeqrugeiupukpveufmnceetilfsqjprcygitjefwgcvqlsxrasvxkifeasofcdvhvrpmxvjevupqtgqfgkqjmhtkyfsjkrdczmnettzdxcqexenpxbsharuapjmdvmfygeytyqfcqigrovhzbxqxidjzxfbrlpjxibtbndgubwgihdzwoywqxegvxvdgaoarlauurxpwmxqjkidwmfuuhcqtljsvruinflvkyiiuwiiveplnxlviszwkjrvyxijqrulchzkerbdyrdhecyhscuojbecgokythwwdulgnfwvdptzdvgamoublzxdxsogqpunbtoixfnkgbdrgknvcydmphuaxqpsofmylyijpzhbqsxryqusjnqfikvoikwthrmdwrwqzrdmlugfglmlngjhpspvnfddqsvrajvielokmzpmxzwjbfssktjtebhhxfphcxefhonkncnrumgduoaeltjvwqwydpdsrbxsgmcdxrthilniqxkqzuuqzqhlccmqcmccfqddncchadnthtxjruvwsmazlzhijygmtabbzelslebyrfpyyvcwnaiqkkzlyillxmkfggyfwgzhhvyzfvnltjfxskdarvugagmnrzomkhldgqtqnghsddgrjmuhpgkfcjkkkaywkzsikptkrvbnvuyamegwempuwfpaypmuhhpuqrufsgpiojhblbihbrpwxdxzolgqmzoyeblpvvrnbnsdnonhpmbrqissifpdavvscezqzclvukfgmrmbmmwvzfpxcgecyxneipexrzqgfwzdqeeqrugeiupukpveufmnceetilfsqjprcygitjefwgcvqlsxrasvxkifeasofcdvhvrpmxvjevupqtgqfgkqjmhtkyfsjkrdczmnettzdxcqexenpxbsharuapjmdvmfygeytyqfcqigrovhzbxqxidjzxfbrlpjxibtbndgubwgihdzwoywqxegvxvdgaoarlauurxpwmxqjkidwmfuuhcqtljsvruinflvkyiiuwiiveplnxlviszwkjrvyxijqrulchzkerbdyrdhecyhscuojbecgokythwwdulgnfwvdptzdvgamoublzxdxsogqpunbtoixfnkgbdrgknvcydmphuaxqpsofmylyijpzhbqsxryqusjnqfikvoikwthrmdwrwqzrdmlugfglmlngjhpspvnfddqsvrajviel"));
        System.out.println(new LongestDuplicateSubstring().longestDupSubstring("obamejakdakdgobamejakdakdg"));
    }
} 

class LongestDuplicateSubstring {

    public String longestDupSubstring(String text) {

        int left = 0; 
        int right = text.length() - 1;
        long mod = (long) Math.pow(2, 32);

        String result = "";

        while (left < right) {
            int mid = left + (right - left) / 2;

            String res = this.search(mid + 1, text, mod);
            if (res.equals("")) {
                right = mid;
            } else {
                result = res; 
                left = mid + 1;
            }
        }

        return result;
    }

    private String search(int patternLength, String text, long mod) {
        
        int constant = 31; 
        long h = 0; 
        long multiplier = 1; 
        int textLength = text.length();

        HashMap<Long, List<Integer>> foundHashes = new HashMap<>();

        for (int i = 0; i < patternLength - 1; i++) {
            multiplier = (multiplier * constant) % mod;
        }

        for (int i = 0; i < patternLength; i++) {
            int textVal = text.charAt(i) - 'a';
            h = (h * constant + textVal) % mod;
        }

        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        foundHashes.put(h, list);
        
        for (int i = 1; i <= textLength - patternLength; i++) {
            int prevNumVal = text.charAt(i-1) - 'a';
            int nextNumVal = text.charAt(i+patternLength - 1) - 'a';

            h = (((h - prevNumVal * multiplier) * constant) + nextNumVal) % mod;

            if (h < 0) {
                h = h + mod;
            }

            String currentSubstring = text.substring(i, i + patternLength);
            
            if (foundHashes.containsKey(h)) {
                List<Integer> current = foundHashes.get(h);
                for (int j = 0; j < current.size(); j++) {
                    if (text.substring(current.get(j), current.get(j) + patternLength).equals(currentSubstring)) {
                        return currentSubstring;
                    }
                }

                current.add(i);
                foundHashes.put(h, current);
            } else {
                ArrayList<Integer> newList = new ArrayList<>();
                newList.add(i);
                foundHashes.put(h, newList);
            }
        }

        return "";
    }
}
