package by.transport.myapp.service.impl;

import by.transport.myapp.dto.StopDto;
import by.transport.myapp.mapper.StopMapper;
import by.transport.myapp.model.dao.StopDao;
import by.transport.myapp.service.StopService;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Service
public class StopServiceImpl implements StopService {
    private final StopDao stopDao;
    private final StopMapper stopMapper = Mappers.getMapper(StopMapper.class);

    public StopServiceImpl(StopDao stopDao) {
        this.stopDao = stopDao;
    }

    @Override
    public StopDto getStopById(Integer id) {
        return stopMapper.stopToDto(stopDao.getById(id));
    }
}
