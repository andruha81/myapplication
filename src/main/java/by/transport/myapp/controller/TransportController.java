package by.transport.myapp.controller;

import by.transport.myapp.dto.TransportDto;
import by.transport.myapp.dto.TransportTypeDto;
import by.transport.myapp.service.RouteNumberService;
import by.transport.myapp.service.TransportService;
import by.transport.myapp.service.TransportTypeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Locale;

@RequestMapping(value = "/transport")
@Controller
public class TransportController {
    private final TransportService transportService;
    private final TransportTypeService transportTypeService;
    private final RouteNumberService routeNumberService;
    private final MessageSource messageSource;

    private static final String ROUTE_NUMBER = "routeNumbers";
    private static final String REDIRECT_DISPATCHER = "redirect:/dispatcher/all";
    private final Logger logger = LogManager.getLogger(TransportController.class);

    public TransportController(TransportService transportService,
                               TransportTypeService transportTypeService,
                               RouteNumberService routeNumberService,
                               MessageSource messageSource) {
        this.transportService = transportService;
        this.transportTypeService = transportTypeService;
        this.routeNumberService = routeNumberService;
        this.messageSource = messageSource;
    }

    @GetMapping(value = "/add")
    public String addTransportForm(@RequestParam(name = "type") Integer typeId,
                                   Model model,
                                   Locale locale) {
        if (typeId == null || typeId <= 0) {
            logger.error(String.format("Incorrect type id %d", typeId));
            return REDIRECT_DISPATCHER;
        }

        TransportTypeDto transportTypeDto = transportTypeService.getTransportTypeById(typeId);

        model.addAttribute("headerMessage", messageSource.getMessage("headerAddTransport", null, locale));
        model.addAttribute("transportType", transportTypeDto);
        model.addAttribute(ROUTE_NUMBER, routeNumberService.getRouteNumbersByType(transportTypeDto));
        model.addAttribute("transport", new TransportDto());

        return "transport/new-transport";
    }

    @GetMapping(value = "/edit")
    public String editTransportForm(@RequestParam(name = "id") Integer transportId,
                                    @RequestParam(name = "type") Integer typeId,
                                    Model model,
                                    Locale locale) {
        if (typeId == null || typeId <= 0) {
            logger.error(String.format("Incorrect type id %d", typeId));
            return REDIRECT_DISPATCHER;
        }

        if (transportId == null || transportId <= 0) {
            logger.error(String.format("Incorrect transport id %d", transportId));
            return REDIRECT_DISPATCHER;
        }

        TransportTypeDto transportTypeDto = transportTypeService.getTransportTypeById(typeId);
        TransportDto transportDto = transportService.getTransportById(transportId);

        model.addAttribute("headerMessage", messageSource.getMessage("headerEditTransport", null, locale));
        model.addAttribute("transport", transportDto);
        model.addAttribute(ROUTE_NUMBER, routeNumberService.getRouteNumbersByType(transportTypeDto));

        return "transport/edit-transport";
    }

    @PostMapping(value = "/add")
    public String addTransport(@ModelAttribute("transport") @Valid TransportDto transportDto,
                               BindingResult bindingResult,
                               Model model) {
        TransportTypeDto typeDto = transportTypeService.getTransportTypeByDescription(transportDto.getType());
        if (bindingResult.hasErrors()) {
            model.addAttribute(ROUTE_NUMBER, routeNumberService.getRouteNumbersByType(typeDto));
            if (transportDto.getTransportDtoId() != null) {
                return "transport/edit-transport";
            }
            model.addAttribute("transportType", typeDto);
            return "transport/new-transport";
        }

        transportService.save(transportDto, typeDto);

        return "redirect:/dispatcher/list?type=" + typeDto.getTransportTypeDtoId();
    }
}
