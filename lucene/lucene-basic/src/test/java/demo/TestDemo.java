package demo;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.IOUtils;
import org.apache.lucene.util.LuceneTestCase;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * https://lucene.apache.org/core/7_3_1/test-framework/org/apache/lucene/util/LuceneTestCase.html
 * https://github.com/apache/lucene-solr/blob/master/lucene/core/src/test/org/apache/lucene/TestDemo.java
 */
public class TestDemo extends LuceneTestCase {

    public static final String FIELDNAME = "fieldname";

    public void testDemo() throws IOException {
        String longTerm = "longtermlongtermlongtermlongtermlongtermlongtermlongtermlongtermlongtermlongtermlongtermlongtermlongtermlongtermlongtermlongtermlongtermlongterm";
        String text = "This is the text to be indexed. " + longTerm;

        Path indexPath = Files.createTempDirectory("tempIndex");
        try(Directory dir = FSDirectory.open(indexPath)) {
            // index write
            Analyzer analyzer = new StandardAnalyzer();
            try(IndexWriter writer = new IndexWriter(dir, new IndexWriterConfig(analyzer))) {
                Document document = new Document();
                document.add(newTextField(FIELDNAME, text, Field.Store.YES));
                writer.addDocument(document);
            }
            // read index
            try(IndexReader reader = DirectoryReader.open(dir)) {
                IndexSearcher searcher = newSearcher(reader);
                assertEquals(1, searcher.count(new TermQuery(new Term(FIELDNAME, longTerm))));

                Query query = new TermQuery(new Term(FIELDNAME, "text"));
                TopDocs hits = searcher.search(query, 1);
                assertEquals(1, hits.totalHits.value);

                for(int i=0; i<hits.scoreDocs.length; i++) {
                    Document hitDoc = searcher.doc(hits.scoreDocs[i].doc);
                    assertEquals(text, hitDoc.get(FIELDNAME));
                }

                PhraseQuery phraseQuery = new PhraseQuery(FIELDNAME, "to", "be");
                assertEquals(1, searcher.count(phraseQuery));
            }
        }
        IOUtils.rm(indexPath);
    }
}
