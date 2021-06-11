package by.transport.myapp.service.impl;

import by.transport.myapp.dto.TransportDto;
import by.transport.myapp.mapper.TransportMapper;
import by.transport.myapp.model.dao.RouteNumberDao;
import by.transport.myapp.model.dao.TransportDao;
import by.transport.myapp.model.dao.TransportTypeDao;
import by.transport.myapp.model.entity.RouteNumber;
import by.transport.myapp.model.entity.TransportType;
import by.transport.myapp.service.TransportService;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransportServiceImpl implements TransportService {
    private final TransportDao transportDao;
    private final TransportTypeDao transportTypeDao;
    private final RouteNumberDao routeNumberDao;
    private final TransportMapper mapper = Mappers.getMapper(TransportMapper.class);
    private static final String BUS_TYPE = "Bus";

    public TransportServiceImpl(TransportDao transportDao, TransportTypeDao transportTypeDao, RouteNumberDao routeNumberDao) {
        this.transportDao = transportDao;
        this.transportTypeDao = transportTypeDao;
        this.routeNumberDao = routeNumberDao;
    }

    @Override
    public List<TransportDto> getBuses() {
        TransportType transportType = transportTypeDao.findTransportTypeByDescription(BUS_TYPE);
        return transportDao.findTransportByTransportType(transportType).stream().map(mapper::transportToDto).collect(Collectors.toList());
    }

    @Override
    public void save(TransportDto transportDto) {
        TransportType transportType = transportTypeDao.findTransportTypeByDescription(transportDto.getType());
        RouteNumber routeNumber = routeNumberDao.getById(transportDto.getRoute());
        transportDao.save(mapper.dtoToTransport(transportDto, transportType, routeNumber));
    }
}
