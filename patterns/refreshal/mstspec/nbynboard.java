import java.util.*; 

class NbyNboard {

    public static int[][] placeQueen(int[][] board, int n) {
        /**
         * You have an N by N board. 
         * Write a function that, given N, 
         * returns the number of possible arrangements of the board 
         * where N queens can be placed on the board without threatening 
         * each other, i.e. no two queens share the same row, column, or diagonal.
         * 
         * 
         * This can be solved using backtracking 
         * Step 1: 
         * Find a solution 
         * - for i to rows.length
         * - Place the first queen on cell(i, 0)
         * - Check if it's safe: 
         *       If it's safe simply means, no other queen is on it's column, row and diagonal 
         * - if (isSafe) 
         *       Try to place the next queen on same row, next column
         * - 
         * 
         * Step 2: 
         * Generate Possible solutions 
         * 
         */
    }
}