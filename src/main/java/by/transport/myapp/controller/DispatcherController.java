package by.transport.myapp.controller;

import by.transport.myapp.dto.TransportTypeDto;
import by.transport.myapp.service.RouteNumberService;
import by.transport.myapp.service.StopService;
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
    private final StopService stopService;
    private static final String HEADER_MESSAGE = "headerMessage";

    public DispatcherController(TransportTypeService transportTypeService,
                                TransportService transportService,
                                RouteNumberService routeNumberService,
                                StopService stopService) {
        this.transportTypeService = transportTypeService;
        this.transportService = transportService;
        this.routeNumberService = routeNumberService;
        this.stopService = stopService;
    }

    @GetMapping("/all")
    public String showDispatcherControl(Model model) {
        model.addAttribute(HEADER_MESSAGE, "Диспетчер");
        model.addAttribute("transportTypes", transportTypeService.getTypes());
        return "dispatcher/dispatcher";
    }

    @GetMapping("/list")
    public String showTransportList(@RequestParam(name = "type") Integer typeId, Model model) {
        TransportTypeDto transportType = transportTypeService.getTransportTypeById(typeId);
        model.addAttribute(HEADER_MESSAGE, "Список: " + transportType.getDescription());
        model.addAttribute("transportType", transportType);
        model.addAttribute("transports", transportService.getTransportByTransportType(typeId));
        return "dispatcher/transport-list";
    }

    @GetMapping("/route")
    public String showTransportRoutes(@RequestParam(name = "type") Integer typeId, Model model) {
        TransportTypeDto transportType = transportTypeService.getTransportTypeById(typeId);
        model.addAttribute(HEADER_MESSAGE, "Маршруты: " + transportType.getDescription());
        model.addAttribute("transportType", transportType);
        model.addAttribute("routesNumber", routeNumberService.getRoutes(typeId));
        return "dispatcher/route-list";
    }

    @GetMapping("/stop")
    public String showStops(Model model) {
        model.addAttribute("stops", stopService.getStops());
        model.addAttribute(HEADER_MESSAGE, "Остановки");
        return "dispatcher/stop-list";
    }
}
