package by.transport.myapp.controller;

import by.transport.myapp.dto.RouteParamDto;
import by.transport.myapp.service.*;
import by.transport.myapp.util.RouteUtil;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Controller
@RequestMapping("/route")
public class RouteController {
    private final RouteNumberService routeNumberService;
    private final RouteService routeService;
    private final RouteLineService routeLineService;
    private final StopService stopService;
    private final TransportTypeService typeService;

    private static final String HEADER_MESSAGE = "headerMessage";
    private static final String ROUTE = "route";
    private static final String ROUTE_PARAMETERS = "route-parameters";

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
        model.addAttribute(HEADER_MESSAGE, typeService.getTypeDescription(id));
        model.addAttribute("routesNumber", routeNumberService.getRoutes(id));
        model.addAttribute("routeN", null);
        model.addAttribute("descN", null);
        model.addAttribute("routeDetail", null);
        model.addAttribute("stopN", null);
        model.addAttribute("stopDetail", null);
        return ROUTE;
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

    @GetMapping("/edit")
    public String showRouteParameters(@RequestParam(name = "id") Integer routeId,
                                      Model model) {
        RouteParamDto routeParamDto = routeService.getRouteById(routeId);
        Collections.sort(routeParamDto.getRouteLines());
        model.addAttribute(HEADER_MESSAGE, "Параметры маршрута");
        model.addAttribute(ROUTE, routeParamDto);

        return ROUTE_PARAMETERS;
    }

    @GetMapping("/new")
    public String showNewRouteParameters(Model model) {
        model.addAttribute(HEADER_MESSAGE, "Создание маршрута");
        model.addAttribute(ROUTE, new RouteParamDto());

        return ROUTE_PARAMETERS;
    }

    @GetMapping("/edit/stop")
    public String editRouteStop(@RequestParam Integer routeId,
                                @RequestParam Integer rlId,
                                Model model) {
        RouteParamDto routeParamDto = routeService.getRouteById(routeId);
        RouteUtil.removeStop(routeParamDto, rlId);
        model.addAttribute(HEADER_MESSAGE, "Параметры маршрута");
        model.addAttribute(ROUTE, routeParamDto);

        return ROUTE_PARAMETERS;
    }

    @PostMapping("/save")
    @Transactional
    public String saveRoute(@ModelAttribute("route") RouteParamDto routeParamDto,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ROUTE_PARAMETERS;
        }
        routeService.save(routeParamDto);
        return "redirect:/dispatcher/all";
    }
}
