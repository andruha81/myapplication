package by.transport.myapp.controller;

import by.transport.myapp.dto.TransportDto;
import by.transport.myapp.service.RouteNumberService;
import by.transport.myapp.service.TransportService;
import by.transport.myapp.service.TransportTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TransportController {
    private final TransportService transportService;
    private final TransportTypeService transportTypeService;
    private final RouteNumberService routeNumberService;

    public TransportController(TransportService transportService,
                               TransportTypeService transportTypeService,
                               RouteNumberService routeNumberService) {
        this.transportService = transportService;
        this.transportTypeService = transportTypeService;
        this.routeNumberService = routeNumberService;
    }

    @RequestMapping(value = "/transport/bus", method = RequestMethod.GET)
    public String viewBuses(Model model) {
        model.addAttribute("buses", transportService.getBuses());
        return "jsp/view-buses";
    }

    @RequestMapping(value = "/transport/addTransportForm", method = RequestMethod.GET)
    public ModelAndView addTransportForm(Model model) {
        model.addAttribute("types", transportTypeService.getTypes());
        model.addAttribute("routes", routeNumberService.getRouteNumbers());
        return new ModelAndView("jsp/add-transport","transport", new TransportDto());
    }

    @RequestMapping(value = "/transport/add", method = RequestMethod.POST)
    public String addTransport(@ModelAttribute("transport")TransportDto transportDto) {
        transportService.save(transportDto);
        return "index";
    }
}
