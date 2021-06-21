package by.transport.myapp.controller;

import by.transport.myapp.dto.TransportTypeDto;
import by.transport.myapp.service.RouteNumberService;
import by.transport.myapp.service.TransportTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/route")
public class RouteController {
    private final RouteNumberService routeNumberService;
    private final TransportTypeService transportTypeService;

    public RouteController(RouteNumberService routeNumberService, TransportTypeService transportTypeService) {
        this.routeNumberService = routeNumberService;
        this.transportTypeService = transportTypeService;
    }

    @GetMapping("/type/{id}")
    public String showRoutes(@PathVariable Integer id, Model model) {
        model.addAttribute("routesNumber", routeNumberService.getRoutes(id));
        model.addAttribute("routeN", null);
        model.addAttribute("descN", null);
        model.addAttribute("stops", null);
        return "route";
    }

    @GetMapping("/{id}")
    public String showRouteStops(@PathVariable Integer id, Model model) {
        return "route::routeStop";
    }
}
