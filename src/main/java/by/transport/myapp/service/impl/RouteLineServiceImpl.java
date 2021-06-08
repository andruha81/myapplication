package by.transport.myapp.service.impl;

import by.transport.myapp.model.dao.RouteLineDao;
import by.transport.myapp.service.RouteLineService;
import org.springframework.stereotype.Service;

@Service
public class RouteLineServiceImpl implements RouteLineService {
    private final RouteLineDao routeLineDao;

    public RouteLineServiceImpl(RouteLineDao routeLineDao) {
        this.routeLineDao = routeLineDao;
    }
}
