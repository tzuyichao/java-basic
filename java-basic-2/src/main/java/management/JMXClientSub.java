package management;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class JMXClientSub {
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry("datagovstg-kfk01.deltaww.com", 9999);
        for(String reg : registry.list()) {
            System.out.println(reg);
        }

        Remote remote = registry.lookup("jmxrmi");
        System.out.println("Remote: " + remote.toString());
    }
}
