package query;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.IntPoint;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;

import java.io.IOException;

public class TestQueryParser {
    public static void main(String[] args) throws IOException, ParseException {
        Directory directory = DataConstructor.constructSimpleDataset("/tmp/lucene");
        IndexReader indexReader = DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        // ???? 這樣寫沒結果
//        QueryParser queryParser = new QueryParser("numStore", new StandardAnalyzer());
//        Query query = queryParser.parse("[100 TO 300]");
        // 這樣可以
        Query query = IntPoint.newRangeQuery("num", 100, 300);
//        QueryParser queryParser = new QueryParser("content", new StandardAnalyzer());
//        Query query = queryParser.parse("hump*");
        System.out.println(query.toString());
        TopDocs topDocs = indexSearcher.search(query, 100);
        SimpleDatasetUtil.printQueryResult(indexReader, topDocs);
    }
}
