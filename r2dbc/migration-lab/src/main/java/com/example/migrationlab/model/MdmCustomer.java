package com.example.migrationlab.model;

import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MdmCustomer {
    private String mdmId;
    private UUID crmCustomerId;
    private String crmCustomerCode;
    private String erpCustomerCode;
    private String dgcCustomerCode;
    private String customerName;
    private String customerHierarchyNodeId;
    private String erpIndustryCode1;
    private String erpIndustryCode2;
    private String erpIndustryCode3;
    private String erpIndustryCode4;
    private String erpIndustryCode5;
    private String erpAccountGroup;
    private String erpName1;
    private String erpName2;
    private String erpName3;
    private String erpName4;
    private String erpSearchTerm;
    private LocalDateTime modifiedDate;
    private String country;
    private String tradingPartner;
    private int crmStatecode;
}
