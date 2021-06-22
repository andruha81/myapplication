package by.transport.myapp.controller;

import by.transport.myapp.dto.RouteStopDto;
import by.transport.myapp.service.RouteNumberService;
import by.transport.myapp.service.RouteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/route")
public class RouteController {
    private final RouteNumberService routeNumberService;
    private final RouteService routeService;

    public RouteController(RouteNumberService routeNumberService, RouteService routeService) {
        this.routeNumberService = routeNumberService;
        this.routeService = routeService;
    }

    @GetMapping("/type/{id}")
    public String showRoutes(@PathVariable Integer id, Model model) {
        model.addAttribute("routesNumber", routeNumberService.getRoutes(id));
        model.addAttribute("routeN", null);
        model.addAttribute("descN", null);
        model.addAttribute("routeDetail", null);
        return "route";
    }

    @GetMapping("/{id}")
    public String showRouteDetails(@PathVariable Integer id, Model model) {
        RouteStopDto routeStopDto = routeService.getRouteDetails(id);
        model.addAttribute("routeN", routeStopDto.getType() + " № " + routeStopDto.getNumber());
        model.addAttribute("descN", routeStopDto.getDescription());
        model.addAttribute("routeDetail", routeStopDto.getRouteLines());
        return "route::routeStop";
    }
}
