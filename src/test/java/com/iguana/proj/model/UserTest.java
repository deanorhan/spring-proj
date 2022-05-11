package com.iguana.proj.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class UserTest {

    @ParameterizedTest
    @CsvSource({
        "Simon, Templar",
        "Dick, Jones"
    })
    public void givenFirstAndLast_whenGettingFullName_thenReturnConcatFirstAndLast(String firstName, String lastName) {
        User user = new User(0, firstName, lastName);

        assertEquals(String.format("%s %s", firstName, lastName), user.getFullName());
    }
    
}
