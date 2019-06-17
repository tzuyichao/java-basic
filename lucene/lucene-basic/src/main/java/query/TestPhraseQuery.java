package query;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MultiPhraseQuery;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;

import java.io.IOException;

public class TestPhraseQuery {
    public static void main(String[] args) throws IOException {
        Directory directory = DataConstructor.constructSimpleDataset("/tmp/lucene");
        IndexReader indexReader = DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        System.out.println("PhraseQuery:");
        PhraseQuery phraseQuery = new PhraseQuery("content", "humpty", "together");
        TopDocs topDocs = indexSearcher.search(phraseQuery, 100);
        SimpleDatasetUtil.printQueryResult(indexReader, topDocs);

        System.out.println("MultiPhraseQuery:");
        MultiPhraseQuery.Builder multiPhraseQueryBuilder = new MultiPhraseQuery.Builder();
        multiPhraseQueryBuilder.add(new Term("content", "humpty"));
        multiPhraseQueryBuilder.add(new Term[] {
                new Term("content", "dumpty"),
                new Term("content", "together")});
        topDocs = indexSearcher.search(multiPhraseQueryBuilder.build(), 100);
        SimpleDatasetUtil.printQueryResult(indexReader, topDocs);
    }
}
