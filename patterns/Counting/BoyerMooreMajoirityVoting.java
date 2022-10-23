class BoyerMooreMajorityVoting {

    // InComplete. 
    // The most frequent has to occure more than n/2 times. 

    private int mostFrequent(int[] arr) {

        int i = 0; 
        int number; 

        for (int item : arr) {
            if (i== 0) {
                number = item;
                i = 1; 
            } else if (number = item) {
                i++; 
            } else {
                i--;
            }
        }

        int count = 0; 
        for (int j = 0; j < arr.length; j++) {
            if (arr[j] == number) {
                count++;
            }
        }
        
        if (count > n/2) return number;

        return -1;
    }
}