package query;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.similarities.TFIDFSimilarity;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.MMapDirectory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class DataConstructor {
    private static Document makeSimpleDatasetDocument(String name, String content, int num) {
        Document doc = new Document();

        StringField nameField = new StringField("name", name, Field.Store.YES);
        doc.add(nameField);
        TextField contentField = new TextField("content", content, Field.Store.YES);
        doc.add(contentField);
        IntPoint numField = new IntPoint("num", num);
        doc.add(numField);
        StoredField numStoredField = new StoredField("numStore", num);
        doc.add(numStoredField);

        return doc;
    }

    public static Directory constructSimpleDataset(String path) throws IOException {
        Path indexPath = Paths.get(path);
        File indexFile = indexPath.toFile();
        if(indexFile.exists()) {
            String[] files = indexFile.list();
            for(String file: files) {
                String filePath = path + File.separator + file;
                Files.delete(Paths.get(filePath));
            }
        }
        Analyzer analyzer = new StandardAnalyzer();
        Directory directory = new MMapDirectory(indexPath);
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
        indexWriter.addDocument(makeSimpleDatasetDocument("First", "Humpty Dumpty sat on a wall,", 100));
        indexWriter.addDocument(makeSimpleDatasetDocument("Second", "Humpty Dumpty had a great fall.", 200));
        indexWriter.addDocument(makeSimpleDatasetDocument("Third", "All the king's horses and all the king's men", 300));
        indexWriter.addDocument(makeSimpleDatasetDocument("Fourth", "Couldn't put Humpty together again.", 400));
        indexWriter.commit();
        indexWriter.close();
        return directory;
    }

    public static Directory constructSimpleDataset(String path, TFIDFSimilarity similarity) throws IOException {
        Path indexPath = Paths.get(path);
        File indexFile = indexPath.toFile();
        if(indexFile.exists()) {
            String[] files = indexFile.list();
            for(String file: files) {
                String filePath = path + File.separator + file;
                Files.delete(Paths.get(filePath));
            }
        }
        Analyzer analyzer = new StandardAnalyzer();
        Directory directory = new MMapDirectory(indexPath);
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        indexWriterConfig.setSimilarity(similarity);
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
        indexWriter.addDocument(makeSimpleDatasetDocument("First", "Humpty Dumpty sat on a wall,", 100));
        indexWriter.addDocument(makeSimpleDatasetDocument("Second", "Humpty Dumpty had a great fall.", 200));
        indexWriter.addDocument(makeSimpleDatasetDocument("Third", "All the king's horses and all the king's men", 300));
        indexWriter.addDocument(makeSimpleDatasetDocument("Fourth", "Couldn't put Humpty together again.", 400));
        indexWriter.commit();
        indexWriter.close();
        return directory;
    }
}
