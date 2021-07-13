package by.transport.myapp.service;

import by.transport.myapp.model.entity.User;

public interface UserService {
    User loadUserByLogin(String login);
    User saveUser(User user);
}
