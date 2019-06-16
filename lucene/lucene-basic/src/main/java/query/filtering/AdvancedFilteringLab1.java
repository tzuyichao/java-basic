package query.filtering;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.MMapDirectory;
import org.apache.lucene.util.BytesRef;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class AdvancedFilteringLab1 {
    private static Document makeDocument(String name, String content, int num) {
        Document doc = new Document();

        StringField nameField = new StringField("name", name, Field.Store.YES);
        doc.add(nameField);
        TextField contentField = new TextField("content", content, Field.Store.YES);
        doc.add(contentField);
        StoredField numField = new StoredField("num", num);
        doc.add(numField);

        return doc;
    }

    public static void main(String[] args) throws IOException {
        Path indexPath = Paths.get("/tmp/lucene");
        File indexFile = indexPath.toFile();
        if(indexFile.exists()) {
            String[] files = indexFile.list();
            for(String file: files) {
                String filePath = "/tmp/lucene" + File.separator + file;
                System.out.println(filePath);
                Files.delete(Paths.get(filePath));
            }
        }
        Analyzer analyzer = new StandardAnalyzer();
        Directory directory = new MMapDirectory(indexPath);
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
        indexWriter.addDocument(makeDocument("First", "Humpty Dumpty sat on a wall,", 100));
        indexWriter.addDocument(makeDocument("Second", "Humpty Dumpty had a great fall.", 200));
        indexWriter.addDocument(makeDocument("Third", "All the king's horses and all the king's men", 300));
        indexWriter.addDocument(makeDocument("Fourth", "Couldn't put Humpty together again.", 400));
        indexWriter.commit();
        indexWriter.close();

        IndexReader indexReader = DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        Query query = new TermQuery(new Term("content", "humpty"));
        TopDocs topDocs = indexSearcher.search(query,100);
        System.out.println("===> Searching 'humpty'");
        for(ScoreDoc scoreDoc : topDocs.scoreDocs) {
            Document doc = indexReader.document(scoreDoc.doc);
            System.out.println("doc#: " + scoreDoc.doc);
            System.out.println("name: " + doc.getField("name").stringValue());
            System.out.println("content: " + doc.getField("content").stringValue());
            System.out.println("num: " + doc.getField("num").stringValue());
        }
        System.out.println("===> Query by content range");
        BooleanQuery.Builder builder = new BooleanQuery.Builder();
        builder.add(new TermQuery(new Term("content", "humpty")), BooleanClause.Occur.SHOULD);
        builder.add(new TermQuery(new Term("content", "dumpty")), BooleanClause.Occur.MUST_NOT);
        //builder.add(new TermRangeQuery("content", new BytesRef("a"), new BytesRef("c"), true, true), BooleanClause.Occur.MUST);
        BooleanQuery bq = builder.build();
        Query q2 = new ConstantScoreQuery(bq);
        topDocs = indexSearcher.search(q2, 100);
        for(ScoreDoc scoreDoc : topDocs.scoreDocs) {
            Document doc = indexReader.document(scoreDoc.doc);
            System.out.println("doc#: " + scoreDoc.doc);
            System.out.println("name: " + doc.getField("name").stringValue());
            System.out.println("content: " + doc.getField("content").stringValue());
            System.out.println("num: " + doc.getField("num").stringValue());
        }

        indexReader.close();
    }
}
