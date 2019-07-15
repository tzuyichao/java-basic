package core;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.CoreAdminRequest;
import org.apache.solr.common.params.CoreAdminParams;
import org.apache.solr.common.util.NamedList;
import org.apache.solr.common.util.SimpleOrderedMap;

import java.io.IOException;
import java.util.Map;

public class CoreReload {
    private static final String RequestHeader = "responseHeader";

    private static void reloadCore(SolrClient client, String coreName) throws SolrServerException, IOException {
        CoreAdminRequest request = new CoreAdminRequest();
        // 如果不設定core name則會列出所有cores
        request.setCoreName(coreName);
        request.setAction(CoreAdminParams.CoreAdminAction.RELOAD);
        NamedList<Object> result = client.request(request);
        System.out.println(result);

        SimpleOrderedMap map = (SimpleOrderedMap) result.get(RequestHeader);
        for(Object obj : map) {
            Map.Entry entry = (Map.Entry) obj;
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        try (SolrClient solrClient = new HttpSolrClient.Builder("http://localhost:8983/solr/").build()) {
            reloadCore(solrClient, "db");
        } catch(SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
