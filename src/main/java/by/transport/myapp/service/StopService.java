package by.transport.myapp.service;

import by.transport.myapp.dto.StopDto;
import by.transport.myapp.model.entity.Stop;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public interface StopService {
    StopDto getStopById(Integer id) throws EntityNotFoundException;

    List<StopDto> getStops();

    Integer save(StopDto stopDto);
}
