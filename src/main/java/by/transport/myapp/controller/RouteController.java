package by.transport.myapp.controller;

import by.transport.myapp.service.RouteService;
import org.springframework.stereotype.Controller;

@Controller
public class RouteController {
    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }
}
