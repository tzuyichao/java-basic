package brute;

/**
 * = 6
 * Runtime: 36 ms, faster than 14.08% of Java online submissions for ZigZag Conversion.
 * Memory Usage: 40.7 MB, less than 23.91% of Java online submissions for ZigZag Conversion.
 */
public class ZigZagConversion {
    public String convert(String s, int numRows) {
        if(numRows == 1) {
            return s;
        }
        int len = s.length();
        int unitSize = numRows+(numRows-2);
        int width = (numRows-1)*(len/unitSize + (len%unitSize==0?0:1));
        char[][] matrix = new char[numRows][width];
        int count = 0;
        for(int j=0; j<width; j++) {

            if (count%unitSize >= 0 && count%unitSize < numRows) {
                for (int i = 0; i < numRows; i++) {
                    if(count < s.length()) {
                        matrix[i][j] = s.charAt(count);
                        count += 1;
                    }
                }
            } else {
                if(count < s.length()) {
                    matrix[unitSize-count%unitSize][j] = s.charAt(count);
                    count += 1;
                }
            }
        }

        StringBuilder res = new StringBuilder();

        for(int i=0;i<numRows; i++) {
            for(int j=0; j<width; j++) {
                if(matrix[i][j] != 0) {
                    res.append(matrix[i][j]);
                }
            }
        }

        return res.toString();
    }
}
