package by.transport.myapp.service.impl;

import by.transport.myapp.dto.RouteStopDto;
import by.transport.myapp.mapper.RouteMapper;
import by.transport.myapp.model.dao.RouteDao;
import by.transport.myapp.service.RouteService;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Service
public class RouteServiceImpl implements RouteService {
    private final RouteDao routeDao;
    private final RouteMapper routeMapper = Mappers.getMapper(RouteMapper.class);

    public RouteServiceImpl(RouteDao routeDao) {
        this.routeDao = routeDao;
    }

    @Override
    public RouteStopDto getRouteDetails(Integer id) {
        return routeMapper.routeToRouteStopDto(routeDao.getById(id));
    }
}
