package query;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;

import java.io.IOException;

public class TestPrefixQuery {
    public static void main(String[] args) throws IOException {
        Directory directory = DataConstructor.constructSimpleDataset("/tmp/lucene");
        IndexReader indexReader = DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        PrefixQuery prefixQuery = new PrefixQuery(new Term("content", "hum"));
        TopDocs topDocs = indexSearcher.search(prefixQuery, 100);
        System.out.println("===> Prefix Query for 'hum'");
        SimpleDatasetUtil.printQueryResult(indexReader, topDocs);

        indexReader.close();
    }
}
