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
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Service
public class RouteServiceImpl implements RouteService {
    private final RouteDao routeDao;
    private final RouteNumberDao routeNumberDao;
    private final TransportTypeDao transportTypeDao;
    private final RouteMapper routeMapper = Mappers.getMapper(RouteMapper.class);

    public RouteServiceImpl(RouteDao routeDao,
                            RouteNumberDao routeNumberDao,
                            TransportTypeDao transportTypeDao) {
        this.routeDao = routeDao;
        this.routeNumberDao = routeNumberDao;
        this.transportTypeDao = transportTypeDao;
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
        RouteNumber routeNumber = routeNumberDao.getRouteNumberByNumber(routeParamDto.getRouteNumber());
        if (routeNumber == null) {
            routeNumber = new RouteNumber();
            routeNumber.setNumber(routeParamDto.getRouteNumber());
            routeNumber.setType(transportTypeDao.findTransportTypeById(routeParamDto.getTypeId()));
        }
        Route route = routeMapper.RouteParamDtoToRoute(routeParamDto, routeNumber);
        RouteUtil.setRouteInRouteLine(route, route.getRouteLines());
        routeParamDto.setRouteParamDtoId(routeDao.save(route).getId());
    }
}
