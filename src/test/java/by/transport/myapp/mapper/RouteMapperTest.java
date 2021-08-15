package by.transport.myapp.mapper;

import by.transport.myapp.dto.RouteDto;
import by.transport.myapp.dto.RouteParamDto;
import by.transport.myapp.dto.RouteStopDto;
import by.transport.myapp.model.entity.Route;
import by.transport.myapp.model.entity.RouteNumber;
import by.transport.myapp.model.entity.TransportType;
import org.junit.Before;
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
    private RouteMapper routeMapper;

    private final TransportType transportType = new TransportType();
    private final RouteNumber routeNumber = new RouteNumber();
    private final Route route = new Route();

    @Before
    public void setUp() {
        transportType.setId(1);
        transportType.setDescription("Test");

        routeNumber.setId(1);
        routeNumber.setNumber(1);
        routeNumber.setType(transportType);

        route.setId(1);
        route.setDescription("Test");
        route.setRouteNumber(routeNumber);
        route.setStartWeekday(LocalTime.of(1, 1));
        route.setEndWeekday(LocalTime.of(1, 1));
        route.setIntervalWeekday(1);
        route.setStartDayoff(LocalTime.of(1, 1));
        route.setEndDayoff(LocalTime.of(1, 1));
        route.setIntervalDayoff(1);
    }

    @Test
    public void routeToDtoTest() {
        RouteDto routeDto = routeMapper.routeToDto(route);
        assertThat(routeDto).isNotNull();
        assertThat(routeDto.getRouteDtoId()).isEqualTo(route.getId());
        assertThat(routeDto.getDescription()).isEqualTo(route.getDescription());
    }

    @Test
    public void routeToRouteStopDtoTest() {
        RouteStopDto routeStopDto = routeMapper.routeToRouteStopDto(route);
        assertThat(routeStopDto).isNotNull();
        assertThat(routeStopDto.getRouteStopDtoId()).isEqualTo(route.getId());
        assertThat(routeStopDto.getNumber()).isEqualTo(route.getRouteNumber().getNumber());
        assertThat(routeStopDto.getType()).isEqualTo(route.getRouteNumber().getType().getDescription());
        assertThat(routeStopDto.getDescription()).isEqualTo(route.getDescription());
    }

    @Test
    public void routeToRouteParamDtoTest() {
        RouteParamDto routeParamDto = routeMapper.routeToRouteParamDto(route);
        assertThat(routeParamDto).isNotNull();
        assertThat(routeParamDto.getRouteParamDtoId()).isEqualTo(route.getId());
        assertThat(routeParamDto.getDescription()).isEqualTo(route.getDescription());
        assertThat(routeParamDto.getRouteNumber()).isEqualTo(route.getRouteNumber().getNumber());
        assertThat(routeParamDto.getStartWeekday()).isEqualTo(route.getStartWeekday());
        assertThat(routeParamDto.getEndWeekday()).isEqualTo(route.getEndWeekday());
        assertThat(routeParamDto.getIntervalWeekday()).isEqualTo(route.getIntervalWeekday());
        assertThat(routeParamDto.getStartDayoff()).isEqualTo(route.getStartDayoff());
        assertThat(routeParamDto.getEndDayoff()).isEqualTo(route.getEndDayoff());
        assertThat(routeParamDto.getIntervalDayoff()).isEqualTo(route.getIntervalDayoff());
    }
}
