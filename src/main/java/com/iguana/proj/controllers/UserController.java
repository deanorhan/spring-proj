package com.iguana.proj.controllers;

import com.iguana.proj.model.User;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @GetMapping("/{userId}")
    @ResponseBody
    public User getUser(int userId) {
        return new User();
    }
}
