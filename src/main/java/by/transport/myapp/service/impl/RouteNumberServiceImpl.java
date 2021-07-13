package by.transport.myapp.service.impl;

import by.transport.myapp.dto.RouteNumberDto;
import by.transport.myapp.mapper.RouteNumberMapper;
import by.transport.myapp.model.dao.RouteNumberDao;
import by.transport.myapp.model.dao.TransportTypeDao;
import by.transport.myapp.model.entity.RouteNumber;
import by.transport.myapp.service.RouteNumberService;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RouteNumberServiceImpl implements RouteNumberService {
    private final RouteNumberDao routeNumberDao;
    private final TransportTypeDao transportTypeDao;
    private final RouteNumberMapper routeNumberMapper = Mappers.getMapper(RouteNumberMapper.class);

    public RouteNumberServiceImpl(RouteNumberDao routeNumberDao, TransportTypeDao transportTypeDao) {
        this.routeNumberDao = routeNumberDao;
        this.transportTypeDao = transportTypeDao;
    }

    @Override
    public List<Integer> getRouteNumbersByType(Integer transportTypeId) {
        return routeNumberDao.getRouteNumbersByType(transportTypeDao.getById(transportTypeId))
                .stream()
                .map(RouteNumber::getNumber)
                .collect(Collectors.toList());
    }

    @Override
    public List<RouteNumberDto> getRoutes(Integer transportTypeId) {
        var transportType = transportTypeDao.getById(transportTypeId);
        return routeNumberDao.getRouteNumbersByType(transportType).stream()
                .map(routeNumberMapper::routeNumberToDto)
                .collect(Collectors.toList());
    }
}
