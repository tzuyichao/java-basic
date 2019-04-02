package stream;

public class Organization {
    private String name;
    private OrgType orgType;

    public Organization() {}

    public Organization(String name, OrgType orgType) {
        this.name = name;
        this.orgType = orgType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OrgType getOrgType() {
        return orgType;
    }

    public void setOrgType(OrgType orgType) {
        this.orgType = orgType;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "name='" + name + '\'' +
                ", orgType=" + orgType +
                '}';
    }
}
