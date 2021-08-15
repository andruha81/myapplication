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

import javax.persistence.EntityNotFoundException;
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
    public StopDto getStopById(Integer id) throws EntityNotFoundException {
        Stop stop = stopDao.getById(id);
        logger.info(String.format("Find stop %s by id %d", stop.getName(), id));
        return stopMapper.stopToDto(stop);
    }

    @Override
    public Stop getStop(Integer id) {
        logger.info("StopServiceImpl2");
        return stopDao.getById(id);
    }

    @Override
    public List<StopDto> getStops() {
        List<StopDto> stops = stopDao.findAll()
                .stream()
                .map(stopMapper::stopToDto)
                .sorted(Comparator.comparing(StopDto::getName))
                .collect(Collectors.toList());
        if (!stops.isEmpty()) {
            logger.info("Get stops from database");
        } else {
            logger.error("There are none stops in database");
        }
        return stops;
    }

    @Override
    @Transactional
    public Integer save(StopDto stopDto) {
        return stopDao.save(stopMapper.stopDtoToStop(stopDto)).getId();
    }
}
