package by.transport.myapp.controller;

import by.transport.myapp.dto.RouteLineStopDto;
import by.transport.myapp.dto.RouteNumberDto;
import by.transport.myapp.dto.StopDto;
import by.transport.myapp.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/timetable")
public class TimetableController {
    private final StopService stopService;
    private final RouteLineService routeLineService;
    private final TransportTypeService typeService;
    private final RouteNumberService routeNumberService;
    private final RouteService routeService;
    private final Logger logger = LogManager.getLogger(TimetableController.class);
    private static final String HEADER_MESSAGE = "headerMessage";
    private static final String STOP_N = "stopN";
    private static final String STOP_DETAILS = "stopDetails";
    private static final String REDIRECT_STOPS = "redirect:/timetable/stops/all";
    private static final String REDIRECT_MAIN = "redirect:/";

    public TimetableController(StopService stopService,
                               RouteLineService routeLineService,
                               TransportTypeService typeService,
                               RouteNumberService routeNumberService,
                               RouteService routeService) {
        this.stopService = stopService;
        this.routeLineService = routeLineService;
        this.typeService = typeService;
        this.routeNumberService = routeNumberService;
        this.routeService = routeService;
    }

    @GetMapping("/stops/all")
    public String showStops(Model model) {
        model.addAttribute(HEADER_MESSAGE, "Остановки");
        model.addAttribute("stops", stopService.getStops());
        model.addAttribute(STOP_N, null);
        model.addAttribute(STOP_DETAILS, null);
        return "timetable/stop";
    }

    @GetMapping("/stops/{stopId}")
    public String showStopDetail(@PathVariable Integer stopId, Model model) {
        StopDto stopDto;
        Map<String, List<RouteLineStopDto>> routeLines;

        if (stopId == null || stopId <= 0) {
            logger.error(String.format("Incorrect stop id %d", stopId));
            return REDIRECT_STOPS;
        }

        try {
            stopDto = stopService.getStopById(stopId);
        } catch (EntityNotFoundException e) {
            logger.error(String.format("Can't find stop with id %d", stopId));
            return REDIRECT_STOPS;
        }

        try {
            routeLines = routeLineService.getStopDetails(stopId);
        } catch (EntityNotFoundException e) {
            logger.error(String.format("Can't find details for stop with id %d", stopId));
            return REDIRECT_STOPS;
        }

        model.addAttribute(HEADER_MESSAGE, "Остановки");
        model.addAttribute(STOP_N, stopDto.getName());
        model.addAttribute(STOP_DETAILS, routeLines);
        return "timetable/stop::stopDetail";
    }

    @GetMapping("/routes/type/{id}")
    public String showRoutes(@PathVariable Integer id, Model model) {
        String typeDescription;
        List<RouteNumberDto> numbers;

        if (id == null || id <= 0) {
            logger.error(String.format("Incorrect type id %d", id));
            return REDIRECT_MAIN;
        }

        try {
            typeDescription = typeService.getTypeDescription(id);
        } catch (EntityNotFoundException e) {
            logger.error(String.format("Can't find transport type with id %d", id));
            return REDIRECT_MAIN;
        }

        try {
            numbers = routeNumberService.getRoutes(id);
        } catch (EntityNotFoundException e) {
            logger.error(String.format("Can't find route numbers for transport type with id %d", id));
            return REDIRECT_MAIN;
        }

        model.addAttribute(HEADER_MESSAGE, typeDescription);
        model.addAttribute("routesNumber", numbers);
        model.addAttribute("routeN", null);
        model.addAttribute("descN", null);
        model.addAttribute("routeDetail", null);
        model.addAttribute(STOP_N, null);
        model.addAttribute(STOP_DETAILS, null);
        return "timetable/route";
    }

    @GetMapping("/routes/{id}")
    public String showRouteDetails(@PathVariable Integer id, Model model) {
        if (id == null || id <= 0) {
            return "timetable/route";
        }
        var routeStopDto = routeService.getRouteDetails(id);

        model.addAttribute("routeN", routeStopDto.getType() + " № " + routeStopDto.getNumber());
        model.addAttribute("descN", routeStopDto.getDescription());
        model.addAttribute("routeDetail", routeStopDto.getRouteLines());
        model.addAttribute(STOP_DETAILS, null);
        return "timetable/route::routeStop";
    }

    @GetMapping("/routes/stop/{stopId}")
    public String showRouteStopDetail(@PathVariable Integer stopId, Model model) {
        model.addAttribute(STOP_N, stopService.getStopById(stopId).getName());
        model.addAttribute(STOP_DETAILS, routeLineService.getStopDetails(stopId));
        return "timetable/route::stopDetail";
    }
}
