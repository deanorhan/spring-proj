package com.iguana.proj.repos;

import com.iguana.proj.model.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    
}
