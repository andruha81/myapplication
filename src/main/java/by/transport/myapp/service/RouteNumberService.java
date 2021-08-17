package by.transport.myapp.service;

import by.transport.myapp.dto.RouteNumberDto;
import by.transport.myapp.dto.TransportTypeDto;
import by.transport.myapp.model.entity.RouteNumber;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface RouteNumberService {
    List<Integer> getRouteNumbersByType(TransportTypeDto typeDto);

    List<RouteNumberDto> getRoutes(TransportTypeDto typeDto);

    List<RouteNumberDto> getRouteNumbers();

    RouteNumberDto getRouteNumberById(Integer routeNumberId) throws EntityNotFoundException;

    RouteNumber getRouteNumberByNumber(int number) throws EntityNotFoundException;

    Integer save(RouteNumberDto routeNumberDto, TransportTypeDto transportTypeDto);
}
