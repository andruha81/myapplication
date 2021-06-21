package by.transport.myapp.service;

import by.transport.myapp.dto.RouteNumberDto;
import by.transport.myapp.dto.TransportTypeDto;

import java.util.List;

public interface RouteNumberService {
    List<Integer> getRouteNumbers();
    List<RouteNumberDto> getRoutes(Integer transportTypeId);
}
