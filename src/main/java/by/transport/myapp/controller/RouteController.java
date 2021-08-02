package by.transport.myapp.controller;

import by.transport.myapp.dto.RouteLineNewParamDto;
import by.transport.myapp.dto.RouteLineParamDto;
import by.transport.myapp.dto.RouteParamDto;
import by.transport.myapp.dto.StopDto;
import by.transport.myapp.mapper.RouteLineParamMapper;
import by.transport.myapp.service.*;
import by.transport.myapp.util.RouteUtil;
import by.transport.myapp.util.StopUtil;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/route")
public class RouteController {
    private final RouteNumberService routeNumberService;
    private final RouteService routeService;
    private final RouteLineService routeLineService;
    private final StopService stopService;
    private final TransportTypeService typeService;

    private static final String HEADER_MESSAGE = "headerMessage";
    private static final String ROUTE = "route/route";
    private static final String ROUTE_PARAMETERS = "route/route-parameters";
    private static final String PARAMETERS = "Редактирование маршрута";
    private static final String EDIT_ROUTE = "redirect:/route/edit?id=";

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
        return "route/route::routeStop";
    }

    @GetMapping("/stop/{stopId}")
    public String showStopDetail(@PathVariable Integer stopId, Model model) {
        model.addAttribute("stopN", stopService.getStopById(stopId).getName());
        model.addAttribute("stopDetail", routeLineService.getStopDetails(stopId));
        return "route/route::stopDetail";
    }

    @GetMapping("/edit")
    public String showRouteParameters(@RequestParam(name = "id") Integer routeId,
                                      Model model) {
        RouteParamDto routeParamDto = routeService.getRouteById(routeId);
        Collections.sort(routeParamDto.getRouteLines());
        model.addAttribute(HEADER_MESSAGE, PARAMETERS);
        model.addAttribute("route", routeParamDto);

        return ROUTE_PARAMETERS;
    }

    @GetMapping("/new")
    public String showNewRouteParameters(@RequestParam(name = "type") Integer typeId,
                                         Model model) {
        RouteParamDto routeParamDto = new RouteParamDto();
        routeParamDto.setTypeId(typeId);
        model.addAttribute(HEADER_MESSAGE, "Создание маршрута");
        model.addAttribute("route", routeParamDto);

        return ROUTE_PARAMETERS;
    }

    @GetMapping("/edit/stop")
    @Transactional
    public String editRouteStop(@RequestParam Integer routeId,
                                @RequestParam Integer rlId) {
        RouteParamDto routeParamDto = routeService.getRouteById(routeId);
        RouteUtil.removeStop(routeParamDto, rlId);
        routeService.save(routeParamDto);

        return EDIT_ROUTE + routeParamDto.getRouteParamDtoId();
    }

    @GetMapping("/add/stop")
    public String addRouteStop(@RequestParam Integer routeId,
                               Model model) {
        RouteParamDto routeParamDto = routeService.getRouteById(routeId);
        List<StopDto> stops = StopUtil.removeStops(stopService.getStops(), routeParamDto.getRouteLines());

        model.addAttribute(HEADER_MESSAGE, "Добавление остановки к маршруту");
        model.addAttribute("route", routeParamDto);
        model.addAttribute("routeLine", new RouteLineNewParamDto());
        model.addAttribute("stops", stops);

        return "route/new-routeline";
    }

    @PostMapping("/add/stop/{routeId}")
    @Transactional
    public String newRouteStop(@PathVariable Integer routeId,
                               @ModelAttribute("routeLine") RouteLineNewParamDto routeLineNewParamDto) {
        RouteLineParamDto routeLineParamDto = RouteLineParamMapper.map(routeLineNewParamDto,
                stopService.getStopById(routeLineNewParamDto.getStopId()));
        RouteParamDto routeParamDto = routeService.getRouteById(routeId);
        RouteUtil.addStop(routeParamDto, routeLineParamDto.getStopOrder());
        routeParamDto.getRouteLines().add(routeLineParamDto);
        routeService.save(routeParamDto);

        return EDIT_ROUTE + routeParamDto.getRouteParamDtoId();
    }

    @PostMapping("/save")
    @Transactional
    public String saveRoute(@ModelAttribute("route") RouteParamDto routeParamDto,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ROUTE_PARAMETERS;
        }
        if (routeParamDto.getRouteLines() == null) {
            routeParamDto.setRouteLines(new ArrayList<>());
        }
        routeService.save(routeParamDto);
        return EDIT_ROUTE + routeParamDto.getRouteParamDtoId();
    }
}
