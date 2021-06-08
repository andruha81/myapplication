package by.transport.myapp.service.impl;

import by.transport.myapp.model.dao.TransportTypeDao;
import by.transport.myapp.service.TransportTypeService;
import org.springframework.stereotype.Service;

@Service
public class TransportTypeServiceImpl implements TransportTypeService {
    private final TransportTypeDao transportTypeDao;

    public TransportTypeServiceImpl(TransportTypeDao transportTypeDao) {
        this.transportTypeDao = transportTypeDao;
    }
}
