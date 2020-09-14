package attach;

import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachineDescriptor;
import com.sun.tools.attach.spi.AttachProvider;

import java.io.IOException;
import java.util.List;

public class ListAttachProvider {
    public static void main(String[] args) throws IOException, AttachNotSupportedException {
        List<AttachProvider> attachProviderList = AttachProvider.providers();
        for(AttachProvider attachProvider : attachProviderList) {
            System.out.println(attachProvider);
            try {
                // this process should not access able
                attachProvider.attachVirtualMachine("100");
            } catch(IOException | AttachNotSupportedException e) {
                e.printStackTrace();
            }
            List<VirtualMachineDescriptor> virtualMachineDescriptorList = attachProvider.listVirtualMachines();
            for(VirtualMachineDescriptor virtualMachineDescriptor : virtualMachineDescriptorList) {
                System.out.println(virtualMachineDescriptor.toString());
            }
        }
    }
}
