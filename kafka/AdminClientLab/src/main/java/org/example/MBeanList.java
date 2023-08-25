package org.example;

import javax.management.*;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.util.Set;

public class MBeanList {
    public static void main(String[] args) {
        try {
            JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://datagovstg-kfk03.deltaww.com:9999/jmxrmi");

            JMXConnector connector = JMXConnectorFactory.connect(url);
            MBeanServerConnection connection = connector.getMBeanServerConnection();

            Set<ObjectInstance> mbeans = connection.queryMBeans(null, null);
            for (ObjectInstance mbean : mbeans) {
                ObjectName objectName = mbean.getObjectName();
                System.out.println("MBean Name: " + objectName);
            }

            connector.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
