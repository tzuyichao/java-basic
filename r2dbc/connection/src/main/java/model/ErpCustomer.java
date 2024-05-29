package model;

import java.time.LocalDateTime;

public class ErpCustomer {
    private String erpCustomerCode;
    private String customerName;
    private String customerHierarchyNodeId;
    private String erpAccountGroup;
    private String customerClassification;
    private String tradingPartner;
    private String country;
    private String erpName1;
    private String erpName2;
    private String erpName3;
    private String erpName4;
    private String erpSearchTerm1;
    private String erpSearchTerm2;
    private String erpIndustryCode1;
    private String erpIndustryCode2;
    private String erpIndustryCode3;
    private String erpIndustryCode4;
    private String erpIndustryCode5;
    private LocalDateTime modifiedDate;

    public String getErpCustomerCode() {
        return erpCustomerCode;
    }

    public void setErpCustomerCode(String erpCustomerCode) {
        this.erpCustomerCode = erpCustomerCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerHierarchyNodeId() {
        return customerHierarchyNodeId;
    }

    public void setCustomerHierarchyNodeId(String customerHierarchyNodeId) {
        this.customerHierarchyNodeId = customerHierarchyNodeId;
    }

    public String getErpAccountGroup() {
        return erpAccountGroup;
    }

    public void setErpAccountGroup(String erpAccountGroup) {
        this.erpAccountGroup = erpAccountGroup;
    }

    public String getCustomerClassification() {
        return customerClassification;
    }

    public void setCustomerClassification(String customerClassification) {
        this.customerClassification = customerClassification;
    }

    public String getTradingPartner() {
        return tradingPartner;
    }

    public void setTradingPartner(String tradingPartner) {
        this.tradingPartner = tradingPartner;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getErpName1() {
        return erpName1;
    }

    public void setErpName1(String erpName1) {
        this.erpName1 = erpName1;
    }

    public String getErpName2() {
        return erpName2;
    }

    public void setErpName2(String erpName2) {
        this.erpName2 = erpName2;
    }

    public String getErpName3() {
        return erpName3;
    }

    public void setErpName3(String erpName3) {
        this.erpName3 = erpName3;
    }

    public String getErpName4() {
        return erpName4;
    }

    public void setErpName4(String erpName4) {
        this.erpName4 = erpName4;
    }

    public String getErpSearchTerm1() {
        return erpSearchTerm1;
    }

    public void setErpSearchTerm1(String erpSearchTerm1) {
        this.erpSearchTerm1 = erpSearchTerm1;
    }

    public String getErpSearchTerm2() {
        return erpSearchTerm2;
    }

    public void setErpSearchTerm2(String erpSearchTerm2) {
        this.erpSearchTerm2 = erpSearchTerm2;
    }

    public String getErpIndustryCode1() {
        return erpIndustryCode1;
    }

    public void setErpIndustryCode1(String erpIndustryCode1) {
        this.erpIndustryCode1 = erpIndustryCode1;
    }

    public String getErpIndustryCode2() {
        return erpIndustryCode2;
    }

    public void setErpIndustryCode2(String erpIndustryCode2) {
        this.erpIndustryCode2 = erpIndustryCode2;
    }

    public String getErpIndustryCode3() {
        return erpIndustryCode3;
    }

    public void setErpIndustryCode3(String erpIndustryCode3) {
        this.erpIndustryCode3 = erpIndustryCode3;
    }

    public String getErpIndustryCode4() {
        return erpIndustryCode4;
    }

    public void setErpIndustryCode4(String erpIndustryCode4) {
        this.erpIndustryCode4 = erpIndustryCode4;
    }

    public String getErpIndustryCode5() {
        return erpIndustryCode5;
    }

    public void setErpIndustryCode5(String erpIndustryCode5) {
        this.erpIndustryCode5 = erpIndustryCode5;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Override
    public String toString() {
        return "ErpCustomer{" +
                "erpCustomerCode='" + erpCustomerCode + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerHierarchyNodeId='" + customerHierarchyNodeId + '\'' +
                ", erpAccountGroup='" + erpAccountGroup + '\'' +
                ", customerClassification='" + customerClassification + '\'' +
                ", tradingPartner='" + tradingPartner + '\'' +
                ", country='" + country + '\'' +
                ", erpName1='" + erpName1 + '\'' +
                ", erpName2='" + erpName2 + '\'' +
                ", erpName3='" + erpName3 + '\'' +
                ", erpName4='" + erpName4 + '\'' +
                ", erpSearchTerm1='" + erpSearchTerm1 + '\'' +
                ", erpSearchTerm2='" + erpSearchTerm2 + '\'' +
                ", erpIndustryCode1='" + erpIndustryCode1 + '\'' +
                ", erpIndustryCode2='" + erpIndustryCode2 + '\'' +
                ", erpIndustryCode3='" + erpIndustryCode3 + '\'' +
                ", erpIndustryCode4='" + erpIndustryCode4 + '\'' +
                ", erpIndustryCode5='" + erpIndustryCode5 + '\'' +
                ", modifiedDate=" + modifiedDate +
                '}';
    }
}
