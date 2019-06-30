package com.carlos.invoice.server.controller;

import com.carlos.invoice.server.exception.ExceptionHandlingController;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/login")
public class LoginUserController extends ExceptionHandlingController {


    @GetMapping
    public String loginUser() {

        return "login";
    }
}
