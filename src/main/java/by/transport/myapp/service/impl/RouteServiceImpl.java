package by.transport.myapp.service.impl;

import by.transport.myapp.dto.RouteStopDto;
import by.transport.myapp.dto.RouteTransportDto;
import by.transport.myapp.mapper.RouteStopMapper;
import by.transport.myapp.mapper.RouteTransportMapper;
import by.transport.myapp.model.dao.RouteDao;
import by.transport.myapp.service.RouteService;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RouteServiceImpl implements RouteService {
    private final RouteDao routeDao;
    private final RouteStopMapper mapper = Mappers.getMapper(RouteStopMapper.class);
    private final RouteTransportMapper mapperTransport = Mappers.getMapper(RouteTransportMapper.class);

    public RouteServiceImpl(RouteDao routeDao) {
        this.routeDao = routeDao;
    }

    @Override
    public Set<RouteStopDto> getRootsWithStop() {
        return routeDao.findAll().stream().map(mapper::routeToDto).collect(Collectors.toSet());
    }

    @Override
    public Set<RouteTransportDto> getRootsWithTransport() {
        return routeDao.findAll().stream().map(mapperTransport::routeTransportToDto).collect(Collectors.toSet());
    }
}
