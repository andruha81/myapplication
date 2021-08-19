package by.transport.myapp.service.impl;

import by.transport.myapp.dto.RouteLineStopDto;
import by.transport.myapp.dto.StopDto;
import by.transport.myapp.mapper.RouteLineMapper;
import by.transport.myapp.mapper.StopMapper;
import by.transport.myapp.model.dao.RouteDao;
import by.transport.myapp.model.dao.RouteLineDao;
import by.transport.myapp.model.entity.RouteLine;
import by.transport.myapp.model.entity.Stop;
import by.transport.myapp.service.RouteLineService;
import by.transport.myapp.util.TimeUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RouteLineServiceImpl implements RouteLineService {
    private final RouteLineDao routeLineDao;
    private final RouteDao routeDao;
    private final RouteLineMapper mapper = Mappers.getMapper(RouteLineMapper.class);
    private final StopMapper stopMapper = Mappers.getMapper(StopMapper.class);
    private final Logger logger = LogManager.getLogger(RouteLineServiceImpl.class);

    public RouteLineServiceImpl(RouteLineDao routeLineDao, RouteDao routeDao) {
        this.routeLineDao = routeLineDao;
        this.routeDao = routeDao;
    }

    @Override
    public Map<String, List<RouteLineStopDto>> getStopDetails(StopDto stopDto) {
        Stop stop = stopMapper.stopDtoToStop(stopDto);
        List<RouteLine> routeLines = routeLineDao.getRouteLinesByStop(stop);
        logger.info(String.format("Find details for stop %s", stop.getName()));
        return routeLines.stream()
                .map(mapper::routeLineToRouteLineStopDto)
                .peek(x -> TimeUtil.findTimeStop(x, routeDao.getById(x.getRouteId())))
                .collect(Collectors.groupingBy(RouteLineStopDto::getType));
    }
}
