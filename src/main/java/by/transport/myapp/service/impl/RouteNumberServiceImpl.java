package by.transport.myapp.service.impl;

import by.transport.myapp.dto.RouteNumberDto;
import by.transport.myapp.dto.TransportTypeDto;
import by.transport.myapp.mapper.RouteNumberMapper;
import by.transport.myapp.mapper.TransportTypeMapper;
import by.transport.myapp.model.dao.RouteNumberDao;
import by.transport.myapp.model.entity.RouteNumber;
import by.transport.myapp.model.entity.TransportType;
import by.transport.myapp.service.RouteNumberService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RouteNumberServiceImpl implements RouteNumberService {
    private final RouteNumberDao routeNumberDao;
    private final RouteNumberMapper routeNumberMapper = Mappers.getMapper(RouteNumberMapper.class);
    private final TransportTypeMapper typeMapper = Mappers.getMapper(TransportTypeMapper.class);
    private final Logger logger = LogManager.getLogger(RouteNumberServiceImpl.class);

    public RouteNumberServiceImpl(RouteNumberDao routeNumberDao) {
        this.routeNumberDao = routeNumberDao;
    }

    @Override
    public List<Integer> getRouteNumbersByType(TransportTypeDto typeDto) {
        var transportType = typeMapper.dtoToTransportType(typeDto);
        List<RouteNumber> numbers = getNumbers(transportType);

        return numbers.stream()
                .map(RouteNumber::getNumber)
                .collect(Collectors.toList());
    }

    @Override
    public List<RouteNumberDto> getRoutes(TransportTypeDto typeDto) {
        var transportType = typeMapper.dtoToTransportType(typeDto);
        List<RouteNumber> numbers = getNumbers(transportType);

        return numbers.stream()
                .map(routeNumberMapper::routeNumberToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RouteNumberDto> getRouteNumbers() {
        List<RouteNumber> numbers = routeNumberDao.findAll();
        if (!numbers.isEmpty()) {
            logger.info("Got route numbers");
        } else {
            logger.error("Didn't get route numbers");
        }
        return numbers.stream()
                .map(routeNumberMapper::routeNumberToDto)
                .collect(Collectors.toList());
    }

    @Override
    public RouteNumber getRouteNumberByNumber(int number) {
        RouteNumber routeNumber = routeNumberDao.getRouteNumberByNumber(number);
        logger.info(String.format("Got route number %d by number %d", routeNumber.getNumber(), number));
        return routeNumber;
    }

    @Override
    public Integer save(RouteNumberDto routeNumberDto, TransportTypeDto transportTypeDto) {
        TransportType type = typeMapper.dtoToTransportType(transportTypeDto);
        Integer id = routeNumberDao.save(routeNumberMapper.dtoToRouteNumber(routeNumberDto, type)).getId();
        if (id == null) {
            logger.error(String.format("Didn't save route number %s to database", routeNumberDto.getNumber()));
        } else {
            logger.info(String.format("Saved route number %s to database", routeNumberDto.getNumber()));
        }
        return id;
    }

    @Override
    public RouteNumberDto getRouteNumberById(Integer routeNumberId) {
        RouteNumber routeNumber = routeNumberDao.getById(routeNumberId);
        logger.info(String.format("Got route number %d by id %d", routeNumber.getNumber(), routeNumberId));
        return routeNumberMapper.routeNumberToDto(routeNumber);
    }

    private List<RouteNumber> getNumbers(TransportType transportType) {
        List<RouteNumber> numbers = routeNumberDao.getRouteNumbersByType(transportType);
        if (!numbers.isEmpty()) {
            logger.info(String.format("Got route numbers for transport type %s", transportType.getDescription()));
        } else {
            logger.error(String.format("Didn't get route numbers for transport type %s", transportType.getDescription()));
        }
        return numbers;
    }
}
