package by.transport.myapp.service.impl;

import by.transport.myapp.dto.RouteParamDto;
import by.transport.myapp.dto.RouteStopDto;
import by.transport.myapp.mapper.RouteMapper;
import by.transport.myapp.model.dao.RouteDao;
import by.transport.myapp.model.dao.RouteNumberDao;
import by.transport.myapp.model.entity.Route;
import by.transport.myapp.service.RouteService;
import by.transport.myapp.util.RouteUtil;
import by.transport.myapp.util.TimeUtil;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Service
public class RouteServiceImpl implements RouteService {
    private final RouteDao routeDao;
    private final RouteNumberDao routeNumberDao;
    private final RouteMapper routeMapper = Mappers.getMapper(RouteMapper.class);

    public RouteServiceImpl(RouteDao routeDao,
                            RouteNumberDao routeNumberDao) {
        this.routeDao = routeDao;
        this.routeNumberDao = routeNumberDao;
    }

    @Override
    public RouteStopDto getRouteDetails(Integer id) {
        var route = routeDao.getById(id);
        var routeStopDto = routeMapper.routeToRouteStopDto(route);
        routeStopDto.getRouteLines().forEach(x -> TimeUtil.findTime(x, route));
        return routeStopDto;
    }

    @Override
    public RouteParamDto getRouteById(Integer id) {
        return routeMapper.routeToRouteParamDto(routeDao.getById(id));
    }

    @Override
    public void save(RouteParamDto routeParamDto) {
        Route route = routeMapper.RouteParamDtoToRoute(routeParamDto,
                routeNumberDao.getRouteNumberByNumber(routeParamDto.getRouteNumber()));
        RouteUtil.setRouteInRouteLine(route, route.getRouteLines());
        routeDao.save(route);
    }
}
