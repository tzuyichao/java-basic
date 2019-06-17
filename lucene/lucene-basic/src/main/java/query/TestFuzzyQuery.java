package query;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;

import java.io.IOException;

public class TestFuzzyQuery {
    public static void main(String[] args) throws IOException {
        Directory directory = DataConstructor.constructSimpleDataset("/tmp/lucene");
        IndexReader indexReader = DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        // X上限還是兩個
        FuzzyQuery fuzzyQuery = new FuzzyQuery(new Term("content", "Xumpty"));
        TopDocs topDocs = indexSearcher.search(fuzzyQuery, 100);
        SimpleDatasetUtil.printQueryResult(indexReader, topDocs);
    }
}
