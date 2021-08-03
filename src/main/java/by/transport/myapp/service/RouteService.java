package by.transport.myapp.service;

import by.transport.myapp.dto.RouteParamDto;
import by.transport.myapp.dto.RouteStopDto;

public interface RouteService {
    RouteStopDto getRouteDetails(Integer id);
    RouteParamDto getRouteById(Integer id);
    boolean save(RouteParamDto routeParamDto);
}
