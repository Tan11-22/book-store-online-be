package com.BookStore.UserService.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user-service/")
public class UserController {

    @GetMapping("/check")
    public String funcTest() {
        return "Đâng là service user, test đúng r đó.";
    }
}
