package by.transport.myapp.service;

import by.transport.myapp.dto.StopDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StopService {
    StopDto getStopById(Integer id);

    public List<StopDto> getStops();
}
