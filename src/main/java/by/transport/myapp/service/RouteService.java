package by.transport.myapp.service;

import by.transport.myapp.dto.RouteParamDto;
import by.transport.myapp.dto.RouteStopDto;
import by.transport.myapp.model.entity.RouteNumber;

public interface RouteService {
    RouteStopDto getRouteDetails(Integer id);

    RouteParamDto getRouteById(Integer id);

    Integer save(RouteParamDto routeParamDto, RouteNumber routeNumber);
}
