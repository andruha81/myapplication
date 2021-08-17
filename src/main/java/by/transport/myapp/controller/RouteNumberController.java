package by.transport.myapp.controller;

import by.transport.myapp.dto.RouteNumberDto;
import by.transport.myapp.dto.TransportTypeDto;
import by.transport.myapp.model.entity.RouteNumber;
import by.transport.myapp.service.RouteNumberService;
import by.transport.myapp.service.TransportTypeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@Controller
@RequestMapping("/routeNumber")
public class RouteNumberController {
    private final RouteNumberService routeNumberService;
    private final TransportTypeService typeService;
    private final Logger logger = LogManager.getLogger(RouteNumberController.class);
    private static final String HEADER_MESSAGE = "headerMessage";
    private static final String REDIRECT_DISPATCHER = "redirect:/dispatcher/all";
    private static final String NUMBER_PARAMETERS = "routeNumber/number-parameters";
    private static final String TYPES = "types";

    public RouteNumberController(RouteNumberService routeNumberService,
                                 TransportTypeService typeService) {
        this.routeNumberService = routeNumberService;
        this.typeService = typeService;
    }

    @GetMapping("/edit")
    public String showRouteNumberParameters(@RequestParam(name = "id") Integer routeNumberId,
                                            Model model) {
        RouteNumberDto routeNumberDto;

        if (routeNumberId == null || routeNumberId <= 0) {
            logger.error(String.format("Incorrect stop id %d", routeNumberId));
            return REDIRECT_DISPATCHER;
        }

        try {
            routeNumberDto = routeNumberService.getRouteNumberById(routeNumberId);
        } catch (EntityNotFoundException e) {
            logger.error(String.format("Can't find route number by id %d", routeNumberId));
            return REDIRECT_DISPATCHER;
        }

        model.addAttribute(HEADER_MESSAGE, "Редактирование номера маршрута");
        model.addAttribute("routeNumber", routeNumberDto);
        model.addAttribute(TYPES, typeService.getTypes());

        return NUMBER_PARAMETERS;
    }

    @GetMapping("/add")
    public String showNewRouteNumberParameters(Model model) {
        model.addAttribute(HEADER_MESSAGE, "Редактирование номера маршрута");
        model.addAttribute("routeNumber", new RouteNumberDto());
        model.addAttribute(TYPES, typeService.getTypes());

        return NUMBER_PARAMETERS;
    }

    @PostMapping("/save")
    public String saveRouteNumber(@ModelAttribute("routeNumber") RouteNumberDto routeNumberDto,
                                  BindingResult bindingResult,
                                  Model model) {
        TransportTypeDto transportTypeDto;

        RouteNumber routeNumber = routeNumberService.getRouteNumberByNumber(routeNumberDto.getNumber());
        if (routeNumber != null && !routeNumber.getId().equals(routeNumberDto.getRouteNumberDtoId())) {
            bindingResult.rejectValue("number", "error.routeNumber", "There is route number with this number");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute(TYPES, typeService.getTypes());
            return NUMBER_PARAMETERS;
        }

        try {
            transportTypeDto = typeService.getTransportTypeByDescription(routeNumberDto.getTransportType());
        } catch (EntityNotFoundException e) {
            logger.error(String.format("Can't find transport type by description %s", routeNumberDto.getTransportType()));
            return REDIRECT_DISPATCHER;
        }

        if (routeNumberService.save(routeNumberDto, transportTypeDto) == null) {
            logger.error(String.format("Didn't save route number %s to database", routeNumberDto.getNumber()));
        } else {
            logger.info(String.format("Saved route number %s to database", routeNumberDto.getNumber()));
        }

        return "redirect:/dispatcher/number";
    }
}
