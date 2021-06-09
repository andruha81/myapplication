package by.transport.myapp.service.impl;

import by.transport.myapp.dto.RouteStopDto;
import by.transport.myapp.mapper.RouteMapper;
import by.transport.myapp.model.dao.RouteDao;
import by.transport.myapp.service.RouteService;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RouteServiceImpl implements RouteService {
    private final RouteDao routeDao;
    private final RouteMapper mapper = Mappers.getMapper(RouteMapper.class);

    public RouteServiceImpl(RouteDao routeDao) {
        this.routeDao = routeDao;
    }

    @Override
    public Set<RouteStopDto> getRoots() {
        return routeDao.findAll().stream().map(mapper::routeToDto).collect(Collectors.toSet());
    }
}
