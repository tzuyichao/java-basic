package attach;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;

import java.io.IOException;

public class MyAttachMain {
    public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
        System.out.println("Attach agent to pid");
        String pid = args[0];
        String agent = args[1];
        System.out.println("pid: " + args[0]);
        System.out.println("agent: " + agent);
        VirtualMachine vm = VirtualMachine.attach(pid);
        try {
            vm.loadAgent(agent);
            System.out.println("Done.");
        } finally {
            vm.detach();
        }
    }
}