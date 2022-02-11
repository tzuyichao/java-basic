package brute;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * 36. Valid Sudoku
 * Runtime: 31 ms, faster than 7.98% of Java online submissions for Valid Sudoku.
 * Memory Usage: 48.6 MB, less than 5.22% of Java online submissions for Valid Sudoku.
 */
public class ValidSudoku {
    private boolean isRowValid(char[][] board, int row) {
        Set<Character> set = new HashSet<>();
        return new String(board[row]).chars().filter(c -> c != '.').mapToObj(c -> (char) c).allMatch(c -> set.add(c));
    }

    private boolean isColumnValid(char[][] board, int column) {
        Set<Character> set = new HashSet<>();
        for(int i=0; i<9; i++) {
            if(board[i][column] != '.' && !set.add(board[i][column])) {
                return false;
            }
        }
        return true;
    }

    private boolean isBoxValid(char[][] board, int box) {
        int d = box / 3;
        int m = box % 3;
        Set<Character> set = new HashSet<>();
        for(int i=d*3; i<(d+1)*3; i++) {
            for(int j=m*3; j<(m+1)*3; j++) {
                if(board[i][j] != '.' && !set.add(board[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValidSudoku(char[][] board) {
        return IntStream.range(0, 9).allMatch(i -> isRowValid(board, i)) &&
                IntStream.range(0, 9).allMatch(i -> isColumnValid(board, i)) &&
                IntStream.range(0, 9).allMatch(i -> isBoxValid(board, i));
    }
}
