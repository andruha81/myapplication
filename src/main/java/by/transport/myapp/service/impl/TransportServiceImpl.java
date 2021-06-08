package by.transport.myapp.service.impl;

import by.transport.myapp.model.dao.TransportDao;
import by.transport.myapp.service.TransportService;
import org.springframework.stereotype.Service;

@Service
public class TransportServiceImpl implements TransportService {
    private final TransportDao transportDao;

    public TransportServiceImpl(TransportDao transportDao) {
        this.transportDao = transportDao;
    }
}
