package by.transport.myapp.controller;

import by.transport.myapp.service.*;
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
    private final RouteLineService routeLineService;
    private final StopService stopService;
    private final TransportTypeService typeService;

    public RouteController(RouteNumberService routeNumberService,
                           RouteService routeService,
                           RouteLineService routeLineService,
                           StopService stopService,
                           TransportTypeService typeService) {
        this.routeNumberService = routeNumberService;
        this.routeService = routeService;
        this.routeLineService = routeLineService;
        this.stopService = stopService;
        this.typeService = typeService;
    }

    @GetMapping("/type/{id}")
    public String showRoutes(@PathVariable Integer id, Model model) {
        model.addAttribute("headerMessage", typeService.getTypeDescription(id));
        model.addAttribute("routesNumber", routeNumberService.getRoutes(id));
        model.addAttribute("routeN", null);
        model.addAttribute("descN", null);
        model.addAttribute("routeDetail", null);
        model.addAttribute("stopN", null);
        model.addAttribute("stopDetail", null);
        return "route";
    }

    @GetMapping("/{id}")
    public String showRouteDetails(@PathVariable Integer id, Model model) {
        var routeStopDto = routeService.getRouteDetails(id);
        model.addAttribute("routeN", routeStopDto.getType() + " № " + routeStopDto.getNumber());
        model.addAttribute("descN", routeStopDto.getDescription());
        model.addAttribute("routeDetail", routeStopDto.getRouteLines());
        return "route::routeStop";
    }

    @GetMapping("/stop/{stopId}")
    public String showStopDetail(@PathVariable Integer stopId, Model model) {
        model.addAttribute("stopN", stopService.getStopById(stopId).getName());
        model.addAttribute("stopDetail", routeLineService.getStopDetails(stopId));
        return "route::stopDetail";
    }
}
