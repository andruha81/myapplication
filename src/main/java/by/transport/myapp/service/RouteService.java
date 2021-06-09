package by.transport.myapp.service;

import by.transport.myapp.dto.RouteStopDto;
import by.transport.myapp.dto.RouteTransportDto;

import java.util.Set;

public interface RouteService {
    Set<RouteStopDto> getRootsWithStop();
    Set<RouteTransportDto> getRootsWithTransport();
}
