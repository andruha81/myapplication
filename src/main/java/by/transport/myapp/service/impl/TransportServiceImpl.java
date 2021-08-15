package by.transport.myapp.service.impl;

import by.transport.myapp.dto.TransportDto;
import by.transport.myapp.mapper.TransportMapper;
import by.transport.myapp.model.dao.RouteNumberDao;
import by.transport.myapp.model.dao.TransportDao;
import by.transport.myapp.model.dao.TransportTypeDao;
import by.transport.myapp.model.entity.RouteNumber;
import by.transport.myapp.model.entity.TransportType;
import by.transport.myapp.service.TransportService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransportServiceImpl implements TransportService {
    private final TransportDao transportDao;
    private final TransportTypeDao transportTypeDao;
    private final RouteNumberDao routeNumberDao;
    private final TransportMapper mapper = Mappers.getMapper(TransportMapper.class);
    private final Logger logger = LogManager.getLogger(TransportServiceImpl.class);

    public TransportServiceImpl(TransportDao transportDao, TransportTypeDao transportTypeDao, RouteNumberDao routeNumberDao) {
        logger.info("TransportServiceImpl");
        this.transportDao = transportDao;
        this.transportTypeDao = transportTypeDao;
        this.routeNumberDao = routeNumberDao;
    }

    @Override
    public List<TransportDto> getTransportByTransportType(Integer typeId) {
        logger.info("TransportServiceImpl");
        TransportType transportType = transportTypeDao.getById(typeId);
        return transportDao.findTransportByTransportType(transportType)
                .stream()
                .map(mapper::transportToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Integer save(TransportDto transportDto) {
        logger.info("TransportServiceImpl");
        TransportType transportType = transportTypeDao.findTransportTypeByDescription(transportDto.getType());
        RouteNumber routeNumber = routeNumberDao.getRouteNumberByNumber(transportDto.getRoute());
        return transportDao.save(mapper.dtoToTransport(transportDto, transportType, routeNumber)).getId();
    }

    @Override
    public TransportDto getTransportById(Integer id) {
        logger.info("TransportServiceImpl");
        return mapper.transportToDto(transportDao.getById(id));
    }
}
