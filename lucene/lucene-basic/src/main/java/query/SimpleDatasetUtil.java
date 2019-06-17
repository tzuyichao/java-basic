package query;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

import java.io.IOException;

public final class SimpleDatasetUtil {

    public static void printQueryResult(IndexReader indexReader, TopDocs topDocs) throws IOException {
        if(topDocs.scoreDocs.length == 0) {
            System.out.println("No Data Found");
        }
        for(ScoreDoc scoreDoc : topDocs.scoreDocs) {
            Document doc = indexReader.document(scoreDoc.doc);
            for(IndexableField field : doc.getFields()) {
                System.out.println("Indexable Field: " + field.name());
            }
            //System.out.println(doc.toString());
            System.out.print("doc#: " + scoreDoc.doc);
            System.out.print(", name: " + doc.getField("name").stringValue());
            System.out.print(", content: " + doc.getField("content").stringValue());
            System.out.println(", numStore: " + doc.getField("numStore").numericValue());
        }
    }
}
