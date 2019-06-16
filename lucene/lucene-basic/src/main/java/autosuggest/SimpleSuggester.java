package autosuggest;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.spell.Dictionary;
import org.apache.lucene.search.spell.LuceneDictionary;
import org.apache.lucene.search.suggest.Lookup;
import org.apache.lucene.search.suggest.analyzing.AnalyzingInfixSuggester;
import org.apache.lucene.search.suggest.analyzing.AnalyzingSuggester;
import org.apache.lucene.search.suggest.analyzing.FreeTextSuggester;
import org.apache.lucene.search.suggest.analyzing.FuzzySuggester;
import org.apache.lucene.store.MMapDirectory;
import org.apache.lucene.store.Directory;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class SimpleSuggester {
    public static final String FIELD_CONTENT = "content";
    public static void main(String[] args) throws IOException {
        StandardAnalyzer analyzer = new StandardAnalyzer();
        Directory directory = new MMapDirectory(Paths.get("/tmp/lucene"));
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter indexWriter = new IndexWriter(directory, config);

        Document document = new Document();
        document.add(new StringField(FIELD_CONTENT, "Humpty Dumpty sat on a wall", Field.Store.YES));
        indexWriter.addDocument(document);

        document = new Document();
        document.add(new StringField("content", "Humpty Dumpty had a great fall", Field.Store.YES));
        indexWriter.addDocument(document);

        document = new Document();
        document.add(new StringField("content", "All the king's horses and all the king's men. The following describes how Lucene scoring evolves from underlying information retrieval models to (efficient) implementation.", Field.Store.YES));
        indexWriter.addDocument(document);

        document = new Document();
        document.add(new StringField("content", "Couldn't put Humpty together again", Field.Store.YES));
        indexWriter.addDocument(document);

        indexWriter.commit();
        indexWriter.close();

        IndexReader indexReader = DirectoryReader.open(directory);
        Dictionary dictionary = new LuceneDictionary(indexReader, FIELD_CONTENT);

        System.out.println("AnalyzingSuggester:");
        AnalyzingSuggester analyzingSuggester = new AnalyzingSuggester(directory, FIELD_CONTENT, new StandardAnalyzer());
        analyzingSuggester.build(dictionary);
        List<Lookup.LookupResult> lookupResultList = analyzingSuggester.lookup("humpty", false, 10);
        for(Lookup.LookupResult result : lookupResultList) {
            System.out.println(result.key + ":" + result.value);
        }

        System.out.println("AnalyzingInfixSuggester:");
        AnalyzingInfixSuggester analyzingInfixSuggester = new AnalyzingInfixSuggester(directory, new StandardAnalyzer());
        analyzingInfixSuggester.build(dictionary);
        lookupResultList = analyzingInfixSuggester.lookup("humpty", false, 10);
        for(Lookup.LookupResult result : lookupResultList) {
            System.out.println(result.key + ":" + result.value);
        }

        // Builds an ngram model from the text sent to build and predicts based on the last grams-1
        // tokens in the request sent to lookup.
        System.out.println("FreeTextSuggester:");
        FreeTextSuggester freeTextSuggester = new FreeTextSuggester(new StandardAnalyzer(), new StandardAnalyzer(), 3);
        freeTextSuggester.build(dictionary);
        lookupResultList = freeTextSuggester.lookup("f", false, 10);
        for(Lookup.LookupResult result : lookupResultList) {
            System.out.println(result.key + ":" + result.value);
        }

        System.out.println("FuzzySuggester:");
        FuzzySuggester fuzzySuggester = new FuzzySuggester(directory, FIELD_CONTENT, new StandardAnalyzer());
        fuzzySuggester.build(dictionary);
        lookupResultList = fuzzySuggester.lookup("h", false, 10);
        for(Lookup.LookupResult result : lookupResultList) {
            System.out.println(result.key + ":" + result.value);
        }
    }
}
