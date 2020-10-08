package com.example.activiti.demo.controller;

import com.example.activiti.demo.service.ProcessService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProcessController {
    private ProcessService processService;

    public ProcessController(ProcessService processService) {
        this.processService = processService;
    }

    @GetMapping("/task/{processInstanceId}")
    public void listTask(@PathVariable("processInstanceId") String processInstanceId) {
        this.processService.listActiveActivityIds(processInstanceId);
    }
}
