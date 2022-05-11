package com.iguana.proj.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    private int id;
    private String firstName;
    private String lastName;

    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }
}