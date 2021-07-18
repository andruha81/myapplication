package by.transport.myapp.service;

import by.transport.myapp.dto.RouteNumberDto;
import by.transport.myapp.model.entity.RouteNumber;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RouteNumberService {
    List<Integer> getRouteNumbersByType(Integer typeId);

    List<RouteNumberDto> getRoutes(Integer transportTypeId);

    RouteNumber getRouteNumberByNumber(int number);
}
