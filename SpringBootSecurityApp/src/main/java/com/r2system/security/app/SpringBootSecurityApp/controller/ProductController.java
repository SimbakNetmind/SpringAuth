package com.r2system.security.app.SpringBootSecurityApp.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("api")
public class ProductController {

    @GetMapping("member")
    public String member(){
     return "Member Method";
    }

    @GetMapping("admin")
    public String admin(){
        return "Admin Method";
    }


    @GetMapping("both")
    public String both(){
        return "Both Method";
    }

}
