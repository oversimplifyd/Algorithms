import java.util.*; 

class KnightTour {

    /**
     * Given N * M board anda Knight, find if it is posible to visit all squares only once by the knight 
     * 
     * possibleMoves 
     * leftUp,  leftDown, rightUp, rightDown, upLeft,   upRight, downleft,  downRight 
     * [-1, 2]  [-1, -2]  [1,2]    [1,-2]     [2, -1]   [2, 1]   [-2, -1]   [-2, 1]
     * 
     * 
     * moveMatrix 
     * nonOrthogonalDirections  [-1, -1, 1, 1, 2, 2, -2, -2]
     * orthogonalDirections     [2, -2, 2, -2, -1, 1, -1, 1]
     * 
     * 0 0 0    1 0 0   1 0 0
     * 0 0 0    0 0 0   0 0 1 
     * 
     * 0 0      1 0     1 0 
     * 0 0      0 0     0 0
     * 0 0      0 1     1 1
     * 
     * 0 0 0    1 0 0   1 0 1   1 0 1   1 0 1   
     * 0 0 0    0 0 0   0 0 0   1 0 0   1 0 1 
     * 0 0 0    0 1 0   0 1 0   1 1 0   1 1 0
     *  
     * 0 0 0    1 0 0   1 1 0   1 1 0   1 0 0   1 0 0   1 0 0   1 0 0   1 0 0
     * 0 0 0    0 0 1   0 0 1   0 0 1   1 0 1   1 1 1   1 1 1   0 1 1   1 1 1 
     * 0 0 0    0 0 0   1 0 0   1 0 1   1 0 1   1 0 1   1 0 1   1 0 1   1 0 1
     * 0 0 0    0 0 0   0 0 0   0 0 0   0 0 1   0 1 1   1 1 1   1 0 1   1 0 0
     * 
     * 
     * starting from left corner, 
     * pick a legal move from the moveMatrix for the current position, recurse, 
     * until, there is a solution OR there is no solution 
     * 
     * ff there's no solution.... backtrack the current move, and pick the next legal move from the moveeMatrix subsequentially ntil there  no more moves left for this position in moveMatrix and there is no solution. 
     * 
     * No solution -> I still have unfilled squares but no legal moves anymore. 
     * Solution: I do not have unfilled squares anymore
     * 
     * 
     */

     public static boolean canTour(int[][] board) {

        int[][] legalMovesMatrix = {{-1, 2}, {-1, -2}, {1, 2}, {1, -2}, {2, -1}, {2, 1}, {-2, 1}, {-2, -1}};

        return canTourRecurse(board, 0, 0, legalMovesMatrix);
     }

     public static boolean canTourRecurse(int[][] board, int row, int col, int[][] legalMovesMatrix) {

        board[row][col] = 1;  //mark the current cell as possible since it's safe. 

        for (int i = 0; i < legalMovesMatrix.length; i++) {

            int xMove = row + legalMovesMatrix[i][0];
            int yMove = col + legalMovesMatrix[i][1];

            if (isSafe(board, xMove, yMove)) {
                // System.out.println(xMove+"..."+yMove);
                printCurrentBoardState(board);       
                canTourRecurse(board, xMove, yMove, legalMovesMatrix);
            }
        }        

        if (hasSolution(board)) return true;; 

        board[row][col] = 0; //backtracking.. 

        return false;
     }

     private static boolean isSafe(int[][] board, int xMove, int yMove) {
         return xMove >= 0 && yMove >= 0 && xMove < board.length && yMove < board[0].length && board[xMove][yMove] != 1;
     }

     private static boolean hasSolution(int[][] board) {

         for (int row = 0; row < board.length; row++) {
             for (int col = 0; col < board[row].length; col++) {
                 if (board[row][col] == 0) return false; 
             }
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

        int[][] board = new int[5][5];
        System.out.println(canTour(board));
     }
}
