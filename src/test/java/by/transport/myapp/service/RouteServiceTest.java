package by.transport.myapp.service;

import by.transport.myapp.dto.RouteParamDto;
import by.transport.myapp.dto.RouteStopDto;
import by.transport.myapp.model.dao.RouteDao;
import by.transport.myapp.model.entity.Route;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RouteServiceTest {
    @Autowired
    private RouteService routeService;

    @MockBean
    private RouteDao routeDao;

    private final Integer id = 1;

    @Before
    public void setUp() {
        Route route = new Route();
        route.setId(id);
        route.setRouteLines(new ArrayList<>());

        when(routeDao.getById(id)).thenReturn(route);
    }

    @Test
    public void getRouteDetailsTest() {
        RouteStopDto found = routeService.getRouteDetails(id);
        assertThat(found).isNotNull();
    }

    @Test
    public void getRouteByIdTest() {
        RouteParamDto found = routeService.getRouteById(id);
        assertThat(found).isNotNull();
    }
}
