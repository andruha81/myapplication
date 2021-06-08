package by.transport.myapp.service.impl;

import by.transport.myapp.model.dao.StopDao;
import by.transport.myapp.service.StopService;
import org.springframework.stereotype.Service;

@Service
public class StopServiceImpl implements StopService {
    private final StopDao stopDao;

    public StopServiceImpl(StopDao stopDao) {
        this.stopDao = stopDao;
    }
}
