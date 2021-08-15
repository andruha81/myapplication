package by.transport.myapp.service.impl;

import by.transport.myapp.dto.RouteNumberDto;
import by.transport.myapp.mapper.RouteNumberMapper;
import by.transport.myapp.model.dao.RouteNumberDao;
import by.transport.myapp.model.dao.TransportTypeDao;
import by.transport.myapp.model.entity.RouteNumber;
import by.transport.myapp.model.entity.TransportType;
import by.transport.myapp.service.RouteNumberService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RouteNumberServiceImpl implements RouteNumberService {
    private final RouteNumberDao routeNumberDao;
    private final TransportTypeDao transportTypeDao;
    private final RouteNumberMapper routeNumberMapper = Mappers.getMapper(RouteNumberMapper.class);
    private final Logger logger = LogManager.getLogger(RouteNumberServiceImpl.class);

    public RouteNumberServiceImpl(RouteNumberDao routeNumberDao, TransportTypeDao transportTypeDao) {
        this.routeNumberDao = routeNumberDao;
        this.transportTypeDao = transportTypeDao;
    }

    @Override
    public List<Integer> getRouteNumbersByType(Integer transportTypeId) throws EntityNotFoundException {
        TransportType type = transportTypeDao.getById(transportTypeId);
        logger.info(String.format("Get transport type %s by id %d", type.getDescription(), transportTypeId));
        List<RouteNumber> numbers = routeNumberDao.getRouteNumbersByType(type);
        if (!numbers.isEmpty()) {
            logger.info(String.format("Get route numbers for transport type %s", type.getDescription()));
        } else {
            logger.error(String.format("Can't get route numbers for transport type %s", type.getDescription()));
        }

        return numbers.stream()
                .map(RouteNumber::getNumber)
                .collect(Collectors.toList());
    }

    @Override
    public List<RouteNumberDto> getRoutes(Integer transportTypeId) {
        var transportType = transportTypeDao.getById(transportTypeId);
        logger.info(String.format("Get transport type %s by id %d", transportType.getDescription(), transportTypeId));
        List<RouteNumber> numbers = routeNumberDao.getRouteNumbersByType(transportType);
        if (!numbers.isEmpty()) {
            logger.info(String.format("Get route numbers for transport type %s", transportType.getDescription()));
        } else {
            logger.error(String.format("Can't get route numbers for transport type %s", transportType.getDescription()));
        }
        return numbers.stream()
                .map(routeNumberMapper::routeNumberToDto)
                .collect(Collectors.toList());
    }

    @Override
    public RouteNumber getRouteNumberByNumber(int number) {
        return routeNumberDao.getRouteNumberByNumber(number);
    }
}
