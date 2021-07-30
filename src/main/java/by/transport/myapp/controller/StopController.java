package by.transport.myapp.controller;

import by.transport.myapp.service.RouteLineService;
import by.transport.myapp.service.StopService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stop")
public class StopController {
    private final StopService stopService;
    private final RouteLineService routeLineService;

    public StopController(StopService stopService, RouteLineService routeLineService) {
        this.stopService = stopService;
        this.routeLineService = routeLineService;
    }

    @GetMapping("/all")
    public String showStops(Model model) {
        model.addAttribute("headerMessage", "Остановки");
        model.addAttribute("stops", stopService.getStops());
        return "stop/stop";
    }

    @GetMapping("/{stopId}")
    public String showStopDetail(@PathVariable Integer stopId, Model model) {
        model.addAttribute("stopN", stopService.getStopById(stopId).getName());
        model.addAttribute("stopDetail", routeLineService.getStopDetails(stopId));
        return "stop/stop::stopDetail";
    }
}
