package query.filtering;

import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import query.DataConstructor;
import java.io.IOException;


public class AdvancedFilteringLab1 {


    public static void main(String[] args) throws IOException {
        Directory directory = DataConstructor.constructSimpleDataset("/tmp/lucene");
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
