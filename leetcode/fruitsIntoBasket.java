class Solution {
    public int totalFruit(int[] fruits) {

        if (fruits.length == 1) return fruits.length;

        int first = -1;
        int second = -1;
        
        int windowStart = 0;
        
        int max = 0;
        int tempMax = 0;

        for (int windowEnd = 0; windowEnd < fruits.length; windowEnd++) {

            int currentFruit = fruits[windowEnd];

            if (first < 0) {
                first = currentFruit;
            } else if (second < 0 && first != currentFruit) {
                second = currentFruit;
            }

            if (currentFruit != first && currentFruit != second) {

                max = Math.max(max, tempMax);

                int subWindowEnd = windowStart;

                while (subWindowEnd <= windowEnd - 1) {
                    if (fruits[windowStart] != fruits[subWindowEnd]) {
                        windowStart++;
                    } else {
                        subWindowEnd++;
                    }
                }
                second = currentFruit;
                first = fruits[windowStart];
                tempMax = windowEnd - windowStart + 1;
            } else {
                tempMax++;
            }
        }
        return Math.max(tempMax, max);
    }
}
