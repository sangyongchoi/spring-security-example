package com.example.demospringsecurity.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogInOutController {
    @GetMapping("/login")
    public String loginForm(){return "login";}
    @GetMapping("/logoutForm")
    public String logoutForm(){return "logout";}
}
