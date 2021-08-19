package by.transport.myapp.service;

import by.transport.myapp.dto.RouteNumberDto;
import by.transport.myapp.dto.TransportTypeDto;
import by.transport.myapp.model.entity.RouteNumber;

import java.util.List;

public interface RouteNumberService {
    List<Integer> getRouteNumbersByType(TransportTypeDto typeDto);

    List<RouteNumberDto> getRoutes(TransportTypeDto typeDto);

    List<RouteNumberDto> getRouteNumbers();

    RouteNumberDto getRouteNumberById(Integer routeNumberId);

    RouteNumber getRouteNumberByNumber(int number);

    Integer save(RouteNumberDto routeNumberDto, TransportTypeDto transportTypeDto);
}
