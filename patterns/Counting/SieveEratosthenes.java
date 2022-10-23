class SieveEratosthenes {

    private int countPrimes(int[] arr) {
        boolean[] list = new boolean[n];

        for (int i = 2; i < n; i++) {
            if (list[i] == false) {
                for (int j = 2 * i; j < n; j += i) {
                    list[j] = true;
                }
            }
        }

        int count = 0;

        for (int i = 2; i < list.length; i++) {
            if (!list[i])
                count++;
        }

        return count;
    }
}