package stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PartitionLab {
    public static void main(String[] args) {
        List<Organization> organizations = new ArrayList<>();
        organizations.add(new Organization("ACME", OrgType.Department));
        organizations.add(new Organization("Development", OrgType.Department));
        organizations.add(new Organization("Project X", OrgType.Project));

        Map<OrgType, List<Organization>> partitions = organizations
                .stream().collect(Collectors.groupingBy(Organization::getOrgType, Collectors.toList()));
        System.out.println("partitions size: " + partitions.size());
        partitions.keySet().forEach(orgType -> {
            System.out.println("OrgType: " + orgType);
            partitions.get(orgType).stream().forEach(System.out::println);
        });
    }
}
