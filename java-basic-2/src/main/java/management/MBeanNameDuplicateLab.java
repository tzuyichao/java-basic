package management;

import javax.management.*;
import java.lang.management.ManagementFactory;

/**
 * Source: https://www.baeldung.com/java-management-extensions
 */
public class MBeanNameDuplicateLab {
    public static void main(String[] args) {
        try {
            MBeanServer server = ManagementFactory.getPlatformMBeanServer();
            ObjectName objectName = new ObjectName("com.baeldung.tutorial:type=basic,name=game");
            server.registerMBean(new Game(), objectName);
            //server.registerMBean(new Game("Terence.Chao"), objectName); // javax.management.InstanceAlreadyExistsException: com.baeldung.tutorial:type=basic,name=game
            while (true) {

            }
        } catch (MalformedObjectNameException | InstanceAlreadyExistsException |
                 MBeanRegistrationException | NotCompliantMBeanException e) {
            e.printStackTrace();
        }
    }
}
