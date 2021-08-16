package by.transport.myapp.service;

import by.transport.myapp.dto.RouteNumberDto;
import by.transport.myapp.dto.TransportTypeDto;
import by.transport.myapp.model.entity.RouteNumber;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface RouteNumberService {
    List<Integer> getRouteNumbersByType(Integer typeId) throws EntityNotFoundException;

    List<RouteNumberDto> getRoutes(TransportTypeDto typeDto) throws EntityNotFoundException;

    RouteNumber getRouteNumberByNumber(int number);
}
