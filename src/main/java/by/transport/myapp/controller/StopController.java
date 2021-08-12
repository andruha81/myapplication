package by.transport.myapp.controller;

import by.transport.myapp.dto.StopDto;
import by.transport.myapp.service.RouteLineService;
import by.transport.myapp.service.StopService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/stop")
public class StopController {
    private final StopService stopService;
    private final RouteLineService routeLineService;
    private static final String HEADER_MESSAGE = "headerMessage";
    private static final String STOP_PARAMETERS = "stop/stop-parameters";

    public StopController(StopService stopService, RouteLineService routeLineService) {
        this.stopService = stopService;
        this.routeLineService = routeLineService;
    }

    @GetMapping("/all")
    public String showStops(Model model) {
        model.addAttribute(HEADER_MESSAGE, "Остановки");
        model.addAttribute("stops", stopService.getStops());
        return "stop/stop";
    }

    @GetMapping("/{stopId}")
    public String showStopDetail(@PathVariable Integer stopId, Model model) {
        model.addAttribute("stopN", stopService.getStopById(stopId).getName());
        model.addAttribute("stopDetail", routeLineService.getStopDetails(stopId));
        return "stop/stop::stopDetail";
    }

    @GetMapping("/edit")
    public String showStopParameters(@RequestParam(name = "id") Integer stopId,
                                     Model model) {
        model.addAttribute(HEADER_MESSAGE, "Редактирование остановки");
        model.addAttribute("stop", stopService.getStopById(stopId));
        return STOP_PARAMETERS;
    }

    @GetMapping("/add")
    public String addStop(Model model) {
        model.addAttribute(HEADER_MESSAGE, "Создание остановки");
        model.addAttribute("stop", new StopDto());
        return STOP_PARAMETERS;
    }

    @PostMapping("/save")
    @Transactional
    public String saveStop(@ModelAttribute("stop") @Valid StopDto stopDto,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return STOP_PARAMETERS;
        }
        stopService.save(stopDto);
        return "redirect:/dispatcher/stop";
    }
}
