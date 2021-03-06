package by.transport.myapp.mapper;

import by.transport.myapp.dto.RouteNumberDto;
import by.transport.myapp.model.entity.RouteNumber;
import by.transport.myapp.model.entity.TransportType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(classes = RouteNumberMapperImpl.class)
@RunWith(SpringRunner.class)
public class RouteNumberMapperTest {
    @Autowired
    private RouteNumberMapper routeNumberMapper;

    @Test
    public void routeNumberToDtoTest() {
        TransportType type = new TransportType();
        type.setDescription("Test type");

        RouteNumber routeNumber = new RouteNumber();
        routeNumber.setId(1);
        routeNumber.setNumber(1);
        routeNumber.setType(type);

        RouteNumberDto routeNumberDto = routeNumberMapper.routeNumberToDto(routeNumber);
        assertThat(routeNumberDto).isNotNull();
        assertThat(routeNumberDto.getRouteNumberDtoId()).isEqualTo(routeNumber.getId());
        assertThat(routeNumberDto.getNumber()).isEqualTo(routeNumber.getNumber());
        assertThat(routeNumberDto.getTransportType()).isEqualTo(type.getDescription());
    }
}
