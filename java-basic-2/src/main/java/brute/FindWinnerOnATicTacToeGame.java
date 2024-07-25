package brute;

public class FindWinnerOnATicTacToeGame {
    // 由最後一手開始算，有算到贏家就可以結束，因為A先下，所以idx如果是偶數代表A贏。
    // 如果判斷不出贏家，已經下了9步代表和局；否則就是還可以繼續下
    public String tictactoe(int[][] moves) {
        int totalMoves = moves.length;
        int[] counter = new int[8];

        for(int idx = totalMoves-1; idx >= 0; idx -= 2) {
            int row = moves[idx][0];
            int col = moves[idx][1];

            counter[row]+=1;
            counter[col+3]+=1;

            if(row == col) {
                counter[6]+=1;
            }
            if(row + col == 2) {
                counter[7]+=1;
            }
            if(counter[row] == 3 || counter[col+3] == 3 || counter[6] == 3 || counter[7] == 3) {
                return idx % 2 == 0?"A":"B";
            }
        }
        return totalMoves == 9? "Draw":"Pending";
    }
}
