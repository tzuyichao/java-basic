package com.example.proxy.model;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@ToString
@Data
public class SevenEntity {
    private List<String> company;
    private List<String> person;
    private List<String> problem;
    private List<String> property;
    private List<String> area;
    private List<String> product;
    private List<String> machine;
}
