package com.r2system.security.app.SpringBootSecurityApp.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthController {

    @GetMapping("sign-up")
    public String signUp(){

        return "Sing Up user";
    }
}
