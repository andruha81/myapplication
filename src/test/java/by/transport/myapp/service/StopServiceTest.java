package by.transport.myapp.service;

import by.transport.myapp.dto.StopDto;
import by.transport.myapp.mapper.StopMapper;
import by.transport.myapp.model.dao.StopDao;
import by.transport.myapp.model.entity.Stop;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

@SpringBootTest()
@RunWith(SpringRunner.class)
public class StopServiceTest {
    @Autowired
    private StopService stopService;

    @MockBean
    private StopDao stopDao;

    private StopDto stopDto;
    private final Integer id = 1;

    @Before
    public void setUp() {
        Stop stop = Stop.builder()
                .id(id)
                .name("Test stop")
                .build();

        stopDto = StopDto.builder()
                .stopDtoId(id)
                .name("Test stop")
                .build();

        when(stopDao.getById(id)).thenReturn(new Stop());

        when(stopDao.findAll()).thenReturn(new ArrayList<>());

        when(stopDao.save(any(Stop.class)))
                .thenReturn(stop);
    }

    @Test
    public void getStopByIdTest() {
        StopDto found = stopService.getStopById(id);
        assertThat(found).isNotNull();
    }

    @Test
    public void getStopTest() {
        Stop found = stopService.getStop(id);
        assertThat(found).isNotNull();
    }

    @Test
    public void getStopsTest() {
        List<StopDto> found = stopService.getStops();
        assertThat(found).isNotNull();
    }

    @Test
    public void saveTest() {
        Integer stopId = stopService.save(stopDto);
        assertThat(stopId).isNotNull();
    }
}
