package by.transport.myapp.service.impl;

import by.transport.myapp.dto.TransportLiteDto;
import by.transport.myapp.mapper.TransportMapper;
import by.transport.myapp.model.dao.TransportDao;
import by.transport.myapp.model.dao.TransportTypeDao;
import by.transport.myapp.model.entity.TransportType;
import by.transport.myapp.service.TransportService;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TransportServiceImpl implements TransportService {
    private final TransportDao transportDao;
    private final TransportTypeDao transportTypeDao;
    private final TransportMapper mapper = Mappers.getMapper(TransportMapper.class);
    private final static String BUS_TYPE = "Bus";

    public TransportServiceImpl(TransportDao transportDao, TransportTypeDao transportTypeDao) {
        this.transportDao = transportDao;
        this.transportTypeDao = transportTypeDao;
    }

    @Override
    public Set<TransportLiteDto> getBuses() {
        TransportType transportType = transportTypeDao.findTransportTypeByDescription(BUS_TYPE);
        return transportDao.findTransportByTransportType(transportType).stream().map(mapper::transportToLiteDto).collect(Collectors.toSet());
    }
}
