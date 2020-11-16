package com.example.jpa.demo.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Data
@Entity
@IdClass(AccountId.class)
public class Account {
    @Id
    private String accountNumber;
    @Id
    private String accountType;
    private String accountName;
    private boolean activated;
}
