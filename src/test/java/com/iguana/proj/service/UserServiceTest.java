package com.iguana.proj.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.iguana.proj.exceptions.UserNotFoundException;
import com.iguana.proj.model.User;
import com.iguana.proj.repos.UserRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository repository;
    @InjectMocks
    private UserService service;

    @Test
    public void whenGetAllUsers_theGetAllUsers() {
        User user1 = new User(5, "Simon", "Magis");
        User user2 = new User(17, "Jack", "Reacher");
        doReturn(Arrays.asList(user1, user2)).when(repository).findAll();

        List<User> users = service.getUsers();
        assertEquals(2, users.size());
        assertEquals(user1.getFullName(), users.get(0).getFullName());
    }

    @Test
    public void whenGetUserById_thenGetUser() {
        User user = new User(5, "Simon", "Magis");
        doReturn(Optional.of(user)).when(repository).findById(5L);

        User actualUser = service.getUserById(5);
        assertEquals(user.getFullName(), actualUser.getFullName());
    }

    @Test
    public void givenUserNotExist_whenGetUser_thenThrowNotFoundExcption() {
        doReturn(Optional.empty()).when(repository).findById(5L);

        assertThrows(UserNotFoundException.class, () -> service.getUserById(5L));
    }

    @Test
    public void whenSaveUser_thenGetUser() {
        User user = new User(5, "Simon", "Magis");
        doReturn(user).when(repository).save(user);

        User actualUser = service.saveOrUpdateUser(user);
        assertEquals(user.getId(), actualUser.getId());
    }

    @Test
    public void whenUpdateUser_thenGetUpdatedUser() {
        User user = new User(5, "Simon", "Magis");
        doReturn(Optional.of(user)).when(repository).findById(5L);
        doReturn(user).when(repository).save(user);

        User actualUser = service.updateUser(5L, user);
        assertEquals(actualUser.getId(), user.getId());
        assertEquals(actualUser.getFullName(), user.getFullName());
    }

    @Test
    public void givenUserNotExist_whenUpdateUser_thenThrowNotFoundExcption() {
        doReturn(Optional.empty()).when(repository).findById(5L);

        assertThrows(UserNotFoundException.class, () -> service.updateUser(5L, new User()));
    }
}
