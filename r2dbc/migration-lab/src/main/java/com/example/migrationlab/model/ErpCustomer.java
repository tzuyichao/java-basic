package com.example.migrationlab.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErpCustomer {
    private String kunnr;
    private String ktokd;
    private String name1;
    private String name2;
    private String name3;
    private String name4;
    private String sort1;
    private String sort2;
    private String country;
    private String bran1;
    private String bran2;
    private String bran3;
    private String bran4;
    private String bran5;
}
