package concurrent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Duration;
import java.util.concurrent.Executors;

//
// Thread:
// -                    Thread (reserved=32856KB, committed=1492KB)
//                            (threads #32)
//                            (stack: reserved=32768KB, committed=1404KB, peak=16256KB)
//                            (malloc=52KB #201) (peak=1932KB #7263)
//                            (arena=37KB #63) (peak=1272KB #2063)
// Virtual Thread:
// -                    Thread (reserved=45178KB, committed=2394KB)
//                            (threads #44)
//                            (stack: reserved=45056KB, committed=2272KB, peak=2272KB)
//                            (malloc=72KB #270) (peak=80KB #274)
//                            (arena=51KB #87) (peak=170KB #74)
public class VirtualThreadMemoryLab {
    public static void main(String[] args) throws IOException {
        long pid = ProcessHandle.current().pid();
        System.out.println(STR."Process ID: \{pid}");
        try (var exc = Executors.newVirtualThreadPerTaskExecutor()) {
        //try(var exc = Executors.newThreadPerTaskExecutor(Thread.ofPlatform().factory())) {
            for(int i=0; i<1000; i++) {
                exc.submit(VirtualThreadMemoryLab::sneakySleep);
            }
        }
        Process process = new ProcessBuilder("jcmd", String.valueOf(pid), "VM.native_memory", "summary").start();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    private static void sneakySleep() {
        try {
            Thread.sleep(Duration.ofMinutes(2));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
