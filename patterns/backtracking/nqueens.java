import java.util.*; 

class NQueen {

    /**
     *  This problem was asked by Microsoft.
     * 
        You have an N by N board. Write a function that, given N, 
        returns the number of possible arrangements of the board where N queens 
        can be placed on the board without threatening each other, i.e. no two queens
        share the same row, column, or diagonal.

        2 x 2   -> 2 -> 0 
        1 0      
        0 0

        0 1
        0 0

        3 x 3  -> 3 
        1 0 0 
        0 0 0 
        0 0 0 

        1 0 0 
        0 0 1 
        0 0 0 

        1 0 0 
        0 0 1 
        0 0 0 
        
        0 1 0 
        0 0 0
        1 0 0

        0 0 0
        0 0 1
        0 0 0

        1 0 0 0 
        0 0 0 0 
        0 0 0 0
        0 0 0 0 

        1 0 0 0 
        0 0 1 0 
        0 0 0 0
        0 0 0 0 

        1 0 0 0 
        0 0 1 0 
        0 0 0 0
        0 1 0 0 

        0 1 0 0 
        0 0 0 1 
        1 0 0 0
        0 0 1 0 

        Identify safeMoves  
        safeMove  -> the horizontal, vertical or diagnoal positions do not have a 1 filled already 

        foreach of the queens 

        if (QueenCount == 0) return true; -> there is a solution 
         - if itSafe() 
            - placeQueen 
                - recursively call for the board and (totalQueen - 1) -> remeainingQueens, remembering the last placedQueen position
         - else 
            Backtrack -> increase the count of the queen again, and unmark the lastPosition a queen was placed. 

     */

     public static boolean placeQueen(int[][] board, int N) {

        return placeQueenRecursive(board, N, new PositionToRemember(0,0));
     }

     private static boolean placeQueenRecursive(int[][] board, int remainingQueens, PositionToRemember lastPosition) {

        if (remainingQueens == 0) return true; 

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {

                if (isSafeToPlace(board, row, col)) {
                    board[row][col] = 1; 
                    printCurrentBoardState(board);
                    if (placeQueenRecursive(board, remainingQueens - 1, new PositionToRemember(row, col))) return true;
                }

            }
        }

        board[lastPosition.row][lastPosition.col] = 0;

        return false; 
     }

     private static boolean isSafeToPlace(int[][] board, int row, int col) {

        if (row >= 0 && col >= 0 && row <= board.length - 1 && col <= board[0].length - 1) {
            if (checkNonDiagonals(board, row, col) && checkDiagonals(board, row, col)) {
                return true; 
            }
        }

        return false; 
     }

     private static boolean checkNonDiagonals(int[][] board, int row, int col) {

        int index = 0; 
        while (index < board[0].length) {
            if (board[row][index] == 1 || board[index][col] == 1) return false; 
            index++; 
        }

        return true; 
     }

     private static boolean checkDiagonals(int[][] board, int row, int col) {

         /**
         *     0 1 2 3 
         * 0   0 0 0 0 
         * 1   0 0 0 0 
         * 2   0 0 x 0 
         * 3   0 0 0 0 
         * 
         * 2 2 -> -1 -1 -> 1 1  0 0 2 2 3 3
         * 
         * 
         */

        int rowIndex = row; 
        int colIndex = col; 

        while (rowIndex < board.length  && colIndex < board.length) {

           if (board[rowIndex][colIndex] == 1) return false; 
           rowIndex++; 
           colIndex++;
        }

        rowIndex = row; 
        colIndex = col;

        while (rowIndex >= 0 && colIndex >= 0) {

           if (board[rowIndex][colIndex] == 1) return false; 
           rowIndex--;
           colIndex--;
        }

        rowIndex = row; 
        colIndex = col;

        while (rowIndex < board.length && colIndex >= 0) {

           if (board[rowIndex][colIndex] == 1) return false; 
           rowIndex++;
           colIndex--;
        }

        rowIndex = row; 
        colIndex = col;

        while (rowIndex >= 0 && colIndex < board.length) {

           if (board[rowIndex][colIndex] == 1) return false; 
           rowIndex--;
           colIndex++;
        }

        return true; 
     }

     public static void  printCurrentBoardState(int[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                System.out.print(board[row][col]+"-");
            }
            System.out.println();
        }
        System.out.println("D   I    V   I   D   E   R");
     }

     public static void main(String[] args) {

        int[][] board = new int[3][3];

        System.out.println(placeQueen(board, 3));
     }
}

class PositionToRemember {

    int row; 
    int col; 

    public PositionToRemember(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
