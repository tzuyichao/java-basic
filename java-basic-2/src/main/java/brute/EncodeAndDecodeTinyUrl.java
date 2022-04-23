package brute;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * 535. Encode and Decode TinyURL
 * https://leetcode.com/problems/encode-and-decode-tinyurl/
 *
 * Runtime: 5 ms, faster than 58.12% of Java online submissions for Encode and Decode TinyURL.
 * Memory Usage: 42.4 MB, less than 80.07% of Java online submissions for Encode and Decode TinyURL.
 */
public class EncodeAndDecodeTinyUrl {
    private String map = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private Map<Integer, String> database = new HashMap<>();
    private AtomicInteger counter = new AtomicInteger(1);

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        int n = counter.getAndIncrement();
        int id = n;
        StringBuilder shortUrl = new StringBuilder();
        while(n > 0) {
            shortUrl.append(map.charAt(n%62));
            n = n/62;
        }
        shortUrl.reverse();
        database.put(id, longUrl);
        return shortUrl.toString();
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        int id = 0;
        for(int i=0; i<shortUrl.length(); i++) {
            if('a' <= shortUrl.charAt(i) && shortUrl.charAt(i) <= 'z') {
                id = id * 62 + shortUrl.charAt(i) - 'a';
            }
            if('A' <= shortUrl.charAt(i) && shortUrl.charAt(i) <= 'Z') {
                id = id * 62 + shortUrl.charAt(i) - 'A' + 26;
            }
            if('0' <= shortUrl.charAt(i) && shortUrl.charAt(i) <= '9') {
                id = id * 62 + shortUrl.charAt(i) - '0' + 52;
            }
        }
        System.out.println(id);
        return database.get(id);
    }

    public static void main(String[] args) {
        var solver = new EncodeAndDecodeTinyUrl();
        String shortUrl = solver.encode("https://leetcode.com/problems/design-tinyurl");
        System.out.println(shortUrl);
        System.out.println(solver.decode(shortUrl));
    }
}
