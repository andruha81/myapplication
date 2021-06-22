package by.transport.myapp.service.impl;

import by.transport.myapp.dto.RouteLineDto;
import by.transport.myapp.dto.RouteStopDto;
import by.transport.myapp.mapper.RouteMapper;
import by.transport.myapp.model.dao.RouteDao;
import by.transport.myapp.model.entity.Route;
import by.transport.myapp.service.RouteService;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.TreeSet;

@Service
public class RouteServiceImpl implements RouteService {
    private final RouteDao routeDao;
    private final RouteMapper routeMapper = Mappers.getMapper(RouteMapper.class);

    public RouteServiceImpl(RouteDao routeDao) {
        this.routeDao = routeDao;
    }

    @Override
    public RouteStopDto getRouteDetails(Integer id) {
        Route route = routeDao.getById(id);
        RouteStopDto routeStopDto = routeMapper.routeToRouteStopDto(route);
        routeStopDto.getRouteLines().forEach(x -> findTime(x, route));
        return routeStopDto;
    }

    private void findTime(RouteLineDto routeLineDto, Route route) {

        LocalTime currentTime = LocalTime.now().minusHours(5);
        LocalTime endTime = route.getEndWeekday();
        if (compareTime(currentTime, endTime)) {
            LocalTime closestTime = route.getStartWeekday();
            int interval = route.getIntervalWeekday();

            while (compareTime(closestTime, currentTime)) {
                closestTime = closestTime.plusMinutes(interval);
            }

            if (compareTime(closestTime, endTime)) {
                routeLineDto.setClosestTime(closestTime);
                closestTime = closestTime.plusMinutes(interval);
                if (compareTime(closestTime, endTime)) {
                    routeLineDto.setNextTime(closestTime);
                }
            }
        }
    }

    private boolean compareTime(LocalTime firstTime, LocalTime secondtime) {
        return firstTime.compareTo(secondtime) < 0;
    }
}
