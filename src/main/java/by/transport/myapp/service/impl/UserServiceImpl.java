package by.transport.myapp.service.impl;

import by.transport.myapp.model.dao.UserDao;
import by.transport.myapp.model.entity.User;
import by.transport.myapp.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User loadUserByLogin(String login) {
        logger.error("UserServiceImpl");
        return userDao.findUserByLogin(login);
    }

    @Override
    @Transactional
    public User saveUser(User user) {
        logger.error("UserServiceImpl");
        return userDao.save(user);
    }
}
