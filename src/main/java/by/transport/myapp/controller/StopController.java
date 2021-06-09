package by.transport.myapp.controller;

import by.transport.myapp.service.StopService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StopController {
    private final StopService stopService;

    public StopController(StopService stopService) {
        this.stopService = stopService;
    }
}
