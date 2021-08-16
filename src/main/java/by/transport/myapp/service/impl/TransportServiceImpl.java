package by.transport.myapp.service.impl;

import by.transport.myapp.dto.TransportDto;
import by.transport.myapp.dto.TransportTypeDto;
import by.transport.myapp.mapper.TransportMapper;
import by.transport.myapp.mapper.TransportTypeMapper;
import by.transport.myapp.model.dao.RouteNumberDao;
import by.transport.myapp.model.dao.TransportDao;
import by.transport.myapp.model.entity.RouteNumber;
import by.transport.myapp.model.entity.Transport;
import by.transport.myapp.model.entity.TransportType;
import by.transport.myapp.service.TransportService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransportServiceImpl implements TransportService {
    private final TransportDao transportDao;
    private final RouteNumberDao routeNumberDao;
    private final TransportMapper mapper = Mappers.getMapper(TransportMapper.class);
    private final TransportTypeMapper typeMapper = Mappers.getMapper(TransportTypeMapper.class);
    private final Logger logger = LogManager.getLogger(TransportServiceImpl.class);

    public TransportServiceImpl(TransportDao transportDao,
                                RouteNumberDao routeNumberDao) {
        this.transportDao = transportDao;
        this.routeNumberDao = routeNumberDao;
    }

    @Override
    public List<TransportDto> getTransportByTransportType(TransportTypeDto transportTypeDto) {
        TransportType transportType = typeMapper.dtoToTransportType(transportTypeDto);
        List<Transport> transports = transportDao.findTransportByTransportType(transportType);
        if (!transports.isEmpty()) {
            logger.info(String.format("Got transports for type %s", transportType.getDescription()));
        } else {
            logger.error(String.format("Didn't get transports for type %s", transportType.getDescription()));
        }

        return transports.stream()
                .map(mapper::transportToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Integer save(TransportDto transportDto, TransportTypeDto typeDto) throws EntityNotFoundException {
        TransportType transportType = typeMapper.dtoToTransportType(typeDto);
        RouteNumber routeNumber = routeNumberDao.getRouteNumberByNumber(transportDto.getRoute());
        logger.info(String.format("Got route number %d by number %d", routeNumber.getNumber(), transportDto.getRoute()));
        return transportDao.save(mapper.dtoToTransport(transportDto, transportType, routeNumber)).getId();
    }

    @Override
    public TransportDto getTransportById(Integer id) throws EntityNotFoundException {
        Transport transport = transportDao.getById(id);
        logger.info(String.format("Got transport %s by id %d", transport.getModel(), id));
        return mapper.transportToDto(transport);
    }
}
