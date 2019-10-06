package com.baeldung.logisticregression;

import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public final class Utils {
    private static final Logger logger = LoggerFactory.getLogger(Utils.class);

    private Utils() {}

    public static void downloadAndSave(String url, File file) throws IOException {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        logger.info("Connect to {}", url);
        try (CloseableHttpResponse response = client.execute(new HttpGet(url))) {
            HttpEntity entity = response.getEntity();
            if(entity != null) {
                logger.info("Downloaded {} bytes", entity.getContentLength());
                try (FileOutputStream outputStream = new FileOutputStream(file)) {
                    logger.info("save to local file");
                    entity.writeTo(outputStream);
                    outputStream.flush();
                    logger.info("Local file saved");
                }
            }
        }
    }

    public static void extractTarArchive(File file, String folder) throws IOException {
        logger.info("Extracting archive {} into folder {}", file.getName(), folder);
        try(FileInputStream inputStream = new FileInputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            GzipCompressorInputStream gzipCompressorInputStream = new GzipCompressorInputStream(bufferedInputStream);
            TarArchiveInputStream tarArchiveInputStream = new TarArchiveInputStream(gzipCompressorInputStream)) {
            TarArchiveEntry tarArchiveEntry;
            while((tarArchiveEntry = (TarArchiveEntry) tarArchiveInputStream.getNextEntry()) != null) {
                extractEntry(tarArchiveEntry, tarArchiveInputStream, folder);
            }
        }
        logger.info("Archive extracted");
    }

    public static void extractEntry(TarArchiveEntry tarArchiveEntry, InputStream inputStream, String folder) throws IOException {
        final int bufferSize = 4096;
        final String path = folder + tarArchiveEntry.getName();
        if(tarArchiveEntry.isDirectory()) {
            new File(path).mkdirs();
        } else {
            int count;
            byte[] data = new byte[bufferSize];
            try (FileOutputStream outputStream = new FileOutputStream(path)) {
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
                while((count = inputStream.read(data, 0, bufferSize)) != -1) {
                    bufferedOutputStream.write(data, 0, count);
                }
            }
        }
    }
}
