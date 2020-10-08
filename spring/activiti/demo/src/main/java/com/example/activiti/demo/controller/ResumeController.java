package com.example.activiti.demo.controller;

import com.example.activiti.demo.service.ResumeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResumeController {
    private ResumeService resumeService;

    public ResumeController(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    @RequestMapping("/startHiring")
    public String startHiring() {
        this.resumeService.startHiring();
        return "Done";
    }
}
