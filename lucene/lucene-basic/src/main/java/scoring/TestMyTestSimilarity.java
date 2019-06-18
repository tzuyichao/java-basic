package scoring;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.similarities.TFIDFSimilarity;
import org.apache.lucene.store.Directory;
import query.DataConstructor;
import query.SimpleDatasetUtil;

import java.io.IOException;

public class TestMyTestSimilarity {
    public static void main(String[] args) {
        try {
            TFIDFSimilarity myTestSimilarity = new MyTestSimilarity();
            Directory directory = DataConstructor.constructSimpleDataset("/tmp/lucene", myTestSimilarity);
            IndexReader indexReader = DirectoryReader.open(directory);
            IndexSearcher indexSearcher = new IndexSearcher(indexReader);
            indexSearcher.setSimilarity(myTestSimilarity);

            QueryParser queryParser = new QueryParser("content", new StandardAnalyzer());
            Query query = queryParser.parse("humpty dumpty");
            System.out.println(query.toString());
            TopDocs topDocs = indexSearcher.search(query, 100);
            SimpleDatasetUtil.printQueryResult(indexReader, topDocs);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
