package brute;

public class MaximumOddBinaryNumber {
    public String maximumOddBinaryNumber(String s) {
        int oneCount = 0;
        int zeroCount = 0;
        String[] strs = s.split("");

        for(String str: strs) {
            if(str.equals("1")) {
                oneCount++;
            } else {
                zeroCount++;
            }
        }

        StringBuilder result = new StringBuilder();
        if(oneCount > 1) {
            result.append("1".repeat(oneCount - 1));
            result.append("0".repeat(Math.max(0, zeroCount)));
            result.append("1");
        } else {
            result.append("0".repeat(Math.max(0, zeroCount)));
            result.append("1");
        }

        System.out.println(result);

        return result.toString();
    }
}
