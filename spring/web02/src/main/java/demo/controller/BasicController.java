package demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BasicController {

    @RequestMapping("/welcome")
    @ResponseBody
    public String welcome() {
        return "Welcome to Spring MVC";
    }

    @RequestMapping("/welcome-view")
    public String welcomeView() {
        return "welcome";
    }
}
