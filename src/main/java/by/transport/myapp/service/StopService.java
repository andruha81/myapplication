package by.transport.myapp.service;

import by.transport.myapp.dto.StopDto;

import java.util.List;

public interface StopService {
    StopDto getStopById(Integer id);

    public List<StopDto> getStops();
}
