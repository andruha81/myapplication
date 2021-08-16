package by.transport.myapp.service;

import by.transport.myapp.dto.TransportTypeDto;
import by.transport.myapp.model.entity.TransportType;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface TransportTypeService {
    List<TransportTypeDto> getTypes();

    String getTypeDescription(Integer id) throws EntityNotFoundException;

    TransportTypeDto getTransportTypeById(Integer id) throws EntityNotFoundException;

    TransportType getTransportTypeByDescription(String description);
}