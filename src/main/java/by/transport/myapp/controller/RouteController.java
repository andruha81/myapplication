package by.transport.myapp.controller;

import by.transport.myapp.dto.RouteLineNewParamDto;
import by.transport.myapp.dto.RouteLineParamDto;
import by.transport.myapp.dto.RouteParamDto;
import by.transport.myapp.dto.StopDto;
import by.transport.myapp.mapper.RouteLineParamMapper;
import by.transport.myapp.service.RouteService;
import by.transport.myapp.service.StopService;
import by.transport.myapp.util.RouteUtil;
import by.transport.myapp.util.StopUtil;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/route")
public class RouteController {
    private final RouteService routeService;
    private final StopService stopService;

    private static final String HEADER_MESSAGE = "headerMessage";
    private static final String ROUTE_PARAMETERS = "route/route-parameters";
    private static final String PARAMETERS = "Редактирование маршрута";
    private static final String EDIT_ROUTE = "redirect:/route/edit?id=";
    private static final String ROUTE_DTO = "route";
    private static final String NOT_SAVED = "NotSaved";

    public RouteController(RouteService routeService,
                           StopService stopService) {
        this.routeService = routeService;
        this.stopService = stopService;
    }

    @GetMapping("/edit")
    public String showRouteParameters(@RequestParam(name = "id") Integer routeId,
                                      Model model) {
        RouteParamDto routeParamDto = routeService.getRouteById(routeId);
        Collections.sort(routeParamDto.getRouteLines());
        model.addAttribute(HEADER_MESSAGE, PARAMETERS);
        model.addAttribute(NOT_SAVED, "");
        model.addAttribute(ROUTE_DTO, routeParamDto);

        return ROUTE_PARAMETERS;
    }

    @GetMapping("/new")
    public String showNewRouteParameters(@RequestParam(name = "type") Integer typeId,
                                         Model model) {
        RouteParamDto routeParamDto = new RouteParamDto();
        routeParamDto.setTypeId(typeId);
        model.addAttribute(HEADER_MESSAGE, "Создание маршрута");
        model.addAttribute(NOT_SAVED, "");
        model.addAttribute(ROUTE_DTO, routeParamDto);

        return ROUTE_PARAMETERS;
    }

    @GetMapping("/edit/stop")
    public String editRouteStop(@RequestParam Integer routeId,
                                @RequestParam Integer rlId) {
        RouteParamDto routeParamDto = routeService.getRouteById(routeId);
        RouteUtil.removeStop(routeParamDto, rlId);
        routeService.save(routeParamDto);

        return EDIT_ROUTE + routeParamDto.getRouteParamDtoId();
    }

    @GetMapping("/add/stop")
    public String addRouteStop(@RequestParam(required = false) Integer routeId, Model model) {
        if (routeId == null) {
            model.addAttribute(NOT_SAVED, "Перед добавлением остановки необходмимо сохранить маршрут");
            model.addAttribute(ROUTE_DTO, new RouteParamDto());
            return ROUTE_PARAMETERS;
        }

        RouteParamDto routeParamDto = routeService.getRouteById(routeId);
        List<StopDto> stops = StopUtil.removeStops(stopService.getStops(), routeParamDto.getRouteLines());

        model.addAttribute(HEADER_MESSAGE, "Добавление остановки к маршруту");
        model.addAttribute(ROUTE_DTO, routeParamDto);
        model.addAttribute("routeLine", new RouteLineNewParamDto());
        model.addAttribute("stops", stops);

        return "route/new-routeline";
    }

    @PostMapping("/add/stop/{routeId}")
    public String newRouteStop(@PathVariable Integer routeId,
                               @ModelAttribute("routeLine") @Valid RouteLineNewParamDto routeLineNewParamDto,
                               BindingResult bindingResult,
                               Model model) {
        RouteParamDto routeParamDto = routeService.getRouteById(routeId);

        if (bindingResult.hasErrors()) {
            List<StopDto> stops = StopUtil.removeStops(stopService.getStops(), routeParamDto.getRouteLines());
            model.addAttribute(ROUTE_DTO, routeParamDto);
            model.addAttribute("stops", stops);
            return "route/new-routeline";
        }

        RouteLineParamDto routeLineParamDto = RouteLineParamMapper.map(routeLineNewParamDto,
                stopService.getStopById(routeLineNewParamDto.getStopId()));
        RouteUtil.addStop(routeParamDto, routeLineParamDto.getStopOrder());
        routeParamDto.getRouteLines().add(routeLineParamDto);
        routeService.save(routeParamDto);

        return EDIT_ROUTE + routeParamDto.getRouteParamDtoId();
    }

    @PostMapping("/save")
    public String saveRoute(@ModelAttribute("route") @Valid RouteParamDto routeParamDto,
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
