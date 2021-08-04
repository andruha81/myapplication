package by.transport.myapp.service;

import by.transport.myapp.model.dao.UserDao;
import by.transport.myapp.model.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {
    @Autowired
    UserService userService;

    @MockBean
    UserDao userDao;

    private final String login = "Test user";
    private final User user = new User();

    @Before
    public void setUp() {
        user.setId(1);
        user.setLogin(login);
        user.setPassword("");
        user.setUserRole("");

        when(userDao.findUserByLogin(login))
                .thenReturn(user);

        when(userDao.save(user))
                .thenReturn(user);
    }

    @Test
    public void loadUserByLoginTest() {
        User found = userService.loadUserByLogin(login);
        assertThat(found).isNotNull();
    }

    @Test
    public void saveUserTest() {
        User found = userService.saveUser(user);
        assertThat(found).isNotNull();
    }
}