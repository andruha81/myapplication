package by.transport.myapp.service.impl;

import by.transport.myapp.model.dao.RouteNumberDao;
import by.transport.myapp.service.RouteNumberService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteNumberServiceImpl implements RouteNumberService {
    private final RouteNumberDao routeNumberDao;

    public RouteNumberServiceImpl(RouteNumberDao routeNumberDao) {
        this.routeNumberDao = routeNumberDao;
    }

    @Override
    public List<Integer> getRouteNumbers() {
        return routeNumberDao.getNumbers();
    }
}
