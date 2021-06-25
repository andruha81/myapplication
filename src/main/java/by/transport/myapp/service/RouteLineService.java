package by.transport.myapp.service;

import by.transport.myapp.dto.RouteLineStopDto;

import java.util.List;
import java.util.Map;

public interface RouteLineService {
    Map<String, List<RouteLineStopDto>> getStopDetails(Integer routeId, Integer stopId);
}
