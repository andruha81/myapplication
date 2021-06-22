package by.transport.myapp.service;

import by.transport.myapp.dto.RouteStopDto;

import java.util.Set;

public interface RouteService {
    RouteStopDto getRouteDetails(Integer id);
}
