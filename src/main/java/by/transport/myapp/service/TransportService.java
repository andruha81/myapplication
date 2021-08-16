package by.transport.myapp.service;

import by.transport.myapp.dto.TransportDto;
import by.transport.myapp.dto.TransportTypeDto;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public interface TransportService {
    List<TransportDto> getTransportByTransportType(TransportTypeDto transportTypeDto);
    Integer save(TransportDto transportDto, TransportTypeDto typeDto) throws EntityNotFoundException;
    TransportDto getTransportById(Integer id) throws EntityNotFoundException;
}
