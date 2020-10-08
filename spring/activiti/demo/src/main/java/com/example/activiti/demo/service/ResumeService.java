package com.example.activiti.demo.service;

import lombok.extern.log4j.Log4j2;
import org.activiti.engine.RuntimeService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@Service
public class ResumeService {
    private RuntimeService runtimeService;

    public ResumeService(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    public void startHiring() {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("applicantName", "John Doe");
        variables.put("email", "john.doe@activiti.com");
        variables.put("phoneNumber", "123456789");
        runtimeService.startProcessInstanceByKey("hireProcess", variables);
    }

    public void storeResume() {
        log.info("storing resume...");
    }
}
