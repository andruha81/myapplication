package by.transport.myapp.service;

import by.transport.myapp.dto.TransportLiteDto;

import java.util.Set;

public interface TransportService {
    Set<TransportLiteDto> getBuses();
}
