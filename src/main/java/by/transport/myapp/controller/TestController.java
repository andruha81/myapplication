package by.transport.myapp.controller;

import by.transport.myapp.dto.RouteStopDto;
import by.transport.myapp.service.RouteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class TestController {
    private final RouteService routeService;

    public TestController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/root")
    public Set<RouteStopDto> getRoots() {
        return routeService.getRoots();
    }
}
