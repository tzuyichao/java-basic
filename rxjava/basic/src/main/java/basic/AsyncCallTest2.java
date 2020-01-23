package basic;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class AsyncCallTest2 {
    public static void main(String[] args) throws Exception {
        List<String> ipList = new ArrayList<>();
        for(int i=1; i<10; i++) {
            ipList.add("192.168.0." + i);
        }
        long start = System.currentTimeMillis();
        Flowable.fromArray(ipList.toArray(new String[0]))
                .subscribeOn(Schedulers.io())
                .map(v -> AsyncCallTest.rpcCall(v, v))
                .subscribe(log::info);
        log.info("Time: {}", (System.currentTimeMillis() - start));
        Thread.currentThread().join();
    }
}
