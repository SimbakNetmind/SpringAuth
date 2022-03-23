package com.r2system.security.app.SpringBootSecurityApp.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorHandlerController {

    @GetMapping("/error/unauthorize")
    public String errorHandler(){

        return "401 UnAuthorize User";
    }
}
