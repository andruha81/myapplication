package by.transport.myapp.controller;

import by.transport.myapp.service.TransportService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/transport")
public class TransportController {
    private final TransportService transportService;

    public TransportController(TransportService transportService) {
        this.transportService = transportService;
    }

    @GetMapping("/bus")
    public String viesBuses(Model model) {
        model.addAttribute("buses", transportService.getBuses());
        return "view-buses";
    }
}
