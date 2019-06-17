package query;


import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.RegexpQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;

import java.io.IOException;

public class TestRegexQuery {
    public static void main(String[] args) {
        try {
            Directory directory = DataConstructor.constructSimpleDataset("/tmp/lucene");
            IndexReader indexReader = DirectoryReader.open(directory);
            IndexSearcher indexSearcher = new IndexSearcher(indexReader);

            RegexpQuery regexpQuery = new RegexpQuery(new Term("content", ".um.*"));
            TopDocs topDocs = indexSearcher.search(regexpQuery, 100);
            SimpleDatasetUtil.printQueryResult(indexReader, topDocs);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
