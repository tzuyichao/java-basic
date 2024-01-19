package management;

import javax.management.*;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.util.Set;

public class MBeanAttributes {
    public static void main(String[] args) {
        try {
            JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://datagovstg-kfk01.deltaww.com:9996/jmxrmi");
            JMXConnector jmxc = JMXConnectorFactory.connect(url, null);
            MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();
            Set<ObjectInstance> mbeans = mbsc.queryMBeans(null, null);
            for(ObjectInstance mbean : mbeans) {
                ObjectName objectName = mbean.getObjectName();
                System.out.println("MBean Name: " + objectName.toString());
                if("debezium.sql_server:type=connector-metrics,server=datagov.pipeline.mgt.syncerp_qas_db,task=0,context=streaming,database=SYNCERP".equals(objectName.toString())) {
                    MBeanInfo mBeanInfo = mbsc.getMBeanInfo(objectName);
                    MBeanAttributeInfo[] attrInfos = mBeanInfo.getAttributes();
                    for(MBeanAttributeInfo attrInfo: attrInfos) {
                        System.out.println("\t" + attrInfo.getName() + ";" + attrInfo.getType() + ";" + attrInfo.getDescription());
                    }
                }
            }

            jmxc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }    }
}
