package query;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

import java.io.IOException;

public final class SimpleDatasetUtil {

    public static void printQueryResult(IndexReader indexReader, TopDocs topDocs) throws IOException {
        for(ScoreDoc scoreDoc : topDocs.scoreDocs) {
            Document doc = indexReader.document(scoreDoc.doc);
            System.out.println("doc#: " + scoreDoc.doc);
            System.out.println("name: " + doc.getField("name").stringValue());
            System.out.println("content: " + doc.getField("content").stringValue());
            System.out.println("num: " + doc.getField("num").stringValue());
        }
    }
}
