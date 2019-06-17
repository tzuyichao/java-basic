package query;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;

import java.io.IOException;
import java.util.Arrays;

public class TestDisjunctionMaxQuery {
    public static void main(String[] args) {
        try {
            Directory directory = DataConstructor.constructSimpleDataset("/tmp/lucene");
            IndexReader indexReader = DirectoryReader.open(directory);
            IndexSearcher indexSearcher = new IndexSearcher(indexReader);

            PhraseQuery phraseQuery = new PhraseQuery("content", "humpty", "together");
            Query disjunctionMaxQuery = new DisjunctionMaxQuery(Arrays.asList(phraseQuery, new TermQuery(new Term("name", "First"))), 0.1f);
            TopDocs topDocs = indexSearcher.search(disjunctionMaxQuery, 100);
            SimpleDatasetUtil.printQueryResult(indexReader, topDocs);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
