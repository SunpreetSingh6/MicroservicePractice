package com.UserService.Service;

import com.UserService.Model.User;

import java.util.List;

public interface UserService {

    User addUser(User user);
    List<User> getUsers();
    User getUser(String userId);

}
