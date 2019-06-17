package query;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.store.Directory;

import java.io.IOException;

public class TestWildcardQuery {
    public static void main(String[] args) throws IOException {
        Directory directory = DataConstructor.constructSimpleDataset("/tmp/lucene");
        IndexReader indexReader = DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        WildcardQuery wildcardQuery = new WildcardQuery(new Term("content", "*um*"));
        TopDocs topDocs = indexSearcher.search(wildcardQuery, 100);
        SimpleDatasetUtil.printQueryResult(indexReader, topDocs);
    }
}
