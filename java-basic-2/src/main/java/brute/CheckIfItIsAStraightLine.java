package brute;

import java.util.Arrays;

public class CheckIfItIsAStraightLine {
    public boolean checkStraightLine(int[][] coordinates) {
        int dx0 = coordinates[1][0] - coordinates[0][0];
        int dy0 = coordinates[1][1] - coordinates[0][1];
        for(int i=2; i<coordinates.length; i++) {
            int dx = coordinates[i][0] - coordinates[i-1][0];
            int dy = coordinates[i][1] - coordinates[i-1][1];
            if(dx * dy0 != dy * dx0) {
                return false;
            }
        }
        return true;
    }
}
