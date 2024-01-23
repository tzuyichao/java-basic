package management;

import javax.management.*;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Set;

public class QueryLab {
    public static void main(String[] args) throws MalformedURLException {
        final String[] attrList = {"CurrentScn", "OldestScn", "CommittedScn", "OffsetScn", "CapturedTables"};
        JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://datagov-kfk01.deltaww.com:9996/jmxrmi");
        try(JMXConnector jmxc = JMXConnectorFactory.connect(url, null)) {
            MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();
            Set<ObjectInstance> mbeans = mbsc.queryMBeans(new ObjectName("debezium.oracle:type=connector-metrics,context=streaming,server=*"), null);
            for(ObjectInstance mbean : mbeans) {
                System.out.println(mbean.getObjectName());
                AttributeList attributeList = mbsc.getAttributes(mbean.getObjectName(), attrList);
                attributeList.asList().forEach(attr -> {
                    if(attr.getValue() instanceof String) {
                        System.out.println("\t" + attr.getName() + "=" + attr.getValue());
                    } else if (attr.getValue() instanceof String[]) {
                        System.out.println("\t" + attr.getName() + "=");
                        String[] vals = (String[]) attr.getValue();
                        for(String val : vals) {
                            System.out.println("\t\t" + val);
                        }
                    }
                });
            }
        } catch(IOException | MalformedObjectNameException | ReflectionException | InstanceNotFoundException e) {
            e.printStackTrace();
        }
    }
}
