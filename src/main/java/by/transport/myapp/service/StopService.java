package by.transport.myapp.service;

import by.transport.myapp.dto.StopDto;
import by.transport.myapp.model.entity.Stop;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StopService {
    StopDto getStopById(Integer id);
    Stop getStop(Integer id);
    List<StopDto> getStops();
    boolean save(StopDto stopDto);
}
