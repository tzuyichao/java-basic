package brute;

/**
 * 289. Game of Life
 * https://leetcode.com/problems/game-of-life/
 *
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Game of Life.
 * Memory Usage: 40.7 MB, less than 84.66% of Java online submissions for Game of Life.
 */
public class GameOfLife {
    private int lives(int[][] board, int i, int j) {
        int m = board.length;
        int n = board[0].length;
        int res = 0;
        if(i-1 >= 0 && j-1 >=0) {
            res += board[i-1][j-1];
        }
        if(i-1 >= 0) {
            res += board[i-1][j];
        }
        if(i-1 >=0 && j+1 < n) {
            res += board[i-1][j+1];
        }
        if(j-1 >=0) {
            res += board[i][j-1];
        }
        if(j+1 < n) {
            res += board[i][j+1];
        }
        if(i+1 < m && j-1 >=0) {
            res += board[i+1][j-1];
        }
        if(i+1 < m) {
            res += board[i+1][j];
        }
        if(i+1 < m && j+1 < n) {
            res += board[i+1][j+1];
        }
        return res;
    }

    public void gameOfLife(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        int[][] res = new int[m][n];
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                var lives = lives(board, i, j);
                if(board[i][j] == 1) {
                    if(lives == 2 || lives == 3) {
                        res[i][j] = 1;
                    } else {
                        res[i][j] = 0;
                    }
                } else {
                    if(lives == 3) {
                        res[i][j] = 1;
                    } else {
                        res[i][j] = 0;
                    }
                }

            }
        }
        for(int i=0; i<m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = res[i][j];
            }
        }
    }
}
