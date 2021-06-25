package by.transport.myapp.service.impl;

import by.transport.myapp.dto.RouteLineStopDto;
import by.transport.myapp.mapper.RouteLineMapper;
import by.transport.myapp.model.dao.RouteLineDao;
import by.transport.myapp.model.dao.StopDao;
import by.transport.myapp.service.RouteLineService;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RouteLineServiceImpl implements RouteLineService {
    private final RouteLineDao routeLineDao;
    private final StopDao stopDao;
    private final RouteLineMapper mapper = Mappers.getMapper(RouteLineMapper.class);

    public RouteLineServiceImpl(RouteLineDao routeLineDao, StopDao stopDao) {
        this.routeLineDao = routeLineDao;
        this.stopDao = stopDao;
    }

    @Override
    public Map<String, List<RouteLineStopDto>> getStopDetails(Integer routeId, Integer stopId) {
        return routeLineDao.getRouteLinesByStop(stopDao.getById(stopId))
                .stream()
                .map(mapper::routeLineToRouteLineStopDto)
                .collect(Collectors.groupingBy(RouteLineStopDto::getType));
    }
}
