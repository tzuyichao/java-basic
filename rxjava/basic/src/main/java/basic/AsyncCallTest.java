package basic;

import io.reactivex.Flowable;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
public class AsyncCallTest {
    public static String rpcCall(String ip, String param) {
        log.info("ip: {}, param: {}", ip, param);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return param;
    }

    public static void main(String[] args) {
        List<String> ipList = new ArrayList<>();
        for(int i=1; i<10; i++) {
            ipList.add("192.168.0." + i);
        }
        long start = System.currentTimeMillis();
        Flowable.fromArray(ipList.toArray(new String[0]))
                .map(v -> rpcCall(v, v))
                .subscribe(System.out::println);
        log.info("Time: {}", (System.currentTimeMillis() - start));
    }
}
