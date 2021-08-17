package by.transport.myapp.service;

import by.transport.myapp.dto.RouteParamDto;
import by.transport.myapp.dto.RouteStopDto;
import by.transport.myapp.model.entity.RouteNumber;

import javax.persistence.EntityNotFoundException;

public interface RouteService {
    RouteStopDto getRouteDetails(Integer id) throws EntityNotFoundException;
    RouteParamDto getRouteById(Integer id) throws EntityNotFoundException;
    Integer save(RouteParamDto routeParamDto, RouteNumber routeNumber);
}
