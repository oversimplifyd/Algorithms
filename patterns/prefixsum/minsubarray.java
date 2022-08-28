class MinimumSubArray {

    /** 
     * 1D prefix Sum. 
     * Given an array of positive integers nums and a positive integer target, return the minimal length of a contiguous subarray [numsl, numsl+1, ..., numsr-1, numsr]
     *  of which the sum is greater than or equal to target. If there is no such subarray, return 0 instead.
     */
    public int minSubArrayLen(int target, int[] nums) {
        
        //         Sliding Window Solution
        //         int windowStart = 0; 
        //         int sum = 0; 
                
        //         int min = Integer.MAX_VALUE; 
                
        //         for(int windowEnd = 0; windowEnd < nums.length; windowEnd++) {
                    
        //             sum += nums[windowEnd]; 
                    
        //             while (sum >= target) {
        //                 if ((windowEnd - windowStart + 1) < min) {
        //                     min = windowEnd - windowStart + 1;
        //                 }
        //                 sum -= nums[windowStart];
        //                 windowStart++;
        //             }
        //         }
                
        //         return min == Integer.MAX_VALUE ? 0 : min;
                
                int right = -1; 
                
                // prefix Sum 
                for (int i = 1; i < nums.length; i++) {
                    nums[i] = nums[i] + nums[i-1];
                }

                for (int i = 0; i < nums.length; i++) {
                    if (nums[i] >= target) {
                        right = i;
                        break;
                    }
                }
                
                if (right < 0) return 0;

                int min = right + 1;
                int left = 0;
                
                while (left <= right && right < nums.length) {
                    if (nums[right] - nums[left] < target) {
                        right++;
                        continue;
                    }
                    
                    min = Math.min(min, right - left);
                    left++;
                }
                
                return min == Integer.MAX_VALUE ? 0 : min;
    }
}