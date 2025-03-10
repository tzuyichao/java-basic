package org.example;

import javax.management.*;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.util.Set;

public class MBeanList {
    public static void main(String[] args) {
        try {
            JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://qqq:9999/jmxrmi");

            JMXConnector connector = JMXConnectorFactory.connect(url);
            MBeanServerConnection connection = connector.getMBeanServerConnection();

            Set<ObjectInstance> mbeans = connection.queryMBeans(null, null);
            for (ObjectInstance mbean : mbeans) {
                ObjectName objectName = mbean.getObjectName();
                if(objectName.toString().startsWith("kafka.server:type=Request") &&
                   objectName.toString().contains("PLM")) {
                    System.out.println("MBean Name: " + objectName);
                    MBeanInfo mBeanInfo = connection.getMBeanInfo(objectName);
                    for(MBeanAttributeInfo attribute : mBeanInfo.getAttributes()) {
                        System.out.println("Attribute: " + attribute.getName());
                        System.out.println("Description: " + attribute.getDescription());
                        System.out.println("Value: " + connection.getAttribute(objectName, attribute.getName()));
                    }
                    System.out.println();
                }
            }

            connector.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
