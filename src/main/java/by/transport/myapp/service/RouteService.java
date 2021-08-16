package by.transport.myapp.service;

import by.transport.myapp.dto.RouteParamDto;
import by.transport.myapp.dto.RouteStopDto;

import javax.persistence.EntityNotFoundException;

public interface RouteService {
    RouteStopDto getRouteDetails(Integer id) throws EntityNotFoundException;
    RouteParamDto getRouteById(Integer id);
    boolean save(RouteParamDto routeParamDto);
}
