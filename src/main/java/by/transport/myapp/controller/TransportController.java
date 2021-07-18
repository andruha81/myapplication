package by.transport.myapp.controller;

import by.transport.myapp.dto.TransportDto;
import by.transport.myapp.service.RouteNumberService;
import by.transport.myapp.service.TransportService;
import by.transport.myapp.service.TransportTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/transport")
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

    @GetMapping(value = "/add")
    public String addTransportForm(@RequestParam(name = "type") Integer typeId, Model model) {
        model.addAttribute("headerMessage", "Добавление транспорта");
        model.addAttribute("transportType", transportTypeService.getTransportTypeById(typeId));
        model.addAttribute("routeNumbers", routeNumberService.getRouteNumbersByType(typeId));
        model.addAttribute("transport", new TransportDto());
        return "new-transport";
    }

    @GetMapping(value = "/edit")
    public String editTransportForm(@RequestParam(name = "id") Integer transportId,
                                    @RequestParam(name = "type") Integer typeId,
                                    Model model) {
        model.addAttribute("headerMessage", "Редактирование транспорта");
        model.addAttribute("transport", transportService.getTransportById(transportId));
        model.addAttribute("routeNumbers", routeNumberService.getRouteNumbersByType(typeId));
        return "edit-transport";
    }

    @PostMapping(value = "/add")
    @Transactional
    public String addTransport(@ModelAttribute("transport") TransportDto transportDto,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "index";
        }
        transportService.save(transportDto);
        return "redirect:/dispatcher/list?type=" + transportTypeService.getTransportTypeByDescription(transportDto.getType()).getId();
    }
}
