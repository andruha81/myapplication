package by.transport.myapp.service;

import by.transport.myapp.dto.RouteNumberDto;

import java.util.List;

public interface RouteNumberService {
    List<Integer> getRouteNumbersByType(Integer typeId);

    List<RouteNumberDto> getRoutes(Integer transportTypeId);
}
