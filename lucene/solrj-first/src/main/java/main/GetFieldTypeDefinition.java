package main;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.request.schema.SchemaRequest;
import org.apache.solr.client.solrj.response.schema.SchemaResponse;
import org.apache.solr.common.util.NamedList;

import java.io.IOException;
import java.util.Map;

public class GetFieldTypeDefinition {
    public static void main(String[] args) {
        String coreName = "db";
        String fieldTypeName = "text_general";
        try(SolrClient client = new HttpSolrClient.Builder("http://localhost:8983/solr/").build()) {
            SchemaRequest.FieldType fieldType = new SchemaRequest.FieldType(fieldTypeName);
            SchemaResponse.FieldTypeResponse response = fieldType.process(client, coreName);
            NamedList<Object> result = response.getResponse();
//            result = (NamedList<Object>) result.get("fieldType");
//            for(Map.Entry<String, Object> entry: result) {
//                String key = entry.getKey();
//                Object val = entry.getValue();
//                System.out.println(key + ": " + (val == null?"":val.toString()));
//            }
            // "responseHeader" and "fieldType"
            for(Map.Entry<String, Object> entry: result) {
                String key = entry.getKey();
                Object val = entry.getValue();
                System.out.println(key + ": " + (val == null?"":val.toString()));
            }
        } catch(IOException e) {
            e.printStackTrace();
        } catch(SolrServerException e) {
            e.printStackTrace();
        }
    }
}
