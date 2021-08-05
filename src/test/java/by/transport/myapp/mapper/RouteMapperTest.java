package by.transport.myapp.mapper;

import by.transport.myapp.dto.RouteDto;
import by.transport.myapp.dto.RouteParamDto;
import by.transport.myapp.dto.RouteStopDto;
import by.transport.myapp.model.entity.Route;
import by.transport.myapp.model.entity.RouteNumber;
import by.transport.myapp.model.entity.TransportType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(classes = RouteMapperImpl.class)
@RunWith(SpringRunner.class)
public class RouteMapperTest {
    @Autowired
    RouteMapper routeMapper;

    private final Integer id = 1;
    private final String description = "Test";
    private final LocalTime startWeekday = LocalTime.of(1, 1);
    private final LocalTime endWeekday = LocalTime.of(2, 2);
    private final int intervalWeekday = 1;
    private final LocalTime startDayoff = LocalTime.of(3, 3);
    private final LocalTime endDayoff = LocalTime.of(4, 4);
    private final int intervalDayoff = 1;
    private final int number = 1;


    @Test
    public void routeToDtoTest() {
        Route route = new Route();
        route.setId(id);
        route.setDescription(description);

        RouteDto routeDto = routeMapper.routeToDto(route);
        assertThat(routeDto).isNotNull();
        assertThat(routeDto.getRouteDtoId()).isEqualTo(route.getId());
        assertThat(routeDto.getDescription()).isEqualTo(route.getDescription());
    }

    @Test
    public void routeToRouteStopDtoTest() {
        TransportType transportType = new TransportType();
        transportType.setId(id);
        transportType.setDescription(description);

        RouteNumber routeNumber = new RouteNumber();
        routeNumber.setId(id);
        routeNumber.setNumber(number);
        routeNumber.setType(transportType);

        Route route = new Route();
        route.setId(id);
        route.setRouteNumber(routeNumber);
        route.setDescription(description);

        RouteStopDto routeStopDto = routeMapper.routeToRouteStopDto(route);
        assertThat(routeStopDto).isNotNull();
        assertThat(routeStopDto.getRouteStopDtoId()).isEqualTo(route.getId());
        assertThat(routeStopDto.getNumber()).isEqualTo(route.getRouteNumber().getNumber());
        assertThat(routeStopDto.getType()).isEqualTo(route.getRouteNumber().getType().getDescription());
        assertThat(routeStopDto.getDescription()).isEqualTo(route.getDescription());
    }

    @Test
    public void routeToRouteParamDtoTest() {
        TransportType transportType = new TransportType();
        transportType.setId(id);

        RouteNumber routeNumber = new RouteNumber();
        routeNumber.setId(id);
        routeNumber.setNumber(number);
        routeNumber.setType(transportType);

        Route route = new Route();
        route.setId(id);
        route.setDescription(description);
        route.setRouteNumber(routeNumber);
        route.setDescription(description);
        route.setStartWeekday(startWeekday);
        route.setEndWeekday(endWeekday);
        route.setIntervalWeekday(intervalWeekday);
        route.setStartDayoff(startDayoff);
        route.setEndDayoff(endDayoff);
        route.setIntervalDayoff(intervalDayoff);

        RouteParamDto routeParamDto = routeMapper.routeToRouteParamDto(route);
        assertThat(routeParamDto).isNotNull();
    }
}
