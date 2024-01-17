package management;

import javax.management.MBeanServerConnection;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.util.Set;

public class JMXClientLab2 {
    public static void main(String[] args) throws IOException {
        try {
            JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://datagovstg-kfk01.deltaww.com:9996/jmxrmi");
            JMXConnector jmxc = JMXConnectorFactory.connect(url, null);
            MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();
            Set<ObjectInstance> mbeans = mbsc.queryMBeans(null, null);
            for(ObjectInstance mbean : mbeans) {
                ObjectName objectName = mbean.getObjectName();
                System.out.println("MBean Name: " + objectName.toString());
                if("debezium.sql_server:type=connector-metrics,server=datagov.pipeline.mgt.syncerp_qas_db,task=0,context=streaming,database=SYNCERP".equals(objectName.toString())) {
                    System.out.println("\tCapturedTables: " + ((String[])mbsc.getAttribute(objectName, "CapturedTables")).length);
                }
            }

            jmxc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
