class Solution {
    public static void main(String[] args) {

        int[][] matrix = new  int[][] {
            new int[]{10, 20, 30},
            new int[]{5, 10, 20},
            new int[]{2, 4, 6}
        };

        TwoDPrefixsUM d = new TwoDPrefixsUM();

        int[][] res =d.prefixSum(matrix);

        d.print(res);

    }
}

class TwoDPrefixsUM {

    /**
     * 1 0 1 
     * 1 1 2 
     * 0 2 3 
     * 
     * Prefix Sum prefixSum[i][j] = 
     * prefixSum[i-1][j] + prefixSum[i][j-1] - prefixSum[i-1][j-1] + arr[i][j]
     */

     public int[][] prefixSum(int[][] matrix) {

        int[][] prefixSum = new int[matrix.length][matrix[0].length] ;
        prefixSum[0][0] = matrix[0][0];

        for (int i = 1; i < matrix.length; i++) {
            prefixSum[i][0] = matrix[i][0] + prefixSum[i-1][0];
        }

        for (int i = 1; i < matrix[0].length; i++) {
            prefixSum[0][i] = matrix[0][i] + prefixSum[0][i-1];
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                prefixSum[i][j] = prefixSum[i-1][j] + prefixSum[i][j-1] - prefixSum[i-1][j-1] + matrix[i][j];
            }
        }

        return prefixSum;
     }

     public void print(int[][] res) {
        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[i].length; j++) {
                System.out.print(res[i][j]);
                System.out.print(',');
            }

            System.out.println();
        }
     }
}