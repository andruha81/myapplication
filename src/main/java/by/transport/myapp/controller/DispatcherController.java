package by.transport.myapp.controller;

import by.transport.myapp.dto.TransportTypeDto;
import by.transport.myapp.service.RouteNumberService;
import by.transport.myapp.service.TransportService;
import by.transport.myapp.service.TransportTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/dispatcher")
public class DispatcherController {
    private final TransportTypeService transportTypeService;
    private final TransportService transportService;
    private final RouteNumberService routeNumberService;

    public DispatcherController(TransportTypeService transportTypeService,
                                TransportService transportService,
                                RouteNumberService routeNumberService) {
        this.transportTypeService = transportTypeService;
        this.transportService = transportService;
        this.routeNumberService = routeNumberService;
    }

    @GetMapping("/all")
    public String showDispatcherControl(Model model) {
        model.addAttribute("headerMessage", "Диспетчер");
        model.addAttribute("transportTypes", transportTypeService.getTypes());
        return "dispatcher";
    }

    @GetMapping("/list")
    public String showTransportList(@RequestParam(name = "type") Integer typeId, Model model) {
        TransportTypeDto transportType = transportTypeService.getTransportTypeById(typeId);
        model.addAttribute("headerMessage", "Список: " + transportType.getDescription());
        model.addAttribute("transportType", transportType);
        model.addAttribute("transports", transportService.getTransportByTransportType(typeId));
        return "transport-list";
    }

    @GetMapping("/route")
    public String showTransportRoutes(@RequestParam(name = "type") Integer typeId, Model model) {
        TransportTypeDto transportType = transportTypeService.getTransportTypeById(typeId);
        model.addAttribute("headerMessage", "Маршруты: " + transportType.getDescription());
        model.addAttribute("transportType", transportType);
        model.addAttribute("routesNumber", routeNumberService.getRoutes(typeId));
        return "route-list";
    }
}
