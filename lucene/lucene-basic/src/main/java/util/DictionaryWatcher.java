package util;

import java.io.IOException;
import java.nio.file.*;

public class DictionaryWatcher {
    public static void main(String[] args) throws IOException, InterruptedException {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path target = Paths.get("/Users/tzuyichao/lab/data/segment");
        target.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
        WatchKey watchKey;

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                watchService.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }));

        while((watchKey = watchService.take()) != null) {
            for(WatchEvent<?> event : watchKey.pollEvents()) {
                System.out.println("Event Kind: " + event.kind() + ". File Affected: " + event.context());
            }
            watchKey.reset();
        }
    }
}
