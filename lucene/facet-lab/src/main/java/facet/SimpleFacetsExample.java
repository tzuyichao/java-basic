package facet;

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
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.MMapDirectory;
import org.apache.lucene.util.IOUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SimpleFacetsExample implements AutoCloseable {
    public static final String FACET_FIELD_PUB_DATE = "Publish Date";
    public static final String FACET_FIELD_AUTHOR = "Author";
    private Path indexPath = Path.of("./tmpindex");
    private Path taxoPath = Path.of("./tmptaxo");

    private final Directory indexDir;
    private final Directory taxoDir;
    private final FacetsConfig config = new FacetsConfig();

    public SimpleFacetsExample() throws IOException {
        indexDir = new MMapDirectory(indexPath);
        taxoDir = new MMapDirectory(taxoPath);
        config.setHierarchical(FACET_FIELD_PUB_DATE, true);
    }

    private void index() throws IOException {
        IndexWriter indexWriter = new IndexWriter(indexDir, new IndexWriterConfig(
                new WhitespaceAnalyzer()).setOpenMode(IndexWriterConfig.OpenMode.CREATE));
        DirectoryTaxonomyWriter taxoWriter = new DirectoryTaxonomyWriter(taxoDir);

        Document doc = new Document();
        doc.add(new FacetField(FACET_FIELD_AUTHOR, "Bob"));
        doc.add(new FacetField(FACET_FIELD_PUB_DATE, "2010", "10", "15"));
        indexWriter.addDocument(config.build(taxoWriter, doc));

        doc = new Document();
        doc.add(new FacetField("Author", "Lisa"));
        doc.add(new FacetField("Publish Date", "2010", "10", "20"));
        indexWriter.addDocument(config.build(taxoWriter, doc));

        doc = new Document();
        doc.add(new FacetField("Author", "Lisa"));
        doc.add(new FacetField("Publish Date", "2012", "1", "1"));
        indexWriter.addDocument(config.build(taxoWriter, doc));

        doc = new Document();
        doc.add(new FacetField("Author", "Susan"));
        doc.add(new FacetField("Publish Date", "2012", "1", "7"));
        indexWriter.addDocument(config.build(taxoWriter, doc));

        doc = new Document();
        doc.add(new FacetField("Author", "Frank"));
        doc.add(new FacetField("Publish Date", "1999", "5", "5"));
        indexWriter.addDocument(config.build(taxoWriter, doc));

        indexWriter.close();
        taxoWriter.close();
    }

    private List<FacetResult> facetsOnly() throws IOException {
        List<FacetResult> results = new ArrayList<>();
        try(DirectoryReader indexReader = DirectoryReader.open(indexDir);
            TaxonomyReader taxoReader = new DirectoryTaxonomyReader(taxoDir)) {
            IndexSearcher searcher = new IndexSearcher(indexReader);
            FacetsCollector facetsCollector = new FacetsCollector();
            searcher.search(new MatchAllDocsQuery(), facetsCollector);
            Facets facets = new FastTaxonomyFacetCounts(taxoReader, config, facetsCollector);

            results.add(facets.getTopChildren(10, FACET_FIELD_AUTHOR));
            results.add(facets.getTopChildren(10, FACET_FIELD_PUB_DATE));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    public List<FacetResult> runFacetOnly() throws IOException {
        index();
        return facetsOnly();
    }

    public static void main(String[] args) {
        System.out.println("Facet counting example:");
        System.out.println("-----------------------");
        try(SimpleFacetsExample example = new SimpleFacetsExample()) {
            List<FacetResult> results1 = example.runFacetOnly();
            System.out.println("Author: " + results1.get(0));
            System.out.println("Publish Date: " + results1.get(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        IOUtils.rm(indexPath);
        IOUtils.rm(taxoPath);
    }
}
