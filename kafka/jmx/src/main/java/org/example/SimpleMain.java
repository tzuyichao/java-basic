package org.example;

import javax.management.*;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SimpleMain {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 9998;
        String jmxUrl = "service:jmx:rmi:///jndi/rmi://" + host + ":" + port + "/jmxrmi";
        Map<String, Object> env = new HashMap<>();
        //env.put(JMXConnector.CREDENTIALS, new String[]{"ACCOUNT", "PWD"});

        try {
            JMXServiceURL url = new JMXServiceURL(jmxUrl);
            JMXConnector connector = JMXConnectorFactory.connect(url, env);
            MBeanServerConnection connection = connector.getMBeanServerConnection();

            // Method 1
            for (int brokerId = 0; brokerId < 8; brokerId++) {
                String brokerName = "kafka.server:type=Broker,brokerId=" + brokerId;
                ObjectName objectName = new ObjectName(brokerName);

                double cpuLoad = (double) connection.getAttribute(objectName, "CpuPercent");
                System.out.println("Broker " + brokerId + " CPU Load: " + cpuLoad);

                long memoryLoad = (long) connection.getAttribute(objectName, "HeapMemoryUsage");
                System.out.println("Broker " + brokerId + " Memory Load: " + memoryLoad);

                long ioLoad = (long) connection.getAttribute(objectName, "TotalProduceRequestsPerSec");
                System.out.println("Broker " + brokerId + " IO Load: " + ioLoad);
            }

            // Method 2
            Set<ObjectName> brokerNames = connection.queryNames(new ObjectName("kafka.server:type=BrokerTopicMetrics,name=BytesInPerSec"), null);

            for (ObjectName brokerName : brokerNames) {
                String brokerId = brokerName.getKeyProperty("brokerId");
                double cpuLoad = (double) connection.getAttribute(brokerName, "Value");
                long memoryUsage = (long) connection.getAttribute(new ObjectName("java.lang:type=Memory"), "HeapMemoryUsage");

                System.out.println("Broker ID: " + brokerId);
                System.out.println("CPU Load: " + cpuLoad);
                System.out.println("Memory Usage: " + memoryUsage);
                System.out.println();
            }

            connector.close();
        } catch(IOException | MalformedObjectNameException | ReflectionException |
                InstanceNotFoundException | MBeanException | AttributeNotFoundException e) {
            e.printStackTrace();
        }
    }
}