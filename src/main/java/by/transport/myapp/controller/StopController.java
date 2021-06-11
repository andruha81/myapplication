package by.transport.myapp.controller;

import by.transport.myapp.service.StopService;
import org.springframework.stereotype.Controller;

@Controller
public class StopController {
    private final StopService stopService;

    public StopController(StopService stopService) {
        this.stopService = stopService;
    }
}
