package by.transport.myapp.mapper;

import by.transport.myapp.dto.TransportDto;
import by.transport.myapp.model.entity.RouteNumber;
import by.transport.myapp.model.entity.Transport;
import by.transport.myapp.model.entity.TransportType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(classes = TransportMapperImpl.class)
@RunWith(SpringRunner.class)
public class TransportMapperTest {
    @Autowired
    TransportMapper transportMapper;

    private final Integer id = 1;
    private final String model = "Test";
    private final int seatNum = 1;
    private final int carNum = 1;

    @Test
    public void transportToDtoTest() {
        Transport transport = new Transport();
        transport.setId(id);
        transport.setTransportType(new TransportType());
        transport.setModel(model);
        transport.setSeatNum(seatNum);
        transport.setCarNum(carNum);
        transport.setRoute(new RouteNumber());

        TransportDto transportDto = transportMapper.transportToDto(transport);
        assertThat(transportDto).isNotNull();
        assertThat(transportDto.getTransportDtoId()).isEqualTo(transport.getId());
        assertThat(transportDto.getType()).isEqualTo(transport.getTransportType().getDescription());
        assertThat(transportDto.getModel()).isEqualTo(transport.getModel());
        assertThat(transportDto.getSeatNum()).isEqualTo(transport.getSeatNum());
        assertThat(transportDto.getCarNum()).isEqualTo(transport.getCarNum());
        assertThat(transportDto.getRoute()).isEqualTo(transport.getRoute().getNumber());
    }

    @Test
    public void dtoToTransportTest() {
        TransportDto transportDto = new TransportDto();
        transportDto.setTransportDtoId(id);
        transportDto.setModel(model);
        transportDto.setSeatNum(seatNum);
        transportDto.setCarNum(carNum);

        Transport transport = transportMapper.dtoToTransport(transportDto, new TransportType(), new RouteNumber());
        assertThat(transport).isNotNull();
        assertThat(transport.getId()).isEqualTo(transportDto.getTransportDtoId());
        assertThat(transport.getModel()).isEqualTo(transportDto.getModel());
        assertThat(transport.getSeatNum()).isEqualTo(transportDto.getSeatNum());
        assertThat(transport.getCarNum()).isEqualTo(transportDto.getCarNum());
    }
}
