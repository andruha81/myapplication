package by.transport.myapp.service;

import by.transport.myapp.dto.TransportDto;

import java.util.List;

public interface TransportService {
    List<TransportDto> getBuses();
    void save(TransportDto transportDto);
}
