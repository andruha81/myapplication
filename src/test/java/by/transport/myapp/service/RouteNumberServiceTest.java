package by.transport.myapp.service;

import by.transport.myapp.dto.RouteNumberDto;
import by.transport.myapp.model.dao.RouteNumberDao;
import by.transport.myapp.model.entity.RouteNumber;
import by.transport.myapp.model.entity.TransportType;
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

@SpringBootTest
@RunWith(SpringRunner.class)
public class RouteNumberServiceTest {
    @Autowired
    RouteNumberService routeNumberService;

    @MockBean
    RouteNumberDao routeNumberDao;

    private final Integer typeId = 1;
    private final int number = 1;

    @Before
    public void setUp() {
        when(routeNumberDao.getRouteNumbersByType(new TransportType()))
                .thenReturn(new ArrayList<>());

        when(routeNumberDao.getRouteNumberByNumber(number)).thenReturn(new RouteNumber());
    }

    @Test
    public void getRouteNumbersByTypeTest() {
        List<Integer> found = routeNumberService.getRouteNumbersByType(typeId);
        assertThat(found).isNotNull();
    }

    @Test
    public void getRoutesTest() {
        List<RouteNumberDto> found = routeNumberService.getRoutes(typeId);
        assertThat(found).isNotNull();
    }

    @Test
    public void getRouteNumberByNumberTest() {
        RouteNumber found = routeNumberService.getRouteNumberByNumber(number);
        assertThat(found).isNotNull();
    }
}
