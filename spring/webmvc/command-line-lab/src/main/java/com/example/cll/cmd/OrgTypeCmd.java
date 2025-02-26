package com.example.cll.cmd;

import com.example.cll.core.ExecCommand;
import com.example.cll.core.ResponseFor;
import com.example.cll.service.OrgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Slf4j
@Component
@ResponseFor("OrgType")
public class OrgTypeCmd implements ExecCommand {
    private OrgService orgService;
    public OrgTypeCmd(OrgService orgService) {
        this.orgService = orgService;
    }

    @Override
    public void run(String[] args) {
        log.info("OrgType command executed");
        LocalDate now = LocalDate.now();
        orgService.retrieveOrgType(now);
    }
}
