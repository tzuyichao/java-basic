package file;

import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * https://www.geeksforgeeks.org/java-nio-file-filesystems-class-in-java/
 */
public class FileUtil {
    public void testDefaultFileSystem() {
        FileSystem fileSystem = FileSystems.getDefault();
        for(FileStore fileStore : fileSystem.getFileStores()) {
            System.out.println(fileStore.type());
        }
    }

    public void testNewFileSystem() {
        try {
            Map<String, String> env = new HashMap<>();
            Path zipPath = Paths.get("websocket-demo.zip");
            URI uri = new URI("jar:file", zipPath.toUri().getPath(), null);
            FileSystem fileSystem = FileSystems.newFileSystem(uri, env);
            System.out.println("FileSystem created successfully");
            if(fileSystem.isOpen()) {
                System.out.println("File System is open");
                for(var fileStore : fileSystem.getFileStores()) {
                    System.out.println(String.format("%s (%s)", fileStore.name(), fileStore.type()));
                }
                for(var path: fileSystem.getRootDirectories()) {
                    System.out.println(path);
                }
            } else {
                System.out.println("File System is close");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testFiles(String path) throws IOException {
        Path p = Paths.get(path);
        if(path != null && Files.exists(p)) {
            try(Stream<Path> stream = Files.list(p)) {
                stream.filter(f -> !Files.isDirectory(f))
                        .forEach(f -> {
                            System.out.println(f.toFile().lastModified());
                            System.out.println(Duration.ofMillis(System.currentTimeMillis()).minus(Duration.ofMillis(f.toFile().lastModified())).toDays());
                        });
            }
        } else {
            System.out.println(String.format("path is null or %s doest not exists", path));
        }
    }
}
