package management;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.IOException;

public class JMXClientLab {
    public static void main(String[] args) throws IOException {
        try {
            JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://datagovstg-kfk01.deltaww.com:9999/jmxrmi");
            JMXConnector jmxc = JMXConnectorFactory.connect(url, null);
            MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();
            // Get metrics for BytesInPerSec
            ObjectName bytesInPerSec = new ObjectName("kafka.server:type=BrokerTopicMetrics,name=BytesInPerSec");
            double byteRate = (double) mbsc.getAttribute(bytesInPerSec, "OneMinuteRate");
            System.out.println("BytesInPerSec OneMinuteRate: " + byteRate);

            // Get metrics for MessagesInPerSec
            ObjectName messagesInPerSec = new ObjectName("kafka.server:type=BrokerTopicMetrics,name=MessagesInPerSec");
            double messageRate = (double) mbsc.getAttribute(messagesInPerSec, "OneMinuteRate");
            System.out.println("MessagesInPerSec OneMinuteRate: " + messageRate);

            jmxc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
