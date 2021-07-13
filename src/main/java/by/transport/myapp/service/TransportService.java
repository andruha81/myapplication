package by.transport.myapp.service;

import by.transport.myapp.dto.TransportDto;

import java.util.List;

public interface TransportService {
    List<TransportDto> getTransportByTransportType(Integer typeId);
    void save(TransportDto transportDto);
}
