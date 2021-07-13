package by.transport.myapp.service.impl;

import by.transport.myapp.dto.StopDto;
import by.transport.myapp.mapper.StopMapper;
import by.transport.myapp.model.dao.StopDao;
import by.transport.myapp.service.StopService;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<StopDto> getStops() {
        return stopDao.findAll().stream().map(stopMapper::stopToDto).sorted(Comparator.comparing(StopDto::getName)).collect(Collectors.toList());
    }
}
