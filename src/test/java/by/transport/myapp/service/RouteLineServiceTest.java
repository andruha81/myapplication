package by.transport.myapp.service;

import by.transport.myapp.dto.RouteLineStopDto;
import by.transport.myapp.model.dao.RouteLineDao;
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
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RouteLineServiceTest {
    @Autowired
    RouteLineService routeLineService;

    @MockBean
    RouteLineDao routeLineDao;

    @Before
    public void setUp() {
        when(routeLineDao.getRouteLinesByStop(new Stop()))
                .thenReturn(new ArrayList<>());
    }

    @Test
    public void getStopDetailsTest() {
        Integer stopId = 1;

        Map<String, List<RouteLineStopDto>> found = routeLineService.getStopDetails(stopId);
        assertThat(found).isNotNull();
    }
}
