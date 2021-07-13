package by.transport.myapp.service.impl;

import by.transport.myapp.dto.RouteLineStopDto;
import by.transport.myapp.mapper.RouteLineMapper;
import by.transport.myapp.model.dao.RouteDao;
import by.transport.myapp.model.dao.RouteLineDao;
import by.transport.myapp.model.dao.StopDao;
import by.transport.myapp.service.RouteLineService;
import by.transport.myapp.util.TimeUtil;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RouteLineServiceImpl implements RouteLineService {
    private final RouteLineDao routeLineDao;
    private final StopDao stopDao;
    private final RouteDao routeDao;
    private final RouteLineMapper mapper = Mappers.getMapper(RouteLineMapper.class);

    public RouteLineServiceImpl(RouteLineDao routeLineDao, StopDao stopDao, RouteDao routeDao) {
        this.routeLineDao = routeLineDao;
        this.stopDao = stopDao;
        this.routeDao = routeDao;
    }

    @Override
    public Map<String, List<RouteLineStopDto>> getStopDetails(Integer stopId) {
        return routeLineDao.getRouteLinesByStop(stopDao.getById(stopId))
                .stream()
                .map(mapper::routeLineToRouteLineStopDto)
                .peek(x -> TimeUtil.findTimeStop(x, routeDao.getById(x.getRouteId())))
                .collect(Collectors.groupingBy(RouteLineStopDto::getType));
    }
}
