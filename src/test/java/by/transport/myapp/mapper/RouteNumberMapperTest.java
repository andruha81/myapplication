package by.transport.myapp.mapper;

import by.transport.myapp.dto.RouteNumberDto;
import by.transport.myapp.model.entity.RouteNumber;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(classes = RouteNumberMapperImpl.class)
@RunWith(SpringRunner.class)
public class RouteNumberMapperTest {
    @Autowired
    RouteNumberMapper routeNumberMapper;

    @Test
    public void routeNumberToDtoTest() {
        RouteNumber routeNumber = new RouteNumber();
        routeNumber.setId(1);
        routeNumber.setNumber(1);

        RouteNumberDto routeNumberDto = routeNumberMapper.routeNumberToDto(routeNumber);
        assertThat(routeNumberDto).isNotNull();
        assertThat(routeNumberDto.getRouteNumberDtoId()).isEqualTo(routeNumber.getId());
        assertThat(routeNumberDto.getNumber()).isEqualTo(routeNumber.getNumber());
    }
}
