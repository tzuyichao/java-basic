package management;

import javax.management.*;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.util.Set;

public class JMXClientLabZk {
    public static void main(String[] args) throws IOException {
        try {
            JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://zk_address:jmx_port/jmxrmi");
            JMXConnector jmxc = JMXConnectorFactory.connect(url, null);
            MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();
            Set<ObjectInstance> mbeans = mbsc.queryMBeans(null, null);
            for(ObjectInstance mbean : mbeans) {
                ObjectName objectName = mbean.getObjectName();
                System.out.println("MBean Name: " + objectName.toString());
                if(objectName.toString().startsWith("org.apache.ZooKeeperService")) {
                    MBeanInfo mBeanInfo = mbsc.getMBeanInfo(objectName);
                    MBeanAttributeInfo[] mBeanAttributeInfos = mBeanInfo.getAttributes();
                    for (MBeanAttributeInfo info : mBeanAttributeInfos) {
                        System.out.println("\t" + info.getName() + " : " + mbsc.getAttribute(objectName, info.getName()));
                    }
                }
            }

            jmxc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
