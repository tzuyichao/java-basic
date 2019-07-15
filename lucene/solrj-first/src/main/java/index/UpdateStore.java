package index;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;

import java.io.IOException;

/**
 * 跟add一樣的寫法
 */
public class UpdateStore {
    public static void main(String[] args) {
        try(SolrClient solrClient = new HttpSolrClient.Builder("http://localhost:8983/solr/").build()) {
            Store store1 = new Store();
            store1.setId("t2");
            store1.setName("麵屋武藏-神山2");
            store1.setCity("Taipei");
            store1.setType("Japan Food");
            store1.setState("Taiwan");
            store1.getTags().add("ramen");
            store1.getTags().add("sit down");
            store1.setPrice(9.0);
            solrClient.addBean("db", store1);
            solrClient.commit("db");
        } catch(SolrServerException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
