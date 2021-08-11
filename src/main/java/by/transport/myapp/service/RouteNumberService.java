package by.transport.myapp.service;

import by.transport.myapp.dto.RouteNumberDto;
import by.transport.myapp.model.entity.RouteNumber;

import java.util.List;

public interface RouteNumberService {
    List<Integer> getRouteNumbersByType(Integer typeId);

    List<RouteNumberDto> getRoutes(Integer transportTypeId);

    RouteNumber getRouteNumberByNumber(int number);
}
