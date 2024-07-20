package brute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueens {
    public static void main(String[] args) {
        int n = 8;
        List<List<String>> solutions = solveNQueens(n);
        for (List<String> solution : solutions) {
            for (String row : solution) {
                System.out.println(row);
            }
            System.out.println();
        }
    }

    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> solutions = new ArrayList<>();
        char[] emptyRow = new char[n];
        Arrays.fill(emptyRow, '.');
        solve(solutions, new ArrayList<>(), emptyRow, n);
        return solutions;
    }

    private static void solve(List<List<String>> solutions, List<StringBuilder> board, char[] emptyRow, int n) {
        if (board.size() == n) {
            List<String> solution = new ArrayList<>();
            for (StringBuilder row : board) {
                solution.add(row.toString());
            }
            solutions.add(solution);
            return;
        }

        int row = board.size();
        for (int col = 0; col < n; col++) {
            if (isValid(board, row, col, n)) {
                StringBuilder newRow = new StringBuilder(new String(emptyRow));
                newRow.setCharAt(col, 'Q');
                board.add(newRow);
                solve(solutions, board, emptyRow, n);
                board.removeLast();
            }
        }
    }

    private static boolean isValid(List<StringBuilder> board, int row, int col, int n) {
        for (int i = 0; i < row; i++) {
            StringBuilder currentRow = board.get(i);
            if (currentRow.charAt(col) == 'Q') {
                return false;
            }
            int leftDiagonal = col - (row - i);
            int rightDiagonal = col + (row - i);
            if (leftDiagonal >= 0 && currentRow.charAt(leftDiagonal) == 'Q') {
                return false;
            }
            if (rightDiagonal < n && currentRow.charAt(rightDiagonal) == 'Q') {
                return false;
            }
        }
        return true;
    }
}

