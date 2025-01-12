package com.example.cll.cmd;

import com.example.cll.core.ExecCommand;
import com.example.cll.core.ResponseFor;
import org.springframework.stereotype.Component;

@Component
@ResponseFor("OrgType")
public class OrgTypeCmd implements ExecCommand {
    @Override
    public void run(String[] args) {
        System.out.println("OrgType command executed");
    }
}
