package by.transport.myapp.service;

import by.transport.myapp.dto.RouteLineStopDto;
import by.transport.myapp.dto.StopDto;
import by.transport.myapp.model.dao.RouteDao;
import by.transport.myapp.model.dao.RouteLineDao;
import by.transport.myapp.model.dao.StopDao;
import by.transport.myapp.model.entity.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RouteLineServiceTest {
    @Autowired
    private RouteLineService routeLineService;

    @MockBean
    private RouteLineDao routeLineDao;
    @MockBean
    private StopDao stopDao;
    @MockBean
    private RouteDao routeDao;

    private final Integer id = 1;

    @Before
    public void setUp() {
        LocalTime localTime = LocalTime.of(1, 1);

        Stop stop = new Stop();

        TransportType transportType = new TransportType();
        transportType.setDescription("test type");

        RouteNumber routeNumber = new RouteNumber();
        routeNumber.setType(transportType);

        Route route = new Route();
        route.setId(id);
        route.setStartWeekday(localTime);
        route.setStartDayoff(localTime);
        route.setEndWeekday(localTime);
        route.setEndDayoff(localTime);
        route.setRouteNumber(routeNumber);

        RouteLine routeLine = new RouteLine();
        routeLine.setRouteRouteLine(route);

        List<RouteLine> routeLines = new ArrayList<>();
        routeLines.add(routeLine);

        when(routeLineDao.getRouteLinesByStop(any(Stop.class)))
                .thenReturn(routeLines);

        when(stopDao.getById(id))
                .thenReturn(stop);

        when(routeDao.getById(id))
                .thenReturn(route);
    }

    @Test
    public void getStopDetailsTest() {
        Map<String, List<RouteLineStopDto>> found = routeLineService.getStopDetails(new StopDto());
        assertThat(found.size()).isNotZero();
    }
}
