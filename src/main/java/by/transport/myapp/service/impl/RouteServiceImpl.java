package by.transport.myapp.service.impl;

import by.transport.myapp.model.dao.RouteDao;
import by.transport.myapp.service.RouteService;
import org.springframework.stereotype.Service;

@Service
public class RouteServiceImpl implements RouteService {
    private final RouteDao routeDao;

    public RouteServiceImpl(RouteDao routeDao) {
        this.routeDao = routeDao;
    }
}
