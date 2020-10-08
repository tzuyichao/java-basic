package com.example.activiti.demo.service;

import lombok.extern.log4j.Log4j2;
import org.activiti.api.process.model.ProcessDefinition;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.api.task.runtime.TaskRuntime;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Component;

import java.util.List;

@Log4j2
@Component
public class ProcessService {
    private TaskService taskService;
    private ProcessRuntime processRuntime;
    private TaskRuntime taskRuntime;

    public ProcessService(TaskService taskService, ProcessRuntime processRuntime, TaskRuntime taskRuntime) {
        this.taskService = taskService;
        this.processRuntime = processRuntime;
        this.taskRuntime = taskRuntime;
    }

    public void listActiveActivityIds(String processInstanceId) {
        log.info("Query Id: {}", processInstanceId);
        List<Task> taskList = taskService.createTaskQuery()
                .processInstanceId(processInstanceId)
                .orderByTaskName().asc()
                .list();
        taskList.stream().forEach(task -> {
           log.info(task.toString());
        });
        listTask();
    }

    public void listProcess() {
        Authentication.setAuthenticatedUserId("admin");
        Page<ProcessDefinition> processDefinitionPage = processRuntime.processDefinitions(Pageable.of(0, 10));
        log.info("Available Process Definitions: {}", processDefinitionPage.getTotalItems());
        for(ProcessDefinition pd: processDefinitionPage.getContent()) {
            log.info("\tProcess Definition: {}", pd);
        }
    }

    public void listTask() {
        Authentication.setAuthenticatedUserId("admin");
        Page<org.activiti.api.task.model.Task> taskPage = taskRuntime.tasks(Pageable.of(0, 10));
        log.info("Available Tasks: {}", taskPage.getTotalItems());
        for(org.activiti.api.task.model.Task task : taskPage.getContent()) {
            log.info("\tTask: {}", task.toString());
        }
    }
}
