package by.transport.myapp.service;

import by.transport.myapp.dto.TransportTypeDto;

import java.util.List;

public interface TransportTypeService {
    List<TransportTypeDto> getTypes();

    String getTypeDescription(Integer id);

    TransportTypeDto getTransportTypeById(Integer id);

    TransportTypeDto getTransportTypeByDescription(String description);
}