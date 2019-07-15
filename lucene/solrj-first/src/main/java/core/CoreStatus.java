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

public class CoreStatus {
    private static final String STATUS = "status";

    private static void printCoreStatus(SolrClient client, String coreName) throws SolrServerException, IOException {
        CoreAdminRequest request = new CoreAdminRequest();
        // 如果不設定core name則會列出所有cores
        request.setCoreName(coreName);
        request.setAction(CoreAdminParams.CoreAdminAction.STATUS);
        NamedList<Object> result = client.request(request);
        System.out.println(request);

        SimpleOrderedMap map = (SimpleOrderedMap) result.get(STATUS);
        for(Object obj : map) {
            Map.Entry entry = (Map.Entry) obj;
            SimpleOrderedMap coreMap = (SimpleOrderedMap) entry.getValue();
            for(Object object : coreMap) {
                Map.Entry coreMapEntry = (Map.Entry) object;
                System.out.println(coreMapEntry.getKey() + ": " + coreMapEntry.getValue());
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        try (SolrClient solrClient = new HttpSolrClient.Builder("http://localhost:8983/solr/").build()) {
            printCoreStatus(solrClient, "db");
        } catch(IOException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
    }
}
