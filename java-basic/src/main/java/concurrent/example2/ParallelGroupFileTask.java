package concurrent.example2;

import java.io.File;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ParallelGroupFileTask implements Runnable {
    private final String filename;
    private final ConcurrentLinkedQueue<File> directories;
    private final Result parallelResult;
    private boolean found;

    public ParallelGroupFileTask(String filename, Result parallelResult, ConcurrentLinkedQueue<File> directories) {
        this.filename = filename;
        this.directories = directories;
        this.parallelResult = parallelResult;
        this.found = false;
    }

    @Override
    public void run() {
        while(directories.size()>0) {
            File file = directories.poll();
            try {
                processDirectory(file, filename, parallelResult);
                if(found) {
                    System.out.printf("%s has found the file%n", Thread.currentThread().getName());
                    System.out.printf("Parallel Search: Path: %s%n", parallelResult.getPath());
                    return;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void processDirectory(File file, String filename, Result parallelResult) throws InterruptedException {
        File[] contents = file.listFiles();
        if((null == contents) || contents.length == 0) {
            return;
        }

        for(File content: contents) {
            if(content.isDirectory()) {
                processDirectory(content, filename, parallelResult);
//                if(Thread.currentThread().isInterrupted()) {
//                    throw new InterruptedException();
//                }
                if(found) {
                    return;
                }
            } else {
                processFile(content, filename, parallelResult);
//                if(Thread.currentThread().isInterrupted()) {
//                    throw new InterruptedException();
//                }
                if(found) {
                    return;
                }
            }
        }
    }

    private void processFile(File content, String filename, Result parallelResult) {
        if(content.getName().equals(filename)) {
            parallelResult.setPath(content.getAbsolutePath());
            this.found = true;
        }
    }

    public boolean isFound() {
        return found;
    }
}
