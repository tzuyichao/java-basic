package brute;

/**
 * 130. Surrounded Regions
 * https://leetcode.com/problems/surrounded-regions/
 *
 *
 */
public class SurroundedRegions {
    public void solve(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if((i == 0 || i == m-1 || j==0 || j == n-1) && board[i][j] == 'O') {
                    solveDFS(board, i, j);
                }
            }
        }
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(board[i][j] == 'O') board[i][j] = 'X';
                if(board[i][j] == 'V') board[i][j] = 'O';
            }
        }
    }

    private void solveDFS(char[][] board, int i, int j) {
        if(board[i][j] == 'O') {
            board[i][j] = 'V';
            if(i > 0 && board[i-1][j] == 'O') {
                solveDFS(board, i-1, j);
            }
            if(j > 0 && board[i][j-1] == 'O') {
                solveDFS(board, i, j-1);
            }
            if(i < board.length-1 && board[i+1][j] == 'O') {
                solveDFS(board, i+1, j);
            }
            if(j < board[0].length - 1 && board[i][j+1] == 'O') {
                solveDFS(board, i, j+1);
            }
        }
    }
}
