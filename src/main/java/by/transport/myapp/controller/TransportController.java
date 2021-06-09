package by.transport.myapp.controller;

import by.transport.myapp.service.TransportService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransportController {
    private final TransportService transportService;

    public TransportController(TransportService transportService) {
        this.transportService = transportService;
    }
}
