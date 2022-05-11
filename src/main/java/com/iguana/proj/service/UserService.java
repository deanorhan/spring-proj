package com.iguana.proj.service;

import java.util.ArrayList;
import java.util.List;

import com.iguana.proj.exceptions.UserNotFoundException;
import com.iguana.proj.model.User;
import com.iguana.proj.repos.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;
    
    public List<User> getUsers() {
        List<User> users = new ArrayList<>();

        repository.findAll().forEach(users::add);

        return users;
    }

    public User getUserById(long userId) {
        return repository.findById(userId).orElseThrow(() -> new UserNotFoundException());
    }

    public User saveOrUpdateUser(User user) {
        return repository.save(user);
    }

    public User updateUser(long id, User user) {
        return repository.findById(id)
            .map(u -> {
                u.setFirstName(user.getFirstName());
                u.setLastName(user.getLastName());
                return repository.save(u);
            })
            .orElseThrow(() -> new UserNotFoundException());
    }

    public void deleteUser(long userId) {
        repository.deleteById(userId);
    }
}
