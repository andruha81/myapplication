package by.transport.myapp.service;

import by.transport.myapp.model.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User loadUserByLogin(String login);
    User saveUser(User user);
}
