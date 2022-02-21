package brute;

import java.util.ArrayList;
import java.util.List;

/**
 * 212. Word Search II
 * Runtime: 2686 ms, faster than 5.01% of Java online submissions for Word Search II.
 * Memory Usage: 43.5 MB, less than 43.00% of Java online submissions for Word Search II.
 */
public class WordSearch2 {
    public boolean search(char[][] board, String word, int idx, int i, int j, boolean[][] visited) {
        if(idx == word.length()) {
            return true;
        }
        int m = board.length;
        int n = board[0].length;
        if(i<0 || j<0 || i>=m || j>=n || visited[i][j] || board[i][j] != word.charAt(idx)) {
            return false;
        }
        visited[i][j] = true;
        boolean result = search(board, word, idx+1, i-1, j, visited)
                || search(board, word, idx+1, i+1, j, visited)
                || search(board, word, idx+1, i, j-1, visited)
                || search(board, word, idx+1, i, j+1, visited);
        visited[i][j] = false;
        return result;
    }

    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        if(m == 0 || n == 0) {
            return false;
        }
        int l = word.length();
        if(l > m*n) {
            return false;
        }

        boolean[][] visited = new boolean[m][n];
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(search(board, word, 0, i, j, visited)) {
                    return true;
                }
            }
        }

        return false;
    }

    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();

        for(String word: words) {
            if(exist(board, word) == true) {
                result.add(word);
            }
        }

        return result;
    }
}
