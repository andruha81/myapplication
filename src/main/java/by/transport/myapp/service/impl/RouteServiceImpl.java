package by.transport.myapp.service.impl;

import by.transport.myapp.dto.RouteParamDto;
import by.transport.myapp.dto.RouteStopDto;
import by.transport.myapp.mapper.RouteMapper;
import by.transport.myapp.model.dao.RouteDao;
import by.transport.myapp.model.dao.RouteNumberDao;
import by.transport.myapp.model.dao.TransportTypeDao;
import by.transport.myapp.model.entity.Route;
import by.transport.myapp.model.entity.RouteNumber;
import by.transport.myapp.service.RouteService;
import by.transport.myapp.util.RouteUtil;
import by.transport.myapp.util.TimeUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class RouteServiceImpl implements RouteService {
    private final RouteDao routeDao;
    private final RouteNumberDao routeNumberDao;
    private final TransportTypeDao transportTypeDao;
    private final RouteMapper routeMapper = Mappers.getMapper(RouteMapper.class);
    private final Logger logger = LogManager.getLogger(RouteServiceImpl.class);

    public RouteServiceImpl(RouteDao routeDao,
                            RouteNumberDao routeNumberDao,
                            TransportTypeDao transportTypeDao) {
        this.routeDao = routeDao;
        this.routeNumberDao = routeNumberDao;
        this.transportTypeDao = transportTypeDao;
    }

    @Override
    public RouteStopDto getRouteDetails(Integer id) throws EntityNotFoundException {
        var route = routeDao.getById(id);
        logger.info(String.format("Get route details for route %s by id %d", route.getDescription(), id));
        var routeStopDto = routeMapper.routeToRouteStopDto(route);
        routeStopDto.getRouteLines().forEach(x -> TimeUtil.findTime(x, route));
        return routeStopDto;
    }

    @Override
    public RouteParamDto getRouteById(Integer id) throws EntityNotFoundException {
        Route route = routeDao.getById(id);
        logger.info(String.format("Got route %s by id %d", route.getDescription(), id));
        return routeMapper.routeToRouteParamDto(route);
    }

    @Override
    @Transactional
    public Integer save(RouteParamDto routeParamDto, RouteNumber routeNumber) {
        Route route = routeMapper.RouteParamDtoToRoute(routeParamDto, routeNumber);
        RouteUtil.setRouteInRouteLine(route, route.getRouteLines());
        routeParamDto.setRouteParamDtoId(routeDao.save(route).getId());
        return routeParamDto.getRouteParamDtoId();
    }
}
