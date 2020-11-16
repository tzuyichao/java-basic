package com.example.jpa.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountId implements Serializable {
    private String accountNumber;
    private String accountType;
}
