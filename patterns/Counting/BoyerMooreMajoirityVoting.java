class BoyerMooreMajorityVoting {

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
        
        return number;
    }
}