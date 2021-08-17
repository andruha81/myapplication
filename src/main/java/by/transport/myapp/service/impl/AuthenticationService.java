package by.transport.myapp.service.impl;

import by.transport.myapp.model.entity.User;
import by.transport.myapp.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class AuthenticationService implements UserDetailsService {
    private final UserService userService;
    private final Logger logger = LogManager.getLogger(AuthenticationService.class);

    public AuthenticationService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.loadUserByLogin(username);
        if (user == null) {
            logger.error(String.format("Can't find user with login %s", username));
            throw new UsernameNotFoundException(username);
        }
        logger.info(String.format("You login by %s successfully ", username));
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), new HashSet<>());
    }
}
