package by.transport.myapp.service;

import by.transport.myapp.dto.StopDto;
import by.transport.myapp.model.dao.StopDao;
import by.transport.myapp.model.entity.Stop;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class StopServiceTest {

    @Autowired
    StopService stopService;

    @MockBean
    StopDao stopDao;

    @Before
    public void setUp() {
        Stop stop = new Stop();
        stop.setId(1);
        stop.setName("Test stop");

        Mockito.when(stopDao.getById(stop.getId()))
                .thenReturn(stop);
    }

    @Test
    public void getStopTest() {
        Integer id = 1;
        Stop found = stopService.getStop(id);
        Assertions.assertNotEquals(null, found);
    }

    @Test
    public void saveStopTest() {
        boolean isSaved = stopService.save(new StopDto());
        Assertions.assertTrue(isSaved);
    }


}
