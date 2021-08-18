package by.transport.myapp.controller;

import by.transport.myapp.dto.RouteLineNewParamDto;
import by.transport.myapp.dto.RouteLineParamDto;
import by.transport.myapp.dto.RouteParamDto;
import by.transport.myapp.dto.StopDto;
import by.transport.myapp.mapper.RouteLineParamMapper;
import by.transport.myapp.model.entity.RouteNumber;
import by.transport.myapp.service.RouteNumberService;
import by.transport.myapp.service.RouteService;
import by.transport.myapp.service.StopService;
import by.transport.myapp.util.RouteUtil;
import by.transport.myapp.util.StopUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/route")
public class RouteController {
    private final RouteService routeService;
    private final StopService stopService;
    private final RouteNumberService routeNumberService;
    private final MessageSource messageSource;
    private final Logger logger = LogManager.getLogger(RouteController.class);

    private static final String HEADER_MESSAGE = "headerMessage";
    private static final String ROUTE_PARAMETERS = "route/route-parameters";
    private static final String EDIT_ROUTE = "redirect:/route/edit?id=";
    private static final String ROUTE_DTO = "route";
    private static final String NOT_SAVED = "NotSaved";
    private static final String REDIRECT_DISPATCHER = "redirect:/dispatcher/all";

    public RouteController(RouteService routeService,
                           StopService stopService,
                           RouteNumberService routeNumberService,
                           MessageSource messageSource) {
        this.routeService = routeService;
        this.stopService = stopService;
        this.routeNumberService = routeNumberService;
        this.messageSource = messageSource;
    }

    @GetMapping("/edit")
    public String showRouteParameters(@RequestParam(name = "id") Integer routeId,
                                      Model model,
                                      Locale locale) {
        RouteParamDto routeParamDto;
        if (routeId == null || routeId <= 0) {
            logger.error(String.format("Incorrect route id %d", routeId));
            return REDIRECT_DISPATCHER;
        }

        try {
            routeParamDto = routeService.getRouteById(routeId);
        } catch (EntityNotFoundException e) {
            logger.error(String.format("Can't find route by id %d", routeId));
            return REDIRECT_DISPATCHER;
        }

        Collections.sort(routeParamDto.getRouteLines());

        model.addAttribute(HEADER_MESSAGE, messageSource.getMessage("headerEditRoute", null, locale));
        model.addAttribute(NOT_SAVED, "");
        model.addAttribute("routeNumbers", routeNumberService.getRouteNumbers());
        model.addAttribute(ROUTE_DTO, routeParamDto);

        return ROUTE_PARAMETERS;
    }

    @GetMapping("/new")
    public String showNewRouteParameters(@RequestParam(name = "type") Integer typeId,
                                         Model model,
                                         Locale locale) {
        if (typeId == null || typeId <= 0) {
            logger.error(String.format("Incorrect type id %d", typeId));
            return REDIRECT_DISPATCHER;
        }

        RouteParamDto routeParamDto = new RouteParamDto();
        routeParamDto.setTypeId(typeId);

        model.addAttribute(HEADER_MESSAGE, messageSource.getMessage("headerAddRoute", null, locale));
        model.addAttribute(NOT_SAVED, "");
        model.addAttribute("routeNumbers", routeNumberService.getRouteNumbers());
        model.addAttribute(ROUTE_DTO, routeParamDto);

        return ROUTE_PARAMETERS;
    }

    @GetMapping("/edit/stop")
    public String editRouteStop(@RequestParam Integer routeId,
                                @RequestParam Integer rlId) {
        RouteParamDto routeParamDto;
        RouteNumber routeNumber;

        if (routeId == null || routeId <= 0) {
            logger.error(String.format("Incorrect route id %d", routeId));
            return REDIRECT_DISPATCHER;
        }

        if (rlId == null || rlId <= 0) {
            logger.error(String.format("Incorrect route line id %d", rlId));
            return REDIRECT_DISPATCHER;
        }

        try {
            routeParamDto = routeService.getRouteById(routeId);
        } catch (EntityNotFoundException e) {
            logger.error(String.format("Can't find route by id %d", routeId));
            return REDIRECT_DISPATCHER;
        }

        RouteUtil.removeStop(routeParamDto, rlId);

        try {
            routeNumber = routeNumberService.getRouteNumberByNumber(routeParamDto.getRouteNumber());
        } catch (EntityNotFoundException e) {
            logger.error(String.format("Can't find route number by number %d", routeParamDto.getRouteNumber()));
            return REDIRECT_DISPATCHER;
        }

        if (routeService.save(routeParamDto, routeNumber) != null) {
            logger.info(String.format("Saved route %s to database", routeParamDto.getDescription()));
        } else {
            logger.error(String.format("Didn't save route %s to database", routeParamDto.getDescription()));
        }

        return EDIT_ROUTE + routeParamDto.getRouteParamDtoId();
    }

    @GetMapping("/add/stop")
    public String addRouteStop(@RequestParam(required = false) Integer routeId,
                               Model model,
                               Locale locale) {
        RouteParamDto routeParamDto;

        if (routeId == null) {
            model.addAttribute(NOT_SAVED, "Перед добавлением остановки необходмимо сохранить маршрут");
            model.addAttribute(ROUTE_DTO, new RouteParamDto());
            return ROUTE_PARAMETERS;
        }

        if (routeId <= 0) {
            logger.error(String.format("Incorrect route id %d", routeId));
            return REDIRECT_DISPATCHER;
        }

        try {
            routeParamDto = routeService.getRouteById(routeId);
        } catch (EntityNotFoundException e) {
            logger.error(String.format("Can't find route by id %d", routeId));
            return REDIRECT_DISPATCHER;
        }

        List<StopDto> stops = StopUtil.removeStops(stopService.getStops(), routeParamDto.getRouteLines());

        model.addAttribute(HEADER_MESSAGE, messageSource.getMessage("headerAddLine", null, locale));
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
        RouteParamDto routeParamDto;
        StopDto stopDto;
        RouteNumber routeNumber;

        if (routeId == null || routeId <= 0) {
            logger.error(String.format("Incorrect route id %d", routeId));
            return REDIRECT_DISPATCHER;
        }

        if (routeLineNewParamDto == null) {
            logger.error("Didn't get route line from view");
            return REDIRECT_DISPATCHER;
        }

        try {
            routeParamDto = routeService.getRouteById(routeId);
        } catch (EntityNotFoundException e) {
            logger.error(String.format("Can't find route by id %d", routeId));
            return REDIRECT_DISPATCHER;
        }

        if (bindingResult.hasErrors()) {
            List<StopDto> stops = StopUtil.removeStops(stopService.getStops(), routeParamDto.getRouteLines());
            model.addAttribute(ROUTE_DTO, routeParamDto);
            model.addAttribute("stops", stops);
            return "route/new-routeline";
        }

        try {
            stopDto = stopService.getStopById(routeLineNewParamDto.getStopId());
        } catch (EntityNotFoundException e) {
            logger.error(String.format("Can't find route by id %d", routeId));
            return REDIRECT_DISPATCHER;
        }

        RouteLineParamDto routeLineParamDto = RouteLineParamMapper.map(routeLineNewParamDto, stopDto);
        RouteUtil.addStop(routeParamDto, routeLineParamDto.getStopOrder());
        routeParamDto.getRouteLines().add(routeLineParamDto);

        try {
            routeNumber = routeNumberService.getRouteNumberByNumber(routeParamDto.getRouteNumber());
        } catch (EntityNotFoundException e) {
            logger.error(String.format("Can't find route number by number %d", routeParamDto.getRouteNumber()));
            return REDIRECT_DISPATCHER;
        }

        if (routeService.save(routeParamDto, routeNumber) != null) {
            logger.info(String.format("Saved route %s to database", routeParamDto.getDescription()));
        } else {
            logger.error(String.format("Didn't save route %s to database", routeParamDto.getDescription()));
        }

        return EDIT_ROUTE + routeParamDto.getRouteParamDtoId();
    }

    @PostMapping("/save")
    public String saveRoute(@ModelAttribute("route") @Valid RouteParamDto routeParamDto,
                            BindingResult bindingResult,
                            Model model) {
        RouteNumber routeNumber;

        if (bindingResult.hasErrors()) {
            model.addAttribute("routeNumbers", routeNumberService.getRouteNumbers());
            return ROUTE_PARAMETERS;
        }

        if (routeParamDto.getRouteLines() == null) {
            routeParamDto.setRouteLines(new ArrayList<>());
        }

        try {
            routeNumber = routeNumberService.getRouteNumberByNumber(routeParamDto.getRouteNumber());
        } catch (EntityNotFoundException e) {
            logger.error(String.format("Can't find route number by number %d", routeParamDto.getRouteNumber()));
            return REDIRECT_DISPATCHER;
        }

        if (routeService.save(routeParamDto, routeNumber) != null) {
            logger.info(String.format("Saved route %s to database", routeParamDto.getDescription()));
        } else {
            logger.error(String.format("Didn't save route %s to database", routeParamDto.getDescription()));
        }

        return EDIT_ROUTE + routeParamDto.getRouteParamDtoId();
    }
}
