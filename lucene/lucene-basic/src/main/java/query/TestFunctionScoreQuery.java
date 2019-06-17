package query;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queries.function.FunctionScoreQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;

import java.io.IOException;

import static query.SimpleDatasetUtil.FIELD_CONTENT;

public class TestFunctionScoreQuery {
    public static void main(String[] args) {
        try {
            Directory directory = DataConstructor.constructSimpleDataset("/tmp/lucene");
            IndexReader indexReader = DirectoryReader.open(directory);
            IndexSearcher indexSearcher = new IndexSearcher(indexReader);

            FunctionScoreQuery functionScoreQuery = FunctionScoreQuery.boostByQuery(
                    new TermQuery(new Term(FIELD_CONTENT, "humpty")),
                    new TermQuery(new Term(FIELD_CONTENT, "together")),
                    100f);
            TopDocs topDocs = indexSearcher.search(functionScoreQuery, 100);
            SimpleDatasetUtil.printQueryResult(indexReader, topDocs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
