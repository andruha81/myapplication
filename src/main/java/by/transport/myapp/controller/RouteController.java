package by.transport.myapp.controller;

import by.transport.myapp.dto.RouteTransportDto;
import by.transport.myapp.service.RouteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class RouteController {
    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/route")
    public Set<RouteTransportDto> getRoots() {
        return routeService.getRootsWithTransport();
    }
}
