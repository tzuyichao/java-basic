package query;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.spans.*;
import org.apache.lucene.store.Directory;

import java.io.IOException;

import static query.SimpleDatasetUtil.FIELD_CONTENT;

public class TestSpanQuery {
    public static void main(String[] args) {
        try {
            Directory directory = DataConstructor.constructSimpleDataset("/tmp/lucene");
            IndexReader indexReader = DirectoryReader.open(directory);
            IndexSearcher indexSearcher = new IndexSearcher(indexReader);

            System.out.println("SpanNearQuery Lab:");
            // slop一定要完全符合才會算中
            // 這個例子inOrder如果設定為true就會找不到符合的文件
            SpanQuery spanNearQuery = new SpanNearQuery(new SpanQuery[] {
                    new SpanTermQuery(new Term(FIELD_CONTENT, "wall")),
                    new SpanTermQuery(new Term(FIELD_CONTENT, "humpty"))
            }, 4, false);
            TopDocs topDocs = indexSearcher.search(spanNearQuery, 100);
            SimpleDatasetUtil.printQueryResult(indexReader, topDocs);

            // Matches spans near the beginning of a field
            // 從field開頭算到end之間有出現查詢Term的就算符合
            // 測試結果認為文件中找到term位置跟end越接近的分數越高
            System.out.println("SpanFirstQuery Lab:");
            SpanQuery spanFirstQuery = new SpanFirstQuery(new SpanTermQuery(new Term(FIELD_CONTENT, "humpty")), 3);
            topDocs = indexSearcher.search(spanFirstQuery, 100);
            SimpleDatasetUtil.printQueryResult(indexReader, topDocs);

            System.out.println("SpanNotQuery Lab:");
            SpanQuery spanNotQuery = new SpanNotQuery(spanFirstQuery, new SpanTermQuery(new Term(FIELD_CONTENT, "together")), 1);
            System.out.println(spanNotQuery.toString());
            topDocs = indexSearcher.search(spanNotQuery, 100);
            SimpleDatasetUtil.printQueryResult(indexReader, topDocs);

            System.out.println("SpanOrQuery lab:");
            SpanQuery spanOrQuery = new SpanOrQuery(spanNearQuery, new SpanTermQuery(new Term(FIELD_CONTENT, "together")));
            topDocs = indexSearcher.search(spanOrQuery, 100);
            SimpleDatasetUtil.printQueryResult(indexReader, topDocs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
