package com.example.cll.service;

import java.time.LocalDate;

public interface OrgService {
    void retrieveOrgType(LocalDate start);
    void retrieveOrgSubType(LocalDate start);
}
