package com.example.cll.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
public class OrgServiceImpl implements OrgService {
    @Override
    public void retrieveOrgType(LocalDate start) {
        log.info("retrieveOrgType start {}", start);
    }

    @Override
    public void retrieveOrgSubType(LocalDate start) {
        log.info("retrieveOrgSubType start {}", start);
    }
}
