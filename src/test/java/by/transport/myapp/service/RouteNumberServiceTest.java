package by.transport.myapp.service;

import by.transport.myapp.dto.RouteNumberDto;
import by.transport.myapp.dto.TransportTypeDto;
import by.transport.myapp.model.dao.RouteNumberDao;
import by.transport.myapp.model.dao.TransportTypeDao;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RouteNumberServiceTest {
    @Autowired
    private RouteNumberService routeNumberService;

    @MockBean
    private RouteNumberDao routeNumberDao;
    @MockBean
    private TransportTypeDao transportTypeDao;

    private final Integer typeId = 1;
    private final int number = 1;

    @Before
    public void setUp() {
        List<RouteNumber> routeNumbers = new ArrayList<>();
        routeNumbers.add(new RouteNumber());

        when(routeNumberDao.getRouteNumbersByType(any(TransportType.class)))
                .thenReturn(routeNumbers);

        when(routeNumberDao.getRouteNumberByNumber(number)).thenReturn(new RouteNumber());

        when(transportTypeDao.getById(typeId))
                .thenReturn(new TransportType());
    }

    @Test
    public void getRouteNumbersByTypeTest() {
        List<Integer> found = routeNumberService.getRouteNumbersByType(typeId);
        assertThat(found.size()).isNotZero();
    }

    @Test
    public void getRoutesTest() {
        List<RouteNumberDto> found = routeNumberService.getRoutes(new TransportTypeDto());
        assertThat(found.size()).isNotZero();
    }

    @Test
    public void getRouteNumberByNumberTest() {
        RouteNumber found = routeNumberService.getRouteNumberByNumber(number);
        assertThat(found).isNotNull();
    }
}
