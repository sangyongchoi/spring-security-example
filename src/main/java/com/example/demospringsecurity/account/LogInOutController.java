package com.example.demospringsecurity.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogInOutController {
    @GetMapping("/login")
    public String login(){return "login";}
    @GetMapping("/loginForm")
    public String loginForm(){return "loginForm";}
    @GetMapping("/logoutForm")
    public String logoutForm(){return "logout";}
}
