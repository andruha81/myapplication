package by.transport.myapp.controller;

import by.transport.myapp.dto.RouteNumberDto;
import by.transport.myapp.dto.TransportDto;
import by.transport.myapp.dto.TransportTypeDto;
import by.transport.myapp.message.DBMessageSource;
import by.transport.myapp.service.RouteNumberService;
import by.transport.myapp.service.StopService;
import by.transport.myapp.service.TransportService;
import by.transport.myapp.service.TransportTypeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/dispatcher")
public class DispatcherController {
    private final TransportTypeService transportTypeService;
    private final TransportService transportService;
    private final RouteNumberService routeNumberService;
    private final StopService stopService;
    private final MessageSource messageSource;
    private final DBMessageSource dbMessageSource;

    private static final String HEADER_MESSAGE = "headerMessage";
    private static final String REDIRECT_DISPATCHER = "redirect:/dispatcher/all";
    private final Logger logger = LogManager.getLogger(DispatcherController.class);

    public DispatcherController(TransportTypeService transportTypeService,
                                TransportService transportService,
                                RouteNumberService routeNumberService,
                                StopService stopService,
                                MessageSource messageSource,
                                DBMessageSource dbMessageSource) {
        this.transportTypeService = transportTypeService;
        this.transportService = transportService;
        this.routeNumberService = routeNumberService;
        this.stopService = stopService;
        this.messageSource = messageSource;
        this.dbMessageSource = dbMessageSource;
    }

    @GetMapping("/all")
    public String showDispatcherControl(Model model, Locale locale) {
        model.addAttribute(HEADER_MESSAGE, messageSource.getMessage("headerDisp", null, locale));
        model.addAttribute("transportTypes", transportTypeService.getTypes());
        return "dispatcher/dispatcher";
    }

    @GetMapping("/list")
    public String showTransportList(@RequestParam(name = "type") Integer typeId,
                                    Model model,
                                    Locale locale) {
        TransportTypeDto transportTypeDto;
        List<TransportDto> transports;

        if (typeId == null || typeId <= 0) {
            logger.error(String.format("Incorrect type id %d", typeId));
            return REDIRECT_DISPATCHER;
        }

        try {
            transportTypeDto = transportTypeService.getTransportTypeById(typeId);
        } catch (EntityNotFoundException e) {
            logger.error(String.format("Can't find transport type by id %d", typeId));
            return REDIRECT_DISPATCHER;
        }

        try {
            transports = transportService.getTransportByTransportType(transportTypeDto);
        } catch (EntityNotFoundException e) {
            logger.error(String.format("Can't find transports by type %s", transportTypeDto.getDescription()));
            return REDIRECT_DISPATCHER;
        }

        String message = messageSource.getMessage("headerList", null, locale) + ": ";
        message += dbMessageSource.getMessage("", new Object[]{transportTypeDto}, locale);

        model.addAttribute(HEADER_MESSAGE, message);
        model.addAttribute("transportType", transportTypeDto);
        model.addAttribute("transports", transports);
        return "dispatcher/transport-list";
    }

    @GetMapping("/route")
    public String showTransportRoutes(@RequestParam(name = "type") Integer typeId,
                                      Model model,
                                      Locale locale) {
        TransportTypeDto transportTypeDto;
        List<RouteNumberDto> numbers;

        if (typeId == null || typeId <= 0) {
            logger.error(String.format("Incorrect type id %d", typeId));
            return REDIRECT_DISPATCHER;
        }

        try {
            transportTypeDto = transportTypeService.getTransportTypeById(typeId);
        } catch (EntityNotFoundException e) {
            logger.error(String.format("Can't find transport type by id %d", typeId));
            return REDIRECT_DISPATCHER;
        }

        try {
            numbers = routeNumberService.getRoutes(transportTypeDto);
        } catch (EntityNotFoundException e) {
            logger.error(String.format("Can't find route numbers for type %s", transportTypeDto.getDescription()));
            return REDIRECT_DISPATCHER;
        }

        String message = messageSource.getMessage("headerRoute", null, locale) + ": ";
        message += dbMessageSource.getMessage("", new Object[]{transportTypeDto}, locale);

        model.addAttribute(HEADER_MESSAGE, message);
        model.addAttribute("transportType", transportTypeDto);
        model.addAttribute("routesNumber", numbers);
        return "dispatcher/route-list";
    }

    @GetMapping("/stop")
    public String showStops(Model model, Locale locale) {
        model.addAttribute("stops", stopService.getStops());
        model.addAttribute(HEADER_MESSAGE, messageSource.getMessage("headerStop", null, locale));
        return "dispatcher/stop-list";
    }

    @GetMapping("/number")
    public String showRouteNumbers(Model model, Locale locale) {
        List<RouteNumberDto> numbers = routeNumberService.getRouteNumbers();
        Collections.sort(numbers);
        model.addAttribute("numbers", numbers);
        model.addAttribute(HEADER_MESSAGE, messageSource.getMessage("headerNum", null, locale));
        return "dispatcher/number-list";
    }
}
