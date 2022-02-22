package brute;

import java.util.stream.IntStream;

/**
 * 794. Valid Tic-Tac-Toe State
 * 規則裡寫的都要做，雖然舉例看起來沒有，但是包含要做判斷輸贏的動作
 * FIXME: finish me
 */
public class ValidTicTacToeState {
    public boolean validTicTacToe(String[] board) {
        int x = 0;
        int o = 0;
        for(int i=0; i<board.length; i++) {
            for(int j=0; j<3; j++) {
                if('X' == board[i].charAt(j)) {
                    x++;
                }
                if('O' == board[i].charAt(j)) {
                    o++;
                }
            }
        }
        if(x == o || (x-o) == 1) {
            return true;
        }
        return false;
    }
}
