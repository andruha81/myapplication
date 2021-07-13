package by.transport.myapp.service.impl;

import by.transport.myapp.model.dao.UserDao;
import by.transport.myapp.model.entity.User;
import by.transport.myapp.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User loadUserByLogin(String login) {
        return userDao.findUserByLogin(login);
    }

    @Override
    public User saveUser(User user) {
        return userDao.save(user);
    }
}
