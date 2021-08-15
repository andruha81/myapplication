package by.transport.myapp.mapper;

import by.transport.myapp.dto.TransportTypeDto;
import by.transport.myapp.model.entity.TransportType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(classes = TransportTypeMapperImpl.class)
@RunWith(SpringRunner.class)
public class TransportTypeMapperTest {
    @Autowired
    private TransportTypeMapper transportTypeMapper;

    private final Integer id = 1;
    private final String description = "Test";

    @Test
    public void transportTypeToDtoTest() {
        TransportType transportType = new TransportType();
        transportType.setId(id);
        transportType.setDescription(description);

        TransportTypeDto transportTypeDto = transportTypeMapper.transportTypeToDto(transportType);
        assertThat(transportTypeDto).isNotNull();
        assertThat(transportTypeDto.getTransportTypeDtoId()).isEqualTo(transportType.getId());
        assertThat(transportTypeDto.getDescription()).isEqualTo(transportType.getDescription());
    }

    @Test
    public void dtoToTransportTypeTest() {
        TransportTypeDto transportTypeDto = new TransportTypeDto();
        transportTypeDto.setTransportTypeDtoId(id);
        transportTypeDto.setDescription(description);

        TransportType transportType = transportTypeMapper.dtoToTransportType(transportTypeDto);
        assertThat(transportType).isNotNull();
        assertThat(transportType.getId()).isEqualTo(transportTypeDto.getTransportTypeDtoId());
        assertThat(transportType.getDescription()).isEqualTo(transportTypeDto.getDescription());
    }

}
