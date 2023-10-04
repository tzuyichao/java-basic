package management;

import javax.management.ObjectInstance;
import javax.management.ObjectName;
import javax.management.remote.rmi.RMIConnection;
import javax.management.remote.rmi.RMIServer;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Set;

public class JMXClientStub {
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry("datagovstg-kfk01.deltaww.com", 9999);
        for(String reg : registry.list()) {
            System.out.println(reg);
        }

        Remote remote = registry.lookup("jmxrmi");
        System.out.println("Remote: " + remote.toString());

        RMIServer rmiServer = (RMIServer) remote;
        try(RMIConnection rmiConnection = rmiServer.newClient(null);) {
            String connectionId = rmiConnection.getConnectionId();
            System.out.println("Connection Id: " + connectionId);
            Set<ObjectInstance> mbeans = rmiConnection.queryMBeans(null, null, null);
            for(ObjectInstance mbean : mbeans) {
                ObjectName objectName = mbean.getObjectName();
                System.out.println("MBean Name: " + objectName.toString());
            }
        }
    }
}
