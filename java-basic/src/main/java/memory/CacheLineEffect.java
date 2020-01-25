package memory;

import lombok.extern.slf4j.Slf4j;

/**
 * https://tech.meituan.com/2016/11/18/disruptor.html
 */
@Slf4j
public class CacheLineEffect {
    static long[][] arr;
    public static void main(String[] args) {
        arr = new long[1024*1024][];
        for(int i=0; i<1024*1024; i++) {
            arr[i] = new long[8];
            for(int j=0; j<8; j++) {
                arr[i][j] = 0L;
            }
        }
        long sum = 0L;
        long mark = System.currentTimeMillis();
        for(int i=0; i<1024*1024; i++) {
            for(int j=0; j<8; j++) {
                sum += arr[i][j];
            }
        }
        log.info("Loop time: {} ms", (System.currentTimeMillis() - mark));
        mark = System.currentTimeMillis();
        for(int i=0; i<8; i++) {
            for(int j=0; j<1024*1024; j++) {
                sum += arr[j][i];
            }
        }
        log.info("Loop time: {} ms", (System.currentTimeMillis() - mark));
    }
}
