package by.transport.myapp.service;

import by.transport.myapp.dto.TransportDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransportService {
    List<TransportDto> getTransportByTransportType(Integer typeId);
    void save(TransportDto transportDto);
    TransportDto getTransportById(Integer id);
}
