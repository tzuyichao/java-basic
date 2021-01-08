package demo.facet;

import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.facet.*;
import org.apache.lucene.facet.taxonomy.FastTaxonomyFacetCounts;
import org.apache.lucene.facet.taxonomy.TaxonomyReader;
import org.apache.lucene.facet.taxonomy.directory.DirectoryTaxonomyReader;
import org.apache.lucene.facet.taxonomy.directory.DirectoryTaxonomyWriter;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.store.ByteBuffersDirectory;
import org.apache.lucene.store.Directory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MultiCategoryListsFacetsExample {
    private final Directory indexDir = new ByteBuffersDirectory();
    private final Directory taxonomyDir = new ByteBuffersDirectory();
    private final FacetsConfig config = new FacetsConfig();

    public MultiCategoryListsFacetsExample() {
        config.setIndexFieldName("Author", "author");
        config.setIndexFieldName("Publish Date", "pubdate");
        config.setHierarchical("Publish Date", true);
    }

    private void index() throws IOException {
        IndexWriter indexWriter = new IndexWriter(indexDir, new IndexWriterConfig(
                new WhitespaceAnalyzer()).setOpenMode(IndexWriterConfig.OpenMode.CREATE));
        DirectoryTaxonomyWriter taxonomyWriter = new DirectoryTaxonomyWriter(taxonomyDir);

        Document doc = new Document();
        doc.add(new FacetField("Author", "Bob"));
        doc.add(new FacetField("Publish Date", "2010", "10", "15"));
        indexWriter.addDocument(config.build(taxonomyWriter, doc));

        doc = new Document();
        doc.add(new FacetField("Author", "Lisa"));
        doc.add(new FacetField("Publish Date", "2010", "10", "20"));
        indexWriter.addDocument(config.build(taxonomyWriter, doc));

        doc = new Document();
        doc.add(new FacetField("Author", "Lisa"));
        doc.add(new FacetField("Publish Date", "2012", "1", "1"));
        indexWriter.addDocument(config.build(taxonomyWriter, doc));

        doc = new Document();
        doc.add(new FacetField("Author", "Susan"));
        doc.add(new FacetField("Publish Date", "2012", "1", "7"));
        indexWriter.addDocument(config.build(taxonomyWriter, doc));

        doc = new Document();
        doc.add(new FacetField("Author", "Frank"));
        doc.add(new FacetField("Publish Date", "1999", "5", "5"));
        indexWriter.addDocument(config.build(taxonomyWriter, doc));

        indexWriter.close();
        taxonomyWriter.close();
    }

    private List<FacetResult> search() throws IOException {
        try(DirectoryReader indexReader = DirectoryReader.open(indexDir);
            TaxonomyReader taxonomyReader = new DirectoryTaxonomyReader(taxonomyDir)) {
            IndexSearcher searcher = new IndexSearcher(indexReader);

            FacetsCollector facetsCollector = new FacetsCollector();
            FacetsCollector.search(searcher, new MatchAllDocsQuery(), 10, facetsCollector);

            List<FacetResult> results = new ArrayList<>();

            Facets author = new FastTaxonomyFacetCounts("author", taxonomyReader, config, facetsCollector);
            results.add(author.getTopChildren(10, "Author"));

            Facets pubDate = new FastTaxonomyFacetCounts("pubdate", taxonomyReader, config, facetsCollector);
            results.add(pubDate.getTopChildren(10, "Publish Date"));

            return results;
        }
    }

    public List<FacetResult> runSearch() throws IOException {
        index();
        return search();
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Facet counting over multiple category lists example:");
        System.out.println("-----------------------");
        List<FacetResult> results = new MultiCategoryListsFacetsExample().runSearch();

        FacetResult authorFacetResult = results.get(0);
        System.out.println("Author: " + authorFacetResult);
        for(LabelAndValue labelAndValue : authorFacetResult.labelValues) {
            System.out.println(labelAndValue.label + ":" + labelAndValue.value);
        }

        System.out.println("=======================");
        System.out.println("Publish Date: " + results.get(1));

    }
}
