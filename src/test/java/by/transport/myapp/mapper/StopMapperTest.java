package by.transport.myapp.mapper;

import by.transport.myapp.dto.StopDto;
import by.transport.myapp.model.entity.Stop;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(classes = StopMapperImpl.class)
@RunWith(SpringRunner.class)
public class StopMapperTest {
    @Autowired
    private StopMapper stopMapper;

    private final Integer id = 1;
    private final String name = "Test stop";

    @Test
    public void stopToDtoTest() {
        Stop stop = new Stop();
        stop.setId(id);
        stop.setName(name);

        StopDto stopDto = stopMapper.stopToDto(stop);
        assertThat(stopDto).isNotNull();
        assertThat(stopDto.getStopDtoId()).isEqualTo(stop.getId());
        assertThat(stopDto.getName()).isEqualTo(stop.getName());
    }

    @Test
    public void stopDtoToStop() {
        StopDto stopDto = new StopDto();
        stopDto.setStopDtoId(id);
        stopDto.setName(name);

        Stop stop = stopMapper.stopDtoToStop(stopDto);
        assertThat(stop).isNotNull();
        assertThat(stop.getId()).isEqualTo(stopDto.getStopDtoId());
        assertThat(stop.getName()).isEqualTo(stopDto.getName());
    }
}
