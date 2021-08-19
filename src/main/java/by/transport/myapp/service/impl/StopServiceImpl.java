package by.transport.myapp.service.impl;

import by.transport.myapp.dto.StopDto;
import by.transport.myapp.mapper.StopMapper;
import by.transport.myapp.model.dao.StopDao;
import by.transport.myapp.model.entity.Stop;
import by.transport.myapp.service.StopService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StopServiceImpl implements StopService {
    private final StopDao stopDao;
    private final StopMapper stopMapper = Mappers.getMapper(StopMapper.class);
    private final Logger logger = LogManager.getLogger(StopServiceImpl.class);

    public StopServiceImpl(StopDao stopDao) {
        this.stopDao = stopDao;
    }

    @Override
    public StopDto getStopById(Integer id) {
        Stop stop = stopDao.getById(id);
        logger.info(String.format("Find stop %s by id %d", stop.getName(), id));
        return stopMapper.stopToDto(stop);
    }

    @Override
    public List<StopDto> getStops() {
        List<StopDto> stops = stopDao.findAll()
                .stream()
                .map(stopMapper::stopToDto)
                .sorted(Comparator.comparing(StopDto::getName))
                .collect(Collectors.toList());
        if (!stops.isEmpty()) {
            logger.info("Got stops");
        } else {
            logger.error("Didn't get stops");
        }
        return stops;
    }

    @Override
    @Transactional
    public Integer save(StopDto stopDto) {
        Integer id = stopDao.save(stopMapper.stopDtoToStop(stopDto)).getId();
        if (id == null) {
            logger.error(String.format("Didn't save stop %s to database", stopDto.getName()));
        } else {
            logger.info(String.format("Saved stop %s to database", stopDto.getName()));
        }
        return id;
    }
}
