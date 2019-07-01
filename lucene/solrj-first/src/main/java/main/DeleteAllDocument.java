package main;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.ConcurrentUpdateSolrClient;
//import org.apache.solr.client.solrj.impl.HttpSolrClient;

import java.io.IOException;

public class DeleteAllDocument {
    public static void main(String[] args) {
        String coreName = "graph";
        String solrBaseUrl = "http://localhost:8983/solr/";
        // SolrClient solrClient = new HttpSolrClient.Builder(solrCoreUrl).build();
        SolrClient solrClient = new ConcurrentUpdateSolrClient.Builder(solrBaseUrl + coreName).build();

        try {
            solrClient.deleteByQuery("*");

            solrClient.commit();
            System.out.println("Delete document complete.");
        } catch(IOException | SolrServerException e) {
            e.printStackTrace();
        } finally {
            try {
                solrClient.close();
            } catch(IOException e) {}
        }
    }
}
