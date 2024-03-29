import java.util.*;

import javax.naming.RefAddr;

class Solution {

    public static void main(String[] args) {
        int[][] a = new int[][] {
            new int[]{0,1,2,3,4,5,7},
            new int[]{0,6,2,3,4,7,9},
            new int[]{8,1,2,3,4,9,8},
        };

        int[][] c = new int[][] {
            new int[]{1},
            new int[]{0,1,2,3,4},
            new int[]{4,0,1,2,3},
        };

        int[][] b = new int[][] {
            new int[]{340,88,187,280,359,397,300,255,258,201,301,17,245,124,380,206,345,377,191},
            new int[]{224,300,255,258,201,301,17,245,124,380,206,339,260,55,398,83,266,201,189},
            new int[]{375,15,240,22,157,360,314,300,255,258,201,301,17,245,124,380,206,303,296},
            new int[]{331,87,86,257,116,6,300,255,258,201,301,17,245,124,380,206,394,102,276},
            new int[]{118,207,263,176,295,180,235,137,300,255,258,201,301,17,245,124,380,206,337},
        };

        int[][] d = new int[][] {
            new int[]{913,510,506,730,880,145,269,976,636,667,355,406,46,309,149,986,314,173,35,786,677,389,825,897,80,569,596,268,523,941,187,444,411,55,613,974,457,179,464,533,503,51,313,804,494,122,608,85,654,393,727,86,448,380,848,227,430,331,948,681,864,695,386,354,716,971,83,528,751,54,944,441,77,573,38,190,863,904,215,423,106,397,223,211,858,443,544,466,541,338,244,365,41,747,992,781,479,567,866,32,292,801,472,560,694,851,196,831,325,779,737,926,283,318,20,350,361,512,977,720,333,509,228,330,208,290,336,604,384,488,165,240,501,591,920,576,332,507,890,931,329,61,154,912,427,714,561,59,584,140,353,914,713,270,731,692,828,829,929,28,812,440,186,638,889,185,241,998,750,219,649,137,853,738,982,666,505,151,19,793,597,34,490,985,193,775,956,838,249,961,669,857,709,264,937,255,133,777,822,99,867,693,275,847,625,679,495,536,823,155,454,673,575,653,66,141,722,342,598,31,92,621,928,390,585,3,792,946,826,983,617,873,980,565,47,610,180,230,144,698,462,13,242,453,374,73,930,373,9,237,633,364,127,650,687,459,538,918,312,485,903,159,908,212,60,174,404,949,978,407,644,113,748,950,136,524,774,477,586,123,433,258,33,845,429,899,753,434,383,274,840,267,532,834,696,958,830,370,21,87,712,301,239,2,813,463,114,859,339,819,514,537,924,628,735,672,766,319,251,547,306,745,282,778,743,566,357,218,199,780,755,17,942,960,648,795,426,917,726,993,82,376,742,233,439,68,221,932,238,191,194,758,571,701,540,981,65,381,486,839,662,497,768,962,172,594,200,849,344,952,142,799,26,95,916,367,202,682,243,11,707,771,217,741,422,770,181,45,349,216,892,634,483,587,169,896,62,518,939,475,415,401,44,277,88,105,345,590,531,733,307,431,554,878,476,321,935,25,762,48,496,498,936,854,868,671,728,678,419,821,808,539,220,579,652,715,189,317,656,645,451,188,405,158,417,925,740,81,222,129,911,388,250,639,706,470,265,534,456,806,520,347,248,206,446,861,400,907,796,157,247,413,736,279,299,303,789,623,308,837,79,395,615,311,810,131,968,824,461,964,261,609,152,117,63,209,870,548,542,375},
            new int[]{375,542,548,870,209,63,117,152,609,261,964,461,824,968,131,810,311,615,395,79,837,308,623,789,303,299,279,736,413,247,157,796,907,400,861,446,206,248,347,520,806,456,534,265,470,706,639,250,388,911,129,222,81,740,925,417,158,405,188,451,645,656,317,189,715,652,579,220,539,808,821,419,678,728,671,868,854,936,498,496,48,762,25,935,321,476,878,554,431,307,733,531,590,345,105,88,277,44,401,415,475,939,518,62,896,169,587,483,634,892,216,349,45,181,770,422,741,217,771,707,11,243,682,202,367,916,95,26,799,142,952,344,849,200,594,172,962,768,497,662,839,486,381,65,981,540,701,571,758,194,191,238,932,221,68,439,233,742,376,82,993,726,917,426,795,648,960,942,17,755,780,199,218,357,566,743,778,282,745,306,547,251,319,766,672,735,628,924,537,514,819,339,859,114,463,813,2,239,301,712,87,21,370,830,958,696,834,532,267,840,274,383,434,753,899,429,845,33,258,433,123,586,477,774,524,136,950,748,113,644,407,978,949,404,174,60,212,908,159,903,485,312,918,538,459,687,650,127,364,633,237,9,373,930,73,374,453,242,13,462,698,144,230,180,610,47,565,980,873,617,983,826,946,792,3,585,390,928,621,92,31,598,342,722,141,66,653,575,673,454,155,823,536,495,679,625,847,275,693,867,99,822,777,133,255,937,264,709,857,669,961,249,838,956,775,193,985,490,34,597,793,19,151,505,666,982,738,853,137,649,219,750,998,241,185,889,638,186,440,812,28,929,829,828,692,731,270,713,914,353,140,584,59,561,714,427,912,154,61,329,931,890,507,332,576,920,591,501,240,165,488,384,604,336,290,208,330,228,509,333,720,977,512,361,350,20,318,283,926,737,779,325,831,196,851,694,560,472,801,292,32,866,567,479,781,992,747,41,365,244,338,541,466,544,443,858,211,223,397,106,423,215,904,863,190,38,573,77,441,944,54,751,528,83,971,716,354,386,695,864,681,948,331,430,227,848,380,448,86,727,393,654,85,608,122,494,804,313,51,503,533,464,179,457,974,613,55,411,444,187,941,523,268,596,569,80,897,825,389,677,786,35,173,314,986,149,309,46,406,355,667,636,976,269,145,880,730,506,510,913}
        };

        int[][] e = new int[][] {
            new int[]{1, 2, 3, 4},
            new int[]{2, 1, 3, 4},
        };

        LongestCommonSubpath l = new LongestCommonSubpath();

        System.out.println(l.longestCommonSubpath(3, a));
        System.out.println(l.longestCommonSubpath(5, b));
        System.out.println(l.longestCommonSubpath(5, c));
        System.out.println(l.longestCommonSubpath(5, d));
    }
} 

class LongestCommonSubpath {
    public int longestCommonSubpath(int n, int[][] paths) {
        
        int min = Integer.MAX_VALUE;
        int min_index = -1;         
        
        for (int i = 0; i < paths.length; i++) {
            if (paths[i].length < min) {
                min = paths[i].length; 
                min_index = i;
            }
        }

        int[] valToSearch = paths[min_index];
        
        int left = 0; 
        int right = valToSearch.length - 1;

        int max = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2; 

            int length = mid + 1;

            int res  = this.mainSearch(length, valToSearch, paths, min_index);

            if (res == -1) {
                right = mid -1; 
            } else {
                max = Math.max(max, length);
                left = mid + 1;
            }
        }

        return max;
    }

    private int mainSearch(int length, int[] refA, int[][] arrList, int skip) {
        
        Map<Integer, Set<Long>> map = new HashMap<>();
        long mod = (long)(Math.pow(10,11) + 3);

        for (int i = 0; i < arrList.length; i++) {
           Set<Long> hashSet = this.computeHashSet(length, arrList[i], mod);
           map.put(i, hashSet);
        }

        Set<Long> theSet = map.get(skip);

        Iterator<Long> setIter = theSet.iterator();

        int found = 0; 

        while (setIter.hasNext()) {
            Long search = setIter.next();

            for (Map.Entry<Integer, Set<Long>> entry: map.entrySet()) {
                Set<Long> currentSet = entry.getValue();
                if (!currentSet.contains(search)) {
                    break;
                }
                found++;
            }

            if (found == arrList.length) {
                return 1;
            }
            found = 0;
        }
        return found == arrList.length ? 1 : -1;
    }

    private Set<Long> computeHashSet(int patternLength, int[] text, long mod) {
        
        long textHash = 0; 
        int constant = 10003; 
        long multiplier = 1; 
        
        Set<Long> hashes = new HashSet<>();

        for (int i = 0; i < patternLength - 1; i++) {
            multiplier = (multiplier * constant) % mod; 
        }

        for (int i = 0; i < patternLength; i++) {
            textHash = (textHash * constant + text[i]) % mod; 
        }

        hashes.add(textHash);

        for (int i = 1; i <= text.length - patternLength; i++) {

            textHash = ((textHash - multiplier * text[i-1]) * constant) % mod; 
            textHash = textHash + text[i+patternLength - 1];

            if (textHash < 0) {
                textHash = textHash + mod;
            }

            hashes.add(textHash);
        }
        
        return hashes;
    }
}
