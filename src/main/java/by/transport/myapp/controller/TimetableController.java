package by.transport.myapp.controller;

import by.transport.myapp.dto.*;
import by.transport.myapp.message.DBMessageSource;
import by.transport.myapp.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping("/timetable")
public class TimetableController {
    private final StopService stopService;
    private final RouteLineService routeLineService;
    private final TransportTypeService typeService;
    private final RouteNumberService routeNumberService;
    private final RouteService routeService;
    private final DBMessageSource dbMessageSource;
    private final MessageSource messageSource;
    private final Logger logger = LogManager.getLogger(TimetableController.class);

    private static final String HEADER_MESSAGE = "headerMessage";
    private static final String STOP_N = "stopN";
    private static final String STOP_DETAILS = "stopDetails";
    private static final String REDIRECT_MAIN = "redirect:/";

    public TimetableController(StopService stopService,
                               RouteLineService routeLineService,
                               TransportTypeService typeService,
                               RouteNumberService routeNumberService,
                               RouteService routeService,
                               DBMessageSource dbMessageSource,
                               MessageSource messageSource) {
        this.stopService = stopService;
        this.routeLineService = routeLineService;
        this.typeService = typeService;
        this.routeNumberService = routeNumberService;
        this.routeService = routeService;
        this.dbMessageSource = dbMessageSource;
        this.messageSource = messageSource;
    }

    @GetMapping("/stops/all")
    public String showStops(Model model, Locale locale) {
        model.addAttribute(HEADER_MESSAGE, messageSource.getMessage("headerStop", null, locale));
        model.addAttribute("stops", stopService.getStops());
        model.addAttribute(STOP_N, null);
        model.addAttribute(STOP_DETAILS, null);
        return "timetable/stop";
    }

    @GetMapping("/stops/{stopId}/{detail}")
    public String showStopDetail(@PathVariable Integer stopId, @PathVariable String detail, Model model) {
        StopDto stopDto;
        Map<String, List<RouteLineStopDto>> stopDetails;

        if (stopId == null || stopId <= 0) {
            logger.error(String.format("Incorrect stop id %d", stopId));
            return null;
        }

        try {
            stopDto = stopService.getStopById(stopId);
        } catch (EntityNotFoundException e) {
            logger.error(String.format("Can't find stop with id %d", stopId));
            return null;
        }

        try {
            stopDetails = routeLineService.getStopDetails(stopDto);
        } catch (EntityNotFoundException e) {
            logger.error(String.format("Can't find stop details for stop %s with id %d", stopDto.getName(), stopId));
            return REDIRECT_MAIN;
        }

        model.addAttribute(STOP_N, stopDto.getName());
        model.addAttribute(STOP_DETAILS, stopDetails);
        return "timetable/" + detail + "::stopDetail";
    }

    @GetMapping("/routes/type/{id}")
    public String showRoutes(@PathVariable Integer id, Model model, Locale locale) {
        if (id == null || id <= 0) {
            logger.error(String.format("Incorrect type id %d", id));
            return REDIRECT_MAIN;
        }

        TransportTypeDto transportTypeDto;
        try {
            transportTypeDto = typeService.getTransportTypeById(id);
        } catch (EntityNotFoundException e) {
            logger.error(String.format("Can't find transport type with id %d", id));
            return REDIRECT_MAIN;
        }

        List<RouteNumberDto> numbers;
        try {
            numbers = routeNumberService.getRoutes(transportTypeDto);
        } catch (EntityNotFoundException e) {
            logger.error(String.format("Can't find route numbers for transport type with id %d", id));
            return REDIRECT_MAIN;
        }


        model.addAttribute(HEADER_MESSAGE, dbMessageSource.getMessage("", new Object[]{transportTypeDto}, locale));
        model.addAttribute("routesNumber", numbers);
        model.addAttribute("routeN", null);
        model.addAttribute("descN", null);
        model.addAttribute("routeDetail", null);
        model.addAttribute(STOP_N, null);
        model.addAttribute(STOP_DETAILS, null);
        return "timetable/route";
    }

    @GetMapping("/routes/{id}")
    public String showRouteDetails(@PathVariable Integer id,
                                   Model model,
                                   Locale locale) {
        RouteStopDto routeStopDto;

        if (id == null || id <= 0) {
            logger.error(String.format("Incorrect route id %d", id));
            return REDIRECT_MAIN;
        }

        try {
            routeStopDto = routeService.getRouteDetails(id);
        } catch (EntityNotFoundException e) {
            logger.error(String.format("Can't find route with id %d", id));
            return REDIRECT_MAIN;
        }
        String routeN = dbMessageSource.getMessage("", new Object[]{routeStopDto}, locale);

        model.addAttribute("routeN", routeN + " № " + routeStopDto.getNumber());
        model.addAttribute("descN", routeStopDto.getDescription());
        model.addAttribute("routeDetail", routeStopDto.getRouteLines());
        model.addAttribute(STOP_DETAILS, null);
        return "timetable/route::routeStop";
    }
}
