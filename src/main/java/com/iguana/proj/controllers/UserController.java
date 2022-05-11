package com.iguana.proj.controllers;

import java.net.URI;
import java.util.List;

import com.iguana.proj.model.User;
import com.iguana.proj.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserService service;

    @GetMapping
    public List<User> getUsers() {
        return service.getUsers();
    }

    @PostMapping
    public ResponseEntity<Void> addUser(@RequestBody User user) {
        User newUser = service.saveOrUpdateUser(user);

        return ResponseEntity
            .created(URI.create(String.format("/users/%d", newUser.getId())))
            .build();
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable long userId) {
        return service.getUserById(userId);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable long userId, @RequestBody User user) {
        User updatedUser = service.updateUser(userId, user);

        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable long userId) {
        service.deleteUser(userId);

        return ResponseEntity.noContent().build();
    }
}
