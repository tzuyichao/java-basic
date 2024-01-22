package management;

import javax.management.*;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.util.Set;

public class MBeanAttributes {
    public static void main(String[] args) {
        try {
            JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://datagov-kfk01.deltaww.com:9996/jmxrmi");
            JMXConnector jmxc = JMXConnectorFactory.connect(url, null);
            MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();
            Set<ObjectInstance> mbeans = mbsc.queryMBeans(null, null);
            for(ObjectInstance mbean : mbeans) {
                ObjectName objectName = mbean.getObjectName();
                //System.out.println("MBean Name: " + objectName.toString());
                if(objectName.toString().startsWith("debezium")) {
                    System.out.println("MBean Name: " + objectName.toString());
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
        }
    }
}
