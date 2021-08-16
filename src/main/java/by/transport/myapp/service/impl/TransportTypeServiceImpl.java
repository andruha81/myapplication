package by.transport.myapp.service.impl;

import by.transport.myapp.dto.TransportTypeDto;
import by.transport.myapp.mapper.TransportTypeMapper;
import by.transport.myapp.model.dao.TransportTypeDao;
import by.transport.myapp.model.entity.TransportType;
import by.transport.myapp.service.TransportTypeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransportTypeServiceImpl implements TransportTypeService {
    private final TransportTypeDao transportTypeDao;
    private final TransportTypeMapper mapper = Mappers.getMapper(TransportTypeMapper.class);
    private final Logger logger = LogManager.getLogger(TransportTypeServiceImpl.class);

    public TransportTypeServiceImpl(TransportTypeDao transportTypeDao) {
        this.transportTypeDao = transportTypeDao;
    }

    @Override
    public List<TransportTypeDto> getTypes() {
        List<TransportType> transportTypes = transportTypeDao.findAll();
        if (!transportTypes.isEmpty()) {
            logger.info("Got transport types");
        } else {
            logger.error("Didn't get transport types");
        }

        return transportTypes.stream()
                .map(mapper::transportTypeToDto)
                .collect(Collectors.toList());
    }

    @Override
    public String getTypeDescription(Integer id) throws EntityNotFoundException {
        TransportType transportType = transportTypeDao.getById(id);
        logger.info(String.format("Get transport type %s by id %d", transportType.getDescription(), id));
        return transportType.getDescription();
    }

    @Override
    public TransportTypeDto getTransportTypeById(Integer id) throws EntityNotFoundException {
        TransportType transportType = transportTypeDao.getById(id);
        logger.info(String.format("Get transport type %s by id %d", transportType.getDescription(), id));
        return mapper.transportTypeToDto(transportType);
    }

    @Override
    public TransportTypeDto getTransportTypeByDescription(String description) throws EntityNotFoundException {
        TransportType transportType = transportTypeDao.findTransportTypeByDescription(description);
        logger.info(String.format("Got transport type %s by description %s", transportType.getDescription(), description));
        return mapper.transportTypeToDto(transportType);
    }
}
