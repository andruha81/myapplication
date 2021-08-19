package by.transport.myapp.controller;

import by.transport.myapp.dto.RouteNumberDto;
import by.transport.myapp.dto.TransportTypeDto;
import by.transport.myapp.model.entity.RouteNumber;
import by.transport.myapp.service.RouteNumberService;
import by.transport.myapp.service.TransportTypeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@Controller
@RequestMapping("/routeNumber")
public class RouteNumberController {
    private final RouteNumberService routeNumberService;
    private final TransportTypeService typeService;
    private final MessageSource messageSource;
    private final Logger logger = LogManager.getLogger(RouteNumberController.class);

    private static final String HEADER_MESSAGE = "headerMessage";
    private static final String REDIRECT_DISPATCHER = "redirect:/dispatcher/all";
    private static final String NUMBER_PARAMETERS = "routeNumber/number-parameters";
    private static final String TYPES = "types";

    public RouteNumberController(RouteNumberService routeNumberService,
                                 TransportTypeService typeService,
                                 MessageSource messageSource) {
        this.routeNumberService = routeNumberService;
        this.typeService = typeService;
        this.messageSource = messageSource;
    }

    @GetMapping("/edit")
    public String showRouteNumberParameters(@RequestParam(name = "id") Integer routeNumberId,
                                            Model model,
                                            Locale locale) {
        if (routeNumberId == null || routeNumberId <= 0) {
            logger.error(String.format("Incorrect stop id %d", routeNumberId));
            return REDIRECT_DISPATCHER;
        }

        RouteNumberDto routeNumberDto = routeNumberService.getRouteNumberById(routeNumberId);

        model.addAttribute(HEADER_MESSAGE, messageSource.getMessage("headerEditNumber", null, locale));
        model.addAttribute("routeNumber", routeNumberDto);
        model.addAttribute(TYPES, typeService.getTypes());

        return NUMBER_PARAMETERS;
    }

    @GetMapping("/add")
    public String showNewRouteNumberParameters(Model model, Locale locale) {
        model.addAttribute(HEADER_MESSAGE, messageSource.getMessage("headerAddNumber", null, locale));
        model.addAttribute("routeNumber", new RouteNumberDto());
        model.addAttribute(TYPES, typeService.getTypes());

        return NUMBER_PARAMETERS;
    }

    @PostMapping("/save")
    public String saveRouteNumber(@ModelAttribute("routeNumber") RouteNumberDto routeNumberDto,
                                  BindingResult bindingResult,
                                  Model model) {
        RouteNumber existRouteNumber = routeNumberService.getRouteNumberByNumber(routeNumberDto.getNumber());
        if (existRouteNumber != null && !existRouteNumber.getId().equals(routeNumberDto.getRouteNumberDtoId())) {
            bindingResult.rejectValue("number", "error.routeNumber", "There is route number with this number");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute(TYPES, typeService.getTypes());
            return NUMBER_PARAMETERS;
        }

        TransportTypeDto transportTypeDto = typeService.getTransportTypeByDescription(routeNumberDto.getTransportType());
        routeNumberService.save(routeNumberDto, transportTypeDto);

        return "redirect:/dispatcher/number";
    }
}
