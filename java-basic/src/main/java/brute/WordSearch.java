package brute;

/**
 * 79. Word Search
 */
public class WordSearch {
    class Record {
        int m;
        int n;

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof Record) {
                Record r = (Record) obj;
                if(r.m == this.m && r.n == this.n) {
                    return true;
                }
            }
            return false;
        }
    }

    public boolean exist(char[][] board, String word) {
        return false;
    }
}
